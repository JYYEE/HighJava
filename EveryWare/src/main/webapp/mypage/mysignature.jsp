<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
<%
	EmployeesVO vo = (EmployeesVO)session.getAttribute("userVO");
	String empId = vo.getEmp_id();
	/* String empId = "a001"; */
%>
mypath = '<%=request.getContextPath()%>';
empId = '<%=empId%>';

	
$(function(){
	
	$('#imageInput').change(function(event) {
    file = event.target.files[0];
    $('#previewImage').attr('src', URL.createObjectURL(file));
  });
	
	showSnackbar = function() {
	  // Get the snackbar DIV
	  var x = document.getElementById("snackbar");
		
	  // Add the "show" class to DIV
	  x.className = "show";
		
	  // After 3 seconds, remove the show class from DIV
	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	}
	
	submit = function(){
		form = $('#sign-form')[0];
	  formData = new FormData(form);
		
		$.ajax({
			url: mypath+'/updateSign.do',
			type: 'post',
			enctype: 'multipart/form-data',
			data: formData,
			success: function(res){
		 		/* var tmpDate = new Date();
		 		console.log(tmpDate.getTime());
				 $("#previewImage").attr("src", "./resources/img/sign/" + empId + "?v=" + tmpDate.getTime()); */
				showSnackbar();
			},
			error: function(xhr){
				alert('상태 : ' + xhr.status);
			},
			dataType: 'text',
			contentType: false,
			processData: false
		})
	}
	
})
</script>
<style type="text/css">
*{
	box-sizing: border-box;
}
.main{
	display: flex;
	flex-direction: row;
}
.container{
	width: 100%;
	margin: 20px 20px 0px 46px;
}
.img-box{
	width: 260px;
	height: 260px;
	margin: 0px auto;
	padding: 25px;
	border-radius: 16px;
  background: #F3F3F3;
}
.upload-btn{
	width: 43px;
  height: 43px;
	text-align: center;
	cursor: pointer;
	display: inline-block;
	-webkit-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
	font-size: 1.7em;
	border-radius: 100px;
	background: lightgreen;
	color: white;
	position: relative;
  bottom: 40px;
  left: 180px;
  box-shadow: 0px 2px 2px 0px rgba(0,0,0,0.2);
}
#imageInput{
	display: none;
}
form{
  width: 80%;
  user-select: auto;
  margin: 0px auto;
  justify-content: center;
}
#previewImage{
	width: 210px;
	height: 210px;
	margin: 0px auto;
	background: white;
}
.btn{
	cursor: pointer;
  padding-left: 60px;
  padding-right: 60px;
  border-radius: 5px;
  padding-top: 10px;
  padding-bottom: 10px;
/*    background-color: #04AA6D; */
  box-shadow: 0px 2px 2px 0px rgba(0,0,0,0.2);
}
.reset-btn{
	background-color: #04AA6D66;
	color: black;
	margin-right: 30px;
}
.submit-btn{
	background-color: #04AA6D;
  color: #ffffff;
}
.submit-btn:hover{
   background-color: #00894F;
}
.btn-wrap{
	text-align: center;
  height: 100px;
  padding: 36px;
}

/* The snackbar - position it at the bottom and in the middle of the screen */
#snackbar {
  visibility: hidden; /* Hidden by default. Visible on click */
  min-width: 250px; /* Set a default minimum width */
  margin-left: -125px; /* Divide value of min-width by 2 */
  background-color: #333; /* Black background color */
  color: #fff; /* White text color */
  text-align: center; /* Centered text */
  border-radius: 2px; /* Rounded borders */
  padding: 16px; /* Padding */
  position: fixed; /* Sit on top of the screen */
  z-index: 1; /* Add a z-index if needed */
  left: 50%; /* Center the snackbar */
  bottom: 30px; /* 30px from the bottom */
}
/* snackbar */
#snackbar.show {
  visibility: visible; /* Show the snackbar */
  /* Add animation: Take 0.5 seconds to fade in and out the snackbar.
  However, delay the fade out process for 2.5 seconds */
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}
@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}
.currentMenu{
	margin-left: 6px;
	font-size: calc(1.325rem + .9vw);
	margin-top: 0;
    margin-bottom: 0.5rem;
    font-weight: 500;
    line-height: 1.2;
}
.menu-li.sign{
	background: #D1FFEC;
	border-radius: 15px 15px 15px 15px;
}

</style>
</head>
<body>
<div class="main">
	<div class="include">
		<jsp:include page="mypage.jsp"></jsp:include>
		<%-- <%@include file="myprofile.jsp" %> --%>
	</div>
	<div class="container">
	<h2 class="currentMenu">내 도장</h2>
	<hr>
		<form id="sign-form">
			<div class="img-box">
				<img id="previewImage" src="<%=request.getContextPath() %>/mypage/img/sign/<%=empId %>?v=1" onerror="this.src='<%=request.getContextPath() %>/mypage/img/승인.png';">
				<label class="upload-btn" for="imageInput">&#x270E;</label>
			</div>
			<input type="file" id="imageInput" name="file" accept="image/*">
			<div class="btn-wrap">
				<span class="btn reset-btn" onclick="location.reload()">취소</span>
				<span class="btn submit-btn" onclick="submit()">적용</span>
			</div>
		</form>
	</div>
</div>

<!-- 이미지 등록 모달 -->
<div class="modal img-upload-modal">

<div id="snackbar">정상적으로 수정되었습니다.</div>
</div>
</body>
</html>