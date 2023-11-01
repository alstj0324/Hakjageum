package com.mySpringWeb.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class PayController {
    @Autowired
    private PaymentService paymentservice;

    @Autowired
    private UserService userservice;

    private final HookUtil hookUtil = new HookUtil();

    // 결제창
    @RequestMapping(value="chargepoint.do")
    public String chargePoint() {
        return "payment";
    }

    // 결제 준비
    @RequestMapping(value="requestpay.do", method= RequestMethod.GET)
    public String readyPay(HttpServletRequest request, HttpSession session) {
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        String kakao_adminKey = envUtil.getValueByKey("KAKAO_ADMINKEY");
        String cid = envUtil.getValueByKey("KAKAO_PAY_CID");
        String approval_url = envUtil.getValueByKey("KAKAO_PAY_APPROVAL");
        String fail_url = envUtil.getValueByKey("KAKAO_PAY_FAIL");
        String cancel_url = envUtil.getValueByKey("KAKAO_PAY_CANCEL");

        headers.put("Authorization", "KakaoAK " + kakao_adminKey);
        headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        params.put("cid", cid);
        params.put("partner_order_id", "Test_" + new Date().getTime());
        params.put("partner_user_id", request.getParameter("user_id"));
        params.put("item_name", request.getParameter("item_name"));
        params.put("quantity", "1");
        params.put("total_amount", request.getParameter("amount"));
        params.put("tax_free_amount", "0");
        params.put("approval_url", approval_url);
        params.put("fail_url", fail_url);
        params.put("cancel_url", cancel_url);

        JSONObject result = requestUtil.requestData(RequestType.KAKAO_PAYREADY, "POST", headers, params);

        String status = (String) result.get("result_status");
        String redirect = "redirect:/chargepoint.do";
        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");

            session.setAttribute("tid", data.get("tid"));
            session.setAttribute("partner_order_id", params.get("partner_order_id"));
            session.setAttribute("partner_user_id", params.get("partner_user_id"));
            redirect = "redirect:" + data.get("next_redirect_pc_url");

            hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "결제 준비 성공",
                String.format(
                    "Function: %s > %s\ncid: %s\npartner_order_id: %s\npartner_user_id: %s\nitem_name: %s\nquantity: %s\ntotal_amount: %s\ntax_free_amount: %s\napproval_url: %s\nfail_url: %s\ncancel_url: %s",
                    getClass().getName(),
                    "readyPay",
                    cid,
                    params.get("partner_order_id"),
                    params.get("partner_user_id"),
                    params.get("item_name"),
                    params.get("quantity"),
                    params.get("total_amount"),
                    params.get("tax_free_amount"),
                    params.get("approval_url"),
                    params.get("fail_url"),
                    params.get("cancel_url")
                )
            );
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "결제 준비 실패",
            String.format(
                "Function: %s > %s\ncid: %s\npartner_order_id: %s\npartner_user_id: %s\nitem_name: %s\nquantity: %s\ntotal_amount: %s\ntax_free_amount: %s\napproval_url: %s\nfail_url: %s\ncancel_url: %s",
                getClass().getName(),
                "readyPay",
                cid,
                params.get("partner_order_id"),
                params.get("partner_user_id"),
                params.get("item_name"),
                params.get("quantity"),
                params.get("total_amount"),
                params.get("tax_free_amount"),
                params.get("approval_url"),
                params.get("fail_url"),
                params.get("cancel_url")
            )
        );
        return redirect;
    }

    // 결제 승인
    @RequestMapping(value="approvepay.do", method=RequestMethod.GET)
    public String payment_Approval(HttpServletRequest request, HttpSession session) {
        EnvUtil envUtil = new EnvUtil();
        RequestUtil requestUtil = new RequestUtil();
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        String kakao_adminKey = envUtil.getValueByKey("KAKAO_ADMINKEY");
        String cid = envUtil.getValueByKey("KAKAO_PAY_CID");
        String tid = (String) session.getAttribute("tid");
        String partner_order_id = (String) session.getAttribute("partner_order_id");
        String partner_user_id = (String) session.getAttribute("partner_user_id");
        String pg_token = request.getParameter("pg_token");

        headers.put("Authorization", "KakaoAK " + kakao_adminKey);
        headers.put("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        params.put("cid", cid);
        params.put("tid", tid);
        params.put("partner_order_id", partner_order_id);
        params.put("partner_user_id", partner_user_id);
        params.put("pg_token", pg_token);

        session.removeAttribute("tid");
        session.removeAttribute("partner_order_id");
        session.removeAttribute("partner_user_id");

        JSONObject result = requestUtil.requestData(RequestType.KAKAO_PAYAPPROVE, "POST", headers, params);

        String status = (String) result.get("result_status");

        if (Objects.equals(status, "success")) {
            JSONObject data = (JSONObject) result.get("result_data");
            JSONObject amountdata = (JSONObject) data.get("amount");

            String userId = (String) params.get("partner_user_id");
            String payType = (String) data.get("payment_method_type");
            int amount = Integer.parseInt(amountdata.get("total").toString());

            PaymentVO vo = new PaymentVO();
            vo.setTid(tid);
            vo.setUser_id(userId);
            vo.setPaytype(payType);
            vo.setAmount(amount);

            paymentservice.insertPayment(vo);

            UserVO user = userservice.getUser(userId);
            int totalpoint = user.getPoint() + paymentservice.getTotalAmount(userId);
            user.setPoint(totalpoint);

            userservice.updatePointUser(user);

            hookUtil.send_Embed_Hook(
                HookLevel.INFO,
                "결제 승인 요청 성공",
                String.format(
                    "Function: %s > %s\ncid: %s\ntid: %s\npartner_order_id: %s\npartner_user_id: %s\npg_token: %s",
                    getClass().getName(),
                    "readyPay",
                    cid,
                    tid,
                    partner_order_id,
                    partner_user_id,
                    pg_token
                )
            );
        } else hookUtil.send_Embed_Hook(
            HookLevel.WARN,
            "결제 승인 요청 실패",
            String.format(
                "Function: %s > %s\ncid: %s\ntid: %s\npartner_order_id: %s\npartner_user_id: %s\npg_token: %s",
                getClass().getName(),
                "readyPay",
                cid,
                tid,
                partner_order_id,
                partner_user_id,
                pg_token
            )
        );

        return "redirect:/chargepoint.do";
    }
    // 결제 목록
    @RequestMapping(value="chargelist.do",method=RequestMethod.GET)
    public String payment_List() {
        //결제 목록
        return "paymentList";
    }
    // 결제 상세정보
    @RequestMapping(value="chargeinfo.do",method=RequestMethod.GET)
    public String payment_Info(Model model, @RequestParam String tid) {
        //결제 상세정보
        model.addAttribute("tid", tid);
        return "paymentDetails";
    }

    @RequestMapping(value="kPaymentfail.do",method=RequestMethod.GET)
    public String payment_Fail() {
        //결제 실패시 이동
        return "kPaymentfail";
    }
    @RequestMapping(value="kPaymentcancel.do",method=RequestMethod.GET)
    public String payment_Cancel() {
        //결제중 취소시 이동
        return "kPaymentcancel";
    }
}
