<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
      <div class="banner-content banner-content-login">
        <div class="login-wrap">
          <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
              <div class="sign-in-htm">
                <form action="login.do" method="post">
                  <div class="group">
                    <label for="user" class="label" id="user-label">ID</label>
                    <input id="user" name="id" type="text" class="input">
                  </div>
                  <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" name="pwd"type="password" class="input" data-type="password">
                  </div>
                  <div class="group">
                    <input type="submit" class="button" value="Sign In">
                  </div>
                </form>
                <div class="hr"></div>
                <div class="foot-lnk">
                  <a href="#forgot">Forgot Password?</a>
                </div>
                <div class="login-api">
                  <a href="naverlogin.do"><img class="login-image" src="resources/images/loginimg/naver_login.png"/></a><br><br>
                  <a href="kakaologin.do"><img class="login-image" src="resources/images/loginimg/kakao_login.png"/></a>
                </div>
              </div>
              <script type="text/javascript">
	              function idCheck() {
	            	  var textdiv1 = document.getElementById('id-check-text');
	            	  $.ajax({
                          type:"get",
                          url:"idCheck.do",
                          dataType:"text",
                          data : {                       // 매개변수로 전달할 데이터
                              "id" : $('#id').val()  // 아이디값
                          },
                          success: function(data){
                              console.log("통신성공");
                              
                              if(data === 'False'){
                                  alert("사용 불가능한 아이디입니다!")
                                  $("#id").val("");
                                  $("#id").focus();
                                  $("#id-check-text").text("아이디 중복체크가 필요합니다!");
      	            			  $("#mail-check-text").css("color","rgb(255, 139, 133)");    
                              }else if(data === 'True'){
                                  alert("사용 가능한 아이디입니다!")
                                  $("#id").attr("readonly",true);        
                                  $("#idcheckValue").val("Y");
                                  $("#email").focus();
                                  $("#id-check-text").text("사용가능한 아이디입니다!");
      	            			  $("#id-check-text").css("color","lightgreen");
                              }
                          },
                          error:function(request, status, error){

                      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                          }
                      })
                  }
	              var code = "";
	              function emailCheck() {
	            	  var email = $("#email").val();
	            	  $.ajax({
	            	        type:"GET",
	            	        url:"emailCheck.do?email=" + email,
	            	        cache : false,
	            	        success:function(data){
	            	        	if(data == "error"){
	            	        		alert("이메일 주소가 올바르지 않습니다. 유효한 이메일 주소를 입력해주세요.");
	            	        		$("#email").val("");
	            					$("#email").attr("autofocus",true);
	            					
	            	        	}else{	        		
	            	        		alert("이메일로 인증코드가 발송되었습니다!")
	            					$("#emailCheck").css('visibility','visible');
	            	        		code = data;
	            	        	}
	            	        }
	            	    });  
	              }
	              
	              function emailCheckbtn(){
	            	  if($("#emailcode").val() == code){
	            		    alert("인증번호가 일치합니다.");
	            			$("#mail-check-text").text("인증번호가 일치합니다.");
	            			$("#emailcheckValue").val("Y");
	            			$("#mail-check-text").css("color","lightgreen");
	            			$("#email").attr("readonly",true); 
	            			$("#emailCheck").css('visibility','hidden'); 
	            			$("#nickname").focus();
	            		}else{
	            			alert("인증번호가 일치하지 않습니다 다시 시도해주세요.");
	            			$("#emailCheck").css('visibility','hidden'); 
	            		}
	              }
	              
	              function emailCheckClose(){
	            	  $("#emailCheck").css('visibility','hidden');
	            	  $("#email").val("");
  					  $("#email").attr("autofocus",true);
	              }
	              
	              
	              
              </script>
              <div class="sign-up-htm">
                <form action=signin.do method=post>
                  <input type="hidden" name="provider" value="local">
                  <div class="group">
                    <label for="id" class="label">ID</label>
                    <input id="id" name="id" type="text" class="input" required>
                    <button type="button" class="id-check-button" id="check-button" onClick="idCheck()">중복검사</button>
                    <div class="id-check-text" id="id-check-text" >아이디 중복체크가 필요합니다!</div>
                    <input type="hidden" id="idcheckValue" value="N">
                  </div>
                  <div class="group" id="group1">
                    <label for="email" class="label">Email Address</label>
                    <input id="email" name="email" type="text" class="input" required>
                    <div class="mail-check-button" id="check-button" onClick="emailCheck()">이메일<br>인증</div>
                    <div class="mail-check-text" id="mail-check-text">이메일 인증이 필요합니다!</div>
                    <input type="hidden" id="emailcheckValue" value="N">
                  </div>
                  <div class="group">
                    <label for="nickname" class="label">Nickname</label>
                    <input id="nickname" name="nickname" type="text" class="input" required>
                    <button type="button" class="nick-check-button" id="nick-check-button" onClick="idCheck()">중복검사</button>
                    <div class="nick-check-text" id="nick-check-text">닉네임 중복체크가 필요합니다!</div>
                  </div>
                  <div class="group">
                    <label for="pwd" class="label">Password</label>
                    <input id="pwd" name="pwd" type="password" class="input" data-type="password" required>
                  </div>
                  <div class="group">
                    <label for="pwd2" class="label">Password Check</label>
                    <input id="pwd2" name="pwd2" type="password" class="input" data-type="password" required>
                    <div class="pwd-check-text" id="pwd-check-text">비밀번호가 일치합니다</div>
                  </div>
                  <div class="group">
                    <input type="submit" class="button" id="loginbtn" value="Sign Up">
                  </div>
                </form>
              </div>
              <div class="emailCheck" id="emailCheck">
              	<div class="emailDoc">
              		이메일 인증
              	</div>
              	<div class="emailCodeBox">
              		<input type="text" id="emailcode" placeholder="인증 코드 입력">
              	</div>
              	<div class="emailCodeSubmit">
              		<button type="button" id="emailCheckbtn" onClick="emailCheckbtn()">인증하기</button>
              	</div>
              	<button type="button" id=emailCheckClose onClick="emailCheckClose()">닫기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <%@ include file="templates/UseJS.jsp" %>
  </body>
</html>