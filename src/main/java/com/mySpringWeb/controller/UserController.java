package com.mySpringWeb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mySpringWeb.domain.RequestType;
import com.mySpringWeb.utils.EnvUtil;
import com.mySpringWeb.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mySpringWeb.domain.pay.PaymentVO;
import com.mySpringWeb.domain.user.UserVO;
import com.mySpringWeb.service.PaymentService;
import com.mySpringWeb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	protected JavaMailSender mailSender;
  
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "signin.do", method = RequestMethod.GET)
	public String signin() {
		return "signin";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public void login(UserVO vo, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String Rid = request.getParameter("username");
		String Rpwd = request.getParameter("password");
		
		List<UserVO> userList = userService.getUserList();
		
		UserVO foundUser = null;
    for (UserVO user : userList) {
      if (user.getId().equals(Rid) && user.getPwd().equals(Rpwd)) {
        foundUser = user;
        break;
      }
    }
		if (foundUser != null) {
			response.getWriter().write("success");
			// 로그인 성공 시
			UserVO user1 = userService.getUserLogin(foundUser);
			String id = user1.getId();
			int point = user1.getPoint();
			session.setAttribute("user", user1);
			session.setAttribute("user_id", id);
			session.setAttribute("point", point);
		} else {
			// 로그인 실패 시
			response.getWriter().write("failure");
		}
	}
	

	/*------------[회원가입 중 id 중복검사]-----------*/
	@RequestMapping(value = "idCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public String idCheck(String id) throws UnsupportedEncodingException {
		System.out.println("넘어온 아이디" + id);
		UserVO vo = new UserVO();
		vo.setId(id);
		String data = userService.logincheckUser(vo);
		System.out.println("결과 값" + data);
		return data;
	}

	/*-------------------------[1028추가 회원가입시 사용될 닉네임검증]------------------------------*/
	@RequestMapping(value = "nickCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public String nickCheck(String nickname) throws UnsupportedEncodingException {
		System.out.println("넘어온 닉네임" + nickname);
		UserVO vo = new UserVO();
		vo.setNickname(nickname);
		String data = userService.logincheckNickname(vo);
		System.out.println("결과 값" + data);
		return data;
	}

	@RequestMapping(value = "emailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheck(@RequestParam("email") String email) throws Exception {
		int serti = (int) ((Math.random() * (99999 - 10000 + 1)) + 10000);
		System.out.println(email);
		String from = "gomdung79@naver.com";// 보내는 이 메일주소
		String to = email;
		String title = "회원가입시 필요한 인증번호 입니다.";
		String content = "[인증번호] " + serti + " 입니다. <br/> 인증번호 확인란에 기입해주십시오.";
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

		} catch (Exception e) {
			num = "error";
		}
		return num;
	}

	/*--------------------------------[회원가입 검증끝]--------------------------------------*/
	@RequestMapping(value = "signin.do", method = RequestMethod.POST)
	public String signin(UserVO vo) {
		System.out.println("회원 가입 처리");
		userService.insertUser(vo);
		return "login";
	}

	@RequestMapping(value = "logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 네이버 로그인
	@RequestMapping(value = "naverlogin.do", method = RequestMethod.GET)
	public String request_naver_login(HttpSession session) {
		EnvUtil envUtil = new EnvUtil();
		String clientId = envUtil.getValueByKey("NAVER_CLIENTID");
		String redirectUrl = URLEncoder.encode(envUtil.getValueByKey("NAVER_LOGIN_REDIRECT"));
		String state = new BigInteger(130, new SecureRandom()).toString();

		String apiHost = RequestType.NAVER_LOGINAUTH.getUrl();
		String apiURL = String.format("%s?response_type=code&client_id=%s&redirect_uri=%s&state=%s", apiHost, clientId,
				redirectUrl, state);

		session.setAttribute("state", state);

		return "redirect:" + apiURL;
	}

	@RequestMapping(value = "navercallback.do", method = RequestMethod.GET)
	public String request_naver_callback(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		LoginUtil loginUtil = new LoginUtil();
		String access_token = loginUtil.getNaverToken(code, state);

		UserVO vo = loginUtil.getNaverUserInfo(access_token);

		if (userService.checkUser(vo) == null)
			userService.insertUser(vo);

		UserVO user = userService.getUser(vo);
		String id = user.getId();
		int point = user.getPoint();
		session.setAttribute("user", user);
		session.setAttribute("user_id", id);
		session.setAttribute("point", point);
		return "redirect:/";
	}

	// 카카오 로그인
	@RequestMapping(value = "kakaologin.do", method = RequestMethod.GET)
	public String request_kakao_login(HttpSession session) {
		EnvUtil envUtil = new EnvUtil();
		String clientId = envUtil.getValueByKey("KAKAO_RESTKEY");
		String redirectUrl = URLEncoder.encode(envUtil.getValueByKey("KAKAO_LOGIN_REDIRECT"));

		String apiHost = RequestType.KAKAO_LOGINAUTH.getUrl();
		String apiURL = String.format("%s?response_type=code&client_id=%s&redirect_uri=%s", apiHost, clientId,
				redirectUrl);

		return "redirect:" + apiURL;
	}

	@RequestMapping(value = "kakaocallback.do", method = RequestMethod.GET)
	public String request_kakao_callback(HttpSession session, HttpServletRequest request) {
		String code = request.getParameter("code");
		LoginUtil loginUtil = new LoginUtil();
		String access_token = loginUtil.getKakaoToken(code);

		UserVO vo = loginUtil.getKakaoUserInfo(access_token);
		if (userService.checkUser(vo) == null)
			userService.insertUser(vo);

		UserVO user = userService.getUser(vo);
		String id = user.getId();
		int point = user.getPoint();
		session.setAttribute("user", user);
		session.setAttribute("user_id", id);
		session.setAttribute("point", point);
		return "redirect:/";
	}

	@RequestMapping(value = "usermanage.do", method = RequestMethod.GET)
	public String usermanage(UserVO vo, HttpSession session, Model model) {
		List<UserVO> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		return "usermanage";
	}

	// 내 정보수정에서 사용할것
	@RequestMapping(value = "userupdate.do", method = RequestMethod.GET)
	public String userupdate(UserVO vo, HttpSession session, Model model) {
		vo = userService.getUser(vo);
		model.addAttribute("user", vo);
		return "userupdate";
	}

	// 관리자 manage에서 사용하는 등급변경
	@RequestMapping(value = "userRoleupdate.do", method = RequestMethod.POST)
	public String userroleupdate(UserVO vo, HttpSession session) {
		userService.roleupdateUser(vo);

		String session_id = (session.getAttribute("user_id")).toString();
		String vo_id = vo.getId();
		if (session_id.equals(vo_id)) { // 본인의 등급을 변경했을 경우 갱신된 VO를 session에 저장후 메인페이지로 이동시킴
			UserVO user = userService.getUser(vo);
			session.setAttribute("user", user);
			return "redirect:main.jsp";
		}
		// System.out.println("session_id : "+session_id+"vo_id : "+vo_id);
		return "redirect:usermanage.do";
	}

	@RequestMapping(value = "userupdate.do", method = RequestMethod.POST)
	public String userupdate(UserVO vo, HttpServletRequest request, HttpSession session) {
		vo.setId(request.getParameter("id"));
		vo.setNickname(request.getParameter("nickname"));
		vo.setPwd(request.getParameter("pwd"));
		System.out.println("nickname : " + vo);
		userService.updateUser(vo);
		UserVO user = userService.getUserLogin(vo); // user정보를 다시가져와서 최신화
		session.setAttribute("user", user);
		session.invalidate();
		return "redirect:main.jsp";
	}

	@RequestMapping(value = "userdelete.do")
	public String userdelete(UserVO vo, HttpSession session) {
		
		String session_id = (session.getAttribute("user_id")).toString();
		String vo_id = vo.getId(); 
		if(session_id.equals(vo_id)) { 
			userService.deleteUser(vo); //본인의 계정을 지웠을경우 계정 제거 + 세션을 끊어 세션에 저장되있던 값 제거 
			session.invalidate(); 
			return "redirect:main.jsp"; 
		}
		userService.deleteUser(vo); 
		return "redirect:usermanage.do";
	}

}
