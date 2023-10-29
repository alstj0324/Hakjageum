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
                    <a class="text-black text-uppercase dropdown-item" href="Test.do">
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
              <c:if test="${user != null}">
                <li>
                  <a style="color:gold;" href="#" class="text-uppercase item-anchor dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">[${user.nickname}]</a>
                   <ul class="dropdown-menu" id="name-dropdown">
	                  <li class="sub-page pe-0">
	                    <a class="text-black text-uppercase dropdown-item" href="getBoardBook.do">
	                      내 정보 수정
	                      <span class="badge bg-secondary">Go</span>
	                    </a>
	                  </li>
	                  <li class="sub-page pe-0">
	                    <div class="text-black text-uppercase dropdown-item" id="openbasket" onClick="javascript:show();">
	                      내 저장 도서
	                      <span class="badge bg-secondary">Open</span>
	                    </div>
	                  </li>
	                  <c:if test="${user.role_id != 0}">
		                  <li class="sub-page pe-0">
		                    <a class="text-black text-uppercase dropdown-item" href="insertBook.do">
		                      회원 관리
		                      <span class="badge bg-secondary">Go</span>
		                    </a>
		                  </li>
		              </c:if>
	               </ul>
	              </li>
                <li>
                  <a href="logout.do" class="text-uppercase item-anchor">LOGOUT</a>
                </li>
                
              </c:if>
              <c:if test="${user == null}">
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
  <script type="text/javascript">
  	function hide(){
  		$("#basketList").css('visibility','hidden');
  	}
  	function show(){
  		$("#basketList").css('visibility','visible');
  	}
</script>
  <div class="basketList" id="basketList">
  	<div class="basketHead">
  	내 도서 목록
  	</div>
  	<div class="ListContainer">
  		<c:forEach var="index" begin="1" end="10">
	  		<div class="ListItem">
	  			<div class="ItemImage">
	  				<img src="http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902">
	  			</div>
	  			<div class="ItemTitle" id="Item">
	  			불곰의 <b>주식</b>투자 불패공식
	  			</div>
	  			<div class="ItemAuthor" id="Item">
	  			저자명 : 불곰 박선목	
	  			</div>
	  			<div class="ItemPublisher" id="Item">
	  			출판사 : 부키
	  			</div>
	  			<div class="ItemPubdate" id="Item">
	  			출간일 : 20160729
	  			</div>
	  			<div class="ItemDiscount" id="Item">
	  			도서가격 : 16000원
	  			</div>
	  			<div class="ItemDelete">
	  			도서삭제
	  			</div>
	  		</div>
	  	</c:forEach>
  	</div>	 
  	<div class="ListClose" id="ListClose" onClick="javascript:hide();">
  		닫기
  	</div>                     
  </div>
</header>