<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header id="header" class=" content-light ">
  <div class="header-wrap container py-3">
    <div class="row align-items-center">
      <div class="primary-nav col-md-5 col-sm-2">
        <nav class="navbar">
          <div class="main-menu">
            <ul class="menu-list  list-unstyled d-flex m-0 ">
              <li class="menu-itemhome1 dropdown">
                <a class="text-uppercase item-anchor" href=<c:url value="/" />> Home</a>
              </li>
              <li class="menu-itemhome1">
                <a href="bookRecommend.do?category=자기계발" class="text-uppercase item-anchor">도서추천</a>
              </li>
              <li class="menu-itemhome1 dropdown">
                <a href="placeRecommend.do">플레이스</a>
              </li>
              <li class="menu-itemhome1 dropdown">
                <a class="text-uppercase item-anchor dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                   aria-expanded="false">게시판</a>
                <ul class="dropdown-menu">
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="getBoardList.do">
                      자유 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="blog-with-sidebar.html">
                      도서 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="single-post.html">
                      취미 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="menu-itemhome1 dropdown">
                <a class="text-uppercase item-anchor dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                   aria-expanded="false"> Notice </a>
                <ul class="dropdown-menu">
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="contact.html">
                      contact&nbsp
                      <span class="badge bg-secondary">pro</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="cart.html">
                      cart&nbsp
                      <span class="badge bg-secondary">pro</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="checkout.html">
                      checkout&nbsp
                      <span class="badge bg-secondary">pro</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="my-account.html">
                      account&nbsp
                      <span class="badge bg-secondary">pro</span>
                    </a>
                  </li>
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
          <a href=<c:url value='/' />>
            <img src="resources/images/mainlogo.png" alt="logo" class="brand-image">
          </a>
        </div>
      </div>
      <div class="right-block col-md-5 col-sm-4">
        <nav class="navbar justify-content-end">
          <div class="user-items">
            <ul class="list-unstyled content-light d-flex align-items-center m-0">
              <c:if test="${user.id != null}">
                <li>
                  <a style="color:gold;">[${user.nickname}]</a>
                </li>
                <c:if test="${user.role_id != 0}">
                  <li>
                    <a href="usermanage.do" class="text-uppercase item-anchor">MANAGE</a>
                  </li>
                </c:if>
                <li>
                  <a href="logout.do" class="text-uppercase item-anchor">LOGOUT</a>
                </li>
              </c:if>
              <c:if test="${user.id == null}">
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