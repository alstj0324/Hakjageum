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
      <div class="banner-content banner-content-board-insert">
		    <h2 class="board-title">Book Board</h2>
		    <div class="board-insert">
		      <a href="getBoardList.do">글목록</a>
		    </div>
		    
		    	<div class="insert-board-form">
		      	<form action=insertBoard.do method=post>
		
		        <div class="board-header">
		          <input type=text name=title placeholder="Title" class="title">
		          <div class="writer-time">
		            <span class="writer">작성자</span>
		            <span class="time">2023-10-22</span>
		          </div>
		          
		        </div>
		
		        <div class="board-center">
		          <div class="text-pos">
		            <textarea class="textarea" name="content" rows="10" cols="40" placeholder="content"></textarea>
		          </div>
		          <div class="board-center-book">
		            <img src="" alt="">
		            <div class="text">
		              <span class="book-name">book-name</span>
		              <span class="writer">writer</span>
		            </div>
		          </div>
		        </div>
		
		        <div class="book-coment">
		          <input type="text" placeholder="comment" maxlength="60">
		        </div>
		        <div class="submit">
		          <input type=submit value=댓글작성>
		        </div>
		        <div class="comment">
		          <span>작성자</span>
		          <span>2023-10-22</span>
		          <button type="button">
		            <i class="fa-solid fa-xmark"></i>
		          </button>
		          <h1>이책은 정말 ㅇ</h1>
		        </div>
		
		      </form>
		    </div>
		
		  </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>