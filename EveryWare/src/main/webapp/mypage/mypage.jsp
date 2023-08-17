<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
<%
	EmployeesVO vo = (EmployeesVO)session.getAttribute("userVO");
	String empId = vo.getEmp_id();
	String empName = vo.getEmp_name();
%>
$(function(){
	$('.menu-li').on('click', function(){
		url = $(this).attr('value');
		location.href=url
	})
})

</script>
<style type="text/css">
*{
	box-sizing: border-box;
}
.wrap{
	display: flex;
	flex-direction: row;
}
.profile{
	border: 1px solid lightgray;
	width: 320px;
	height: 500px;
	display: flex;
	flex-direction: column;
	border-radius: 5px;
	box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.2);
	padding: 10px;
}
.profile *{
	border: none;
}
ul{
	list-style-type: none;
	padding-left: 0;
}
#profile-photo{
	width: 100px;
	height: 100px;
	border-radius: 10px;
	object-fit: cover;
}
.currentMenu{
	margin-left: 6px;
	font-size: calc(1.325rem + .9vw);
	margin-top: 0;
  margin-bottom: 0.5rem;
  font-weight: 500;
  line-height: 1.2;
}
.menu-li{
	margin: 10px 0px;
	padding: 10px;
	font-weight: 500;
	cursor: pointer;
}
.menu-li:hover{
	background: #F3F3F3;
	border-radius: 15px 15px 15px 15px;
}
h2{
	padding-left: 5px;
}
.profile-bottom{
	text-align: center;
	position: relative;
  top: 180px;
	color: #5e5e5e;
}
.profile-bottom span:hover{
	text-decoration: underline;
	cursor: pointer;
}
.profile-bottom span{
	display: inline-block;
  width: 120px;
}
a{
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
<div class="profile">
	<div class="profile-header">
		<h2><%=empName %>님 안녕하세요!</h2>
		<!-- <div>
			<img id="profile-photo" src="./resources/img/profile.jpg" alt="profile">
		</div> -->
	</div>
	<div class="profile-left">
		<ul class="profile-menu">
			<li class="menu-li info" value="myinfo.jsp">내 정보</li>
			<!-- <li class="menu-li alarm">내 알림</li> -->
			<li class="menu-li sign" value="mysignature.jsp">내 도장</li>
			<li class="menu-li log" value="mylog.jsp">접속이력</li>
		</ul>
	</div>
	<div class="profile-bottom">
		<span>관리자 문의</span>
		<span>로그아웃</span>
	</div>
</div>
</body>
</html>