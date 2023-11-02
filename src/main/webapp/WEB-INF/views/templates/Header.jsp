<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg bg-transparent fixed-top z-1">
  <div class="container-xxl">
    <a class="navbar-brand d-flex align-items-center" href=<c:url value='/' />>
      <img src="/biz/resources/images/mainlogo.png" width="30" height="30">
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
          <a class="nav-link text-white" href="/biz/bookRecommend.do">도서추천</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="/biz/placeRecommend.do">플레이스</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="/biz/listBookBoard.do">도서게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white" href="#">설명</a>
        </li>
      </ul>

      <c:if test="${user == null}">
        <a href="/biz/login.do" class="item-anchor text-white">로그인</a>
      </c:if>
      <c:if test="${user != null}">
        <div class="dropdown">
          <a href="#" class="dropdown-toggle" role="button" id="userMenu" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="fa-solid fa-circle-user fa-xl text-white"></i>
          </a>
          <ul class="dropdown-menu dropdown-menu-end mt-2" aria-labelledby="userMenu">
            <li><span class="dropdown-itemtext px-2"><i class="fa-solid fa-hashtag"></i> ${user.nickname}</span></li>
            <li><hr class="dropdown-divider"></li>
            <li><span class="dropdown-itemtext px-2"><i class="fa-solid fa-coins"></i> ${user.point} Point</span></li>
            <li><a href="/biz/chargepoint.do"><span class="badge bg-success content-light">충전하기</span></a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a id="checkModalButton" class="dropdown-item userupdate-button">내 정보 수정 <span class="badge bg-secondary"><i class="fa-solid fa-pen-to-square"></i></span></a></li>
            <li><a class="dropdown-item" onClick="show();">내 도서 목록 <span class="badge bg-secondary"><i class="fa-solid fa-list"></i></span></a></li>
            <c:if test="${user.role_id != 0}">
              <li><a class="dropdown-item" href="/biz/usermanage.do">회원관리 <span class="badge bg-secondary"><i class="fa-solid fa-person"></i></span></a></li>
            </c:if>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="/biz/logout.do">로그아웃 <span class="badge bg-secondary"><i class="fa-solid fa-right-from-bracket"></i></span></a></li>
          </ul>
        </div>
      </c:if>
    </div>
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
  <input type="hidden" id="user-id" value="${user.id}">
</nav>
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