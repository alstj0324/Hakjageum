<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
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
                    <label for="user" class="label">Username</label>
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
                <form action=signin.do method=post>
                  <input type="hidden" name="provider" value="local">
                  <div class="group">
                    <label for="id" class="label">Username</label>
                    <input id="id" name="id" type="text" class="input">
                  </div>
                  <div class="group">
                    <label for="pwd" class="label">Password</label>
                    <input id="pwd" name="pwd" type="password" class="input" data-type="password">
                  </div>
                  <div class="group">
                    <label for="nickname" class="label">Nickname</label>
                    <input id="nickname" name="nickname" type="text" class="input">
                  </div>
                  <div class="group">
                    <label for="email" class="label">Email Address</label>
                    <input id="email" name="email" type="text" class="input">
                  </div>
                  <div class="group">
                    <input type="submit" class="button" value="Sign Up">
                  </div>
                </form>
                <div class="hr"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>