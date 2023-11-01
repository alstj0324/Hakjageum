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
      <div class="banner-content banner-content-update">
        <div class="user-update-form">
          <h2>회원관리</h2>
          <c:if test="${user != null }">
          <form action=userupdate.do method="post">
            <input type="hidden" name="id" value="${user.id }">
            <!--
            	Id ${user.id }
            	Password <input type=text name=pwd value="${user.pwd }" >
            	Nickname <input id="nickName" type=text name=nickname value="${user.nickname }" disabled>
            	<button type="button" id="nickNameChangeButton">변경</button>
            	Email <input type=text name=email value="${user.email }" >
            	Provider ${user.provider }
            	RegisterDate ${user.create_at }
            	<td colspan="2" id="userupdate-submit"><input type=submit value=회원수정></td>
            	
            	 <label for="pwd2" class="label">Password Check</label>
                 <input id="pwd2" name="pwd2" type="password" class="input" placeholder="4자 이상 영문,숫자,특수문자 입력" data-type="password" >
                 <div class="pwd-check-text" id="pwd-check-text">비밀번호가 일치하지 않습니다!</div>
                 <input type="hidden" id="pwdcheckValue" value="N">
             -->
          	<div class="userupdateBox">
          		<span>Id : </span><span class="update-box-userId">${user.id }</span>
          	</div>
          	<div class="userupdateBox updateBox-nickName">
          		<span>Nickname : </span>
          		<input id="nickname" type=text name=nickname value="${user.nickname }" readonly>
          		<button type="button" id="nickNameChangeButton">변경하기</button>
          		<button type="button" id="nickNameCheck" style="display: none;" onClick="nickCheck()">중복검사</button>
          		<button type="button" id="nickNameCheckCancel" style="display: none;">취소</button>
          		<div class="nick-check-text updateBox-check" id="nick-check-text" style="display:none">닉네임 중복체크가 필요합니다!</div>
          		<input type="hidden" id="nickcheckValue" value="N">
          	</div>
          	<div class="userupdateBox">
          		<span>Password : </span>
          		<input id="pwd" type=password name=pwd value="${user.pwd }" readonly>
          		<button type="button" id="passwordChangeButton">변경하기</button>
          		<button type="button" id="passwordCancel" style="display: none;">취소</button>
          	</div>
          	<div class="userupdateBox" id="passwordCheckBox" style="display: none;">
          		<span>Check : </span>
          		<input id="pwd2" type=password name=pwd2 value="" >
          		<div class="pwd-check-text updateBox-check" id="pwd-check-text" >비밀번호가 일치하지 않습니다!</div>
          		<input type="hidden" id="pwdcheckValue" value="N">
          	</div>
          	<div class="userupdateBox">
          		<span>Email : </span>
          		<input type=text name=email value="${user.email }" disabled>
          	</div>
          	<div class="userupdateBox">
          		<span>Provider : </span><span class="update-box-userId">${user.provider }</span>
          	</div>
          	<div class="userupdateBox">
          		<span>RegisterDate : </span><span class="update-box-userId">${user.create_at }</span>
          	</div>
          	<div class="userupdateBox">
          		<input type="submit" value="저장하기" class="userupdateBox-submit">
          	</div>
          </form>
          </c:if>
        </div>
      </div>
   	  <%@ include file="templates/UseJS.jsp" %>
      <script>
      	const nickName = document.getElementById("nickname");
      	const password = document.getElementById("pwd");
      	const password2 = document.getElementById("pwd2");
      	const nickNameCB = document.getElementById("nickNameChangeButton");
      	const passwordCB = document.getElementById("passwordChangeButton");
      	const passwordCancel = document.getElementById("passwordCancel");
      	const nickNameC = document.getElementById("nickNameCheck");
      	const nickNameCC = document.getElementById("nickNameCheckCancel");
      	const nickcheckValue = document.getElementById("nickcheckValue");
      	const nickcheckTest = document.getElementById("nick-check-text");
      	const passwordcheckTest = document.getElementById("pwd-check-text");
      	const passwordCheckBox = document.getElementById("passwordCheckBox"); 
      	const passwordcheckValue = document.getElementById("pwdcheckValue");
      	
      	checkModalButton.disabled = true; //이미 회원관리 페이지에 들어왔을때 내 정보 수정 못누르게 하기위해 만듦
      	
      	window.addEventListener("beforeunload", function (e){
      		checkModalButton.disabled = false; //회원 관리 페이지에서 나왔을때 다시 누를수 있게 만듦
      		
      		e.returnValue = "이 페이지를 나가시겠습니까?"; //한번 물어보기
      	});
      	
      	nickNameCB.addEventListener("click", function(){
      		nickName.style.border = "1px solid white";
      		nickNameCB.style.display = "none";
      		nickNameC.style.display = "inline-block";
      		nickNameCC.style.display = "inline-block";
      		nickcheckTest.style.display = "block";
      		nickcheckValue.value = 'N';
      		nickcheckTest.innerText = "닉네임 중복체크가 필요합니다!";
      		nickcheckTest.style.color = "";
      		nickName.removeAttribute("readonly");
      	});
      	nickNameCC.addEventListener("click", function(){
      		nickName.setAttribute("readonly","readonly");
      		nickNameC.style.display = "none";
      		nickNameCC.style.display = "none";
      		nickNameCB.style.display = "inline-block";
      		nickName.style.border = "0px";
      		nickName.value = "${user.nickname }"; //""를 안붙여주면 숫자는 상관없는데 문자열이 안됨
      		nickcheckTest.style.display = "none";
      	});
      	passwordCB.addEventListener("click", function(){
      		password.style.border = "1px solid white";
      		password.value = "";
      		passwordCancel.style.display = "inline-block";
      		passwordCB.style.display = "none";
      		passwordCheckBox.style.display = "block";
      		password2.style.border = "1px solid white";
      		passwordcheckTest.style.border = "block"
      		password2.value = "";
      		passwordcheckTest.style.color = "";
      		passwordcheckValue.value = "N";
      		passwordcheckTest.innerText = "비밀번호가 일치하지 않습니다!";
      		password.removeAttribute("readonly");
      		password2.removeAttribute("readonly");
      	});
      	passwordCancel.addEventListener("click", function(){
      		password.setAttribute("readonly","readonly");
      		passwordCheckBox.style.display = "none";
      		passwordCancel.style.display = "none";
      		password.style.display = "inline-block";
      		password.style.border = "0px";
      		password.value = "${user.pwd }"; //""를 안붙여주면 숫자는 상관없는데 문자열이 안됨
      		passwordCB.style.display = "inline-block";
      	});
      </script>
      <script type="text/javascript" src="resources/js/signin.js"></script>
    </section>
  </body>
</html>
