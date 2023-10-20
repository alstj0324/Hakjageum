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
        <h2>Free Board</h2>
        <table class="board_table">
          <tr class="board-1th-tr">
            <td>제목</td>
            <td>작성자</td>
            <td>등록일</td>
          </tr>
          <c:forEach items="${boardList }" var="boardList">
            <tr class="board_items">
              <td><a href="getBoard.do?board_id=${boardList.board_id }">${boardList.title }</a></td>
              <td>${"작성자"}</td>
              <td>${boardList.create_at}</td>
            </tr>
          </c:forEach>
        </table>
        <hr>
        <div class="board-insert">
          <a href="insertBoard.do?board_id=${nickname }">글작성</a>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>