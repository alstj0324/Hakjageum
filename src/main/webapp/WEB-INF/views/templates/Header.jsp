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
                    <a class="text-black text-uppercase dropdown-item" href="listFreeBoard.do">
                      자유 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="listBookBoard.do">
                      도서 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="listHobbyBoard.do">
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
                    <a class="text-black text-uppercase dropdown-item" href="Test.do }">
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
		                    <a class="text-black text-uppercase dropdown-item" href="usermanage.do">
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
                  <a href="/biz/login.do" class="text-uppercase item-anchor">Sign In/Sign Up</a>
                </li>
              </c:if>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </div>
  <input type="hidden" id="user_id" value="${user.id}">

  <div class="basketList" id="basketList">
    <div class="basketHead">내 도서 목록</div>
    <div class="ListContainer" id="listContainer">
      <p class="ListNone">저장 도서가 없습니다.</p>
    </div>
    <div class="ListClose" id="ListClose" onClick="javascript:hide();">닫기</div>
  </div>
  <script type="text/javascript">
    function createResultItem(seq, book) {
        let content = []
        content.push("<div class='ListItem' id='basket_" + seq + "'>");
        content.push("  <div class='ItemImage'>");
        content.push("    <a href='" + book.link + "'>");
        content.push("      <img src='" + book.image + "' />");
        content.push("    </a>");
        content.push("  </div>");
        content.push("  <div class='ItemTitle' id='Item'>" + book.title + "</div>");
        content.push("  <div class='ItemAuthor' id='Item'>저자명 : " + book.author + "</div>");
        content.push("  <div class='ItemPublisher' id='Item'>출판사 : " + book.publisher + "</div>");
        content.push("  <div class='ItemPubdate' id='Item'>출간일 : " + book.pubdate + "</div>");
        content.push("  <div class='ItemDiscount' id='Item'>도서가격 : " + book.discount + "</div>");
        content.push("  <button type='button' class='ItemDelete' onclick='deleteBasket(" + book.isbn + ")'>삭제</button>");
        content.push("</div>");

        return content.join("");
    }

    function show() {
        let form = $('#basketList');
        let basketResult = $('#listContainer');
        var user_id = $("#user_id").val();
        let basketList = getBasketList();

        if (basketList.length !== 0) {
            basketResult.empty();

            for (let i = 0; i < basketList.length; i++) {
                let book = basketList[i];

                let element = createResultItem(i, book);
                basketResult.append(element);
            }
        }

        form.css('visibility','visible');
    }

    function hide(){
        $("#basketList").css('visibility','hidden');
    }
      // show func
    function getBasketList() {
        let user_id = $("#user_id").val();
        let res;
        $.ajax({
            type: 'get',
            url: '/biz/api/user/basket/getbookinfo',
            dataType: 'json',
            async: false,
            data: {
                "userId": user_id
            },
            success: function(data) {
                console.log("Get Basket Data 성공")
                res = data;
            },
            error: function() {
                console.log("Get Basket Data 실패")
            }
        })

        return res;
    }

    function deleteBasket(isbn) {
        let user_id = $("#user_id").val();

        $.ajax({
            type: 'post',
            url: '/biz/api/user/basket/delete',
            dataType: 'json',
            data: {
                "userId": user_id,
                "bookId": isbn
            },
            success: function (data) {
                console.log("Delete Basket Data 성공")
                if (data === 'False') {
                    alert("도서가 삭제되었습니다.");
                    return show();
                } else if (data === 'True') {
                    alert("이미 존재하지 않습니다.")
                }
            },
            error: function () {
                console.log("Delete Basket Data 실패")
            }
        })
    }
  </script>
</header>