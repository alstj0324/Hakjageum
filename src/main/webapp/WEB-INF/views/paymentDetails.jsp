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
       <h2>PaymentDetails</h2>
       <div class="relative">
         <div class="payment-details-box">
         	<div class="payment-details-top payment-nice">
         		<div class="payment-details-top-left">
         			<div class="width-100-persent">
         				<span>가맹점 코드 : </span><span>${cid }</span>
         			</div>
         			<div class="width-100-persent">
         				<span>결제 번호 : </span><span>${tid }</span>
         			</div>
         		</div>
         		<div class="payment-details-top-right">
         			<div class="width-100-persent">
         				<i class="fa-solid fa-user"></i><span>${user_id }</span><span> 님</span>
         			</div>
         			<div class="width-100-persent">
         			<span>주문번호 : </span><span>${order_id }</span>
         			</div>
         		</div>
         	</div>

         	<div class="payment-details-middle payment-nice">
         		<div class="payment-details-name">${item_name }</div>
         		<div class="payment-details-type">${payment_type }</div>
         	</div>
         	
         	<div class="payment-details-bottom payment-nice">
         		<div class="payment-details-amunt">가격 : ${amount }, 부가세${vat }</div>
         		<div class="payment-details-time">${paytime }</div>
         		<a href="payCancel.do">이것은 임시 결제취소링크</a>
         	</div>
         	
         </div>
         		 
       </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>