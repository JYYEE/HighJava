<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
count = 0;

$(function(){
	
	$.listPageServer = function(){
		$.ajax({
			url: `<%=request.getContextPath() %>/logList.do`,
			type: 'get',
			data: { "count" : count },
			success: function(res){
				if(res.length == 0){
					showSnackbar();
				}
				console.log(res);
				code = "";
				$.each(res, function(i, v){
					console.log(v.rnum);
					code += '<tr><td>'+v.rnum+'</td><td>'
									+v.log_time+'</td><td>'
									+v.log_ip+'</td><td>'
									+v.log_browser+'</td></tr>';
				})
				
				$('#resultTable').append(code);
				
				count += 10;
				
			}, //success 끝
			error: function(xhr){
				alert('상태: ' + xhr.status);
			},
			dataType: 'json'
		})
	}
	
	$.listPageServer();
	
	$('#more-btn').on('click', function(){
		$.listPageServer();
	})
	
	showSnackbar = function() {
	  // Get the snackbar DIV
	  var x = document.getElementById("snackbar");
	
	  // Add the "show" class to DIV
	  x.className = "show";
	
	  // After 3 seconds, remove the show class from DIV
	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
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
table{
   margin: 5px;
   border-collapse : collapse;
   width: 99%;
}
table td,
table th{
   border: 1px solid lightgray;
   height: 30px;
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
#more-btn{
	cursor: pointer;
	border-radius: 5px;
	padding: 5px 19px;
	display: block;
	text-align: center;
	border-color: #04AA6D;
  color: #04AA6D;
  margin-top: 10px;
  box-shadow: 0px 2px 2px 0px rgba(0,0,0,0.2);
}
#no{
	width: 5%;
	min-width: 50px;
}
#datetime{
	width: 25%;
}
#ip{
	width: 35%;
}
#browser{
	width: 25%;
}
.container{
	width: 100%;
	margin: 20px 20px 0px 46px;
}
.menu-li.log{
	background: #D1FFEC;
	border-radius: 15px 15px 15px 15px;
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
hr{
	height: 1px;
	background: lightgray;
	border: 0;
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
	<h2 class="currentMenu">접속이력</h2>
	<hr>
		<table>
			<tr>
				<th id="no">번호</th>
				<th id="datetime">일시</th>
				<th id="ip">IP</th>
				<th id="browser">브라우저</th>
			</tr>
			<tbody id="resultTable"></tbody>
		</table>
		<span id="more-btn">더보기</span>
	</div>
</div>
<div id="snackbar">마지막 페이지입니다.</div>
</body>
</html>