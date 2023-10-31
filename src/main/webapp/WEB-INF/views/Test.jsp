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
      	<div class="banner-content-pay">
      		<h2><a href="kakaoPay.do">pay</a></h2>
      		<h2><a href="searchKakaoPay.do">search</a></h2>
      		<h2><a href="payCancel.do">cancel</a></h2>
      		<div class="banner-content-paylist">
				<c:forEach items="${paymentList }" var="pay">
				<table class="paylist-table">
					<tr>
						<td bgColor=orange>결제금액</td>
						<td bgColor=orange>결제일</td>
					</tr>
					<tr>
						<%-- <td bgColor=cyan><a href="getBoard.do?tid=${pay.tid }">${pay.tid }</a></td>  --%>
						<td bgColor=cyan>${pay.amount }</td>
						<td bgColor=cyan>${pay.paytime }</td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	  </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>