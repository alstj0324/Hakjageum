package com.mySpringWeb.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mySpringWeb.domain.PaymentVO;
import com.mySpringWeb.domain.UserVO;
import com.mySpringWeb.service.PaymentService;
import com.mySpringWeb.service.UserService;

@Controller
public class KakaopayAPI2 {
	
	@Autowired
	private PaymentService paymentservice;
	@Autowired
	private UserService userservice;
  
	BufferedReader br,br2;
	
	final String kakao_clientId = "c9e9586c57fe79bc3c0ee0c52ad1a6f2"; //kakao client 아이디(REST API 키)
	final String kakao_adminKey = "3d847ad0fc68439f7949aa9254f80fc8"; //kakao adminkey
	
	@RequestMapping(value="Test.do")
	public String Test(HttpSession session, UserVO user_vo, PaymentVO vo) {
		
		try {
			System.out.println("tid = " + vo.getTid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "Test";
	}
	
	@RequestMapping(value="kakaoPay.do",method=RequestMethod.GET)
	public String kakaoPayment(HttpSession session ,HttpServletRequest request) throws IOException, ParseException { 
		
		String cid = "TC0ONETIME"; //가맹점 코드
		String partner_order_id = "Test" + new Date().getTime(); //가맹점 주문번호
		String partner_user_id = (String) session.getAttribute("user_id"); //가맹점 회원 id
		String item_name = "50000포인트"; //상품명
		String quantity = "1"; //상품 수량
		String total_amount = "50000"; //상품 총액
		String tax_free_amount = "0"; // //상품 비과세 금액
		String approval_url = "http://localhost:8080/biz/kPayment.do"; //결제 성공 시 redirect url
		String fail_url = "http://localhost:8080/biz/kPaymentfail.do"; //결제 취소 시 redirect url
		String cancel_url = "http://localhost:8080/biz/kPaymentcancel.do"; //결제 실패 시 redirect url
		
		String apiURL = "https://kapi.kakao.com/v1/payment/ready?"
				+"cid="+cid
				+"&partner_order_id="+partner_order_id
				+"&partner_user_id="+partner_user_id
				+"&item_name="+item_name
				+"&quantity="+quantity
				+"&total_amount="+total_amount
				+"&tax_free_amount="+tax_free_amount
				+"&approval_url="+approval_url
				+"&fail_url="+fail_url
				+"&cancel_url="+cancel_url;
		
		session.setAttribute("cid", cid);
		session.setAttribute("partner_order_id", partner_order_id);
		session.setAttribute("partner_user_id", partner_user_id);
		
		URL url = new URL(apiURL);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "KakaoAK " + kakao_adminKey);
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuilder res = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
			System.out.println("카카오페이 결제 api(1)"+inputLine);
		}
		
		br.close();
		
		JSONParser parsing = new JSONParser();
		Object obj = parsing.parse(res.toString());
		JSONObject jsonObj = (JSONObject)obj;
		
		session.setAttribute("tid", jsonObj.get("tid"));
		
		System.out.println("결제 정보 : "+jsonObj.toJSONString());
		
		return "redirect:" +(String) jsonObj.get("next_redirect_pc_url");
	}
	
	@RequestMapping(value="kPayment.do",method=RequestMethod.GET)
	public String payment_Approval(HttpServletRequest request,HttpSession session, PaymentVO vo, UserVO user_vo) throws Exception {
		//결제 성공시 이동
		
		String cid = (String) session.getAttribute("cid"); //가맹점 코드
		String tid = (String) session.getAttribute("tid"); //결제 고유 번호,
		String partner_order_id = (String) session.getAttribute("partner_order_id"); //가맹점 주문번호
		String partner_user_id = (String) session.getAttribute("user_id"); //가맹점 회원 id
		String pg_token = request.getParameter("pg_token");
		
		String apiURL = "https://kapi.kakao.com/v1/payment/approve?"
				+"cid="+cid
				+"&tid="+tid
				+"&partner_order_id="+partner_order_id
				+"&partner_user_id="+partner_user_id
				+"&pg_token="+pg_token;
		
		URL url = new URL(apiURL);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "KakaoAK " + kakao_adminKey);
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuilder res = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
			System.out.println("카카오 결제 api(2) :"+inputLine);
		}
		
		br.close();
		
		JSONParser parsing = new JSONParser();
		Object obj = parsing.parse(res.toString());
		JSONObject jsonObj = (JSONObject)obj;
		JSONObject resObj = (JSONObject) jsonObj.get("amount");
		
		session.setAttribute("tid", jsonObj.get("tid"));
		session.setAttribute("cid", jsonObj.get("cid")); //가맹점 코드
		session.setAttribute("type", jsonObj.get("payment_method_type")); //결제 타입
		session.setAttribute("item_name", jsonObj.get("item_name")); //결제 상품
		//session.setAttribute("quantity", jsonObj.get("quantity")); //상품 수량
		session.setAttribute("total", resObj.get("total")); //전체 결제 금액
		//session.setAttribute("vat", resObj.get("vat")); //부가세 금액
		//session.setAttribute("approved_at", jsonObj.get("approved_at")); //결제 승인 시각
		
		JSONObject cardObj = (JSONObject) jsonObj.get("card_info");
		if (cardObj!=null) {
			session.setAttribute("card_type", cardObj.get("card_type")); //카드 타입(체크,신용)
			session.setAttribute("issuer_corp", cardObj.get("issuer_corp")); //카드 종류
		}else {
			session.setAttribute("card_type", "");
			session.setAttribute("issuer_corp", "");
		}
		String approve_at = (String) jsonObj.get("approved_at");
		approve_at = approve_at.replace("T", " ");
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date paytime = dtFormat.parse(approve_at);
		
		int amount = Integer.parseInt(resObj.get("total").toString());
		
		vo.setTid(tid);
		vo.setUser_id(partner_user_id);
		vo.setAmount(amount);
		vo.setPaytime(paytime);
		paymentservice.insertPayment(vo); //결제 기록을 남기는 부분
		
		//int basepoint = user_vo.getPoint(); //기존 금액
		int basepoint = Integer.parseInt((session.getAttribute("point")).toString());
		System.out.println("basepoint : "+basepoint);
		int totalpoint = amount+basepoint; // 이번 결제금액 + 기존금액
		
		session.setAttribute("point", totalpoint);
		user_vo.setPoint(totalpoint);
		userservice.updatePointUser(user_vo); //유저의 point를 업데이트하는 부분
		
		int updatepoint = user_vo.getPoint();
		System.out.println("updatepoint : "+updatepoint);
		System.out.println("res : "+res);
		
		return "kPayment";
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
	
	@RequestMapping(value="searchKakaoPay.do",method=RequestMethod.GET)
	public void searchKakaoPay(HttpSession session, UserVO user_vo, PaymentVO vo) throws Exception {
		
		String cid = (String) session.getAttribute("cid"); //가맹점 코드
		String tid = vo.getTid();; //결제 고유 번호,
		
		String apiURL = "https://kapi.kakao.com/v1/payment/order?"
				+"cid="+cid
				+"&tid="+tid;
		
		URL url = new URL(apiURL);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "KakaoAK " + kakao_adminKey);
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuilder res = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
			System.out.println(inputLine);
		}
		
		br.close();
		
		// return 받는 json 형식
		//{"tid":"T53d1b2051b66fd43573","cid":"TC0ONETIME","status":"SUCCESS_PAYMENT","partner_order_id":"Test1698503455778","partner_user_id":"Test1","payment_method_type":"MONEY","item_name":"감자튀김","quantity":123,"amount":{"total":270600,"tax_free":0,"vat":24600,"point":0,"discount":0,"green_deposit":0},"cancel_available_amount":{"total":270600,"tax_free":0,"vat":24600,"point":0,"discount":0,"green_deposit":0},"canceled_amount":{"total":0,"tax_free":0,"vat":0,"point":0,"discount":0,"green_deposit":0},"created_at":"2023-10-28T23:30:57","approved_at":"2023-10-28T23:31:21","payment_action_details":[{"aid":"A53d1b3534b976a88b68","approved_at":"2023-10-28T23:31:21","point_amount":0,"discount_amount":0,"payment_action_type":"PAYMENT"}]}
		
		JSONParser parsing = new JSONParser();
		Object obj = parsing.parse(res.toString());
		JSONObject jsonObj = (JSONObject)obj;
		JSONObject resObj = (JSONObject) jsonObj.get("amount");
		
		System.out.println(resObj);
		
		session.setAttribute("tid", jsonObj.get("tid"));
		session.setAttribute("cancel_amount", resObj.get("total"));
		
		//return Integer.parseInt((resObj.get("total")).toString()); //해당 금액
	}
	
	@RequestMapping(value="payCancel.do", method=RequestMethod.GET)
 	public void payCancel(HttpSession session, UserVO vo) throws Exception {
	
		String cid = (String) session.getAttribute("cid"); //가맹점 코드
		String tid = (String) session.getAttribute("tid"); //결제 고유 번호,
		int cancel_amount = Integer.parseInt((session.getAttribute("cancel_amount")).toString()); // 취소 금액
		int cancel_tax_free_amount = 0; //취소 비과세 금액
		
		String cancelApiURL = "https://kapi.kakao.com/v1/payment/cancel?"
				+"cid="+cid
				+"&tid="+tid
				+"&cancel_amount="+cancel_amount
				+"&cancel_tax_free_amount="+cancel_tax_free_amount;
		
		URL url = new URL(cancelApiURL);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "KakaoAK " + kakao_adminKey);
		con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuilder res = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
			System.out.println(inputLine);
		}
		
		br.close();	
		
		JSONParser parsing = new JSONParser();
		Object obj = parsing.parse(res.toString());
		JSONObject jsonObj = (JSONObject)obj;
		JSONObject resObj = (JSONObject) jsonObj.get("amount");
		
		System.out.println("결제 취소 amount : " + resObj);
		
		int cancelpoint = Integer.parseInt((resObj.get("total")).toString()); //취소한 포인트
		int basepoint = Integer.parseInt((session.getAttribute("point")).toString()); //기존 포인트
		int totalpoint = basepoint - cancelpoint;
		
		vo.setPoint(totalpoint);
		
		userservice.updatePointUser(vo); //유저 포인트 업데이트 부분 
		
	}
	
}
