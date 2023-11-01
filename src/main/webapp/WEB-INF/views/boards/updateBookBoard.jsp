<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="../templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="BookBoardForm-Container">
      	<div class="BookBoardForm-description-container">
      		<div class="BookBoardForm-description">
      			BookBoard Update
      		</div>
      		<div class="backbtnContainer">
          		<button type="button" class="backbtn" onclick="back()">이전 페이지로 이동</button>
            </div>	
      	</div>
      	<div class=BookBoardFormMain-Container>
      		<div class="BookBoard-Form">
      			<form action="updateBookBoard.do" method="post" onsubmit="javascript:updateCheck()">
	      			<div class="BookBoard-Form-description">
	      				[도서 후기 및 의견을 작성해주세요]
	      			</div>
	      			<div class="book-title-wrap">
	      				<input type="text" id="book-title" name="title" value=${post.title } required>
	      				<input type="hidden"name="id" value="${post.id }">
	      			</div>
	      			<div class="book-main-wrap">
	      				<div class="book-image-wrap">
	      					<div class="image-box">
	      						<img id="book-image" src="${image }">
	      					</div>
	      					<div class="title-box">
	      						${title }
	      					</div>
	      					<div class="author-box">
	      						${author }
	      					</div>	
	      				</div>
	      				<div class="book-content-wrap">
	      					<textarea class="book-content" name="content">${post.content }</textarea>
	      				</div>
	      			</div>
	      			<div class="book-submit-wrap">
	      				<input type="submit" value="수정">
	      			</div>
      			</form>
      		</div>
      	</div>
      </div>
      <script type="text/javascript">      
				function back(){
            		history.back();
            	}
				function updateCheck(){
					if(confirm("저장하시겠습니까?")===true){
						return true;
					}else return false;
				}
			</script>
    </section>
    <%@ include file="../templates/UseJS.jsp" %>
  </body>
</html>