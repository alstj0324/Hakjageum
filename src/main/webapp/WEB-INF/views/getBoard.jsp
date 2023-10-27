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
          <form action=updateBoard.do method="post">
            <input type=hidden name=board_id value="${board.board_id }" >
            <div class="detail-1">
              <input type=text name=board_title value="${board.title }">
            </div>
            <div class="board-detail">
              <div class="board-detail1">
                <h5>${"작성자"}</h5>
              </div>
              <div class="board-detail2">
                <h5>${board.create_at }</h5>
              </div>
            </div>
            <div class="detail-2">
              <textarea class="textarea" name="board_content" rows="10" cols="40" disabled>${board.content }</textarea>
            </div>
            <div class="detail-3">
              <input type=submit value=글수정>
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