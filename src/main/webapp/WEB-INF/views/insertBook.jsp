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
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
    	<div class="banner-content banner-content-board">
    		<div class="banner-content-board-insert">
    			<h2 class="board-title">Book Board</h2>
    			<div class="board-insert">
        		<a href="getBoardList.do">글목록</a>
     			</div>
     			
     			<div class="insert-board-book-form insert-book-form insert-form-overflow">
     				<form action=insertBoard.do method=post class="insert-form-book">
     					<div class="board-header">
	            <input type=text name=title placeholder="Title" class="title" required>
		            <div class="writer-time">
		              <span class="writer-2">작성자</span>
		            </div>
            
         			</div>

		          <div class="board-center">
		            <div class="text-pos">
		              <textarea class="textarea" name="content" rows="10" cols="40" placeholder="content" required></textarea>
		            </div>
		            <div class="board-center-book">
		            	<a href="">
		              	<img src="" alt="">
		              </a>
		              <div class="text">
		                <span class="book-name">book-name</span>
		                <span class="writer">writer</span>
		              </div>
		            </div>
		          </div>          
		          <input type=submit value=등록하기 class="insertBook-submit">
     				</form>
     			</div>
    		</div>
    	</div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>