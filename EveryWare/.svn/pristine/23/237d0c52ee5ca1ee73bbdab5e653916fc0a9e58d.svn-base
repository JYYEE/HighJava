<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
submit = function(){
	tel = $('#tel').val()
	addr = $('#addr').val()
	
	$.ajax({
		url: '<%=request.getContextPath() %>/myPage.do',
		type: 'post',
		data: { "tel" : tel,
						"addr" : addr },
		success: function(res){
			if(res > 0){
				$('#snackbar').html('정상적으로 수정되었습니다.')
			} else{
				$('#snackbar').html('정상적으로 수정되지 않았습니다.<br>관리자에게 문의하세요.')
			}
			showSnackbar()
		},
		error: function(xhr){
			alert('상태 : ' + xhr.status)
		},
		dataType: 'json'
	})
}


	showSnackbar = function() {
		  // Get the snackbar DIV
		  var x = document.getElementById("snackbar");
		
		  // Add the "show" class to DIV
		  x.className = "show";
		
		  // After 3 seconds, remove the show class from DIV
		  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	}

</script>
<title>Insert title here</title>
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
form{
	width: 99%;
}
table{
   margin: 5px;
   border-collapse : collapse;
   width: 99%;
}
table td,
table th{
   border: 1px solid lightgray;
   height: 52px;
   padding: 0.5em;
   text-align: center;
}
th{
   background: #F3F3F3;
}
table th:first-child,
table td:first-child {
   border-left: 0;
}
table th:last-child,
table td:last-child {
   border-right: 0;
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
	margin: 30px auto;
  text-align: center;
}
input[type=text]{
	width: 90%;
  height: 35px;
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
.info-cont{
	text-align: left;
	padding-left: 20px;
}
.menu-li.info{
	background: #D1FFEC;
	border-radius: 15px 15px 15px 15px;
}
hr{
	height: 1px;
	background: lightgray;
	border: 0;
}
</style>
</head>
<body>
<%
	/* EmployeesVO vo = (EmployeesVO)session.getAttribute("vo");

	String name = vo.getEmp_name();
	String date = vo.getEmp_date();
	String dept = vo.getDept_name();
	String position = vo.getPosition_name();
	String mail = vo.getEmp_mail();
	String tel = vo.getEmp_tel();
	String addr = vo.getEmp_addr(); */
	
	EmployeesVO vo = (EmployeesVO)session.getAttribute("userVO");
	
	String name = vo.getEmp_name();
	String date = vo.getEmp_date();
	String dept = vo.getDept_name();
	String position = vo.getPosition_name();
	String mail = vo.getEmp_mail();
	String tel = vo.getEmp_tel();
	String addr = vo.getEmp_addr();
%>
<div class="main">
	<div class="include">
		<jsp:include page="mypage.jsp"></jsp:include>
		<%-- <%@include file="myprofile.jsp" %> --%>
	</div>
	<div class="container">
	<h2 class="currentMenu">내 정보</h2>
	<hr>
		<form action="<%=request.getContextPath() %>/myPage.do" method="post">
			<input type="hidden" name="id">
			<table>
				<tr>
					<td>이름</td>
					<td class="info-cont"><span id="name"><%=name %></span></td>
				</tr>
				<tr>
					<td>부서</td>
					<td class="info-cont"><span id="dept"><%=dept %></span></td>
				</tr>
				<tr>
					<td>직급</td>
					<td class="info-cont"><span id="position"><%=position %></span></td>
				</tr>
				<tr>
					<td>입사일자</td>
					<td class="info-cont"><span id="hdate"><%=date %></span></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td class="info-cont"><span id="mail"><%=mail %></span></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td class="info-cont"><input id="tel" type="text" name="tel" value="<%=tel %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td class="info-cont"><input id="addr" type="text" name="addr" value="<%=addr %>"></td>
				</tr>
			</table>
			<div class="btn-wrap">
				<span class="btn reset-btn" onclick="location.reload()">취소</span>
				<span class="btn submit-btn" onclick="submit()">수정</span>
			</div>
		</form>
	</div>
</div>
<div id="snackbar"></div>
</body>
</html>