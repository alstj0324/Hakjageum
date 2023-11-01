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
          <div class="backbtnContainer">
          	<button type="button" class="backbtn" onclick="history.back()">이전 페이지로 이동</button>
          </div>	
        </div>
        <div class="book-description-container">도서 정보</div>
        <div class="book-content">
          <div class="book-content-item1"></div>
          <div class="book-content-item2">
            <div class="book-review-nav">Blog Review</div>
            <div class="blog-content-container"></div>
          </div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
    <script type="text/javascript">
        let bookId, bookname, userId;
        $(function() {
          bookId = ${param.bookId};
          bookname = "";
          userId = $("#user_id").val();

          if (bookId) {
            $.ajax({
              type: "get",
              url: "/biz/api/book/getbybookid",
              dataType: "json",
              data: {
                "bookId": bookId
              },
              async: false,
              success: function (data) {
                console.log("Get Book Info 성공");
                let book = data[0];
                if (book) {
                  $(".book-content-item1").append(createBookInfo(book));
                  bookname = book.title;
                } else {
                  alert("도서를 찾을 수 없습니다.");
                  return history.back();
                }
              },
              error: function () {
                console.log("Get Book Info 실패");
              }
            })
            if (bookname) {
              $.ajax({
                type: "get",
                url: "/biz/api/blog/get",
                dataType: "json",
                data: {
                  "bookName": bookname
                },
                async: false,
                success: function (data) {
                  console.log("Get Basket List 성공");
                  let blogList = data;
                  if (blogList.length > 0) {
                    for (let i = 0; i < blogList.length; i++) {
                      let blog = blogList[i];
                      $(".blog-content-container").append(createBlogInfo(i, blog));
                    }
                  }
                },
                error: function () {
                  console.log("Get Basket List 실패");
                }
              })
            }

            if (userId) {
              $.ajax({
                type: "get",
                url: "/biz/api/user/basket/get",
                dataType: "json",
                data: {
                  "userId": userId
                },
                async: false,
                success: function (data) {
                  console.log("Get Basket List 성공");
                  if (data.length > 0) $(".basket-button").html('<button type="button" class="basket" onclick="deleteBasket()">도서삭제</button>');
                  else $(".basket-button").html('<button type="button" class="basket" onclick="addBasket()">도서저장</button>');
                },
                error: function () {
                  console.log("Get Basket List 실패");
                }
              })
            }
          }
        });

        function createBookInfo(book) {
          let content = [];

          content.push('<div class="book-content-result0">');
          content.push('  <div class="book-review-img">');
          content.push('    <a href="' + book.link + '" target="_blank">');
          content.push('      <img src="' + book.image + '" alt="img">');
          content.push('    </a>');
          content.push('  </div>');
          content.push('  <div class="book-content-description">');
          content.push('    <div class="item">도서명 : <div class="item-in">' + book.title + '</div></div>');
          content.push('    <div class="item">저자명 : <div class="item-in">' + book.author + '</div></div>');
          content.push('    <div class="item1">출간일 : ' + book.pubdate + '</div>');
          content.push('    <div class="item1">출판사 : ' + book.publisher + '</div>');
          content.push('  </div>');
          content.push('  <div class="basket-button"></div>');
          content.push('</div>');
          content.push('<div class="book-content-result1">');
          content.push('  <div class="item2">' + book.description + '</div>');
          content.push('</div>');

          return content.join("");
        }

        function createBlogInfo(seq, blog) {
          let content = [];

          content.push('<div class="review-content-item" id="blog_review_' + seq + '">');
          content.push('  <div class="review-content-item1">');
          content.push('    <a href="' + blog.link + '" target="_blank">');
          content.push('      ' + blog.title);
          content.push('    </a>');
          content.push('  </div>');
          content.push('  <div class="review-content-item2">');
          content.push('    ' + blog.description);
          content.push('  </div>');
          content.push('</div>');

          return content.join("");
        }

        function addBasket() {
          if (confirm("도서를 저장하시겠습니까?")) {
            $.ajax({
              type:"get",
              url:"/biz/api/user/basket/add",
              dataType: "json",
              data : {
                "userId": userId,
                "bookId": bookId
              },
              success: function(data){
                console.log("Add Basket 성공");
                if (data) {
                  alert("도서가 저장되었습니다.");
                  $(".basket-button").html('<button type="button" class="basket" onclick="deleteBasket()">도서삭제</button>');
                } else alert("저장목록에 이미 존재하는 도서입니다.");
              },
              error:function(){
                console.log("Add Basket 실패");
              }
            })
          }
        }

        function deleteBasket() {
          if (confirm("도서를 삭제하시겠습니까?")) {
            $.ajax({
              type:"get",
              url:"/biz/api/user/basket/delete",
              dataType: "json",
              data : {
                "userId": userId,
                "bookId": bookId
              },
              success: function(data){
                console.log("Delete Basket 성공");
                if (data) {
                  alert("도서가 삭제되었습니다.");
                  $(".basket-button").html('<button type="button" class="basket" onclick="deleteBasket()">도서삭제</button>');
                } else alert("저장목록에 존재하지 않는 도서입니다.");
              },
              error:function(){
                console.log("Delete Basket 실패");
              }
            })
          }
        }
    </script>
  </body>
</html>