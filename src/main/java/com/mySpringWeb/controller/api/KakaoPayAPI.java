package com.mySpringWeb.controller.api;

import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.domain.pay.PaymentVO;
import com.mySpringWeb.domain.user.UserVO;
import com.mySpringWeb.domain.webhook.HookLevel;
import com.mySpringWeb.service.PaymentService;
import com.mySpringWeb.service.UserService;
import com.mySpringWeb.utils.EnvUtil;
import com.mySpringWeb.utils.HookUtil;
import com.mySpringWeb.utils.RequestUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/pay")
public class KakaoPayAPI {
    @Autowired
    private PaymentService paymentservice;

    @Autowired
    private UserService userservice;

    private final HookUtil hookUtil = new HookUtil();

    @GetMapping("/getinfo")
    public JSONObject getPaymentInfo(@RequestParam String tid) {
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        PaymentVO payvo = paymentservice.getPayment(tid);
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        String kakao_adminKey = envUtil.getValueByKey("KAKAO_ADMINKEY");
        String cid = envUtil.getValueByKey("KAKAO_PAY_CID");

        headers.put("Authorization", "KakaoAK " + kakao_adminKey);
        headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        params.put("cid", cid);
        params.put("tid", tid);

        JSONObject result = requestUtil.requestData(RequestType.KAKAO_PAYINFO, "GET", headers, params);

        String status = (String) result.get("result_status");

        JSONObject result_json = new JSONObject();
        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONObject amountdata = (JSONObject) data.get("amount");

            String itemname = (String) data.get("item_name");
            String paytype = (String) data.get("payment_method_type");
            String order_id = (String) data.get("partner_order_id");
            String amount = amountdata.get("total").toString();
            String vat = amountdata.get("vat").toString();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.format(payvo.getCreated_at());
            String created_at = dateFormat.format(payvo.getCreated_at());

            result_json.put("cid", cid);
            result_json.put("nickname", userservice.getUser(payvo.getUser_id()).getNickname());
            result_json.put("itemname", itemname);
            result_json.put("paytype", paytype);
            result_json.put("amount", amount);
            result_json.put("created_at", created_at);
            result_json.put("order_id", order_id);
            result_json.put("vat", vat);

            hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "KakaoPay 결제 정보 조회",
                String.format(
                    "tid: %s",
                    tid
                )
            );
        } else {
            hookUtil.send_Embed_Hook(
                HookLevel.WARN,
                "KakaoPay 결제 정보 조회 실패",
                String.format(
                    "tid: %s",
                    tid
                )
            );
        }

        return result_json;
    }

    @PostMapping("/cancel")
    public String cancelPayment(@RequestParam String tid) {
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        PaymentVO payvo = paymentservice.getPayment(tid);

        String kakao_adminKey = envUtil.getValueByKey("KAKAO_ADMINKEY");
        String cid = envUtil.getValueByKey("KAKAO_PAY_CID");
        String userid = payvo.getUser_id();
        int amount = payvo.getAmount();
        int tax_amount = 0;

        UserVO uservo = userservice.getUser(userid);
        int hasamount = uservo.getPoint();
        if (hasamount < amount) return "";

        headers.put("Authorization", "KakaoAK " + kakao_adminKey);
        headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        params.put("cid", cid);
        params.put("tid", tid);
        params.put("cancel_amount", amount);
        params.put("cancel_tax_free_amount", tax_amount);

        JSONObject result = requestUtil.requestData(RequestType.KAKAO_PAYCANCEL, "POST", headers, params);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            uservo.setPoint(hasamount - amount);
            paymentservice.deletePayment(payvo);
            userservice.updateUser(uservo);

            hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "KakaoPay 결제 취소",
                String.format(
                    "cid: %s\ntid: %s\ncancel_amount: %d\ncancel_tax_amount: %d",
                    cid, tid, amount, tax_amount
                )
            );
        } else {
            hookUtil.send_Embed_Hook(
                HookLevel.WARN,
                "KakaoPay 결제 취소 실패",
                String.format(
                    "cid: %s\ntid: %s\ncancel_amount: %d\ncancel_tax_amount: %d",
                    cid, tid, amount, tax_amount
                )
            );
        }

        return "redirect:/chargelist.do";
    }

    @GetMapping("/paylist")
    public List<PaymentVO> getPaymentList(@RequestParam String userId) {
        hookUtil.send_Embed_Hook(
            HookLevel.INFO,
            "Point 충전내역 조회",
            String.format(
                "userId: %s",
                userId
            )
        );

        return paymentservice.getPaymentList(userId);
    }
}
