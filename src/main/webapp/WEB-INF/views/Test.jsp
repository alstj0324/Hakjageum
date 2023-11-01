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
      	<div class="banner-content-paybox">
	      	<div class="banner-content-pay">
	      		<div class="banner-content-paylist">
	      			<table class="paylist-table">
							<tr class="first">
								<td class="paytype">결제수단</td>
								<td class="paytime">결제금액</td>
								<td class="paytime">결제일자</td>
							</tr>
							<c:forEach items="${paymentList }" var="pay">
							<tr>
								<%-- <td bgColor=cyan><a href="getBoard.do?tid=${pay.tid }">${pay.tid }</a></td>  --%>
								<td><a href="searchKakaoPay.do?tid=${pay.tid }">카카오페이(머니)</a></td>
								<td>${pay.amount }</td>
								<td>${pay.paytime }</td>
							</tr>
							</c:forEach>
					</table>
				</div>
			</div>
			<div class="total-point">
				<div class="relative">
					<i class="fa-solid fa-receipt"></i>
					<span>총 결제금액 :</span>
					<span>${totalamount } </span>원
				</div>
			</div>
			
			<div class="banner-content-paybar">
				<div class="paybar-content paybar-content-1">
					<i class="fa-solid fa-user"></i> ${user_id }
				</div>
				<div class="paybar-content paybar-content-2">
					<h5>Point</h5>
					<i class="fa-solid fa-book-open"></i>학자금 포인트 : <span>${totalamount }</span>
				</div>
				<div class="paybar-content paybar-content-3">
					<a href="payment.do" class="white-color">
						<div class="point-payment">
							<span>충전하기</span>	
						</div>
					</a>
				</div>
			</div>
		</div>
	  </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>