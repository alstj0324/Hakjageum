<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body>
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content banner-content-login">
        <div class="login-wrap">
          <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
              <div class="sign-in-htm">
                <form action="login.do" method="post">
                  <div class="group">
                    <label for="user" class="label" id="user-label">ID</label>
                    <input id="user" name="id" type="text" class="input">
                  </div>
                  <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" name="pwd"type="password" class="input" data-type="password">
                  </div>
                  <div class="group">
                    <input type="submit" class="button" value="Sign In">
                  </div>
                </form>
                <div class="hr"></div>
                <div class="foot-lnk">
                  <a href="#forgot">Forgot Password?</a>
                </div>
                <div class="login-api">
                  <a href="naverlogin.do"><img class="login-image" src="resources/images/loginimg/naver_login.png"/></a><br><br>
                  <a href="kakaologin.do"><img class="login-image" src="resources/images/loginimg/kakao_login.png"/></a>
                </div>
              </div>
              <div class="sign-up-htm">
                <form action=signin.do method=post onSubmit="return check()">
                  <input type="hidden" name="provider" value="local">
                  <div class="group">
                    <label for="id" class="label">ID</label>
                    <input id="id" name="id" type="text" class="input" placeholder="영문,숫자 4~12자로 입력." required>
                    <button type="button" class="id-check-button sign-button-div" id="check-button" onClick="idCheck()">중복검사</button>
                    <div class="id-check-text" id="id-check-text" >아이디 중복체크가 필요합니다!</div>
                    <input type="hidden" id="idcheckValue" value="N">
                  </div>
                  <div class="group" id="group1">
                    <label for="email" class="label">Email Address</label>
                    <input id="email" name="email" type="text" class="input" placeholder="이메일 형식으로 입력."  required>
                    <div class="mail-check-button sign-button-div" id="check-button" onClick="emailCheck()">이메일<br>인증</div>
                    <div class="mail-check-text" id="mail-check-text">이메일 인증이 필요합니다!</div>
                    <input type="hidden" id="emailcheckValue" value="N">
                  </div>
                  <div class="group">
                    <label for="nickname" class="label">Nickname</label>
                    <input id="nickname" name="nickname" type="text" class="input" placeholder="한,영,숫자 2~15자로 입력" required>
                    <button type="button" class="nick-check-button sign-button-div" id="nick-check-button" onClick="nickCheck()">중복검사</button>
                    <div class="nick-check-text" id="nick-check-text">닉네임 중복체크가 필요합니다!</div>
                    <input type="hidden" id="nickcheckValue" value="N">
                  </div>
                  <div class="group">
                    <label for="pwd" class="label">Password</label>
                    <input id="pwd" name="pwd" type="password" class="input" placeholder="4자 이상 영문,숫자,특수문자 입력"  data-type="password" required>
                    <button type="button" class="signup-password-clean sign-button-div" id="password-clean">초기화</button>
                  </div>
                  <div class="group">
                    <label for="pwd2" class="label">Password Check</label>
                    <input id="pwd2" name="pwd2" type="password" class="input" placeholder="4자 이상 영문,숫자,특수문자 입력" data-type="password" >
                    <div class="pwd-check-text" id="pwd-check-text">비밀번호가 일치하지 않습니다!</div>
                    <input type="hidden" id="pwdcheckValue" value="N">
                  </div>
                  <div class="group">
                    <input type="submit" class="button" id="loginbtn" value="Sign Up">
                  </div>
                </form>
              </div>
              <div class="emailCheck" id="emailCheck">
              	<div class="emailDoc">
              		이메일 인증
              	</div>
              	<div class="emailCodeBox">
              		<input type="text" id="emailcode" placeholder="인증 코드 입력">
              	</div>
              	<div class="emailCodeSubmit">
              		<button type="button" id="emailCheckbtn" onClick="emailCheckbtn()">인증하기</button>
              	</div>
              	<button type="button" id=emailCheckClose onClick="emailCheckClose()">닫기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
    <script>
	    $(document).ready(function() {
       	    var status = "${status}";
        	alert("dkdk"+status);
	    });	
    
    
    	var passwordClean = document.getElementById("password-clean");
    	var pwd = document.getElementById("pwd");
    	var pwd2 = document.getElementById("pwd2");
    	var pwdCheckValue = document.getElementById("pwdcheckValue");
    	var pwdCheckText = document.getElementById("pwd-check-text");
    	
    	passwordClean.addEventListener("click", function(){
    		pwd.value = "";
    		pwd2.value = "";
    		pwdCheckText.innerText = "비밀번호가 일치하지 않습니다!";
    		pwdCheckText.style.color = "";
    		pwd.removeAttribute("readonly");
    		pwd2.removeAttribute("readonly");
    		pwdCheckValue.value = "N";
    	});
    	
    </script>
    <script type="text/javascript" src="resources/js/signin.js"></script>
  </body>
</html>