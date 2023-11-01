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
       <h2>payment</h2>
       <div class="payment-box">
         <div class="payment-box-left">
         	<button id="changeAmountButton3">
	       		<div class="point-box point-box-3">
	       			<div class="krw">KRW 3000</div>
	       			<div class="i-circle">
	       				<i class="fa-solid fa-book-open"></i>
	       				<span class="point-box-number">3000</span>
	       			</div>
	       			<div class="point-box-point">
	       				<span>3,000 학자금 포인트</span>
	       			</div>
	       		</div>
       		</button>
       		<button id="changeAmountButton5">
	      		<div class="point-box point-box-5">
	      			<div class="krw">KRW 5000</div>
	       			<div class="i-circle">
	       				<i class="fa-solid fa-book-open"></i>
	       				<span class="point-box-number">5000</span>
	       			</div>
	       			<div class="point-box-point">
	       				<span>5,000 학자금 포인트</span>
	       			</div>
	      		</div>
      		</button>
      		<button id="changeAmountButton10">
	      		<div class="point-box point-box-10">
	      			<div class="krw">KRW 10000</div>
	       			<div class="i-circle">
	       				<i class="fa-solid fa-book-open"></i>
	       				<span class="point-box-number">10000</span>
	       			</div>
	       			<div class="point-box-point">
	       				<span>10,000 학자금 포인트</span>
	       			</div>
	      		</div>
      		</button>
      		<button id="changeAmountButton30">
	      		<div class="point-box point-box-30">
	      			<div class="krw">KRW 30000</div>
	       			<div class="i-circle">
	       				<i class="fa-solid fa-book-open"></i>
	       				<span class="point-box-number">30000</span>
	       			</div>
	       			<div class="point-box-point">
	       				<span>30,000 학자금 포인트</span>
	       			</div>
	      		</div>
      		</button>
      		<button id="changeAmountButton50">
	      		<div class="point-box point-box-50">
	      			<div class="krw">KRW 50000</div>
	       			<div class="i-circle">
	       				<i class="fa-solid fa-book-open"></i>
	       				<span class="point-box-number">50000</span>
	       			</div>
	       			<div class="point-box-point">
	       				<span>50,000 학자금 포인트</span>
	       			</div>
	      		</div>
      		</button>
      		<button id="changeAmountButton100">
	      		<div class="point-box point-box-100">
	      			<div class="krw">KRW 100000</div>
	       			<div class="i-circle">
	       				<i class="fa-solid fa-book-open"></i>
	       				<span class="point-box-number">100000</span>
	       			</div>
	       			<div class="point-box-point">
	       				<span>100,000 학자금 포인트</span>
	       			</div>
	      		</div>
      		</button>
         </div>	
       	 <div class="payment-box-right-top">
       	 	<form action="kakaoPay.do">
	       	 	<span>상품명 : </span>
	       	 	<input type="text" id="item-name" name="item_name" disabled required>
	       	 	
	       	 	<span>금액 : </span>
	       	 	<input type="text" id="amount" name="amount" disabled >
	       	 	<span> 원</span>
	       	 	<input type="submit" value="결제하기">
	       	 	<input type="hidden" id="submit-item-name" name="item_name" value="">
	       	 	<input type="hidden" id="submit-amount" name="amount" value="">
       	 	</form>
       	 </div>   	 
       </div>
      </div>
    </section>
    
  	<script>
        // 버튼 요소와 텍스트 요소를 가져옵니다.
        var button3 = document.getElementById('changeAmountButton3');
        var button5 = document.getElementById('changeAmountButton5');
        var button10 = document.getElementById('changeAmountButton10');
        var button30 = document.getElementById('changeAmountButton30');
        var button50 = document.getElementById('changeAmountButton50');
        var button100 = document.getElementById('changeAmountButton100');
        var amount = document.getElementById('amount');
        var item_name = document.getElementById('item-name');
        var submit_amount = document.getElementById('submit-amount');
        var submit_item_name = document.getElementById('submit-item-name');
        var item3 = '3,000 학자금 포인트';
        var item5 = '5,000 학자금 포인트';
        var item10 = '10,000 학자금 포인트';
        var item30 = '30,000 학자금 포인트';
        var item50 = '50,000 학자금 포인트';
        var item100 = '100,000 학자금 포인트';
        var encodeditem3 = encodeURIComponent(item3);
        var encodeditem5 = encodeURIComponent(item5);
        var encodeditem10 = encodeURIComponent(item10);
        var encodeditem30 = encodeURIComponent(item30);
        var encodeditem50 = encodeURIComponent(item50);
        var encodeditem100 = encodeURIComponent(item100);
        // 버튼 클릭 시 텍스트를 변경하는 이벤트 리스너를 추가합니다.
        button3.addEventListener('click', function () {
        	item_name.value = item3;
        	amount.value = 3000;
            submit_item_name.value = encodeditem3;
            submit_amount.value = 3000;
        });
        button5.addEventListener('click', function () {
        	item_name.value = item5;
        	amount.value = 5000;
        	submit_item_name.value = encodeditem5;
        	submit_amount.value = 5000;
        });
        button10.addEventListener('click', function () {
        	item_name.value = item10;
        	amount.value = 10000;
        	submit_item_name.value = encodeditem10;
        	submit_amount.value = 10000;
        });
        button30.addEventListener('click', function () {
        	item_name.value = item30;
        	amount.value = 30000;
        	submit_item_name.value = encodeditem30;
        	submit_amount.value = 30000;
        });
        button50.addEventListener('click', function () {
        	item_name.value = item50;
        	amount.value = 50000;
        	submit_item_name.value = encodeditem50;
        	submit_amount.value = 50000;
        });
        button100.addEventListener('click', function () {
        	item_name.value = item100;
        	amount.value = 100000;
        	submit_item_name.value = encodeditem100;
        	submit_amount.value = 100000;
        });
    </script>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>