<%@ page pageEncoding="UTF-8" %>

<!-- jquery script -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

<!-- swiper script -->
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<!-- Bootstarp script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>

<!-- script -->
<script src="resources/js/plugins.js"></script>
<script src="resources/js/script.js"></script>

<script type="text/javascript">
    $('#mainMenu').on('click', function() {
        let visible = $('#mainMenu > div > ul').is(':visible');
        if (visible) getPoint();
    });

    function header_createResultItem(seq, book) {
        let content = []
        content.push("<div class='ListItem' id='basket_" + seq + "'>");
        content.push("  <div class='ItemImage'>");
        content.push("    <a href='" + book.link + "'>");
        content.push("      <img src='" + book.image + "'  alt='' />");
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

    function getPoint() {
        $.ajax({
            type: 'get',
            url: '/biz/api/user/info/get',
            dataType: 'json',
            data: {
                userId: $("#user_id").val()
            },
            success: function (data) {
                console.log("Get User 성공")
                $("span#user-point").html('<i class="fa-solid fa-coins"></i> ' + data.point + ' Point');
            },
            error: function () {
                console.log("Get User 실패")
            }

        })
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
    const provider = "${user.provider}" ;
    console.log("provider 1 : "+provider);
    console.log("provider 3 : "+"${user.provider}");

    checkModalButton.addEventListener("click", function() {
        if(provider === "local") {
            modal.style.display = "block";
        }else {
            window.location.href = "userupdate.do";
        }
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