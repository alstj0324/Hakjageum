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
	      		
      			
      		</script>
      		<div class="content-container">
      			<div class="content-box">
      			 	<c:forEach items="${boardList }" var="boardList">
      			 		<div class="contents">
      						<div class="content-category" id="content-items">${boardList.board_code }</div>
      						<div class="content-title" id="content-items"><a href="getBookBoard.do?id=${boardList.id}">${boardList.title }</a></div>
      						<div class="content-writer" id="content-items">${boardList.nickname }</div>
      						<div class="content-createtime" id="content-items">${boardList.created_at }</div>
      						<div class="content-viewcount" id="content-items">${boardList.view_count }</div>
      					</div>
      			 	</c:forEach>
      			</div>
      			<div class="paging-box">
      				<div class="paging-item">	
					  <ul id="pagination-wrap">
					  	  <c:if test="${searchVO.prev}">
						      <li class="paging-li"><a href="javascript:void(0);" onclick="fn_go_page(${searchVO.startDate - 1}); return false;" aria-controls="dataTable" data-dt-idx="0" tabindex="0">&lt;&lt;</a></li>
				    	  </c:if>
					  	  <c:forEach var="num" begin="${searchVO.startDate}" end="${searchVO.endDate}">
				    		  <li class="paging-li"><a href="javascript:void(0);" onclick="fn_go_page(${num}); return false;" aria-controls="dataTable" data-dt-idx="0" tabindex="0">${num }</a></li>
				   	 	  </c:forEach>
					  	  <c:if test="${searchVO.next}">
				      	  	  <li class="paging-li"><a href="javascript:void(0);" onclick="fn_go_page(${searchVO.endDate + 1}); return false;" aria-controls="dataTable" data-dt-idx="0" tabindex="0">&gt;&gt;</a></li>
				    	  </c:if>
					  </ul>				
      				</div>
      			</div>
      			<div class="paging-result">
      				<div class="paging-result-item">
      					<span>총게시물 ${totalCount} / 페이지 (${searchVO.pageIndex} / ${totalPageCnt})</span>
      				</div>
      			</div>
      		</div>
      	</div>
      	<div class="write-board">
      		<c:if test="${user != null}">
      			<button type="button" class="writeboard-btn" onclick="checkBookList()">글 작성</button>
      		</c:if> 
      	</div>
      </div>
      <form method="get"  id="listForm" action="listBookBoard.do">
			<input type="hidden" id="pageIndex" name="pageIndex" val="" />
	  </form>
      <script type="text/javascript">
	      	function fn_go_page(pageNo) {
	      		console.log("들어간다")
	      		$("#pageIndex").val(pageNo);
	      		$("#listForm").submit();
	      		return false;
	      	}
      		function checkBookList(){
      			let user_id = $("#user_id").val();
      	        $.ajax({
      	            type: 'get',
      	            url: '/biz/api/user/basket/checkList',
      	            dataType: 'text',
      	            async: false,
      	            data: {
      	                "userId": user_id
      	            },
      	            success: function(data) {
											console.log("Check basket List Data 성공")
											if (data) location.href="insertBookBoard.do";
											else {
												if(confirm("저장된 도서가 없습니다\n도서를 찾아보시겠습니까?")) location.href="bookRecommend.do";
											}
      	            },
      	            error: function() {
      	                console.log("Get Basket Data 실패")
      	            }
      	        })

      	        return res;
      		}
      	</script>
    </section>
    <%@ include file="../templates/UseJS.jsp" %>
  </body>
</html>