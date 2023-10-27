<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
        <div class="banner-content-banner">
          <h2>Book Recommend&nbsp;&nbsp;,&nbsp;&nbsp;Search</h2>
          <div class="h4">
            <h4>Item&nbsp;&nbsp;:&nbsp;&nbsp;${param.category}</h4>
          </div>
        </div>
        <div class="book-select">
          <div class="book-category">
            <form action="bookRecommend.do">
              <select class="bookRecommend" name="category">
                <option value="자기계발" selected>카테고리 변경</option>
                <option value="자기계발">자기계발</option>
                <option value="에세이">에세이</option>
                <option value="스프링 부트">Spring Boot</option>
                <option value="경제">경제</option>
                <option value="소설">소설</option>
              </select>
              <input type="submit" value="Go">
            </form>
          </div>
          <div class="book-search">
            <form action="bookRecommend.do">
              <input id="category" name="category" type="text" class="book-search-input" placeholder="도서검색 : 도서 제목을 입력하세요">
              <input type="submit" value="Go">
            </form>
          </div>
        </div>
        <div class="book-content">
          <c:forEach items="${books}" var="books">
            <div class="book-content-item">
              <div class="book-content-img">
                <a href="${books.link }" target="_blank"><img src="${books.image }" alt="logo"></a>
              </div>
              <div class="book-content-result">
                <div class="item1">${books.title }</div>
                <div class="item2">${fn:replace(books.author, '^', ',')}</div>
                <div class="item3">${books.pubdate }</div>
                <div class="item4">${books.description }</div>
                <div class="item5"><a href="bookReview.do?category=${books.isbn }&blog=${fn:split(books.title,'(')[0]}">도서 리뷰</a></div>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>