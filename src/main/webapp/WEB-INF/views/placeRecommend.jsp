<%@page import="com.mySpringWeb.domain.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=pjn5n9vyj7"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<html>
  <html lang="en">
<head>
  <title>학자금, 학생에게 자기계발은 금이다.</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="format-detection" content="telephone=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="author" content="">
  <meta name="keywords" content="">
  <meta name="description" content="">
  <link rel="stylesheet" type="text/css" href="resources/css/normalize.css">
  <link rel="stylesheet" type="text/css" href="resources/fonts/icomoon.css">
  <link rel="stylesheet" type="text/css" href="resources/css/vendor.css">
  <!--Bootstrap ================================================== -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
    integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" type="text/css" href="resources/css/style.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Syne:wght@600;700;800&display=swap" rel="stylesheet">
  <link
    href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Syne:wght@600;700;800&display=swap"
    rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="resources/css/custom.css">
   <link rel="preconnect" href="https://fonts.googleapis.com">
   <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
   <link href="https://fonts.googleapis.com/css2?family=Henny+Penny&family=Playfair+Display:ital@1&display=swap" rel="stylesheet">
</head>

<body class="hompage bg-accent-light">

  <header id="header" class=" content-light ">
    <div class="header-wrap container py-3">
      <div class="row align-items-center">
        <div class="primary-nav col-md-5 col-sm-2">
          <nav class="navbar">
            <div class="main-menu">
              <ul class="menu-list  list-unstyled d-flex m-0 ">
                <li class="menu-itemhome1 dropdown">
                  <a class="text-uppercase item-anchor" href="http://localhost:8080/biz/main.jsp" > Home </a>
                </li>
                <li class="menu-itemhome1">
                  <a href="bookRecommend.do?category=자기계발" class="text-uppercase item-anchor">도서추천</a>
                </li>
                <li class="menu-itemhome1 dropdown">
                  <a href="placeRecommend.do"> 플레이스 </a>
                </li>
                <li class="menu-itemhome1 dropdown">
                  <a class="text-uppercase item-anchor dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"> Board </a>
                  <ul class="dropdown-menu">
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item" href="getBoardList.do">
                        자유 게시판&nbsp;<span class="badge bg-secondary">Go</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="blog-with-sidebar.html">도서 게시판&nbsp;<span class="badge bg-secondary">Go</span></a>
                    </li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="single-post.html">취미 게시판&nbsp;<span class="badge bg-secondary">Go</span></a></li>
                  </ul>
                </li>
                <li class="menu-itemhome1 dropdown">
                  <a class="text-uppercase item-anchor dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false"> Notice </a>
                  <ul class="dropdown-menu">
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="contact.html">contact&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="cart.html">cart&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="checkout.html">checkout&nbsp;<span class="badge bg-secondary">pro</span></a></li>
                    <li class="sub-page pe-0"><a class="text-black text-uppercase dropdown-item"
                        href="my-account.html">account&nbsp;<span class="badge bg-secondary">pro</span></a></li>
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
            <a href="http://localhost:8080/biz/main.jsp">
              <img src="resources/images/mainlogo.png" alt="logo" class="brand-image">
            </a>
          </div>
        </div>
        <div class="right-block col-md-5 col-sm-4">
          <nav class="navbar justify-content-end">
            <div class="user-items">
              <ul class="list-unstyled content-light d-flex align-items-center m-0">
              	<c:if test="${nickname != null }">
                  <li> 
					<a style="color:gold;">[${nickname }]</a>
              	  </li>
              	  <c:if test="${nickname != admin }">
                  	<li>
                  	   <a href="usermanage.do" class="text-uppercase item-anchor">MANAGE</a>
                  	</li>
                  </c:if>
                  <li>                  
					<a href="logout.do" class="text-uppercase item-anchor">LOGOUT</a>
                  </li>
                </c:if>
                <c:if test="${nickname == null }">
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
  <section id="billboard" class="padding-large no-padding-top position-relative">
    <div class="image-holder">
      <img src="resources/images/banner-image.jpg" alt="banner" class="banner-image">
      <div class="banner-content-board">
      	<div class="banner-content-banner">
      	  <h2>Place</h2>
      	</div>
        <div class="map-content">
				<div class="place-content-item1">
			        <div class="address-select">
			        	<div class="address-category">
			        	<script>
			        	$('document').ready(function() {
			        		 var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
			        		  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
			        		   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
			        		   var area3 = ["대덕구","동구","서구","유성구","중구"];
			        		   var area4 = ["광산구","남구","동구",     "북구","서구"];
			        		   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
			        		   var area6 = ["남구","동구","북구","중구","울주군"];
			        		   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
			        		   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
			        		   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
			        		   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
			        		   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
			        		   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
			        		   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
			        		   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
			        		   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
			        		   var area16 = ["서귀포시","제주시","남제주군","북제주군"];

			        		 

			        		 // 시/도 선택 박스 초기화

			        		 $("select[name^=address-select1]").each(function() {
			        		  $selsido = $(this);
			        		  $.each(eval(area0), function() {
			        		   $selsido.append("<option value='"+this+"'>"+this+"</option>");
			        		  });
			        		  $selsido.next().append("<option value=''>구/군 선택</option>");
			        		 });

			        		 

			        		 // 시/도 선택시 구/군 설정

			        		 $("select[name^=address-select1]").change(function() {
			        		  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
			        		  var $gugun = $(this).next(); // 선택영역 군구 객체
			        		  $("option",$gugun).remove(); // 구군 초기화

			        		  if(area == "area0")
			        		   $gugun.append("<option value=''>구/군 선택</option>");
			        		  else {
			        		   $.each(eval(area), function() {
			        		    $gugun.append("<option value='"+this+"'>"+this+"</option>");
			        		   });
			        		  }
			        		 });


			        		});
			        	</script>
			        		<form action="placeRecommend.do">
							    <select class="placeRecommend" name="address-select1"></select>
							    <select class="placeRecommend" name="address-select2"></select>
							    <input type="submit" value="지역선택">
							</form>
			        	</div>
			        </div>
					<div class="place-map-container">
						<c:if test="${placeCategory eq '카페' }">
							<div class="place-map-nav1-checked">
								<a href="placeRecommend.do?placeCategory=카페">Cafe</a>
							</div>
							<div class="place-map-nav2">
								<a href="placeRecommend.do?placeCategory=도서관">Library</a>
							</div>
						</c:if>
						<c:if test="${placeCategory eq '도서관' }">
							<div class="place-map-nav1">
								<a href="placeRecommend.do?placeCategory=카페">Cafe</a>
							</div>
							<div class="place-map-nav2-checked">
								<a href="placeRecommend.do?placeCategory=도서관">Library</a>
							</div>
						</c:if>
					</div>
					<div class="place-expression">
						<c:if test="${placeCategory eq '카페' }">
							강서구 카페 위치정보(현재 카페 데이터는 강서구만 존재합니다.)
						</c:if>
						<c:if test="${placeCategory eq '도서관' }">
							서울시 도서관 위치정보 (현재 도서관 데이터는 서울시만 존재합니다.)
						</c:if>
					</div>
					<div class="place-map" id="place-map">
						<c:if test="${placeCategory eq '카페' }">
							<script type="text/javascript">
							$(function() {
								initMap();
								
							});
							function initMap(){
								var cafeaddress = new Array();
								cafeaddress = ${cafeArray}
								console.log("이까지는 됨")
								console.log(cafeaddress)
								let markers=new Array();//마커정보를 담는 배열
								let infoWindows=new Array();//정보창을 담는 배열
								var map = new naver.maps.Map('place-map',{
									center: new naver.maps.LatLng(37.54815556, 126.851675),
									zoom: 15
								});
								
								for (var i = 0; i < cafeaddress.length; i++) {
									// 지역을 담은 배열의 길이만큼 for문으로 마커와 정보창을 채워주자 !
								    var marker = new naver.maps.Marker({
								        map: map,
								        title: cafeaddress[i].name, // 지역구 이름 
								        position: new naver.maps.LatLng(cafeaddress[i].latitude , cafeaddress[i].longitude) // 지역구의 위도 경도 넣기 
								    });
								    
								    /* 정보창 */
									 var infoWindow = new naver.maps.InfoWindow({
									     content: '<div style="width:200px;text-align:center;padding:10px;color:black;"><b>' + cafeaddress[i].name + '</b><br></div>'
									 }); // 클릭했을 때 띄워줄 정보 HTML 작성
								    
									 markers.push(marker); // 생성한 마커를 배열에 담는다.
									 infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
								}
								
								function getClickHandler(seq) {
						
						            return function(e) {  // 마커를 클릭하는 부분
						                var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
						                    infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다
										
						                if (infoWindow.getMap()) {
						                    infoWindow.close();
						                } else {
						                    infoWindow.open(map, marker); // 표출
						                    map.panTo(e.coord);
						                    $.ajax({
						                        type:"get",
						                        url:"placeSearchFromDB.do",
						                        dataType:"json",
						                        data : {                       // 매개변수로 전달할 데이터                   
						                            "place" :  "강서구"+" "+ cafeaddress[seq].name  // 검색 시작 위치
						                        },
						                        success: function(data){
						                            console.log("통신성공");
						                            console.log(data);
						                            str = ""; 
						                            str2 =  '<div class="place-search-container">'+
				                                    		'<div class="place-search-item">'+
				                                    		'<div class="place-search-item1">'+
				                                    		              '검색결과가 존재하지 않습니다!' + 
				                                    		'</div>'+
				                                    		'<div class="place-search-item2">'+
				                                    		'OTL..'+
				                                    		'</div>' +
				                                    		'</div>'+
				                                    		'<div class="place-search-item3">'+
				                                    		'(1) Cafe, Library를 선택합니다(기본선택 Cafe)'+
															'</div>'+
														    '<div class="place-search-item4">'+
															'(2) 지도상의 마커 클릭시 마커내의 정보와 비슷한 값을 검색합니다.'+
														    '</div>'+
														    '<div class="place-search-item5">'+
															'(3) 장소검색에 검색어 입력 후 GO버튼 클릭시 검색이 가능합니다.'+
															'</div>'+
													         '</div>';
						                                $.each(data , function(i){
						                                    str += '<div class="place-search-container">'+
						                                    		'<div class="place-search-item">'+
						                                    		'<div class="place-search-item1">'+
						                                    		              data[i].title + 
						                                    		'</div>'+
						                                    		'<div class="place-search-item2">'+
						                                    		data[i].category +
						                                    		'</div>' +
						                                    		'</div>'+
						                                    		'<div class="place-search-item3">'+
						                                    		'[주소 정보]'+
																	'</div>'+
																    '<div class="place-search-item4">'+
																	'도로명&nbsp주소&nbsp;:&nbsp;'+data[i].roadAddress+
																    '</div>'+
																    '<div class="place-search-item5">'+
																	'지번&nbsp;주소&nbsp;:&nbsp;'+data[i].address+
																	'</div>'+
															         '</div>';
						                                });
					                                if(jQuery.isEmptyObject(data)){
						                            	$('.place-inform-container').html(str2);
						                            	
						                            }else if(!jQuery.isEmptyObject(data)){
						                            	$('.place-inform-container').html(str);
						                            }
						                        },
						                        error:function(){
						                            console.log("통신에러");
						                        }
						                    })

						                }
						    		}
						    	}
							    for (var i=0, ii=markers.length; i<ii; i++) {
							    	console.log(markers[i] , getClickHandler(i));
							        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
							    } 
							}
							
							</script>
						</c:if>
						<c:if test="${placeCategory eq '도서관' }">
							<script type="text/javascript">
							$(function() {
								initMap();
								
							});
							function initMap(){
								var libraryaddress = new Array();
								libraryaddress = ${libraryArray}
								
								let markers=new Array();//마커정보를 담는 배열
								let infoWindows=new Array();//정보창을 담는 배열
								var map = new naver.maps.Map('place-map',{
									center: new naver.maps.LatLng(37.566535, 126.9779692),
									zoom: 12
								});
								
								for (var i = 0; i < libraryaddress.length; i++) {
									// 지역을 담은 배열의 길이만큼 for문으로 마커와 정보창을 채워주자 !
								    var marker = new naver.maps.Marker({
								        map: map,
								        title: libraryaddress[i].name, // 지역구 이름 
								        position: new naver.maps.LatLng(libraryaddress[i].latitude , libraryaddress[i].longitude) // 지역구의 위도 경도 넣기 
								    });
								    
								    /* 정보창 */
									 var infoWindow = new naver.maps.InfoWindow({
									     content: '<div style="width:200px;text-align:center;padding:10px;color:black;"><b>' + libraryaddress[i].name + '</b><br></div>'
									 }); // 클릭했을 때 띄워줄 정보 HTML 작성
								    
									 markers.push(marker); // 생성한 마커를 배열에 담는다.
									 infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
								}
								
								function getClickHandler(seq) {
						
						            return function(e) {  // 마커를 클릭하는 부분
						                var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
						                    infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다

						                if (infoWindow.getMap()) {
						                    infoWindow.close();
						                } else {
						                    infoWindow.open(map, marker); // 표출
						                    map.panTo(e.coord);
						                    $.ajax({
						                        type:"get",
						                        url:"placeSearchFromDB.do",
						                        dataType:"json",
						                        data : {                       // 매개변수로 전달할 데이터                   
						                            "place" :  "서울시"+" "+ libraryaddress[seq].name  // 검색 시작 위치
						                        },
						                        success: function(data){
						                            console.log("통신성공");
						                            console.log(data);
						                            str = ""; 
						                            str2 =  '<div class="place-search-container">'+
								                            '<div class="place-search-item">'+
				                                    		'<div class="place-search-item1">'+
				                                    		              '검색결과가 존재하지 않습니다!' + 
				                                    		'</div>'+
				                                    		'<div class="place-search-item2">'+
				                                    		'OTL..'+
				                                    		'</div>' +
				                                    		'</div>'+
				                                    		'<div class="place-search-item3">'+
				                                    		'(1) Cafe, Library를 선택합니다(기본선택 Cafe)'+
															'</div>'+
														    '<div class="place-search-item4">'+
															'(2) 지도상의 마커 클릭시 마커내의 정보와 비슷한 값을 검색합니다.'+
														    '</div>'+
														    '<div class="place-search-item5">'+
															'(3) 장소검색에 검색어 입력 후 GO버튼 클릭시 검색이 가능합니다.'+
															'</div>'+
													         '</div>';
						                                $.each(data , function(i){
						                                    str += '<div class="place-search-container">'+
						                                    		'<div class="place-search-item">'+
						                                    		'<div class="place-search-item1">'+
						                                    		              data[i].title + 
						                                    		'</div>'+
						                                    		'<div class="place-search-item2">'+
						                                    		data[i].category +
						                                    		'</div>' +
						                                    		'</div>'+
						                                    		'<div class="place-search-item3">'+
						                                    		'[주소 정보]'+
																	'</div>'+
																    '<div class="place-search-item4">'+
																	'도로명&nbsp주소&nbsp;:&nbsp;'+data[i].roadAddress+
																    '</div>'+
																    '<div class="place-search-item5">'+
																	'지번&nbsp;주소&nbsp;:&nbsp;'+data[i].address+
																	'</div>'+
															         '</div>';                        
						                                });
					                                if(jQuery.isEmptyObject(data)){
						                            	$('.place-inform-container').html(str2);
						                            	
						                            }else if(!jQuery.isEmptyObject(data)){
						                            	$('.place-inform-container').html(str);
						                            }
						                        },
						                        error:function(){
						                            console.log("통신에러");
						                        }
						                    })
						                }
						    		}
						    	}
							    for (var i=0, ii=markers.length; i<ii; i++) {
							    	console.log(markers[i] , getClickHandler(i));
							        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
							    } 
							}
							</script>
						</c:if>
					</div>
				</div>
				<div class="place-content-item2">
					<div class="place-search">
		        		
		        			<input id="placeSearchKeyword" name="placeSearchKeyword" type="text" class="place-search-input" placeholder="장소검색 : 예시(OO구 횟집)">
		        			<input type="button" value="Go" onclick="placeSearch()">
		        		
		        		<script>
    						function placeSearch() {
    							$.ajax({
			                        type:"get",
			                        url:"placeSearch.do",
			                        dataType:"json",
			                        data : {                       // 매개변수로 전달할 데이터                   
			                            "place" : $('#placeSearchKeyword').val()  // 검색 시작 위치
			                        },
			                        success: function(data){
			                            console.log("통신성공");
			                            console.log(jQuery.isEmptyObject(data));
			                            str = ""; 
			                            str2 =  '<div class="place-search-container">'+
		                                		'<div class="place-search-item">'+
		                                		'<div class="place-search-item1">'+
		                                		              '검색결과가 존재하지 않습니다!' + 
		                                		'</div>'+
		                                		'<div class="place-search-item2">'+
		                                		'OTL..'+
		                                		'</div>' +
		                                		'</div>'+
		                                		'<div class="place-search-item3">'+
		                                		'(1) Cafe, Library를 선택합니다(기본선택 Cafe)'+
												'</div>'+
											    '<div class="place-search-item4">'+
												'(2) 지도상의 마커 클릭시 마커내의 정보와 비슷한 값을 검색합니다.'+
											    '</div>'+
											    '<div class="place-search-item5">'+
												'(3) 장소검색에 검색어 입력 후 GO버튼 클릭시 검색이 가능합니다.'+
												'</div>'+
										         '</div>';
			                                $.each(data , function(i){
			                                    str += '<div class="place-search-container">'+
			                                    		'<div class="place-search-item">'+
			                                    		'<div class="place-search-item1">'+
			                                    		              data[i].title + 
			                                    		'</div>'+
			                                    		'<div class="place-search-item2">'+
			                                    		data[i].category +
			                                    		'</div>' +
			                                    		'</div>'+
			                                    		'<div class="place-search-item3">'+
			                                    		'[주소 정보]'+
														'</div>'+
													    '<div class="place-search-item4">'+
														'도로명&nbsp주소&nbsp;:&nbsp;'+data[i].roadAddress+
													    '</div>'+
													    '<div class="place-search-item5">'+
														'지번&nbsp;주소&nbsp;:&nbsp;'+data[i].address+
														'</div>'+
												         '</div>';
			                                });
			                            if(jQuery.isEmptyObject(data)){
			                            	$('.place-inform-container').html(str2);
			                            	
			                            }else if(!jQuery.isEmptyObject(data)){
			                            	$('.place-inform-container').html(str);
			                            }
			                        },
			                        error:function(){
			                            console.log("통신에러");
			                        }
			                    })
			                    document.getElementById('placeSearchKeyword').value = null;
    							document.getElementById('placeSearchKeyword').focus();
    						}
						</script>
       		 		</div>
					<div class="place-review-nav">
						Information
					</div>
					<div class="place-inform-container">
						<div class="place-search-container">
							<div class="place-search-item">
								<div class="place-search-item1">
									Information Tip!	
								</div>
								<div class="place-search-item2">
									> 정보검색
								</div>
							</div>
							<div class="place-search-item3">
								(1) Cafe, Library를 선택합니다(기본선택 Cafe)  
							</div>
							<div class="place-search-item4">
								(2) 지도상의 마커 클릭시 마커내의 정보와 비슷한 값을 검색합니다.
							</div>
							<div class="place-search-item5">
								(3) 장소검색에 검색어 입력 후 GO버튼 클릭시 검색이 가능합니다.
							</div>
						</div>
					</div>
				</div>
			
        </div>
       
		
	  </div>
    </div>
    
  </section>
	 <!-- script ================================================== -->
  <script src="resources/js/jquery-1.11.0.min.js"></script>
  <script src="resources/js/plugins.js"></script>
  <script src="resources/js/script.js"></script>

  <!-- Bootstarp script ================================================== -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>

  <!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
  <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script> -->

  <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
	
  </body>

</html>