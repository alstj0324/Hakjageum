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
       <h2>결제 상세정보</h2>
       <div class="relative">
         <div class="payment-details-box">
         	<div class="payment-details-top payment-nice">
         		<div class="payment-details-top-left">
         			<div class="width-100-persent">
         				<i class="fa-solid fa-store"></i><span class="payment-details-cid">가맹점 코드 : </span>
         			</div>
         			<div class="width-100-persent">
         				<i class="fa-solid fa-list-ol"></i><span class="payment-details-tid">결제 번호 : </span>
         			</div>
         		</div>
         		<div class="payment-details-top-right">
         			<div class="width-100-persent">
         				<i class="fa-solid fa-user"></i><span class="payment-details-nickname">유저명 : </span>
         			</div>
         			<div class="width-100-persent">
         				<i class="fa-solid fa-truck-ramp-box"></i><span class="payment-details-orderid">주문번호 : </span>
         			</div>
         		</div>
         	</div>

         	<div class="payment-details-middle payment-nice">
         		<div class="payment-details-name">
         			<i class="fa-solid fa-box"></i><span class="payment-details-itemname">상품명 : </span>
         		</div>
         		<div class="payment-details-type">
         			<i class="fa-solid fa-cash-register"></i><span class="payment-details-paymethod">결제 타입 : </span>
         		</div>
         	</div>
         	
         	<div class="payment-details-bottom payment-nice">
         		<div class="payment-details-vat">
         			<span class="payment-details-totalvat">부가세 : </span>
         		</div>
         		<div class="payment-details-amount">
         			<span class="payment-details-totalamount">가격 : </span>
         		</div>
         		
         		<div class="payment-details-time">
         			<span class="payment-details-approvetime">결제 승인 시간 : </span>
         		</div>
         	</div>
         </div>
         <form action="/biz/api/pay/cancel" method="post">
			<input type="hidden" name="tid" value="${tid}">
			<input type="submit" value="결제취소" class="payment-details-paycancel">
		</form>
       </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
		<script>
			$(function() {
				let tid = '${tid}';

				$.ajax({
					url: '/biz/api/pay/getinfo',
					type: 'get',
					data: {
						tid: tid
					},
					dataType: 'json',
					async: false,
					success: function(data) {
						console.log('Get Payment Info 성공');

						$('.payment-details-cid').text('가맹점 코드 : ' + data.cid);
						$('.payment-details-tid').text('결제번호 : ' + tid);
						$('.payment-details-nickname').text('유저명 : ' + data.nickname);
						$('.payment-details-orderid').text('주문번호 : ' + data.order_id);
						$('.payment-details-itemname').text('상품명 : ' + data.itemname);
						$('.payment-details-paymethod').text('결제 타입 : ' + data.paytype);
						$('.payment-details-totalamount').text('가격 : ' + data.amount);
						$('.payment-details-totalvat').text('부가세 : ' + data.vat);
						$('.payment-details-approvetime').text('결제 승인 시간 : ' + data.created_at);
					},
					error: function() {
						console.log('Get Payment Info 실패');
					}
				})
			})
		</script>
  </body>
</html>