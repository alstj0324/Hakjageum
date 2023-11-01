<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
      		<div class="backbtnContainer">
          		<button type="button" class="backbtn" onclick="back()">이전 페이지로 이동</button>
            </div>	
      	</div>
      	<div class="BA2-main-container">
      		<div class="BA2-content-wrap">
      			<div class="BA2-title-wrap">
      				<p>${post.title }</p>
      			</div>
      			<div class="BA2-content-main-box">
      				<div class="BA2-image-box">
      					<div class="BA2-image">
      						<img src="${image }">
      					</div>
      					<div class="BA2-title-box">
      						${title }
      					</div>
      					<div class="BA2-author-box">
      						${author}
      					</div>
      				</div>
      				<div class="BA2-content-box">
      					<div class="BA2-info">
      						<div class="writer">
      						작성자 : ${post.writer_id }
      						</div>
      						<div class="write-time">
      						작성일 : ${post.updated_at }
      						</div>
      					</div>
      					<div class="BA2-content">
      						${post.content }
      					</div>
      				</div>
      			</div>
      			<c:if test="${post.writer_id eq user.nickname || user.role_id eq 1 }">
      				<div class="control-btn">
      					<c:if test="${post.writer_id eq user.nickname}">
	      					<button type="button" class="control-item" id="update-btn" onclick="javascript:updateBoard()">수정</button>
	      				</c:if>
	      				<button type="button" class="control-item" id="delete-btn" onclick="javascript:deleteBoard()">삭제</button>
	      			</div>
      			</c:if>
      			
      			<script type="text/javascript">
      				function deleteBoard(){
      					var id = ${post.id}
      					var board_code = "BA2";
      					if(confirm("게시글을 삭제하시겠습니까?")===true){
      						location.href="deleteBoard.do?id="+id+"&board_code="+board_code;
      					}else{ return false;}
      				}
      				function updateBoard(){
      					var id = ${post.id}
      					if(confirm("게시글을 수정하시겠습니까?")==true){
      						location.href="updateBookBoard.do?id="+id;
      					}else{ return false;}
      				}
      				function back(){
	            		history.back();
	            	}
      			</script>
      		</div>
      		<div class="BA2-comment-wrap">
      			<div class="BA2-input-comment-wrap">
      				<form action="insertComment.do" method="post" onsubmit="return commentSubmit()">
	      				<input type="hidden" id="comment_post_id" name="post_id" value="${post.id }">
	      				<input type="hidden" id="comment_writer_id" name="writer_id" value="${user_id }">
	      				<textarea class="comment" id="comment_content" name="content" maxlength="100" placeholder="댓글을 작성해주세요(100자 이내)" required></textarea>
	      				<div class="comment-submit-container">
	      					<input type="submit" class="comment-submit" value="댓글달기">
	      				</div>
      				</form>
      			</div>
      			<div class="BA2-comment-items-wrap">
	      			<c:forEach items="${boardList }" var="boardList">
	      			 		<div class="contents">
	      						<div class="content-category" id="content-items">${boardList.board_code }</div>
	      						<div class="content-title" id="content-items"><a href="getBookBoard.do?id=${boardList.id}">${boardList.title }</a></div>
	      						<div class="content-writer" id="content-items">${boardList.writer_id }</div>
	      						<div class="content-createtime" id="content-items">${boardList.created_at }</div>
	      						<div class="content-viewcount" id="content-items">${boardList.view_count }</div>
	      					</div>
	      			 	</c:forEach>
	      			
	      			<c:forEach items="${comment }" var="comment">
	      				<div class="BA2-comment-items">
		      				<div class="BA2-items-1">${comment.content }</div>
		      				<div class="BA2-items-2">
		      					<div class="BA2-items-2-writer">${comment.writer_id}</div>
		      					<div class="BA2-items-2-date">${comment.created_at}</div>
		      					<button type="button" class="items-2-delete-btn">삭제</button>
		      				</div>
		      			</div>
	      			</c:forEach>
      			</div>
      			<div class="content-paging-wrap">
      				<ul id="comment-pagination-wrap">
					  	  <li class="comment-paging-li"><a href="#">&lt;&lt;</a></li>
					  	  <li class="comment-paging-li"><a href="#">1</a></li>
					  	  <li class="comment-paging-li"><a href="#">2</a></li>
					  	  <li class="comment-paging-li"><a href="#">3</a></li>
					  	  <li class="comment-paging-li"><a href="#">4</a></li>
					  	  <li class="comment-paging-li"><a href="#">5</a></li>
					  	  <li class="comment-paging-li"><a href="main.jsp">&gt;&gt;</a></li>
					  </ul>	
      			</div>
      		</div>
      		
      		
      	</div>
      </div>
    </section>
    <%@ include file="../templates/UseJS.jsp" %>
    <script type="text/javascript">
    
   	function commentSubmit(){
   		var post_id = $('#comment_post_id')
   		var comment = $('#comment_content')
   		var writer_id = $('#comment_writer_id');
   		console.log(post_id)
   		console.log(comment)
   		if(confirm("댓글을 작성하시겠습니까?")){
   			if(post_id.val() == undefined){
   				alert("오류가 있습니다!")
   				return false;
   			}
   			if(writer_id.val() == ""){
   				alert("로그인 후 작성가능합니다")
   				return false
   			} 
   			if($('#comment_content').val() == undefined){
   				alert("댓글을 입력해주세요")
   				return false;
   			}
   			return true;
   		}else return false;
   	}
    </script>
  </body>
</html>