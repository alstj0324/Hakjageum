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
  <body class="bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content banner-content-manage">
        <table class="memtable" border=1>
          <tr>
            <td id="top" width="200">아이디</td>
            <td id="top">패스워드</td>
            <td id="top">닉네임</td>
            <td id="top">이메일</td>
            <td id="top">제공자</td>
            <td id="top">등록일</td>
            <td id="top">manage</td>
          </tr>
          <c:forEach items="${userList }" var="userList">
            <tr class="user-manage-el">
              <td>${userList.id }</td>
              <td>${userList.pwd }</td>
              <td>${userList.nickname }</td>
              <td>${userList.email }</td>
              <td>${userList.provider }</td>
              <td>${userList.create_date }</td>
              <td><a href="userupdate.do?id=${userList.id }">수정</a>|<a href="userdelete.do?id=${userList.id }">삭제</a></td>
            </tr>
          </c:forEach>
        </table>
        <div>aa</div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>