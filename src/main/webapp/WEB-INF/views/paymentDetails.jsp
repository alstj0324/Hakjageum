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
       <h2>결제 상세정보</h2>
       <div class="relative">
         <div class="payment-details-box">
         	<div class="payment-details-top payment-nice">
         		<div class="payment-details-top-left">
         			<div class="width-100-persent">
         				<span>가맹점 코드 : </span><span class="payment-details-cid"></span>
         			</div>
         			<div class="width-100-persent">
         				<span>결제 번호 : </span><span class="payment-details-tid"></span>
         			</div>
         		</div>
         		<div class="payment-details-top-right">
         			<div class="width-100-persent">
         				<i class="fa-solid fa-user"></i><span class="payment-details-userid">${user_id}</span><span> 님</span>
         			</div>
         			<div class="width-100-persent">
         				<span>주문번호 : </span><span class="payment-details-orderid"></span>
         			</div>
         		</div>
         	</div>

         	<div class="payment-details-middle payment-nice">
         		<div class="payment-details-name"></div>
         		<div class="payment-details-type"></div>
         	</div>
         	
         	<div class="payment-details-bottom payment-nice">
         		<div class="payment-details-amunt">가격 : , 부가세</div>
         		<div class="payment-details-time">결제시간 : </div>
						<form action="/biz/api/pay/cancel" method="post">
							<input type="hidden" name="tid" value="${tid}">
							<input type="submit" value="결제취소" class="btn btn-primary btn-lg btn-block mt-3">
						</form>
         	</div>
         	
         </div>
         		 
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

						let date = new Date(data.created_at);
						$('.payment-details-cid').text(data.cid);
						$('.payment-details-tid').text(tid);
						$('.payment-details-orderid').text(data.order_id);
						$('.payment-details-name').text(data.itemname);
						$('.payment-details-type').text('결제타입 : ' + data.paytype);
						$('.payment-details-amunt').text('가격 : ' + data.amount + ', 부가세 : ' + data.vat);
						$('.payment-details-time').text('결제시간 : ' + date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate());
					},
					error: function() {
						console.log('Get Payment Info 실패');
					}
				})
			})
		</script>
  </body>
</html>