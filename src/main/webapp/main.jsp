<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%@ include file="WEB-INF/views/templates/UseCSS.jsp" %>
  </head>
  <body class="hompage bg-accent-light">
  <%@ include file="WEB-INF/views/templates/Header.jsp" %>
  <section id="billboard" class="padding-large no-padding-top position-relative">
    <div class="image-holder">
      <img src="resources/images/banner-image.jpg" alt="banner" class="banner-image">
    </div>
    <div class="banner-content content-light style1 text-center col-md-6">
      <h2 class="banner-title">
        Self-development
      </h2>
      <div class="btn-center">
        <a href="bookRecommend.do?category=자기계발" class="btn btn-medium btn-light">Start Now</a>
      </div>
    </div>
  </section>
  <section id="company-services">
    <div class="container my-5">
      <div class="row">
        <div class="icon-box-wrapper d-flex flex-wrap justify-content-between">
          <div class="icon-box text-center col-md-3 col-sm-12">
            <div class="content-box border-top border-bottom">
              <div class="icon-box-icon">
                <a href="bookRecommend.do?category=자기계발"><i class="icon fa fa-book" aria-hidden="true"></i></a>
              </div>
              <div class="icon-content">
                <h3 class="no-margin">Book recommendation</h3>
                <p>자기계발에 도움이되는 도서 추천</p>
              </div>
            </div>
          </div>
          <div class="icon-box text-center col-md-3 col-sm-12">
            <div class="content-box border-top border-bottom">
              <div class="icon-box-icon">
                <a href="placeRecommend.do"><i class="icon fa fa-map-marker" aria-hidden="true"></i></a>
              </div>
              <div class="icon-content">
                <h3 class="no-margin">Place</h3>
                <p>독서 및 학습공간 제시</p>
              </div>
            </div>
          </div>
          <div class="icon-box text-center col-md-3 col-sm-12">
            <div class="content-box border-top border-bottom">
              <div class="icon-box-icon">
                <a href="getBoardList.do"><i class="icon fa fa-users" aria-hidden="true"></i></a>
              </div>
              <div class="icon-content">
                <h3 class="no-margin">Community</h3>
                <p>여러 의견을 나눌수 있는 커뮤니티</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section id="fashion-trending" class="product-store padding-large position-relative overflow-hidden">
    <div class="container mb-5">
      <div class="section-header text-center">
        <h2 class="section-title">Hobby</h2>
        <p>즐길수 있는 취미를 가지는것은 본인의 발전에 도움이 됩니다.  </p>
      </div>
      <div class="row">
        <div class="swiper product-swiper">
          <div class="swiper-wrapper">
            <div class="swiper-slide">
              <div class="product-item position-relative">
                <div class="image-holder">
                  <img src="resources/images/reading.jpg" alt="product-item" class="product-image">
                </div>
                <div class="cart-concern">
                  <div class="cart-button d-flex flex-wrap">
                  </div>
                </div>
                <div class="product-detail text-center">
                  <h3 class="product-title">
                    <a href="#">Reading</a>
                  </h3>
                </div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="product-item position-relative">
                <div class="image-holder">
                  <img src="resources/images/trip.jpg" alt="product-item" class="product-image">
                </div>
                <div class="cart-concern">
                  <div class="cart-button d-flex flex-wrap">
                  </div>
                </div>
                <div class="product-detail text-center">
                  <h3 class="product-title">
                    <a href="#">Trip</a>
                  </h3>
                </div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="product-item position-relative">
                <div class="image-holder">
                  <img src="resources/images/skateboard.jpg" alt="product-item" class="product-image">
                </div>
                <div class="cart-concern">
                  <div class="cart-button d-flex flex-wrap">
                  </div>
                </div>
                <div class="product-detail text-center">
                  <h3 class="product-title">
                    <a href="#">Sports</a>
                  </h3>
                </div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="product-item position-relative">
                <div class="image-holder">
                  <img src="resources/images/music.jpg" alt="product-item" class="product-image">
                </div>
                <div class="cart-concern">
                  <div class="cart-button d-flex flex-wrap">
                  </div>
                </div>
                <div class="product-detail text-center">
                  <h3 class="product-title">
                    <a href="#">Music</a>
                  </h3>
                </div>
              </div>
            </div>
            <div class="swiper-slide">
              <div class="product-item position-relative">
                <div class="image-holder">
                  <img src="resources/images/made.jpg" alt="product-item" class="product-image">
                </div>
                <div class="cart-concern">
                  <div class="cart-button d-flex flex-wrap">
                  </div>
                </div>
                <div class="product-detail text-center">
                  <h3 class="product-title">
                    <a href="#">Made</a>
                  </h3>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="swiper-button swiper-button-next"></div>
        <div class="swiper-button swiper-button-prev"></div>
        <div class="btn-center">
          <a href="getBoardList.do" class="btn btn-medium btn-black">Hobby Board</a>
        </div>
      </div>
    </div>
    <div class="swiper-pagination"></div>
  </section>
  <%@ include file="WEB-INF/views/templates/Footer.jsp"%>
  <%@ include file="WEB-INF/views/templates/UseJS.jsp" %>
</body>

</html>