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
										<th>결제수단</th>
										<th>결제금액</th>
										<th>결제일</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
					<div class="total-point">
						<div class="relative">
							<i class="fa-solid fa-receipt"></i>
							<span>총 결제금액 :</span>
							<span id="paylist-charge-total-point"></span>원
						</div>
					</div>
					<div class="banner-content-paybar">
						<div class="paybar-content paybar-content-1">
							<i class="fa-solid fa-user"></i> ${user_id}
						</div>
						<div class="paybar-content paybar-content-2">
							<h5>Point</h5>
							<i class="fa-solid fa-book-open"></i>학자금 포인트 : <span id="playlist-has-total-point"></span>
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
				let total_amount = 0;

				getPayList(total_amount);
				editMyInfo(total_amount);
			})

			function editMyInfo(total_amount) {
				$('#paylist-charge-total-point').text(total_amount);

			}

			function getPayList(total_amount) {
				let user_id = $('#user_id').val();

				if (user_id !== null && user_id !== undefined && user_id !== "") {
					alert('로그인 후 이용해주세요.');
				} else {
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

							if (data.length > 0) {
								for (let i = 0; i < data.length; i++) {
									content.push(createPayList(data[i]));
									total_amount += data[i].amount;
								}
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
				}
			}

			function createPayList(pay) {
				let content = [];

				content.push('<tr>');
				content.push('	<td><a href="chargeinfo.do?tid=' + pay.tid + '">카카오페이(머니)</a></td>');
				content.push('	<td>' + pay.amount + '</td>');
				content.push('	<td>' + pay.created_at + '</td>');
				content.push('</tr>');

				return content.join("");
			}
		</script>
  </body>
</html>