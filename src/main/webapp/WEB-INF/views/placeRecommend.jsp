<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=pjn5n9vyj7"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<html lang="ko">
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
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content banner-content-board">
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
            <div class="place-map" id="place-map"></div>
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
    </section>
    <%@ include file="templates/UseJS.jsp" %>
    <script>
        let map;
        let dataList = [], markerList = [], infoWindowList = [];

        $(function () {
            initMap();
            createCafeMap();
        });

        function initMap() {
            map = new naver.maps.Map('place-map', {
                center: new naver.maps.LatLng(37.47781493431073, 127.06229861277323),
                zoom: 15
            });
        }

        function createCafeMap() {
            let mapCenter = map.getCenter();
            let cafeList = getCafeList(mapCenter.x, mapCenter.y);
            markerList = [];
            infoWindowList = [];

            for (let i = 0; i < cafeList.length; i++) {
                let cafe = cafeList[i];
                let marker = new naver.maps.Marker({
                    map: map,
                    title: cafe.name,
                    position: new naver.maps.LatLng(cafe.x, cafe.y)
                });

                let infoWindow = new naver.maps.InfoWindow({
                    content: '<div style="width:200px; text-align:center; padding:10px;">' + cafe.name + '</div>'
                });

                markerList.push(marker);
                infoWindowList.push(infoWindow);
            };

            for (let i = 0; i < markerList.length; i++) {
                naver.maps.Event.addListener(markerList[i], 'click', getClickHandler(i));
            }
        }

        function getCafeList(x, y) {
            let cafeList = [];

            if (x != undefined && y != undefined) {
                $.ajax({
                    type: "get",
                    url: "/biz/api/place/cafe/" + x + "/" + y,
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        console.log("Place API > Get Cafe 성공");
                        cafeList = data;
                    },
                    error: function () {
                        console.log("Place API > Get Cafe 실패");
                    }
                });
            } else {
                console.log("x, y 좌표가 없습니다.");
            }

            return cafeList;
        }

        function getClickHandler(seq) {
            let marker = markerList[seq];
            let infoWindow = infoWindowList[seq];
            console.log(infoWindow)
            if (infoWindow.getMap()) {
                infoWindow.close();
            } else {
                infoWindow.open(map, marker);
                window.map.panTo(seq.coord);

                console.log(seq);
            }
        }
    </script>
  </body>
</html>