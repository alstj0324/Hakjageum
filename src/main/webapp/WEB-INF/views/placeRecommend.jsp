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
            <select class="select-list" name="si">
              <option value=''>시/도 선택</option>
            </select>
            <select class="select-list" name="gun" disabled>
              <option value=''>구/군 선택</option>
            </select>
            <select class="select-list" name="dong" disabled>
              <option value=''>동/읍/면 선택</option>
            </select>
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
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=wrf3efe7wg"></script>
    <script>
      let map;
      let geo = {};
      let dataList = [], markerList = [], infoWindowList = [];

      // 페이지 로딩
      $(function () {
          $.ajax({
              type: "get",
              url: "/biz/resources/MapGeo.json",
              dataType: "json",
              async: false,
              success: function (data) {
                  console.log("Get Geo Data 성공");
                  geo = data;
              },
              error: function () {
                  console.log("Get Geo Data 실패");
              }
          });
          for (let si in geo) {
              let option = "<option value='" + si + "'>" + si + "</option>";
              $('select[name=si]').append(option);
          }

          let ip =  getMyIp();
          if (!ip) {
              alert('당신의 IP를 조회할 수 없습니다.\n이전 페이지로 돌아갑니다.');
              return history.back();
          }
          let loc = getMyLoc(ip);
          initMap(loc);
      });

      // 구/군 데이터
      $('select[name=si]').change(function () {
          let si = $('select[name=si]').val();
          let select_gun = $('select[name=gun]');
          if (si === "") {
              select_gun.attr("disabled", true);
              $('select[name=dong]').attr("disabled", true);
          } else select_gun.attr("disabled", false);
          select_gun.empty();
          select_gun.append("<option value=''>구/군 선택</option>");
          for (let gun in geo[si]) {
              let option = "<option value='" + gun + "'>" + gun + "</option>";
              select_gun.append(option);
          }
      });

      // 동/읍/면 데이터
      $('select[name=gun]').change(function () {
          let si = $('select[name=si]').val();
          let gun = $('select[name=gun]').val();
          let select_dong = $('select[name=dong]');
          if (gun === "") select_dong.attr("disabled", true);
          else select_dong.attr("disabled", false);
          select_dong.empty();
          select_dong.append("<option value=''>동/읍/면 선택</option>");
          for (let dong in geo[si][gun]) {
              let option = "<option value='" + dong + "'>" + dong + "</option>";
              select_dong.append(option);
          }
      });

      $('#btn-place-city').click(function () {
          let si = $('select[name=si]').val();
          let gun = $('select[name=gun]').val();
          let dong = $('select[name=dong]').val();
          if (si === "") return alert("시/도를 선택해주세요.");
          if (gun === "") return alert("구/군을 선택해주세요.");
          if (dong === "") return alert("동/읍/면을 선택해주세요.");
          let x = geo[si][gun][dong]['x'];
          let y = geo[si][gun][dong]['y'];

          let coord = new naver.maps.LatLng(y, x);
          map.panTo(coord, {duration: 200});
      })

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

          let placelist = getPlaceList(code);
          createPlaceMap(placelist);
      });

      $('#palce-search-input').keydown(function (key) {
          if (key.keyCode === 13) {
              $('#btn-place-search').click();
          }
      });

      $('#btn-place-search').click(function () {
          let keyword = $('#place-search-input').val();
          let placelist = getPlaceSearchList(keyword);
          createPlaceMap(placelist)
      })

      function initMap(loc) {
          map = new naver.maps.Map('place-map', {
              center: new naver.maps.LatLng(loc['x'], loc['y']),
              zoom: 15
          });
      }

      function getMyIp() {
          let ip = "";
          $.ajax({
              type: "get",
              url: "https://api64.ipify.org?format=json",
              dataType: "json",
              async: false,
              success: function (data) {
                  console.log("IP API > Get IP 성공");
                  ip = data.ip;
              },
              error: function () {
                  console.log("IP API > Get IP 실패");
              }
          })
          return ip;
      }

      function getMyLoc(ip) {
        let loc = {};
        $.ajax({
          type: "get",
          url: "/biz/api/loc/get",
          dataType: "json",
          data: {
              ip: ip
          },
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

      function createPlaceMap(placelist) {
          let mapResult = $('.map-result');
          markerList = [];
          infoWindowList = [];

          mapResult.empty();
          for (let i = 0; i < placelist.length; i++) {
              let place = placelist[i];
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
          if (place.url !== "") content.push("  <a href='" + place.url + "' target='_blank'>위치 정보 보기</a>");
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

      function getPlaceList(code) {
        if (code === undefined) return console.log("잘못된 요청입니다.");
        let placeList = [];
        let center = map.getCenter();
        $.ajax({
            type: "get",
            url: "/biz/api/place/" + code,
            dataType: "json",
            async: false,
            data: {
                x: center.x,
                y: center.y
            },
            success: function (data) {
                console.log("Place API > Get Place 성공");
                placeList = data;
            },
            error: function () {
                console.log("Place API > Get Place 실패");
            }
        });

        return placeList;
      }

      function getPlaceSearchList(keyword) {
        if (keyword === "") return console.log("잘못된 요청입니다.");
        let placeList = [];

        let center = map.getCenter();
        $.ajax({
            type: "get",
            url: "/biz/api/place/search",
            dataType: "json",
            data: {
                keyword: keyword,
                x: center.x,
                y: center.y
            },
            async: false,
            success: function (data) {
                console.log("Place API > Get Place Search 성공");
                placeList = data;
            },
            error: function () {
                console.log("Place API > Get Place Search 실패");
            }
        });

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