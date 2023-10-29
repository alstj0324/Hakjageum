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
      <div class="BookBoardForm-Container">
      	<div class="BookBoardForm-description-container">
      		<div class="BookBoardForm-description">
      			BookBoard Form
      		</div>
      	</div>
      	<div class=BookBoardFormMain-Container>
      		<div class="BookBoard-Form">
      			<form action="#" method="post">
      				<input type="hidden" name="writer_id" id="writer_id" value=${user.id }>
      				<input type="hidden" name="board_type" id="board_type" value="">
	      			<div class="BookBoard-Form-description">
	      				[도서 후기 및 의견을 작성해주세요]
	      			</div>
	      			<div class="book-title-wrap">
	      				<input type="text" id="book-title" name="title" placeholder="도서명을 제목에 포함시켜주시면 감사하겠습니다.">
	      			</div>
	      			<div class="book-select-wrap">
	      				<button type="button" id="book-select" name="">도서 목록<br>불러오기</button>
	      				<input type="hidden" id="book_id" name="book_id" value="">
	      			</div>
	      			<div class="book-main-wrap">
	      				<div class="book-image-wrap">
	      					<div class="image-box">
	      						<img src="https://shopping-phinf.pstatic.net/main_3731353/37313533623.20230926085206.jpg">
	      					</div>
	      					<div class="title-box">
	      						도서명: 세이노의 가르침
	      					</div>
	      					<div class="author-box">
	      						저자 : 누가 썼겠지
	      					</div>	
	      				</div>
	      				<div class="book-content-wrap">
	      					<textarea class="book-content" name="content" placeholder="자신이 느낀점이나 기대되는점, 후기를 작성해주세요"></textarea>
	      				</div>
	      			</div>
	      			<div class="book-submit-wrap">
	      				<input type="submit" value="등록">
	      			</div>
      			</form>
      		</div>
      		<div class="book-select-page" id="book-select-page">
      			<div class="select-page-description">
      				내 도서 목록
      			</div>
      			<div class="select-page-content-container" id="select-page-content-container">
      				<div class="selectListItem">
			  			<div class="ItemImage">
			  				<img src="http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902">
			  			</div>
			  			<div class="ItemTitle" id="Item">
			  			불곰의 <b>주식</b>투자 불패공식
			  			</div>
			  			<div class="ItemAuthor" id="Item">
			  			저자명 : 불곰 박선목	
			  			</div>
			  			<div class="ItemPublisher" id="Item">
			  			출판사 : 부키
			  			</div>
			  			<div class="ItemPubdate" id="Item">
			  			출간일 : 20160729
			  			</div>
			  			<div class="ItemDiscount" id="Item">
			  			도서가격 : 16000원
			  			</div>
			  			<div class="ItemDelete">
			  			도서삭제
			  			</div>
			  		</div>
      			</div>
      			<div class="select-page-close">
      				<button type="button" id="closebtn">닫기</button>
      			</div>
      		</div>
      	</div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>