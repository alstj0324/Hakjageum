<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <body class="hompage bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="padding-large no-padding-top position-relative">
      <div class="image-holder">
        <img src="resources/images/banner-image.jpg" alt="banner" class="banner-image">
        <div class="banner-content-update">
          <div class="user-update-form">
            <h2>회원관리</h2>
        <c:if test="${user != null }">
        <form action=userupdate.do method="post">
          <input type="hidden" name="id" value="${user.id }">
          <table class="user-update-table">
            <tr>
              <td>Id</td>
              <td>${user.id }</td>
            </tr>
            <tr>
              <td>Password</td>
              <td><input type=text name=pwd value="${user.pwd }" ></td>
            </tr>
            <tr>
              <td>Nickname</td>
              <td><input type=text name=nickname value="${user.nickname }" ></td>
            </tr>
            <tr>
              <td>Email</td>
              <td><input type=text name=email value="${user.email }" ></td>
            </tr>
            <tr>
              <td>Provider</td>
              <td>${user.provider }</td>
            </tr>
            <tr>
              <td>RegisterDate</td>
              <td>${user.create_at }</td>
            </tr>
            <tr>
              <td colspan="2" id="userupdate-submit"><input type=submit value=회원수정></td>
            </tr>
          </table>
        </form>
        </c:if>
      </div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>
