<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg bg-transparent fixed-top z-1">
  <div class="container-xxl">
    <a class="navbar-brand d-flex align-items-center" href=<c:url value='/' />>
      <img src="/resources/images/mainlogo.png" width="30" height="30">
      <span class="text-white ms-2">학자금</span>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainMenu" aria-expanded="false" aria-label="내비게이션바">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mainMenu">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link text-white" href=<c:url value='/' />>홈</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="/bookRecommend.do">도서추천</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="/placeRecommend.do">플레이스</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="/listBookBoard.do">도서게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="#">설명</a>
        </li>
      </ul>

      <c:if test="${user == null}">
      <a href="/login.do" class="item-anchor text-white">로그인</a>
      </c:if>
      <c:if test="${user != null}">
      <div class="dropdown">
        <a href="#" class="dropdown-toggle" role="button" id="userMenu" data-bs-toggle="dropdown" aria-expanded="false">
          <i class="fa-solid fa-circle-user fa-xl text-white"></i>
        </a>
        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userMenu">
          <li><span class="dropdown-itemtext px-2"><i class="fa-solid fa-hashtag"></i> ${user.nickname}</span></li>
          <li><hr class="dropdown-divider"></li>
          <li><span class="dropdown-itemtext px-2"><i class="fa-solid fa-coins"></i> ${user.point} Point</span></li>
          <li><a href="/chargepoint.do"><span class="badge bg-success content-light">충전하기</span></a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" data-bs-target="#editinfo-checkpassword" data-bs-toggle="modal" data-bs-dismiss="modal">내 정보 수정 <span class="badge bg-secondary"><i class="fa-solid fa-pen-to-square"></i></span></a></li>
          <li><a class="dropdown-item" href="#">내 도서 목록 <span class="badge bg-secondary"><i class="fa-solid fa-list"></i></span></a></li>
          <c:if test="${user.role_id != 0}">
          <li><a class="dropdown-item" href="#">회원관리 <span class="badge bg-secondary"><i class="fa-solid fa-person"></i></span></a></li>
          </c:if>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="/logout.do">로그아웃 <span class="badge bg-secondary"><i class="fa-solid fa-right-from-bracket"></i></span></a></li>
        </ul>
      </div>
      </c:if>
    </div>
  </div>
  <input type="hidden" id="user-id" value="${user.id}">
</nav>

<%@ include file="EditMyInfo.jsp" %>