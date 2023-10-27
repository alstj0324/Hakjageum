<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=pjn5n9vyj7"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

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
        <div class="map-content">
          <div class="place-map" id="place-map" style="width: 100%; height: 100%;"></div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
    <script>
        let map = new naver.maps.Map('place-map', {
            center: new naver.maps.LatLng(37.47781493431073, 127.06229861277323),
            zoom: 1
        });

        $.getJSON('http://localhost:8080/biz/resources/MapGeo.json', function(data) {
            return data.responseJSON;
        }).then(datalist => {
            let stage1Keys = Object.keys(datalist);
            for (let stage1key of stage1Keys) {
                let stage1 = datalist[stage1key];
                let stage2Keys = Object.keys(stage1);
                for (let stage2key of stage2Keys) {
                    let stage2 = stage1[stage2key];
                    let stage3Keys = Object.keys(stage2);
                    for (let stage3key of stage3Keys) {
                        console.log(stage2[stage3key])
                        let x = stage2[stage3key]["x"];
                        let y = stage2[stage3key]["y"];
                        let radius = stage2[stage3key]["radius"];
                        new naver.maps.Circle({
                            map: map,
                            center: new naver.maps.LatLng(y, x),
                            radius: radius,
                            strokeColor: '#5347AA',
                            strokeOpacity: 0.5,
                            strokeWeight: 1,
                            fillColor: '#E51D1A',
                            fillOpacity: 0.3
                        })
                    }
                }
            }
        });
    </script>
  </body>
</html>