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
								<thead class="first">
									<tr>
										<th class="text-center">결제수단</th>
										<th class="text-center">결제금액</th>
										<th class="text-center">결제일</th>
										<th class="text-center">결제상세</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
					<div class="total-point">
						<div class="relative">
							<i class="fa-solid fa-receipt"></i>
							<span id="paylist-charge-total-point">총 결제금액 : 0원</span>
						</div>
					</div>
					<div class="banner-content-paybar">
						<div class="paybar-content paybar-content-1">
							<i class="fa-solid fa-user"></i>
						</div>
						<div class="paybar-content paybar-content-2">
							<h5>Point</h5>
							<i class="fa-solid fa-book-open"></i>학자금 포인트 : <span id="paylist-has-total-point"></span>
						</div>
						<div class="paybar-content paybar-content-3">
							<a href="requestpay.do" class="white-color">
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
		<script>
			$(function() {
				getPayList();
				editMyInfo();
			})

			function editMyInfo() {
				$.ajax({
					url: '/biz/api/user/info/get',
					type: 'get',
					dataType: 'json',
					async: false,
					data: {
						userId: '${user_id}'
					},
					success: function(data) {
						console.log('Get My Info 성공');
						$('.paybar-content.paybar-content-1').append(data.nickname);
						$('#paylist-has-total-point').text(data.point);
					},
					error: function() {
						console.log('Get My Info 실패');
					}
				})
			}

			function getPayList() {
				let user_id = '${user_id}';

				if (user_id !== "") {
					$.ajax({
						url: '/biz/api/pay/paylist',
						type: 'get',
						dataType: 'json',
						async: false,
						data: {
							userId: user_id
						},
						success: function(data) {
							console.log('Get Pay List 성공');

							let tbody = $('.paylist-table tbody');
							let content = [];
							let total_amount = 0;
							if (data.length > 0) {
								for (let i = 0; i < data.length; i++) {
									content.push(createPayList(data[i]));
									total_amount += data[i].amount;
								}

								$('#paylist-charge-total-point').text('총 결제금액 : ' + total_amount + '원');
							} else {
								content.push('<tr>');
								content.push('	<td colspan="3">결제내역이 없습니다.</td>');
								content.push('</tr>');
							}

							tbody.append(content.join(""));
						},
						error: function() {
							console.log('Get Pay List 실패');
						}
					})
				} else alert('로그인 후 이용해주세요.');
			}

			function createPayList(pay) {
				let content = [];

				let paymethod = pay.paytype;
				if (paymethod === 'MONEY') paymethod = '현금';
				else if (paymethod === 'CARD') paymethod = '카드';
				else if (paymethod === 'POINT') paymethod = '포인트';

				content.push('<tr>');
				content.push('	<td>' + paymethod + '</td>');
				content.push('	<td>' + pay.amount + '</td>');
				content.push('	<td>' + pay.created_at + '</td>');
				content.push('	<td><a href="chargeinfo.do?tid=' + pay.tid + '">결제정보 확인</td>');
				content.push('</tr>');

				return content.join("");
			}
		</script>
  </body>
</html>