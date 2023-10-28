package com.mySpringWeb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.mySpringWeb.utils.LoginUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mySpringWeb.domain.UserVO;
import com.mySpringWeb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	protected JavaMailSender mailSender;
	
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value="signin.do", method=RequestMethod.GET)
	public String signin() {
		return "signin";
	}

	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session, Model model) {
		System.out.println("로그인 처리");
	    UserVO user = userService.getUserLogin(vo);
		if (user != null) {
			session.setAttribute("user", user);
	     	return "redirect:/";
	    } else {
	    	return "login";
		}
	}

	/*------------[회원가입 중 id 중복검사]-----------*/
	@RequestMapping(value="idCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public String idCheck(String id) throws UnsupportedEncodingException{
		System.out.println("넘어온 아이디" + id);
		UserVO vo = new UserVO();
		vo.setId(id);
		String data = userService.logincheckUser(vo);
		System.out.println("결과 값"+data);
		return data;
	}
	/*-------------------------[1028추가 회원가입시 사용될 닉네임검증]------------------------------*/
	@RequestMapping(value="nickCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public String nickCheck(String nickname) throws UnsupportedEncodingException{
		System.out.println("넘어온 닉네임" + nickname);
		UserVO vo = new UserVO();
		vo.setNickname(nickname);
		String data = userService.logincheckNickname(vo);
		System.out.println("결과 값"+data);
		return data;
	}
	
	@RequestMapping(value = "emailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheck(@RequestParam("email") String email) throws Exception{
	    int serti = (int)((Math.random()* (99999 - 10000 + 1)) + 10000);
	    System.out.println(email);
	    String from = "gomdung79@naver.com";//보내는 이 메일주소
	    String to = email;
	    String title = "회원가입시 필요한 인증번호 입니다.";
	    String content = "[인증번호] "+ serti +" 입니다. <br/> 인증번호 확인란에 기입해주십시오.";
	    String num = "";
	    try {
	    	MimeMessage mail = mailSender.createMimeMessage();
	        MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
	        
	        mailHelper.setFrom(from);
	        mailHelper.setTo(to);
	        mailHelper.setSubject(title);
	        mailHelper.setText(content, true);       
	        
	        mailSender.send(mail);
	        num = Integer.toString(serti);
	        
	    } catch(Exception e) {
	        num = "error";
	    }
	    return num;
	}	
/*--------------------------------[회원가입 검증끝]--------------------------------------*/
	@RequestMapping(value="signin.do", method=RequestMethod.POST)
	public String signin(UserVO vo) {
		System.out.println("회원 가입 처리");
		userService.insertUser(vo);
		return "login";
	}

	@RequestMapping(value="logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//네이버 로그인
	@RequestMapping(value="naverlogin.do", method=RequestMethod.GET)
	public String request_naver_login(HttpSession session) throws UnsupportedEncodingException {
		String clientId = "XSL_8Ps7NFtNXXxfpzVY";
		String redirectURI = URLEncoder.encode("http://localhost:8080/biz/navercallback.do", "UTF-8");
		String state = new BigInteger(130, new SecureRandom()).toString();

		String apiHost = "https://nid.naver.com/oauth2.0/authorize";
		String apiURL = String.format(
				"%s?response_type=code&client_id=%s&redirect_uri=%s&state=%s",
				apiHost, clientId, redirectURI, state
		);

	    session.setAttribute("state", state);
	    
		return "redirect:" + apiURL;
	}

	@RequestMapping(value="navercallback.do", method=RequestMethod.GET)
	public String request_naver_callback(UserVO vo, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		LoginUtil loginUtil = new LoginUtil();
		String access_token = loginUtil.getNaverToken(code, state);

		loginUtil.getNaverUserInfo(vo, access_token);

		if (userService.checkUser(vo) == null) userService.insertUser(vo);

		UserVO user = userService.getUser(vo);
		session.setAttribute("user", user);
		return "redirect:/";
	}
	
	//카카오 로그인
	@RequestMapping(value="kakaologin.do", method=RequestMethod.GET)
	public String request_kakao_login(HttpSession session) throws UnsupportedEncodingException {
		String clientId = "bbbc3bb1c878a2317fd7f89dec646ea9";
		String redirectURI = URLEncoder.encode("http://localhost:8080/biz/kakaocallback.do", "UTF-8");

		String apiHost = "https://kauth.kakao.com/oauth/authorize";
		String apiURL = String.format(
				"%s?response_type=code&client_id=%s&redirect_uri=%s",
				apiHost, clientId, redirectURI
		);

		return "redirect:" + apiURL;
	}

	@RequestMapping(value="kakaocallback.do",method=RequestMethod.GET)
	public String request_kakao_callback(UserVO vo, HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		String code = request.getParameter("code");
		LoginUtil loginUtil = new LoginUtil();
		String access_token = loginUtil.getKakaoToken(code);

		loginUtil.getKakaoUserInfo(vo, access_token);

		if (userService.checkUser(vo) == null) userService.insertUser(vo);

		UserVO user = userService.getUser(vo);
		session.setAttribute("user", user);
		return "redirect:/";
	}
	
	@RequestMapping(value="usermanage.do", method=RequestMethod.GET)
	public String usermanage(UserVO vo, HttpSession session, Model model) {
		List<UserVO> userList = userService.getUserList();              
    	model.addAttribute("userList", userList);
    	return "usermanage";
	}
	
	//내 정보수정에서 사용할것
	@RequestMapping(value="userupdate.do", method=RequestMethod.GET)
	public String userupdate(UserVO vo, HttpSession session, Model model) {
		vo = userService.getUser(vo);
		model.addAttribute("user", vo);
		return "userupdate";
	}
	//관리자 manage에서 사용하는 등급변경
	@RequestMapping(value="userRoleupdate.do", method=RequestMethod.POST)
	public String userroleupdate(UserVO vo) {
		userService.roleupdateUser(vo);	
		return "redirect:usermanage.do";
	}
	@RequestMapping(value="userupdate.do", method=RequestMethod.POST)
	public String userupdate(UserVO vo) {
		userService.updateUser(vo);
		return "redirect:usermanage.do";
	}
	@RequestMapping(value="userdelete.do")
	public String userdelete(UserVO vo) {
		userService.deleteUser(vo);
		return "redirect:usermanage.do";
	}
	
}
