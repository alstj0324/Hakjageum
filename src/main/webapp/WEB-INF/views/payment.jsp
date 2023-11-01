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
         <div class="payment-box-left"></div>
       	 <div class="payment-box-right-top">
       	 	<form action="requestpay.do" onsubmit="return chkUser()">
	       	 	<span>상품명 : </span>
	       	 	<input type="text" id="item-name" name="item_name" disabled required>
	       	 	
	       	 	<span>금액 : </span>
	       	 	<input type="text" id="amount" name="amount" disabled >
	       	 	<span> 원</span>
            <input type="hidden" name="user_id" value="${user.id}" />
	       	 	<input type="hidden" id="submit-item-name" name="item_name" value="">
	       	 	<input type="hidden" id="submit-amount" name="amount" value="">
						<input type="submit" value="결제하기">
       	 	</form>
       	 </div>   	 
       </div>
      </div>
    </section>

    <%@ include file="templates/UseJS.jsp" %>
  	<script>
			let itemlist = [3000, 5000, 10000, 30000, 50000, 100000];

			$(function () {
				for (let i = 0; i < itemlist.length; i++) {
					$('.payment-box-left').append(createButton(itemlist[i]));
					$('.chargeButton#charge_' + itemlist[i]).on('click', function () {
						$('#amount').val(itemlist[i]);
						$('#item-name').val(itemlist[i] + ' 학자금 포인트');
						$('#submit-item-name').val(itemlist[i] + ' 학자금 포인트');
						$('#submit-amount').val(itemlist[i] + '');
					});
				}
			})

      function chkUser() {
        if('${user.id}' !== '') {
            return true;
        } else {
          alert('로그인 후 이용해주세요.');
          return false;
        }
      }

			function createButton(item) {
				let content = [];

				content.push('<button class="chargeButton" id="charge_' + item + '">');
				content.push('	<div class="point-box">');
				content.push('		<div class="krw">KRW ' + item + '</div>');
				content.push('		<div class="i-circle">');
				content.push('			<i class="fa-solid fa-book-open"></i>');
				content.push('			<span class="point-box-number">' + item + '</span>');
				content.push('		</div>');
				content.push('		<div class="point-box-point">');
				content.push('			<span>' + item + ' 학자금 포인트</span>');
				content.push('		</div>');
				content.push('	</div>');
				content.push('</button>');

				return content.join('');
			}
    </script>
  </body>
</html>