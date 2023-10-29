<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content banner-content-board">
      	<h2>point</h2>
      	<a href="kakaoPay.do">
      		<h4>
      			카카오페이 결제하기
      		</h4>
      	</a>
      	<a href="searchKakaoPay.do">
      		<h4>
      			카카오페이 결제 확인하기(결제상세)
      		</h4>
      	</a>
      	<a href="payCancel.do">
      		<h4>
      			카카오페이 결제 취소하기
      		</h4>
      	</a>   

		</div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>