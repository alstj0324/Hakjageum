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
          <div class="h4"></div><!--  -->
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
        </div>
        <div class="book-content" style='overflow:hidden'>
          <div class="book-content-item1">
            <div class="book-content-img">
              <a href="${books[0].link}" target="_blank">
                <img src="${books[0].image }" alt="logo">
              </a>
            </div>
            <div class="book-content-result1">
              <div class="item1">${books[0].title }</div>
              <div class="item2">저자명 : ${fn:replace(books[0].author, '^', ',')}</div>
              <div class="item3">출간일 : ${books[0].pubdate }</div>
              <div class="item4">${books[0].description }</div>
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