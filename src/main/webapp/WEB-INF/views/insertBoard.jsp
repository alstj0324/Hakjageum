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
      <div class="banner-content banner-content-board">
        <h2>Free Board</h2>
        <div class="insert-board-form">
          <form action=insertBoard.do method=post>
            <div class="insert-1">
              <input type=text name=title placeholder="제목을 입력해주세요">
            </div>
            <div class="insert-2">
              <textarea class="textarea" name="content" rows="10" cols="40" placeholder="content"></textarea>
            </div>
            <div class="insert-3">
              <input type=submit value=글등록>
            </div>
          </form>
        </div>
        <div class="board-insert">
          <a href="getBoardList.do">글목록</a>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>