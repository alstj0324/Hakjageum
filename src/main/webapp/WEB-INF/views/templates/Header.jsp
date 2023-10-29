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
                    <a class="text-black text-uppercase dropdown-item" href="freeBoardList.do">
                      자유 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="bookBoardList.do">
                      도서 게시판&nbsp
                      <span class="badge bg-secondary">Go</span>
                    </a>
                  </li>
                  <li class="sub-page pe-0">
                    <a class="text-black text-uppercase dropdown-item" href="hobbyBoardList.do">
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
                  <a href="http://localhost:8080/biz/login.do" class="text-uppercase item-anchor">Sign In/Sign Up</a>
                </li>
              </c:if>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </div>
  <input type="hidden" id="user_id" value="${user.id}">
  <script type="text/javascript">    
  	function deleteBasket(){
		var user_id = $("#user_id").val();
		var book_unique_id = $("#book_unique_id").val();
		if (confirm("도서를 삭제하시겠습니까?") == true){
			$.ajax({
  	           type:"get",
  	           url:"deleteBasket.do",
  	           dataType:"text",
  	           data : {                       // 매개변수로 전달할 데이터
  	               "user_id" : user_id,
  	               "book_unique_id" : book_unique_id// 아이디값
  	           },
  	           success: function(data){
  	               console.log("통신성공");
  	               
  	               if(data === 'False'){
  	                   alert("도서가 삭제되었습니다.");
  	                   return show();
  	               }else if(data === 'True'){
  	            	   alert("이미 존재하지 않습니다.")
  	            	   
  	               }
  	           },
  	           error:function(request, status, error){
  	        	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
  	           }
  	       })
		}else{
			return false;
		}	
	}
  
  	function hide(){
  		$("#basketList").css('visibility','hidden');
  	}
  	function show(){
  		var user_id = $("#user_id").val();
  		var data1="";
  		var str = "";
  		var str2 = '<div class="ListClose" id="ListClose" onClick="javascript:hide();">'+
  			   '닫기' +
  			   '</div>';
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
	               $.each(data, function(index, item) { // 데이터 =item/api/book/get/{bookId}
	               	   bookId = item.book_id;
	            	   $.ajax({
	        	           type:"get",
	        	           url:"/biz/api/book/get/" + bookId,
	        	           dataType:"json",
	        	           async: false,
	        	   	       success: function(data){
	        	               console.log("통신성공");
	        	               var image = data[0].image;
	        	               var link = data[0].link;
	        	               var title = (data[0].title).split("(");
	        	               var author = data[0].author;
	        	               var publisher = data[0].publisher;
	        	               var isbn = data[0].isbn
	        	               var pubdate = data[0].pubdate;
	        	               var discount = data[0].discount;
	        	               str += '<div class="ListItem">'+
	        		  			      '<div class="ItemImage">' +
	       	  				           '<a href="' +  link  +  '">'  +  '<img src="'+ image + '">' + '</a>' +
	       	  							'</div>' + '<input type="hidden" id="book_unique_id" value="'+isbn+'">' +
	       	  							'<div class="ItemTitle" id="Item">' + title[0] + '</div>' +
	       	 	       	  			    '<div class="ItemAuthor" id="Item">' + '저자명 :' + author + '</div>' + 	
								        '<div class="ItemPublisher" id="Item">' + '출판사 :' + publisher + '</div>' + 
	       	  					        '<div class="ItemPubdate" id="Item">' + '출간일 :'+ pubdate + '</div>' +
	       	  						    '<div class="ItemDiscount" id="Item">' + '도서가격 :' + discount + '원</div>' +
										'<button type="button" class="ItemDelete" onclick="deleteBasket()">' + '도서삭제' + '</div>'+ '</div>';
	        	           },
	        	           error:function(){     	
	        	           }
	        	       });
	        	       console.log(str);
	        	       
					});
	  			   $("#listContainer").html(str);
	           },
	           error:function(request, status, error){
	        	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	           }  
	       });
	     
  		
	    $("#basketList").css('visibility','visible');
  	}
  	
  
</script>
  <div class="basketList" id="basketList">
  	<div class="basketHead">
  	내 도서 목록
  	</div>
  	<div class="ListContainer" id="listContainer">
  		
	  		
  	</div>	
  	<div class="ListClose" id="ListClose" onClick="javascript:hide();">
	 닫기
	</div>'                     
  </div>
</header>