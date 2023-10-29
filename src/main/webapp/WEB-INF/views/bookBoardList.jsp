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
      		<div class="content-container">
      			<div class="content-box">
      			 	<c:forEach var="i" begin="0" end="15">
      			 		<div class="contents">
      						<div class="content-category" id="content-items">[자유 게시판]</div>
      						<div class="content-title" id="content-items"><a href="#">잘가요 내사랑 이젠 보내줄게요 기억 추억 모두잊을게요 지우고 지워도</a></div>
      						<div class="content-writer" id="content-items">윈도우 10 좋습니다</div>
      						<div class="content-createtime" id="content-items">23-10-29</div>
      						<div class="content-viewcount" id="content-items">12369</div>
      					</div>
      			 	</c:forEach>
      			</div>
      			<div class="paging-box">
      			<a href="#">해당 위치에 페이지 이동 처리 예정</a>
      			</div>
      		</div>
      	</div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>