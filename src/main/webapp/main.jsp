<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
  <head>
    <title>학자금, 학생에게 자기계발은 금이다.</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="WEB-INF/views/templates/UseCSS.jsp" %>
  </head>
  <body>
    <%@ include file="WEB-INF/views/templates/Header.jsp" %>
    <section id="billboard" class="position-relative">
      <div class="banner-content content-light style1 text-center col-md-6">
        <h2 class="banner-title">Self-development</h2>
        <div class="btn-center">
          <a href="bookRecommend.do" class="btn btn-medium btn-light">Start Now</a>
        </div>
      </div>
    </section>
    <%@ include file="WEB-INF/views/templates/UseJS.jsp" %>
  </body>
</html>