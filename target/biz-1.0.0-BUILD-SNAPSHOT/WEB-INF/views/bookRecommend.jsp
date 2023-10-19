<%@page import="com.mySpringWeb.domain.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
  <html lang="en">
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
  <link rel="stylesheet" type="text/css" href="resources/css/normalize.css">
  <link rel="stylesheet" type="text/css" href="resources/fonts/icomoon.css">
  <link rel="stylesheet" type="text/css" href="resources/css/vendor.css">
  <!--Bootstrap ================================================== -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
    integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Syne:wght@600;700;800&display=swap" rel="stylesheet">
  <link
    href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Syne:wght@600;700;800&display=swap"
    rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="resources/css/custom.css">
</head>

<body class="hompage bg-accent-light">

  <header id="header" class=" content-light ">
    <div class="header-wrap container py-3">
      <div class="row align-items-center">
        <div class="primary-nav col-md-5 col-sm-2">
          <nav class="navbar">
            <div class="main-menu">
              <ul class="menu-list  list-unstyled d-flex m-0 ">
                <li class="menu-itemhome1 dropdown">
                  <a class="text-uppercase item-anchor" href="http://localhost:8080/biz/main.jsp" > Home </a>
                </li>
                <li class="menu-itemhome1">
                  <a href="bookRecommend.do?category=자기계발" class="text-uppercase item-anchor">도서추천</a>
                </li>
                <li class="menu-itemhome1 dropdown">
                  <a href="placeRecommend.do"> 플레이스 </a>
                </li>
                <li class="menu-itemhome1 dropdown">
                  <a class="text-uppercase item-anchor dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"> Board </a>
                  <ul class="dropdown-menu">
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item" href="getBoardList.do">
                        자유 게시판&nbsp;<span class="badge bg-secondary">Go</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="blog-with-sidebar.html">도서 게시판&nbsp;<span class="badge bg-secondary">Go</span></a>
                    </li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="single-post.html">취미 게시판&nbsp;<span class="badge bg-secondary">Go</span></a></li>
                  </ul>
                </li>
                <li class="menu-itemhome1 dropdown">
                  <a class="text-uppercase item-anchor dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"> Notice </a>
                  <ul class="dropdown-menu">
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="contact.html">contact&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="cart.html">cart&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="checkout.html">checkout&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="my-account.html">account&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                  </ul>
                </li>
              </ul>
              <div class="hamburger">
                <span class="bar"></span>
                <span class="bar"></span>
                <span class="bar"></span>
              </div>
            </div>
          </nav>
        </div>
        <div class="col-md-2 col-sm-4 brand-block ">
          <div class="main-logo text-lg-center">
            <a href="http://localhost:8080/biz/main.jsp">
              <img src="resources/images/mainlogo.png" alt="logo" class="brand-image">
            </a>
          </div>
        </div>
        <div class="right-block col-md-5 col-sm-4">
          <nav class="navbar justify-content-end">
            <div class="user-items">
              <ul class="list-unstyled content-light d-flex align-items-center m-0">
              	<c:if test="${nickname != null }">
                  <li> 
					<a style="color:gold;">[${nickname }]</a>
              	  </li>
              	  <c:if test="${nickname != admin }">
                  	<li>
                  	   <a href="usermanage.do" class="text-uppercase item-anchor">MANAGE</a>
                  	</li>
                  </c:if>
                  <li>                  
					<a href="logout.do" class="text-uppercase item-anchor">LOGOUT</a>
                  </li>
                </c:if>
                <c:if test="${nickname == null }">
                  <li>
                  	<a href="http://localhost:8080/biz/login.do" class="text-uppercase item-anchor">Sign In/Sign Up</a>
                  </li>
                </c:if> 
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </div>
  </header>
  <section id="billboard" class="padding-large no-padding-top position-relative">
    <div class="image-holder">
      <img src="resources/images/banner-image.jpg" alt="banner" class="banner-image">
      <div class="banner-content-board">
      	<div class="banner-content-banner">
      	  <h2>Book Recommend&nbsp;&nbsp;,&nbsp;&nbsp;Search</h2>
      	  <div class="h4"><h4>Item&nbsp;&nbsp;:&nbsp;&nbsp;${param.category}</h4></div><!--  -->
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
    </div>
    
  </section>
	 <!-- script ================================================== -->
  <script src="resources/js/jquery-1.11.0.min.js"></script>
  <script src="resources/js/plugins.js"></script>
  <script src="resources/js/script.js"></script>

  <!-- Bootstarp script ================================================== -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>

  <!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
  <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script> -->

  <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
	
  </body>

</html>