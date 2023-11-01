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
          <h2>도서추천 / 검색</h2>
        </div>
        <div class="book-select">
          <div class="book-category">
            <select class="bookRecommend" name="category">
              <option value="자기계발" selected>카테고리 선택</option>
              <option value="자기계발">자기계발</option>
              <option value="에세이">에세이</option>
              <option value="스프링 부트">Spring Boot</option>
              <option value="경제">경제</option>
              <option value="소설">소설</option>
            </select>
            <button id="select-category">Go</button>
          </div>
          <div class="book-search">
            <input type="text" class="book-search-input" name="bookname" placeholder="도서검색 : 도서 제목을 입력하세요">
            <button id="search-book">Go</button>
          </div>
        </div>
        <div class="book-content"></div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
    <script>
      $(function () {
        categorySearch();
      });

      $('.book-search-input').on('keydown', function(e) {
        if (e.keyCode === 13) $('#search-book').trigger('click');
      })

      $('#select-category').on('click', function() {
          categorySearch();
      });

      $('#search-book').on('click', function() {
        let bookname = $('.book-search-input').val();
        getBookList(bookname);
      });

      function categorySearch() {
        let category = $('.bookRecommend').val();
        getBookList(category);
      }

      function getBookList(category) {
          let bookList = [];
          $.ajax({
              url: '/biz/api/book/getlist',
              type: 'GET',
              data: {
                  category: category
              },
              async: false,
              dataType: 'json',
              success: function (data) {
                  console.log("Get BookList 성공");
                  bookList = data;
              },
              error: function () {
                  console.log("Get BookList 실패");
              }
          });

          let book_content = $('.book-content');
          if (bookList.length > 0) {
              book_content.empty();
              for (let i = 0; i < bookList.length; i++) book_content.append(createbook(i, bookList[i]))
          }
      }

      function createbook(seq, book) {
          let content = []
          content.push("<div class='book-content-item' id='book_" + seq + "'>");
          content.push("  <div class='book-content-img'>");
          content.push("    <a href='" + book.link + "' target='_blank'><img src='" + book.image + "' alt='logo'></a>");
          content.push("  </div>");
          content.push("  <div class='book-content-result'>");
          content.push("    <div class='item1'>" + book.title + "</div>");
          content.push("    <div class='item2'>" + book.author + "</div>");
          content.push("    <div class='item3'>" + book.pubdate + "</div>");
          content.push("    <div class='item5'>");
          content.push("      <a href='bookReview.do?bookId=" + book.isbn + "'>도서 리뷰</a>");
          content.push("    </div>");
          content.push("  </div>");
          content.push("</div>");

          return content.join("");
      }
    </script>
  </body>
</html>