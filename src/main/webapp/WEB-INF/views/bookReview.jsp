<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content banner-content-board">
        <div class="banner-content-banner">
          <h2>Book Review</h2>
          <div class="backbtnContainer">
          	<button type="button" class="backbtn" onclick="back()">이전 페이지로 이동</button>
          </div>	
       </div>
        <div class="book-select">
          <div class="book-category">
            <form action="bookRecommend.do" class="book-category-form">
              <select class="bookRecommend" name="category">
                <option value="자기계발" selected>카테고리 변경</option>
                <option value="자기계발">자기계발</option>
                <option value="에세이">에세이</option>
                <option value="Spring">Spring</option>
                <option value="경제">경제</option>
                <option value="소설">소설</option>
              </select>
              <input type="submit" value="Go">
            </form>
          </div>
          <div class="book-search">
            <form action="bookRecommend.do" class="book-Recommand-form">
              <input id="category" name="category" type="text" class="book-search-input" placeholder="도서검색 : 도서 제목을 입력하세요">
              <input type="submit" value="Go">
            </form>
          </div>
          <div class="book-description-container">
          도서 정보
          </div>
        </div>
        <div class="book-content" style='overflow:hidden'>
          <div class="book-content-item1">
          	<div class="book-content-result0">
	            <div class="book-review-img">
	              <a href="${books[0].link}" target="_blank">
	                <img src="${books[0].image }" alt="logo">
	              </a>
	            </div>
	            <div class="book-content-description">
	            	<div class="item">도서명 : <div class="item-in">${fn:split(books[0].title, '(')[0]}</div></div>
              		<div class="item">저자명 : <div class="item-in">${fn:replace(books[0].author, '^', ',')}</div></div>
              		<div class="item1">출간일 : ${books[0].pubdate }</div>
              		<div class="item1">출판사 : ${books[0].publisher }</div>
              		<input type="hidden" id="user_id" value="${user.id}">
              		<input type="hidden" id="book_unique_id" value="${books[0].isbn }">
	            </div>
	            <script type="text/javascript">
	            	function addBasket(){
	 					var user_id = $("#user_id").val();
	 					var book_unique_id = $("#book_unique_id").val();
	 					if (confirm("도서를 저장하시겠습니까?") == true){
	 						$.ajax({
		            	           type:"get",
		            	           url:"addBasket.do",
		            	           dataType:"text",
		            	           data : {                       // 매개변수로 전달할 데이터
		            	               "user_id" : user_id,
		            	               "book_unique_id" : book_unique_id// 아이디값
		            	           },
		            	           success: function(data){
		            	               console.log("통신성공");
		            	               
		            	               if(data === 'False'){
		            	                   alert("이미 존재하는 도서입니다.")   
		            	               }else if(data === 'True'){
		            	            	   alert("도서가 저장되었습니다.")
		            	            	   $(".basket-button").html('<button type="button" class="basket" onclick="deleteBasket()">도서삭제</button>');
		            	               }
		            	           },
		            	           error:function(request, status, error){
		            	        	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		            	           }
		            	       })	
	 					}else{
	 						return false;
	 					}
	            		
	            	}
	            	
	            	function deleteBasket(){
	            		var user_id = $("#user_id").val();
	 					var book_unique_id = $("#book_unique_id").val();
	 					if (confirm("도서를 삭제하시겠습니까?") == true){
	 						$.ajax({
		            	           type:"get",
		            	           url:"deleteBasket.do",
		            	           dataType:"text",
		            	           data : {                       // 매개변수로 전달할 데이터
		            	               "user_id" : user_id,
		            	               "book_unique_id" : book_unique_id// 아이디값
		            	           },
		            	           success: function(data){
		            	               console.log("통신성공");
		            	               
		            	               if(data === 'False'){
		            	                   alert("도서가 삭제되었습니다.");   
		            	                   $(".basket-button").html('<button type="button" class="basket" onclick="addBasket()">도서저장</button>');
		            	               }else if(data === 'True'){
		            	            	   alert("이미 존재하지 않습니다.")
		            	            	   $(".basket-button").html('<button type="button" class="basket" onclick="addBasket()">도서저장</button>');
		            	               }
		            	           },
		            	           error:function(request, status, error){
		            	        	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		            	           }
		            	       })	
	 					}else{
	 						return false;
	 					}	
	            	}
	            	
	            	function back(){
	            		history.back();
	            	}
	            </script>
	            <c:if test="${user != null}">
		            <c:if test="${bookcheck eq '0'}">
			            <div class="basket-button">
			            	<button type="button" class="basket" onclick="addBasket()">도서저장</button>
			            </div>
			        </c:if>
			        <c:if test="${bookcheck eq '1'}">
			            <div class="basket-button">
			            	<button type="button" class="basket" onclick="deleteBasket()">도서삭제</button>
			            </div>
			        </c:if>
		        </c:if>
	        </div>
            <div class="book-content-result1">    
              <div class="item2">${books[0].description }</div>
            </div>
          </div>
          <div class="book-content-item2">
            <div class="book-review-nav">
              Blog Review
            </div>
            <div class="blog-content-container">
              <c:forEach items="${blogs}" var="blogs">
                <div class="review-content-item">
                  <div class="review-content-item1">
                    <a href="${blogs.link }" target="_blank">
                        ${blogs.title }
                    </a>
                  </div>
                  <div class="review-content-item2">
                      ${blogs.description }
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>