<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="../templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="BookBoardForm-Container">
      	<div class="BookBoardForm-description-container">
      		<div class="BookBoardForm-description">BookBoard Form</div>
      		<div class="backbtnContainer">
						<button type="button" class="backbtn" onclick="back()">이전 페이지로 이동</button>
					</div>
      	</div>
      	<div class=BookBoardFormMain-Container>
      		<div class="BookBoard-Form">
      			<input type="hidden" id="user_id" value="${user.id}"><!-- ajax에 사용 -->
      			<form action="insertBookBoard.do" method="post">
      				<input type="hidden" name="writer_id" id="writer_id" value=${user.id } required>
      				<input type="hidden" name="board_code" id="board_code" value="BA2">
      				<input type="hidden" name="book_id" id="book_id" value="">
	      			<div class="BookBoard-Form-description">
	      				[도서 후기 및 의견을 작성해주세요]
	      			</div>
	      			<div class="book-title-wrap">
	      				<input type="text" id="book-title" name="title" placeholder="도서명을 제목에 포함시켜주시면 감사하겠습니다." required>
	      			</div>
	      			<div class="book-select-wrap">
	      				<button type="button" id="book-select" onClick="selectOpen()">도서 목록<br>불러오기</button>
	      			</div>
	      			<div class="book-main-wrap">
	      				<div class="book-image-wrap">
	      					<div class="image-box">
	      						<img id="book-image" src="">
	      					</div>
	      					<div class="title-box">
	      						[도서를 선택해주세요]
	      					</div>
	      					<div class="author-box">
	      					</div>	
	      				</div>
	      				<div class="book-content-wrap">
	      					<textarea class="book-content" name="content" placeholder="자신이 느낀점이나 기대되는점, 후기를 작성해주세요" required></textarea>
	      				</div>
	      			</div>
	      			<div class="book-submit-wrap">
	      				<input type="submit" value="등록">
	      			</div>
      			</form>
      		</div>
      		<div class="book-select-page" id="book-select-page">
      			<div class="select-page-description">
      				내 도서 목록
      			</div>
      			<div class="select-page-content-container" id="select-page-content-container"></div>
      			<div class="select-page-close">
      				<button type="button" id="closebtn" onclick="selectClose()">닫기</button>
      			</div>
      		</div>
      	</div>
      </div>

      <script type="text/javascript">
      			function selectClose(){
      				$("#book-select-page").css('visibility','hidden');
      			}
      			function selectBook(isbn){
      				var bookId = isbn
      				console.log(bookId)
      				if(confirm("도서를 선택하시겠습니까?")== true){
      					$.ajax({
 	        	           type:"get",
 	        	           url:"/biz/api/book/get/" + bookId,
 	        	           dataType:"json",
 	        	           async: false,
 	        	   	       success: function(data){
 	        	               console.log("통신성공");
 	        	               var image = data[0].image;  
 	        	               var title = (data[0].title).split("(");
 	        	               var author = (data[0].author).replace('^',',');
 	        	               console.log("isbn이요"+isbn)
 	        	               $("#book-image").attr("src", image);
 	        	               $(".title-box").html(title[0]);
 	        	               $(".author-box").html(author);
 	        	               $("#book_id").val(isbn);
 	        	               alert("도서가 추가되었습니다!");
 	        	               selectClose()
 	        	           },
 	        	           error:function(){     	
 	        	           }
 	        	       });
      		  		}else{
      		  			return false;
      		  		}
      			}
      			function selectOpen(){
      				var user_id = $("#user_id").val();
      		  		var str = "";
      		  		$.ajax({
      			           type:"get",
      			           url:"getBasketList.do",
      			           dataType:"json",
      			           data : {                       
      			               "user_id" : user_id
      			           },
      			           success: function(data){
      			               console.log("통신성공");
      			               var bookId="";
      			               $.each(data, function(index, item) {
      			               	   bookId = item.book_id;
      			            	   $.ajax({
      			        	           type:"get",
      			        	           url:"/biz/api/book/get/" + bookId,
      			        	           dataType:"json",
      			        	           async: false,
      			        	   	       success: function(data){
      			        	               console.log("통신성공");
      			        	               var image = data[0].image;  
      			        	               var title = (data[0].title).split("(");
      			        	               var author = (data[0].author).replace('^',',');
      			        	               var publisher = data[0].publisher;
      			        	               var pubdate = data[0].pubdate;
      			        	               var isbn = data[0].isbn;
      			        	        
      			        	               str += '<div class="selectListItem">' +
      			 			  			          '<div class="selectItemImage">' + '<img src="' + image + '">' + '</div>' + 
      					  				          '<input type="hidden" id="book_unique_id" value="' + isbn + '">' +
      					  			              '<div class="selectItemTitle" id="selectItem">' + title[0] + '</div>' +
      					  			              '<div class="selectItemAuthor" id="selectItem">' + '저자명 : ' + author +'</div>' + 
      					  			              '<div class="selectItemPublisher" id="selectItem">' + '출판사 : '+ publisher + '</div>' + 
      					  			              '<div class="selectItemPubdate" id="selectItem">' + '출간일 : ' + pubdate + '</div>' + 
      					  			              '<button type="button" class="selectItemSelect" onclick="selectBook('+isbn+')">' + '도서선택' + '</button>'
      					  			              + '</div>';     
      			        	           },
      			        	           error:function(){     	
      			        	           }
      			        	       });
      			        	       console.log(str);
      							});
      			  			   $("#select-page-content-container").html(str);
      			           },
      			           error:function(request, status, error){
      			        	   //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      			           }  
      			       });	
      		  			$("#book-select-page").css('visibility','visible');
      		  	}


				function selectOpen(){
					var user_id = $("#user_id").val();
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
							basketList = data;
							if(basketList.length != 0){
								let select_container = $("#select-page-content-container");
								select_container.empty();
								for (let i = data.length - 1; i >= 0; i--) {
									select_container.append(createResultItem(i, data[i]));
								}
								$("#book-select-page").css('visibility','visible');
							}else {
								if(confirm("저장된 도서가 없습니다\n도서를 찾아보시겠습니까?") === true){
									location.href="bookRecommend.do";	
								}else {
									alert("자유게시판으로 이동합니다.")
									location.href="listFreeBoard.do";
								} 
								
							}
						},
						error: function() {
							console.log("Get Basket Data 실패")
						}
					});
					
				}

				function selectBook(seq){
					if(confirm("도서를 선택하시겠습니까?") === true) {
						let book = basketList[seq];

						$("#book-image").attr("src", book.image);
						$(".title-box").html(book.title);
						$(".author-box").html(book.author);
						$("#book_id").val(book.isbn);
						alert("도서가 추가되었습니다!");
						selectClose()
					}else{
						return false;
					}
				}

				function selectClose(){
					$("#book-select-page").css('visibility','hidden');
				}

				function createResultItem(seq, book) {
					let content = []

					content.push("<div class='selectListItem' id='basket_" + seq + "'>");
					content.push("  <div class='selectItemImage'>");
					content.push("    <img src='" + book.image + "' />");
					content.push("  </div>");
					content.push("  <input type='hidden' id='book_unique_id' value='" + book.isbn + "' />");
					content.push("  <div class='selectItemTitle' id='selectItem'>" + book.title + "</div>");
					content.push("  <div class='selectItemAuthor' id='selectItem'>저자명 : " + book.author + "</div>");
					content.push("  <div class='selectItemPublisher' id='selectItem'>출판사 : " + book.publisher + "</div>");
					content.push("  <div class='selectItemPubdate' id='selectItem'>출간일 : " + book.pubdate + "</div>");
					content.push("  <button type='button' class='selectItemSelect' onclick='selectBook(" + seq + ")'>도서선택</button>");
					content.push("</div>");

					return content.join("");
				}
				function back(){
            		history.back();
            	}

			</script>
    </section>
    <%@ include file="../templates/UseJS.jsp" %>
  </body>
</html>