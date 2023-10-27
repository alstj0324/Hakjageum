package com.mySpringWeb.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KakaopayAPI {
	
	BufferedReader br,br2;
	
	final String kakao_clientId = "c9e9586c57fe79bc3c0ee0c52ad1a6f2"; //kakao client 아이디(REST API 키)
	final String kakao_adminKey = "3d847ad0fc68439f7949aa9254f80fc8"; //kakao adminkey

	@RequestMapping(value="kakaoPay.do",method=RequestMethod.GET)
	public String kakaoPayment(HttpSession session ,HttpServletRequest request) throws IOException, ParseException {
		
		String cid = "TC0ONETIME"; //가맹점 코드
		String partner_order_id = "Test" + new Date().getTime(); //가맹점 주문번호
		String partner_user_id = "Test1"; //가맹점 회원 id
		String item_name = "감자튀김"; //상품명
		String quantity = "123"; //상품 수량
		String total_amount = "270600"; //상품 총액
		String tax_free_amount = "0"; // //상품 비과세 금액
		String approval_url = "http://localhost:8080/kPayment.do"; //결제 성공 시 redirect url
		String fail_url = "http://localhost:8080/kPaymentfail.do"; //결제 취소 시 redirect url
		String cancel_url = "http://localhost:8080/kPaymentcancel.do"; //결제 실패 시 redirect url
		
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
		
		System.out.println("conURL : "+con.toString());
		
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
		
		session.setAttribute("tid", jsonObj.get("tid"));
		
		System.out.println("결제 정보 : "+jsonObj.toJSONString());
		
		return "redirect:" + jsonObj.get("next_redirect_pc_url");
	}
	
	@RequestMapping(value="kPayment.do",method=RequestMethod.GET)
	public String payment_Approval(HttpServletRequest request,HttpSession session) throws IOException, ParseException {
		//결제 성공시 이동
		
		String cid = (String) session.getAttribute("cid"); //가맹점 코드
		String tid = (String) session.getAttribute("tid"); //결제 고유 번호,
		String partner_order_id = (String) session.getAttribute("partner_order_id"); //가맹점 주문번호
		String partner_user_id = (String) session.getAttribute("partner_user_id"); //가맹점 회원 id
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
		  System.out.println(inputLine);
		}
		
		br.close();
		
		JSONParser parsing = new JSONParser();
		Object obj = parsing.parse(res.toString());
		JSONObject jsonObj = (JSONObject)obj;
		JSONObject resObj = (JSONObject) jsonObj.get("amount");
		
		try {
			
			session.setAttribute("cid", jsonObj.get("cid")); //가맹점 코드
			session.setAttribute("type", jsonObj.get("payment_method_type")); //결제 타입
			session.setAttribute("item_name", jsonObj.get("item_name")); //결제 상품
			session.setAttribute("quantity", jsonObj.get("quantity")); //상품 수량
			session.setAttribute("total", resObj.get("total")); //전체 결제 금액
			session.setAttribute("vat", resObj.get("vat")); //부가세 금액
			session.setAttribute("approved_at", jsonObj.get("approved_at")); //결제 승인 시각
			
			JSONObject cardObj = (JSONObject) jsonObj.get("card_info");
			if (cardObj!=null) {
				session.setAttribute("card_type", cardObj.get("card_type")); //카드 타입(체크,신용)
				session.setAttribute("issuer_corp", cardObj.get("issuer_corp")); //카드 종류
			}else {
				session.setAttribute("card_type", "");
				session.setAttribute("issuer_corp", "");
			}
			
			System.out.println("res : "+res);
		} catch (Exception e) {
			return "kPayment";
		}
	
		return "kPayment";
	}
	@RequestMapping(value="kPaymentfail.do",method=RequestMethod.GET)
	public String payment_Fail() {
		//결제 실패시 이동
		return "kPaymentfail";
	}
	@RequestMapping(value="kPaymentcancel.do",method=RequestMethod.GET)
	public String payment_Cancel() {
		//결제 취소시 이동
		return "kPaymentcancel";
	}
	
}
