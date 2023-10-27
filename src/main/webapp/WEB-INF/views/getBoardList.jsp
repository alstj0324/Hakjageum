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
    <table class="board_table">
      <tr class="board-1th-tr">
        <td>번호</td>
        <td>제목</td>
        <td>작성자</td>
        <td>등록일</td>
        <td>조회수</td>
      </tr>
      <tr class="board_items"> 
        <td class="num">1002</td>
        <td class="title">
          <a href="">
          <span class="cate">[수다]</span>
          <span>제목</span>
          <span class="comment">[8]</span>
          </a>
        </td>
        <td class="user">
          작성자
        </td>
        <td class="date">09/09</td>
        <td class="count">132</td>
      </tr>
      <tr class="board_items"> 
        <td class="num">1001</td>
        <td class="title">
          <a href="">
          <span class="cate">[수다]</span>
          <span>제목</span>
          <span class="comment">[1]</span>
          </a>
        </td>
        <td class="user">
          작성자
        </td>
        <td class="date">09/09</td>
        <td class="count">132</td>
      </tr>
      <tr class="board_items"> 
        <td class="num">1000</td>
        <td class="title">
          <a href="">
          <span class="cate">[신고]</span>
          <span>대충신고글</span>
          <span class="comment">[31]</span>
          </a>
        </td>
        <td class="user">
          작성자
        </td>
        <td class="date">09/09</td>
        <td class="count">132</td>
      </tr>
     </table>
    <!-- <hr> -->
    <a href="">
      <div class="board-insert">
        글작성
      </div>
    </a>
  </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>