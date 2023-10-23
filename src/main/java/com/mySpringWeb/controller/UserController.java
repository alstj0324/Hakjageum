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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.mySpringWeb.utils.LoginUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mySpringWeb.domain.UserVO;
import com.mySpringWeb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/*---------------------로컬 로그인-----------------------*/
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
	
	/*-----------------------------네이버 로그인----------------------------*/
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
	
	/*---------------------------------카카오 로그인--------------------------------------*/
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
	@RequestMapping(value="userupdate.do", method=RequestMethod.POST)//manage 내에서 등급변경 버튼 누를시
	public String userupdate(UserVO vo) {
		userService.updateUser(vo);
		return "redirect:usermanage.do";
	}
	@RequestMapping(value="userdelete.do")//manage 내에서 X버튼 클릭시
	public String userdelete(UserVO vo) {
		userService.deleteUser(vo);
		return "redirect:usermanage.do";
	}
	
}
