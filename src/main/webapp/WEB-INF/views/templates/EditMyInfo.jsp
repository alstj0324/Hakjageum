<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-fade" id="editinfo-checkpassword" aria-hidden="true" tabindex="-1">
  <div class="modal-dialog modal-dialog-centerd modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">비밀번호 확인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form>
          <div class="mb-3">
            <label for="editinfo-input-password" class="col-form-label">비밀번호</label>
            <input type="text" class="form-control" id="editinfo-input-password">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">확인</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<%--          <div id="myModal" class="modal">--%>
<%--            <div class="modal-content kyu-modal">--%>
<%--              <div class="relative modal-relative">--%>
<%--                <span class="close" id="closeModal">&times;</span>--%>
<%--                <input type="password" id="inputValue" placeholder="패스워드를 입력해주세요.">--%>
<%--                <button id="submitValue">확인</button>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </nav>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--  <input type="hidden" id="user_id" value="${user.id}">--%>

<%--  <div class="basketList" id="basketList">--%>
<%--    <div class="basketHead">내 도서 목록</div>--%>
<%--    <div class="ListContainer" id="listContainer">--%>
<%--      <p class="ListNone">저장 도서가 없습니다.</p>--%>
<%--    </div>--%>
<%--    <div class="ListClose" id="ListClose" onClick="hide()">닫기</div>--%>
<%--  </div>--%>
<%--  <script type="text/javascript">--%>
<%--    function header_createResultItem(seq, book) {--%>
<%--        let content = []--%>
<%--        content.push("<div class='ListItem' id='basket_" + seq + "'>");--%>
<%--        content.push("  <div class='ItemImage'>");--%>
<%--        content.push("    <a href='" + book.link + "'>");--%>
<%--        content.push("      <img src='" + book.image + "' />");--%>
<%--        content.push("    </a>");--%>
<%--        content.push("  </div>");--%>
<%--        content.push("  <div class='ItemTitle' id='Item'>" + book.title + "</div>");--%>
<%--        content.push("  <div class='ItemAuthor' id='Item'>저자명 : " + book.author + "</div>");--%>
<%--        content.push("  <div class='ItemPublisher' id='Item'>출판사 : " + book.publisher + "</div>");--%>
<%--        content.push("  <div class='ItemPubdate' id='Item'>출간일 : " + book.pubdate + "</div>");--%>
<%--        content.push("  <div class='ItemDiscount' id='Item'>도서가격 : " + book.discount + "</div>");--%>
<%--        content.push("  <button type='button' class='ItemDelete' onclick='header_deleteBasket(" + seq + ", " + book.isbn + ")'>삭제</button>");--%>
<%--        content.push("</div>");--%>

<%--        return content.join("");--%>
<%--    }--%>

<%--    function show() {--%>
<%--        let form = $('#basketList');--%>
<%--        let basketResult = $('#listContainer');--%>
<%--        var user_id = $("#user_id").val();--%>
<%--        let basketList = getBasketList();--%>
<%--        if (basketList.length !== 0) {--%>
<%--            basketResult.empty();--%>

<%--            for (let i = 0; i < basketList.length; i++) {--%>
<%--                let book = basketList[i];--%>

<%--                let element = header_createResultItem(i, book);--%>
<%--                basketResult.append(element);--%>
<%--            }--%>

<%--            form.css('visibility', 'visible');--%>
<%--        } else {--%>
<%--            if (confirm("저장 도서가 없습니다. 저장하시겠습니까?")) location.href = "bookRecommend.do";--%>
<%--        }--%>
<%--    }--%>

<%--    function hide(){--%>
<%--        let form = $('#basketList');--%>
<%--        form.css('visibility', 'hidden');--%>
<%--    }--%>
<%--    // show func--%>
<%--    function getBasketList() {--%>
<%--        let user_id = $("#user_id").val();--%>
<%--        let res;--%>
<%--        $.ajax({--%>
<%--            type: 'get',--%>
<%--            url: '/biz/api/user/basket/getbookinfo',--%>
<%--            dataType: 'json',--%>
<%--            async: false,--%>
<%--            data: {--%>
<%--                "userId": user_id--%>
<%--            },--%>
<%--            success: function(data) {--%>
<%--                console.log("Get Basket Data 성공")--%>
<%--                res = data;--%>
<%--            },--%>
<%--            error: function() {--%>
<%--                console.log("Get Basket Data 실패")--%>
<%--            }--%>
<%--        })--%>

<%--        return res;--%>
<%--    }--%>

<%--    function header_deleteBasket(seq, isbn) {--%>
<%--        let user_id = $("#user_id").val();--%>

<%--        $.ajax({--%>
<%--            type: 'get',--%>
<%--            url: '/biz/api/user/basket/delete',--%>
<%--            dataType: 'json',--%>
<%--            data: {--%>
<%--                userId: user_id,--%>
<%--                bookId: isbn--%>
<%--            },--%>
<%--            success: function (data) {--%>
<%--                console.log("Delete Basket Data 성공")--%>
<%--                if (data) {--%>
<%--                    alert("도서가 삭제되었습니다.\n" + isbn);--%>
<%--                    $("#basket_" + seq).remove();--%>
<%--                    return show();--%>
<%--                } else {--%>
<%--                    alert("이미 존재하지 않습니다.")--%>
<%--                }--%>
<%--            },--%>
<%--            error: function () {--%>
<%--                console.log("Delete Basket Data 실패")--%>
<%--            }--%>
<%--        })--%>
<%--    }--%>

<%--    const checkModalButton = document.getElementById("checkModalButton");--%>
<%--    const modal = document.getElementById("myModal");--%>
<%--    const closeModal = document.getElementById("closeModal");--%>
<%--    const inputValue = document.getElementById("inputValue");--%>
<%--    const submitValue = document.getElementById("submitValue");--%>
<%--    --%>
<%--    checkModalButton.addEventListener("click", function() {--%>
<%--    		modal.style.display = "block";--%>
<%--    	});--%>

<%--    	// 모달 닫기 버튼 클릭 이벤트 처리--%>
<%--    	closeModal.addEventListener("click", function() {--%>
<%--    		modal.style.display = "none";--%>
<%--    	});--%>

<%--    	// 확인 버튼 클릭 이벤트 처리--%>
<%--    	submitValue.addEventListener("click", function() {--%>
<%--    	  const enteredValue = inputValue.value;--%>
<%--    	  --%>
<%--    	  // 서버에서 가져온 값 (예: 서버에서 가져온 값이 "exampleValue"인 경우)--%>
<%--    	  const serverValue = "${user.pwd}";--%>
<%--    	  --%>
<%--    	  if (enteredValue === serverValue) {--%>
<%--    	    // 입력한 값과 서버에서 가져온 값이 일치할 경우 다른 URL로 이동--%>
<%--    	    window.location.href = "userupdate.do";--%>
<%--    	  } else {--%>
<%--    	    alert("패스워드가 일치하지 않습니다.");--%>
<%--    	    inputValue.value = "";--%>
<%--    	  }--%>
<%--    	  --%>
<%--    	  modal.style.display = "none";--%>
<%--    	});--%>
<%--    --%>
<%--  </script>--%>