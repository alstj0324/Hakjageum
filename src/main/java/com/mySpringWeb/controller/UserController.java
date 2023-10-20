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

	@RequestMapping(value="login.do", method=RequestMethod.POST)//로그인 버튼을 눌렀을 경우 로그인 처리
	public String login(UserVO vo, HttpSession session, Model model) {
		System.out.println("로그인 처리");                                  
	    UserVO user = userService.getUser(vo);	 
	    System.out.println(user);
		if(user != null){                                                 
			session.setAttribute("user", user);  
	     	return "redirect:main.jsp";
	    }else {
	    	return "redirect:login.do";
	     }
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
		String token = loginUtil.getNaverToken(code, state);

		String access_token = token;

		loginUtil.getNaverUserInfo(vo, access_token);

		if (userService.checkUser(vo) == null) {
			userService.insertUser(vo);
		}

		UserVO user = userService.getUser(vo);
		session.setAttribute("user", user);
		return "redirect:/";
	}
	
	/*---------------------------------카카오 로그인--------------------------------------*/
	@RequestMapping(value="kakaologin.do", method=RequestMethod.GET)
	   public String kakaologin() throws UnsupportedEncodingException {
		  String kakao_clientId = "bbbc3bb1c878a2317fd7f89dec646ea9";
	      String redirectURI = URLEncoder.encode("http://localhost:8080/biz/kakaocallback.do", "UTF-8");
	      
	      String apiURL = "https://kauth.kakao.com/oauth/authorize?response_type=code"
	               + "&client_id=" + kakao_clientId
	               + "&redirect_uri=" + redirectURI;
	      
	      return "redirect:"+apiURL;
	   }

	@RequestMapping(value="kakaocallback.do",method=RequestMethod.GET)//네이버 로그인시 콜백
	public String kakaosignin(UserVO vo, HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("카카오 회원 가입 처리");
		String clientId = "bbbc3bb1c878a2317fd7f89dec646ea9";//애플리케이션 클라이언트 아이디값";
	    String code = request.getParameter("code");
	    System.out.println("코드"+code);
	    String redirectURI = URLEncoder.encode("http://localhost:8080/biz/kakaocallback.do", "UTF-8");
	    String apiURL;
	    String apiURL2;
	    apiURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    System.out.println("kakaoapiURL:"+apiURL);
	    apiURL2 = "https://kapi.kakao.com/v2/user/me"; //프로필 조회 api
	    boolean isContainsId=false;

	    //=========================================================================
	    
	    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("POST");
		      con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		      int responseCode1 = con.getResponseCode();
		      BufferedReader br;
		      if(responseCode1==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		    
		      br.close();
		      if(responseCode1==200) {
		    	  System.out.println(res.toString());
		    	  JSONParser parsing = new JSONParser();
		    	  Object obj = parsing.parse(res.toString());
		    	  JSONObject jsonObj = (JSONObject)obj;
		   	        
		    	  access_token = (String)jsonObj.get("access_token");
		    	  refresh_token = (String)jsonObj.get("refresh_token");
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }


	    System.out.println("rr="+access_token);
	    System.out.println("============checkline1====================");
	    try{
	    	URL url2 = new URL(apiURL2);
	        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
	        con2.setRequestMethod("GET");
	        con2.setRequestProperty("Authorization", "Bearer "+access_token);
	        con2.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
	        int responseCode2 = con2.getResponseCode();
	        BufferedReader br2;
	        if(responseCode2==200) { // 정상 호출
	            br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
	          } else {  // 에러 발생
	            br2 = new BufferedReader(new InputStreamReader(con2.getErrorStream()));
	          }
	        
	        String inputLine1;
	        StringBuilder res2 = new StringBuilder();
	        while ((inputLine1 = br2.readLine()) != null) {
	          res2.append(inputLine1);
	        }
	        System.out.println(res2);
	        br2.close();
	        System.out.println(responseCode2);
	        if(responseCode2==200) {
	      	  JSONParser kakaoparsing = new JSONParser();
	          Object kakaoobj = kakaoparsing.parse(res2.toString());
	       	  JSONObject kakaojsonObj = (JSONObject)kakaoobj;
	       	  JSONObject resObj1 = (JSONObject)kakaojsonObj.get("kakao_account");
	       	  JSONObject resObj2 = (JSONObject)resObj1.get("profile");
	       	  vo.setId(String.valueOf(kakaojsonObj.get("id"))); 
	       	  vo.setEmail((String)resObj1.get("email"));
	       	  vo.setPwd("null");
	       	  vo.setNickname((String)resObj2.get("nickname"));
	       	  vo.setProvider("kakao");
	       	  System.out.println("================checkline2==============");
	        }
	    }catch(Exception e){
	      System.out.println("예외처리 : "+e);
	    }
	    
	    
	if(userService.checkUser(vo) == null) {isContainsId = false;
	}else {isContainsId = true;}
	
	if(isContainsId == false) {
		System.out.println("1번과정");
		userService.insertUser(vo);
		UserVO user = userService.getUser(vo);
		if(user != null){                                                 
	     	session.setAttribute("user", user);  
	     	//점프할 대상
	     	 return "redirect:http://localhost:8080/biz/main.jsp";
	     }else {
	    	 return "login";
	     }
	}else if(isContainsId == true){
		System.out.println("2번과정");
		UserVO user = userService.getUser(vo);
		if(user != null){                                                 
	     	session.setAttribute("user", user);  
	     	//점프할 대상
	     	 return "redirect:http://localhost:8080/biz/main.jsp";
	     }else {
	    	 return "redirect:kakaologin.do";
	     }
	}
	return refresh_token;
	}
	
	
	/*--------------------------------회원 가입----------------------------*/
	@RequestMapping(value="signin.do", method=RequestMethod.GET)//url입력으로 들어올경우
	public String signin() {
		return "signin";
	}
	@RequestMapping(value="signin.do", method=RequestMethod.POST)//내부처리로 회원가입 완료 버튼누를경우
	public String signin(UserVO vo) {
		System.out.println("회원 가입 처리");
		userService.insertUser(vo);
		return "login";
	}
	
	
	/*--------------------------------회원관리-------------------------------*/
	@RequestMapping(value="usermanage.do", method=RequestMethod.GET)//manage 페이지로 이동
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
	
	
	/*----------------------------------로그아웃---------------------------------*/
	@RequestMapping(value="logout.do")
	public String logout(HttpSession session) {
        session.invalidate();
		return "redirect:http://localhost:8080/biz/main.jsp";
	}
	
}
