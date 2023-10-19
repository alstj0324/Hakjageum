<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <li>
                  <a href="http://localhost:8080/biz/main.jsp" class="text-uppercase item-anchor">Main</a>
                </li>
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
      <div class="banner-content-login">
      	<div class="login-wrap">
  <div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
    <div class="login-form">
      <div class="sign-in-htm">
        <form action="login.do" method="post">
	        <div class="group">
	          <label for="user" class="label">Username</label>
	          <input id="user" name="id" type="text" class="input">
	        </div>
	        <div class="group">
	          <label for="pass" class="label">Password</label>
	          <input id="pass" name="pwd"type="password" class="input" data-type="password">
	        </div>
	        <div class="group">
	          <input type="submit" class="button" value="Sign In">
	        </div>
	      </form>
	     <div class="hr"></div>
	     <div class="foot-lnk">
	    	 <a href="#forgot">Forgot Password?</a>
	     </div>
	     <div class="login-api">
	      	<a href="naverlogin.do"><img class="login-image" src="resources/images/loginimg/naver_login.png"/></a><br><br>
  			<a href="kakaologin.do"><img class="login-image" src="resources/images/loginimg/kakao_login.png"/></a>
	     </div>
      </div>
      
      <div class="sign-up-htm">
      	<form action=signin.do method=post>
		<input type="hidden" name="provider" value="local">
	        <div class="group">
	          <label for="id" class="label">Username</label>
	          <input id="id" name="id" type="text" class="input">
	        </div>
	        <div class="group">
	          <label for="pwd" class="label">Password</label>
	          <input id="pwd" name="pwd" type="password" class="input" data-type="password">
	        </div>
	        <div class="group">
	          <label for="nickname" class="label">Nickname</label>
	          <input id="nickname" name="nickname" type="text" class="input">
	        </div>
	        <div class="group">
	          <label for="email" class="label">Email Address</label>
	          <input id="email" name="email" type="text" class="input">
	        </div>
	        <div class="group">
	          <input type="submit" class="button" value="Sign Up">
	        </div>
	      </form>
        <div class="hr"></div>
      </div>
    </div>
  </div>
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