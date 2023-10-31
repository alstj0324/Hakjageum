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
    <div></div>
    <div class="login-body">
      <div class="login-veen">
        <div class="login-btn split">
          <p>회원일 경우</p>
          <button class="split-active">로그인</button>
        </div>
        <div class="register-btn split">
          <p>회원이 아닐 경우</p>
          <button class="split-active">회원가입</button>
        </div>
        <div class="wrapper">

        </div>
      </div>
    </div>
    <%@ include file="templates/UseJS.jsp" %>
    <script type="text/javascript" src="resources/js/signin.js"></script>
  </body>
</html>