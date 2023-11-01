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
      <div class="banner-content banner-content-board banner-content-paymentDetails">
       <h2>PaymentDetails</h2>
       <div class="relative">
         <div class="payment-details-box">
         	<div class="payment-details-top payment-nice">
         		<div class="payment-details-top-left">
         			<div class="width-100-persent">
         				<i class="fa-solid fa-store"></i><span>가맹점 코드 : </span><span>${cid }</span>
         			</div>
         			<div class="width-100-persent">
         				<i class="fa-solid fa-list-ol"></i><span>결제 번호 : </span><span>${tid }</span>
         			</div>
         		</div>
         		<div class="payment-details-top-right">
         			<div class="width-100-persent">
         				<i class="fa-solid fa-user"></i><span>유저명 : </span><span>${user_id }</span>
         			</div>
         			<div class="width-100-persent">
         				<i class="fa-solid fa-truck-ramp-box"></i><span>주문번호 : </span><span>${order_id }</span>
         			</div>
         		</div>
         	</div>

         	<div class="payment-details-middle payment-nice">
         		<div class="payment-details-name">
         			<i class="fa-solid fa-box"></i><span>상품명 : </span><span>${item_name }</span>
         		</div>
         		<div class="payment-details-type">
         			<i class="fa-solid fa-cash-register"></i><span>결제 타입 : </span><span>${payment_type }</span>
         		</div>
         	</div>
         	
         	<div class="payment-details-bottom payment-nice">
         	
         		<div class="payment-details-vat">
         			<span>부가세 : </span> <span>${vat }</span>
         		</div>
         		<div class="payment-details-amount">
         			<span>가격 : </span> <span>${amount }</span> 	
         		</div>
         		
         		<div class="payment-details-time">
         			<span>결제 승인 시간 : </span> <span>${paytime }</span>
         		</div>
         		
         	</div>
         	
         </div>
         <a href="paycancel.do">
	         <div class="payment-details-paycancel">
	         	결제 취소
	         </div>	 
	     </a>
       </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>