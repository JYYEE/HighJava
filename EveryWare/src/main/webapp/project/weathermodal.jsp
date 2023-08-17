<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="../css/weathermodal.css">
<script defer src="https://use.fontawesome.com/releases/v5.15.2/js/all.js" integrity="sha384-vuFJ2JiSdUpXLKGK+tDteQZBqNlMwAjhZ3TvPaDfN9QmbPb7Q8qUpbSNapQev3YF" crossorigin="anonymous"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script> -->
<script type="text/javascript" src="../js/weathermodal.js"></script>

<script type="text/javascript">
$(function() {
	const today = new Date();
	const year = today.getFullYear();
	const month = today.getMonth() + 1;
	const date = today.getDate();
	const day = ["일", "월", "화", "수", "목", "금", "토"][today.getDay()];
	const code = `${year}년 ${month}월 ${date}일 ${day}요일`;
	
	$('#date-section').text(code);
})
</script>

</head>
<body>
	
	<a><span class="icon"><ion-icon	name="partly-sunny-outline" id="weatherbtn"></ion-icon></span></a>
	
	<div id="weathermodal" idx="3">
		<div id="info-section">
			<div id="date-section"><span></span></div>
			<div class="info"><span id="temp"></span><span id="country"></span><span id="weathericon"></span></div>
		</div>
		
		<div id="sidebar">
			<div id="details">
				<span id="weatherdetail">Weather Details</span><br><br>
				<div class="detail"><span>Max/Min Temp</span><span id="maxmintemp">10/20</span></div><br><br>
				<div class="detail"><span>Humidity</span><span id="humidity">40</span></div><br><br>
				<div class="detail"><span>Wind</span><span id="wind">10/20</span></div><br><br>
				<div class="detail"><span>Cloudy</span><span id="cloudy">40%</span></div><br><br>
			</div>
		</div>
	</div>
</body>
</html>