<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <%@ include file="../templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="../templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="getBoardList-Container">
      	<div class="getBoardList-description-container">
      		<div class="board-description">Book Board</div>
      	</div>
      	<div class="getboardMain-Container">
      		<div class="main-description">
      			<div class="item-category" id="main-items">게시판 분류</div>
      			<div class="item-title" id="main-items">글 제목</div>
      			<div class="item-writer" id="main-items">작성자</div>
      			<div class="item-createtime" id="main-items">등록시간</div>
      			<div class="item-viewcount" id="main-items">조회수</div>
      		</div>
      		<script type="text/javascript">
	      		$(function() {
	                ch();
	
	            });
      			function ch(){
      				var boardList = new Array();
          			boardList = ${boardList};
          			$.each(${boardList}, function(i){
          				var board_code = boardList[i].board_code
          				console.log(board_code)
          			});
          				
          			}
      			}
      			
      		</script>
      		<div class="content-container">
      			<div class="content-box">
      			 	<c:forEach items="${boardList }" var="boardList">
      			 		<div class="contents">
      						<div class="content-category" id="content-items">${boardList.board_code }</div>
      						<div class="content-title" id="content-items"><a href="getfreeBoard.do?id=${boardList.id} }">${boardList.title }</a></div>
      						<div class="content-writer" id="content-items">${boardList.writer_id }</div>
      						<div class="content-createtime" id="content-items">${boardList.created_at }</div>
      						<div class="content-viewcount" id="content-items">${boardList.view_count }</div>
      					</div>
      			 	</c:forEach>
      			</div>
      			<div class="paging-box">
      				<div class="paging-item">	
					  <ul id="pagination-wrap">
					  	<li class="paging-li"><a href="#">&lt;&lt;</a></li>
					  	<li class="paging-li"><a href="#">1</a></li>
					  	<li class="paging-li"><a href="#">2</a></li>
					  	<li class="paging-li"><a href="#">3</a></li>
					  	<li class="paging-li"><a href="#">4</a></li>
					  	<li class="paging-li"><a href="#">5</a></li>
					  	<li class="paging-li"><a href="main.jsp">&gt;&gt;</a></li>
					  </ul>				
      				</div>
      			</div>
      		</div>
      	</div>
      	<div class="write-board">
      		<c:if test="${user != null}">
      			<a href="insertBookBoard.do">글 작성</a>
      		</c:if> 
      	</div>
      </div>
    </section>
    <%@ include file="../templates/UseJS.jsp" %>
  </body>
</html>