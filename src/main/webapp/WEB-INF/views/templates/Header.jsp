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
                <a href="bookRecommend.do" class="text-uppercase item-anchor">도서추천</a>
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
	                  <!-- 
	                  	<a class="text-black text-uppercase dropdown-item" href="userupdate.do"> 
	                  		내 정보 수정       
	                    </a>
	                  -->
	                   <button id="checkModalButton" class="text-black text-uppercase dropdown-item userupdate-button" type="button">내 정보 수정
	                   	<span class="badge bg-secondary">Go</span>
	                   </button>
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
          
          <div id="myModal" class="modal">
            <div class="modal-content kyu-modal">
              <div class="relative modal-relative">
		        <span class="close" id="closeModal">&times;</span>
		        <input type="password" id="inputValue" placeholder="패스워드를 입력해주세요.">
		        <button id="submitValue">확인</button>
	  	      </div>
            </div>
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
    <div class="ListClose" id="ListClose" onClick="hide()">닫기</div>
  </div>
  <script type="text/javascript">
    function header_createResultItem(seq, book) {
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
        content.push("  <button type='button' class='ItemDelete' onclick='header_deleteBasket(" + seq + ", " + book.isbn + ")'>삭제</button>");
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

                let element = header_createResultItem(i, book);
                basketResult.append(element);
            }

            form.css('visibility', 'visible');
        } else {
            if (confirm("저장 도서가 없습니다. 저장하시겠습니까?")) location.href = "bookRecommend.do";
        }
    }

    function hide(){
        let form = $('#basketList');
        form.css('visibility', 'hidden');
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

    function header_deleteBasket(seq, isbn) {
        let user_id = $("#user_id").val();

        $.ajax({
            type: 'get',
            url: '/biz/api/user/basket/delete',
            dataType: 'json',
            data: {
                userId: user_id,
                bookId: isbn
            },
            success: function (data) {
                console.log("Delete Basket Data 성공")
                if (data) {
                    alert("도서가 삭제되었습니다.\n" + isbn);
                    $("#basket_" + seq).remove();
                    return show();
                } else {
                    alert("이미 존재하지 않습니다.")
                }
            },
            error: function () {
                console.log("Delete Basket Data 실패")
            }
        })
    }
    const checkModalButton = document.getElementById("checkModalButton");
    const modal = document.getElementById("myModal");
    const closeModal = document.getElementById("closeModal");
    const inputValue = document.getElementById("inputValue");
    const submitValue = document.getElementById("submitValue");
    
    checkModalButton.addEventListener("click", function() {
    		modal.style.display = "block";
    	});

    	// 모달 닫기 버튼 클릭 이벤트 처리
    	closeModal.addEventListener("click", function() {
    		modal.style.display = "none";
    	});

    	// 확인 버튼 클릭 이벤트 처리
    	submitValue.addEventListener("click", function() {
    	  const enteredValue = inputValue.value;
    	  
    	  // 서버에서 가져온 값 (예: 서버에서 가져온 값이 "exampleValue"인 경우)
    	  const serverValue = "${user.pwd}";
    	  
    	  if (enteredValue === serverValue) {
    	    // 입력한 값과 서버에서 가져온 값이 일치할 경우 다른 URL로 이동
    	    window.location.href = "userupdate.do";
    	  } else {
    	    alert("패스워드가 일치하지 않습니다.");
    	    inputValue.value = "";
    	  }
    	  
    	  modal.style.display = "none";
    	});
    
  </script>
</header>