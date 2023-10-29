/*회원가입 유효성 검사*/	
function idCheck() {
    var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
    if($("#id").val() == ""){
          alert("아이디 입력바람");
          $("#id").focus();
          return false;
        }
    if(($("#id").val()).search(/\s/) != -1) {
          alert("입력값 내에 공백이 존재합니다!");
       $("#nickname").focus();
          return false; 
       }  		          
       if(!getCheck.test($("#id").val())){
         alert("형식에 맞게 입력해주세요");
         $("#id").val("");
         $("#id").focus();
         return false;
       }
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
                  $("#id-check-text").css("color","rgb(255, 139, 133)");    
               }else if(data === 'True'){
                   if (confirm("사용 가능한 아이디입니다! \n 사용하시겠습니까?") == true){    //확인
                       $("#id").attr("readonly",true);        
                       $("#idcheckValue").val("Y");
                       $("#email").focus();
                       $("#id-check-text").text("사용가능한 아이디입니다!");
                         $("#id-check-text").css("color","lightgreen");
                   }else{
                       $("#id").attr("readonly",false);  
                       $("#id").focus();
                       $("#id-check-text").text("아이디 중복체크가 필요합니다!");
                         $("#id-check-text").css("color","rgb(255, 139, 133)");   
                   }     
               }
           },
           error:function(request, status, error){

               alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
           }
       })
   }  
/*-----------------------------------------------------------------------------------*/ 
var code = "";
function emailCheck() {
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    if($("#email").val() == ""){
          alert("이메일을 입력해주세요");
          $("#email").focus();
          return false;
        }
    if(($("#email").val()).search(/\s/) != -1) {
          alert("입력값 내에 공백이 존재합니다!");
       $("#email").focus();
          return false; 
       } 
       if(!getMail.test($("#email").val())){
         alert("이메일형식에 맞게 입력해주세요")
         $("#email").val("");
         $("#email").focus();
         return false;
       }
       alert("잠시만 기다려주세요...");
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
/*-----------------------------------------------------------------------------------*/ 
function nickCheck() {
    var getCheck= RegExp(/^[A-Za-z0-9가-힣]{2,15}$/);
    if($("#nickname").val() == ""){
          alert("닉네임 입력바람");
          $("#nickname").focus();
          return false;
        }
    if(($("#nickname").val()).search(/\s/) != -1) {
          alert("입력값 내에 공백이 존재합니다!");
       $("#nickname").focus();
          return false; 
       } 
       if(!getCheck.test($("#nickname").val())){
         alert("형식에 맞게 입력해주세요");
         $("#nickname").val("");
         $("#nickname").focus();
         return false;
       }
    $.ajax({
           type:"get",
           url:"nickCheck.do",
           dataType:"text",
           data : {                       // 매개변수로 전달할 데이터
               "nickname" : $('#nickname').val()  // 아이디값
           },
           success: function(data){
               console.log("통신성공");
               
               if(data === 'False'){
                   alert("사용 불가능한 닉네임입니다!")
                   $("#nickname").val("");
                   $("#nickname").focus();
                   $("#nick-check-text").text("닉네임 중복체크가 필요합니다!");
                  $("#nick-check-text").css("color","rgb(255, 139, 133)");    
               }else if(data === 'True'){
                   if (confirm("사용 가능한 닉네임입니다! \n 사용하시겠습니까?") == true){    //확인
                       $("#nickname").attr("readonly",true);        
                       $("#nickcheckValue").val("Y");
                       $("#pwd").focus();
                       $("#nick-check-text").text("사용가능한 닉네임입니다!");
                        $("#nick-check-text").css("color","lightgreen");
                   }else{   
                       $("#nickname").attr("readonly",false);
                       $("#nickname").focus();
                       $("#nick-check-text").text("닉네임 중복체크가 필요합니다!");
                        $("#nick-check-text").css("color","rgb(255, 139, 133)");   
                   }     
               }
           },
           error:function(request, status, error){

               alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
           }
       })
   }
/*-----------------------------------------------------------------------------------*/ 
var pwdCheckform= RegExp(/^[A-Za-z0-9`~!@@#$%^&*|₩₩₩'₩";:₩/?]{4,}$/);

$(function(){
   /*패스워드 1차체크*/
   $("#pwd2").focusin(function(){
       if($("#pwd").val() == ""){
           alert("패스워드 입력바람"); 
           $("#pwd").val("").focus();
           return false;
         }
         if(($("#pwd").val()).search(/\s/) != -1) {
           alert("입력값 내에 공백이 존재합니다!");
          $("#pwd").focus();
           return false; 
        } 
        if(!pwdCheckform.test($("#pwd").val())){
          alert("형식에 맞게 입력해주세요");
          $("#pwd").focus();
          return false;
        }
   }); 

   /*패스워드 2차체크*/
   $("#pwd2").keyup(function(){
       if(($("#pwd").val())===($("#pwd2").val())){
           $("#pwd-check-text").text("비밀번호가 일치합니다!");
           $("#pwd-check-text").css("color","lightgreen");
           $("#pwdcheckValue").val("Y");
       }else{
           $("#pwd-check-text").text("비밀번호가 일치하지 않습니다!");
            $("#pwd-check-text").css("color","rgb(255, 139, 133)"); 
            $("#pwdcheckValue").val("N");
       }
   });
}); 
/*-----------------------------------------------------------------------------------*/
function check(){
    if($("#idcheckValue").val() != "Y"){
     alert("아이디 입력값이 유효하지 않습니다");
     $("#id").val("").focus();
     return false;
    } 
    if($("#emailcheckValue").val() != "Y"){
     alert("이메일 입력값이 유효하지 않습니다!");
     $("#email").val("").focus();
     return false;
    }
    if($("#nickcheckValue").val() != "Y"){
     alert("닉네임 입력값이 유효하지 않습니다!");
     $("#nickname").val("").focus();
     return false;
    }
    if($("#pwdcheckValue").val() != "Y"){
     alert("패스워드 입력값이 유효하지 않습니다!");
     $("#pwd").val("").focus();
     return false;
    }
  }