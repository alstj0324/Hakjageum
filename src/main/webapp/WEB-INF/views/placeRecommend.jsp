<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="templates/UseCSS.jsp" %>
  </head>
  <body class="bg-accent-light">
    <%@ include file="templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content banner-content-board">
        <div class="banner-content-banner">
          <h2>Place</h2>
        </div>
        <div class="place-header-content">
          <div class="place-city-content">
            <select class="select-list" name="si"></select>
            <select class="select-list" name="gun"></select>
            <button class="btn-place" id="btn-place-city">이동하기</button>
          </div>
          <div class="place-select-content">
            <select class="select-list" name="category">
              <option>카테고리 선택</option>
              <option value="cafe">카페</option>
              <option value="library">도서관</option>
            </select>
            <button class="btn-place" id="btn-place-select">표시하기</button>
          </div>
          <div class="place-search-content">
            <input type="text" class="search-input" id="place-search-input" placeholder="장소를 입력하세요.">
            <button class="btn-place" id="btn-place-search">검색하기</button>
          </div>
        </div>
        <div class="place-body-content">
          <div class="place-map" id="place-map"></div>
          <div class="map-result"></div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=pjn5n9vyj7"></script>
    <script>
      let map;
      let dataList = [], markerList = [], infoWindowList = [];

      $('#btn-place-select').click(function () {
        let code = $('select[name=category]').val();
        if (code === "카테고리 선택") return alert("카테고리를 선택해주세요.");

        for (let marker of markerList) {
            marker.setMap(null);
        }

        for (let infoWindow of infoWindowList) {
            infoWindow.setMap(null);
        }

        markerList = [];
        infoWindowList = [];
        createPlaceMap(code);
      });

      $('document').ready(function() {
        var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
        var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
        var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
        var area3 = ["대덕구","동구","서구","유성구","중구"];
        var area4 = ["광산구","남구","동구", "북구","서구"];
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
        $("select[name^=si]").each(function() {
          $selsido = $(this);
          $.each(eval(area0), function() {
            $selsido.append("<option value='"+this+"'>"+this+"</option>");
          });
          $selsido.next().append("<option value=''>구/군 선택</option>");
        });

        // 시/도 선택시 구/군 설정
        $("select[name^=si]").change(function() {
          var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
          var $gugun = $(this).next(); // 선택영역 군구 객체
          $("option",$gugun).remove(); // 구군 초기화

          if(area === "area0")
            $gugun.append("<option value=''>구/군 선택</option>");
          else {
            $.each(eval(area), function() {
              $gugun.append("<option value='"+this+"'>"+this+"</option>");
            });
          }
        });
      });

      $(async function () {
          let ip = await getMyIp();
          let loc = await getMyLoc(ip);
          initMap(loc);
      });

      function initMap(loc) {
          map = new naver.maps.Map('place-map', {
              center: new naver.maps.LatLng(loc['x'], loc['y']),
              zoom: 15
          });
      }

      function getMyIp() {
          return new Promise((resolve) => {
              $.getJSON("https://api64.ipify.org?format=json", function (json) {
                  resolve(json.ip);
              });
          })
      }

      function getMyLoc(ip) {
        let loc = {};
        $.ajax({
          type: "get",
          url: "/biz/api/loc/get/" + ip,
          dataType: "json",
          async: false,
          success: function (data) {
            console.log("Loc API > Get Loc 성공");
            loc = data;
          },
          error: function () {
            console.log("Loc API > Get Loc 실패");
          }
        });

        return loc;
      }

      function createPlaceMap(code) {
          let mapResult = $('.map-result');
          let mapCenter = map.getCenter();
          let placeList = getPlaceList(code, mapCenter.x, mapCenter.y);
          markerList = [];
          infoWindowList = [];

          mapResult.empty();
          for (let i = 0; i < placeList.length; i++) {
              let place = placeList[i];
              let marker = new naver.maps.Marker({
                  map: map,
                  title: place.name,
                  position: new naver.maps.LatLng(place.x, place.y)

              });

              let infoWindow = new naver.maps.InfoWindow({
                  content: createInfoWindow(place),
                  borderWidth: 0
              });

              let element = createResultItem(i, place);
              mapResult.append(element);

              markerList.push(marker);
              infoWindowList.push(infoWindow);
          }

          for (let i = 0; i < markerList.length; i++) {
              naver.maps.Event.addListener(markerList[i], 'click', getClickHandler(i));
          }

          $('.map-result > .place-result-item').click(function () {
              let id = $(this).attr('id');
              let seq = id.split('_')[1];
              let marker = markerList[seq];
              let infoWindow = infoWindowList[seq];

              let position = marker.position
              map.panTo(position, {duration: 200});

              if (infoWindow.getMap()) {
                  infoWindow.close();
              } else {
                  infoWindow.open(map, marker);
              }
          });
      }

      function createInfoWindow(place) {
          let content = []
          content.push("<div class='map-infowindow'>");
          content.push("  <h3>" + place.name + "</h3>");
          content.push("  <p>주소: " + place.address + "</p>");
          content.push("  <p>도로명주소: " + place.road_address + "</p>");
          if (place.phone !== "") content.push("  <p>번호: " + place.phone + "</p>");
          if (place.url !== "") content.push("  <a href='" + place.url + "'>위치 정보 보기</a>");
          content.push("</div>");

          return content.join("");
      }

      function createResultItem(seq, place) {
          let content = []
          content.push("<div class='place-result-item' id=marker_" + seq + ">");
          content.push("  <div class='result-header'>");
          content.push("    <h2>" + place.name + "</h2>");
          content.push("  </div>");
          content.push("  <div class='result-body'>");
          content.push("    <p>주소: " + place.address + "</p>");
          content.push("    <p>도로명주소: " + place.road_address + "</p>");
          content.push("  </div>");
          content.push("</div>");

          return content.join("");
      }

      function getPlaceList(code, x, y) {
        let placeList = [];

        if (x !== undefined && y !== undefined) {
          if (code === undefined) return console.log("잘못된 요청입니다.");
          $.ajax({
              type: "get",
              url: "/biz/api/place/" + code + "/" + x + "/" + y,
              dataType: "json",
              async: false,
              success: function (data) {
                  console.log("Place API > Get Place 성공");
                  placeList = data;
              },
              error: function () {
                  console.log("Place API > Get Place 실패");
              }
          });
        } else {
            console.log("x, y 좌표가 없습니다.");
        }

        return placeList;
      }

      function getClickHandler(seq) {
        return function(e) {
          let marker = markerList[seq];
          let infoWindow = infoWindowList[seq];

          if (infoWindow.getMap()) {
            infoWindow.close();
          } else {
            infoWindow.open(map, marker);
          }
        }
      }
    </script>
  </body>
</html>