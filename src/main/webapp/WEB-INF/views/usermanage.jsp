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
      <div class="banner-content banner-content-manage">
        <div class="manage-header">
        	<div class="manage-id" id="man-h">아이디</div>
        	<div class="manage-nickname" id="man-h">닉네임</div>
        	<div class="manage-email" id="man-h">이메일</div>
        	<div class="manage-provider" id="man-h">계정구분</div>
        	<div class="manage-regdate" id="man-h">등록일</div>
        	<div class="manage-role" id="man-h">등급</div>
        	<div class="manage-manage" id="man-h">매니지먼트</div>
        </div>
        <div class="manage-container">
        	<c:forEach items="${userList }" var="userList">
        		<form action="userRoleupdate.do" method="post">
        			<input type="hidden" name="id" value="${userList.id }">
		        	<div class="manage-id" id="man-c">${userList.id }</div>
		        	<div class="manage-nickname" id="man-c">${userList.nickname }</div>
		        	<div class="manage-email" id="man-c">${userList.email}</div>
		        	<div class="manage-provider" id="man-c">${userList.provider }</div>
		        	<div class="manage-regdate" id="man-c">${userList.create_at }</div>
		        	<div class="manage-role" id="man-c">
		        		<c:if test="${userList.role_id eq '0'}">
		        			<select name="role_id">
		        				<option value="0" selected>일반</option>
		        				<option value="1">관리자</option>
		        			</select>
		        		</c:if>
		        		<c:if test="${userList.role_id ne '0'}">
		        			<select name="role_id">
		        				<option value="1" selected>관리자</option>
		        				<option value="0">일반</option>
		        			</select>
		        		</c:if>
		        	</div>
		        	<div class="manage-manage" id="man-c">
		        		<div class="change" id="child">
		        			<input type="submit" value="등급변경">
		        		</div>
		        		<div class="delete" id="child">
		        			<a href="userdelete.do?id=${userList.id }">X</a>
		        		</div>
		        	</div>
	        	 </form>
        	</c:forEach>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>