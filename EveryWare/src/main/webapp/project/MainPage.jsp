<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="kr.or.ddit.util.MybatisSqlSessionFactory"%>
<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0" /> -->
<title>EveryWare</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.15.2/js/all.js"
	integrity="sha384-vuFJ2JiSdUpXLKGK+tDteQZBqNlMwAjhZ3TvPaDfN9QmbPb7Q8qUpbSNapQev3YF"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<link rel="stylesheet" href="../css/MainsPgaetyle.css" />
<link rel="stylesheet" href="../weather/css/weatherstyle.css">
<script src="../js/jquery-3.6.4.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script> -->

<!-- <script src="../js/MainScripy.js"></script> -->

<script>
<%EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO");

String id = vo.getEmp_id();
String code = vo.getDept_id();
String name = vo.getEmp_name();
String code_names = vo.getDept_name();%>

window.history.forward();
function noBack(){window.history.forward();}

contextPath = '<%=request.getContextPath()%>';
$(function() {
	console.log('<%=vo.getEmp_vac()%>');
	// admin 계정으로 로그인시에만 관리자 항목을 보여줌
	$(document).ready(function() {
		  var id = '<%=vo.getEmp_id()%>';
		  //alert("접속id: " + id);
		  if (id == 'admin') {
		    $('#open-admin').css('display', 'block');
		    $('#open-email').css('display', 'none');
		    $('#open-doc').css('display', 'none');
		  }
		});
	
	
/*---------------- 보조 메뉴 -----------------------*/	
 	
/*결제 보조 메뉴 */ 
	 $('#open-doc').hover(function() {/* 올리면 메뉴 나옴 */
		    $('#doc-side-menu').show();
		  });
		  
	 $(document).click(function(event) {/* 이외에 다른 부분을 클릭했을 때 메뉴 사라짐  */
       if(!$(event.target).closest("#open-doc, #doc-side-menu").length) {
        $("#doc-side-menu").hide();
       }
    });
	 $('#doc-side1').click(function(){
		 $.MovePage("<%=request.getContextPath() %>/document/write");
	 })	 
	  $('#doc-side2').click(function(){
		  $.MovePage("<%=request.getContextPath() %>/document/received");
	 })
	  $('#doc-side3').click(function(){
		  $.MovePage("<%=request.getContextPath() %>/document/uploaded");
	 })
	  $('#doc-side4').click(function(){
		  $.MovePage("<%=request.getContextPath() %>/document/approval");
	 })
	  $('#doc-side5').click(function(){
		  $.MovePage("<%=request.getContextPath() %>/document/scrapped");
	 })
	 
/*HR 보조 메뉴  */	 
	 $('#open-hr').hover(function() {/* 올리면 메뉴 나옴 */
		    $('#hr-side-menu').show();
		  });
		  
	 $(document).click(function(event) {/* 이외에 다른 부분을 클릭했을 때 메뉴 사라짐  */
       if(!$(event.target).closest("#open-hr, #hr-side-menu").length) {
        $("#hr-side-menu").hide();
       }
    });
	 $('#hr-side1').click(function(){
		 $.MovePage("<%=request.getContextPath() %>/Attendances");
	 })	 
	  $('#hr-side2').click(function(){
		  $.MovePage("<%=request.getContextPath() %>/Vacations");
	 })
	 
/*-------------------------------------------------*/		
	$(".Everyware-logo").on("click", () => {
		
		setTimeout(function() {
			$(".rightcolumn .card").show()
		}, 200);
		setTimeout(function() {
			$(".comp").removeClass("open");
		}, 50);
		setTimeout(function() {
		$(".card.clo").removeClass("clo");	
		}, 300);
	});
/*---------------- 메뉴 버튼 눌렀을 때 iframe 링크 부여하는 곳 ---------------  */
	$.MovePage = function( move ) {
		setTimeout(function() {
     		$(".rightcolumn .card").addClass("clo");
     		$(".comp").removeClass("open");
   		}, 100);
	
   		setTimeout(function() {
     		$(".comp").addClass("open");
     		$(".comp").attr("src", move);
     		$(".rightcolumn .card").css("display","none");
   		}, 400);
  	} 

  	$("#open-schedule").on("click", function() {
  		$.MovePage("../schedule/Scheduler.jsp");
  	});

  	$("#open-posts").on("click", function() {
  		$.MovePage("<%=request.getContextPath()%>/postList.do");
  	});
  
 	<%-- $("#open-doc").on("click", function() {
  		$.MovePage("<%=request.getContextPath()%>/document/received");
  	}); --%>
  
  	$("#open-email").on("click", function() {
  		$.MovePage("<%=request.getContextPath()%>/emailList.do");
  	});
  
 <%--  	$("#open-hr").on("click", function() {
  		$.MovePage("<%=request.getContextPath()%>/Attendances");
  	}); --%>
  	
  	$("#open-admin").on("click", function() {
  		$.MovePage("<%=request.getContextPath() %>/AdminView.do");
  	});
	
	
/*---------------------------------------------------------------------------  */
    $("#weatherbtn").click(function() {
		if($("#weathermodal").css("display") === "none" ) {
			$("#weathermodal").show();
		} 
	}) 
	
	$(document).mouseup(function(e) {
		container = $('#weathermodal');
		
		if(container.has(e.target).length === 0) {
			container.css('display','none');
		}
	})
	
    $("#alarmbtn").click(function() {
		if($("#alarm-modal").css("display") === "none" ) {
			$("#alarm-modal").show();
		} 
	}) 
	
	$(document).mouseup(function(e) {
		container = $('#alarm-modal');
		
		if(container.has(e.target).length === 0) {
			container.css('display','none');
		}
	})
	
	 $("#mypagebtn").click(function() {
		if($("#mypage-modal").css("display") === "none" ) {
			$("#mypage-modal").show();
		} 
	}) 
	
	$(document).mouseup(function(e) {
		container = $('#mypage-modal');
		
		if(container.has(e.target).length === 0) {
			container.css('display','none');
		}
	})
	
	$(".hrbtn").on("click",function(){
	$("#currentTime").text("출근이 완료되었습니다. : "+getCurrentTime());
})

	$(".hrbtn2").on("click",function(){
	$("#currentTime").text("퇴근이 완료되었습니다. : "+getCurrentTime());
})
});
/*---------------------------------------------------------------------------  */


function getCurrentTime() {
	var now = new Date();
  	var hours = now.getHours();
  	var minutes = now.getMinutes();
  	var seconds = now.getSeconds();

	if (hours < 10) {
    	hours = "0" + hours;
  	}
	
  	if (minutes < 10) {
    	minutes = "0" + minutes;
  	}
  	
  	if (seconds < 10) {
    	seconds = "0" + seconds;
  	}

  	var timeString = hours + ":" + minutes + ":" + seconds;
  	return timeString;
}

// 1초마다 현재 시간을 업데이트하는 함수
function updateTime() {
  	var currentTimeString = getCurrentTime();
	document.getElementById("current-time").textContent = currentTimeString;
}
//출근버튼으로 근태 입력
$(document).on("click", ".hrbtn", function () {
	time = $("#currentTime").text();
	atttime = time.split(" ")[3]
	empId= '<%=id%>'
	console.log(atttime)
	
	$.ajax({
		  url : '<%=request.getContextPath()%>/Attendances/updateAtt',
		  type : 'get',
		  data : {"attTime" : atttime, "empId" : empId}, // empId는 세션에 저장된거 뽑아와야 함.
		  success : function(res){
			  //alert("출근 되었습니다.")
		  },
		  error : function(xhr){
			  //alert("상태 : " + xhr.status + "\ncode : "+ xhr.statust);
		  }
	  })
  })
//퇴근버튼으로 근태 입력
$(document).on("click", ".hrbtn2", function () {
	time = $("#currentTime").text();
	leavetime = time.split(" ")[3]
	console.log(leavetime)
	empId= '<%=id%>'
	
	$.ajax({
		  url : '<%=request.getContextPath()%>/Attendances/updateLeaveTime',
		  type : 'get',
		  data : {"leaveTime" : leavetime, "empId" : empId}, // empId는 세션에 저장된거 뽑아와야 함.
		  success : function(res){
			  //alert("퇴근 되었습니다.")
		  },
		  error : function(xhr){
			  //alert("상태 : " + xhr.status + "\ncode : "+ xhr.statust);
		  }
	  })
  })
// 1초마다 updateTime 함수를 호출하여 현재 시간을 업데이트합니다.
setInterval(updateTime, 1000);

/* -----------------------------------원형 차트-------------------------------------------- */
 // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '근태 현황');
        data.addColumn('number', '횟수');
        data.addRows([
          ['정상', 0],
          ['지각', 0],
          ['연차', 0],
          ['병가', 0],
          ['예비군', 0]
        ]);
       <%--  console.log(<%=id%>)
        if('<%=id%>' == "admin"){
        	dataurl = '<%=request.getContextPath() %>/Attendances/selectAttByDay' 
        } else {
	        dataurl = '<%=request.getContextPath() %>/Attendances/selectAttByEmp' 
        } --%>
        
		// 데이터 불러오기
		 $.ajax({
		    url :'<%=request.getContextPath() %>/Attendances/selectAttByEmp',
		    type : 'post',
		    data : {"empId" : "<%=id%>"},
		    success : function(res){
		      attstno = 0; // 정상
		      attstla = 0; // 지각
		      attstan = 0; // 연차
		      attstsi = 0; // 병가
		      attstso = 0; // 예비군/민방위

		      $.each(res, function(i,v){
		        if(v.att_state == '정상'){
		          attstno++;
		        } else if(v.att_state == '지각'){
		          attstla++;
		        } else if(v.att_state == '연차'){
		          attstan++;
		        } else if(v.att_state == '병가'){
		          attstsi++;
		        } else if(v.att_state == '예비군/민방위'){
		          attstso++;
		        }
		      })
			  data.setValue(0, 1, attstno); // 0번째 row, 1번째 column (정상)의 값을 변경
		      data.setValue(1, 1, attstla);  // 1번째 row, 1번째 column (지각)의 값을 변경
		      data.setValue(2, 1, attstan); // 2번째 row, 1번째 column (연차)의 값을 변경
		      data.setValue(3, 1, attstsi); // 3번째 row, 1번째 column (병가)의 값을 변경
		      data.setValue(4, 1, attstso); // 4번째 row, 1번째 column (예비군/민방위)의 값을 변경
		      
     		 // Set chart options
      		 var options = {
		    	  		'is3D' : true,
                       	'width':400,
                       	'height':240,
                       	"display":"inline-block"
                       	/* "slices" : {// 색상 지정
           		            {"color" : "#0054ff"}, // 정상 : 파랑
           		            {"color" : "#FFE400"}, // 지각 : 노랑
           		            {"color" : "#FF007F"}, // 연차 : 분홍
           		            {"color" : "#b342f5"}, // 병가 : 보라
           		            {"color" : "#257e4a"}  // 예비군/민방위 : 초록
                        	} */
	        }; // option 끝

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
		 }, // success 끝
	    error : function(xhr){
	      alert("상태 : " + xhr.status + "\ncode : " + xhr.statust)
	    }, 
	    dataType : 'json'
	  });
	}
 /* -----------------------------------원형 차트 끝------------------------------------------ */

 </script>
</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">

	<input type="hidden" value="<%=id%>" id="empid">
	<input type="hidden" value="<%=code%>" id="deptid">
	<input type="hidden" value="<%=name%>" id="username">
	<input type="hidden" value="<%=code_names%>" id="code_names">
	<header id="every-top">

		<nav>
			<h2 class="Everyware-logo">EveryWare</h2>
		</nav>

		<nav class="navigation">
		 	<jsp:include page="weathermodal.jsp"></jsp:include>
			<jsp:include page="Alarm.jsp"></jsp:include>
			<jsp:include page="mypagemodal.jsp"></jsp:include>
		</nav>
	</header>

	<div class="row">
		<!-- 헤더 -->
		<div class="leftcolumn">
			<div class="card" style="height: 1500px;">
				<p class='every-left'><ion-icon name="menu-outline" style="font-size: 1.9em;"></ion-icon></p>
				<p id="open-schedule" class='every-left'>
					&nbsp;
					<ion-icon name="calendar-outline"></ion-icon>
					일정
				</p>
				<p id="open-posts" class='every-left'>
					&nbsp;
					<ion-icon name="laptop-outline"></ion-icon>
					게시판
				</p>
				
				<p id="open-doc" class='every-left'>
					&nbsp;
					<ion-icon name="folder-open-outline"></ion-icon>
					결재
					<i class="fa fa-angle-down" style="font-size:20px"></i>
				</p>
				<div id="doc-side-menu" class="side-menu">
					<a id="doc-side1" class="doc-menu" >새 문서 작성</a><br> 
					<a id="doc-side2" class="doc-menu">수신문서함</a><br>
					<a id="doc-side3" class="doc-menu">기안문서함</a><br> 
					<a id="doc-side4" class="doc-menu">결재문서함</a><br>
					<a id="doc-side5" class="doc-menu">보관문서함</a>
				</div>
				

				<p id="open-email" class='every-left'>
					&nbsp;
					<ion-icon name="mail-outline"></ion-icon>
					이메일
				</p>
				
				<p id="open-hr" class='every-left'>
					&nbsp;
					<ion-icon name="time-outline"></ion-icon>
					H R
					<i class="fa fa-angle-down" style="font-size:20px"></i>
				</p>
				<div id="hr-side-menu" class="side-menu" >
					<a id="hr-side1" class="hr-menu" >근 태</a><br> 
					<a id="hr-side2" class="hr-menu">휴 가</a><br>
				</div>
			

				<p id="open-admin" class='every-left'>
					&nbsp;
					<ion-icon name="time-outline"></ion-icon>
					관리자 권한
				</p>
				<!-- </div> -->
			</div>
			
		</div>


		<!-- 본문 -->
		<div class="rightcolumn">
			
			<!-- first row 시작 -->
			<div class="first-row">
		
      <!-- 근태입력 -->
			<div class="card card-half">
				 <div class="hrbtn-wrap">
	          <button class="hrbtn">출근</button>
	          <button class="hrbtn2">퇴근</button>
         </div>
         <div class="fakeimg op" style="width: 100%;">
            <div id="current-time" style="color: black "></div>
            <div id="currentTime" style="color: black;width: 100%;background-color: #f1f1f1;margin-top: 15px;font-size: 18px;"></div>
         </div>
      </div> 
      
      <!-- 차트 -->
      <div class="card card-half" style="display: inline-block;  overflow: hidden;">
				<h4>Commuting status</h4>
				<div id="chart_div" style="display: flex ;"></div>
			</div> 
        
			<!-- 날씨 시작--------------------------------------------------------------- -->
			<div class="card card-whole"
				style="width: 1210px; padding: 10px; /* float: left;  */overflow: hidden;">

				<div style="width: 100%" id="weatherdiv">

					<div class="container-fluid" style="display: inline;width: 50%;float: left;">

						<h1 class="city-title">
							<span class="material-icons">place</span> <span id="city"></span>
						</h1>
						<p id="ntime" class="text-center"></p>
						<div class="row col-9 margin-auto">
							<div class="col-6" id="iconbox">
								<img src="" alt="" id="icon" class="floating">
							</div>
							<div class="col-6" id="nowtemp"></div>
							<div class="col-12 text-center">
								<p id="maxmintemp"></p>
								<p id="desc"></p>
							</div>
						</div>

						<div class="row light-blue py-20">
							<div class="offset-1 col-5">
								<ul>
									<li><i class="wi wi-cloudy"></i> 구름 <span id="myclouds"></span></li>
									<li><i class="wi wi-windy"></i> 바람 <span id="mywind"></span></li>
								</ul>
							</div>
							<div class="col-5">
								<ul>
									<li><i class="wi wi-humidity"></i> 습도 <span id="myhumidity"></span></li>
									<li><i class="material-icons">accessibility</i> 체감 <span
										id="feels_like"></span></li>
								</ul>
							</div>
						</div>
					</div>


					<div class="container swiper-slide" id="wrapper">
						<ul id="swiper" class="row" style="height: 235px;">
							<li>
								<div class="dayWeather">
									<p class="daydate"></p>
									<img src="" alt="">
									<p class="daytemp"></p>
									<p class="daydesc"></p>
								</div>
							</li>
						</ul>
					</div>
				</div>

			</div>
			<!-- 날씨 끝--------------------------------------------------------------- -->

			</div> <!-- first row 끝 -->
			
			<!-- second row 시작 -->
			<div class="second-row">
			
				<div class="card card-whole" style="/* float: left; */ width: 1210px;">
					<h2>일정</h2>
					<div class="main-schedule-box">
						<div class="schedule-today">
							<div id="mydate"><h4>오늘의 일정</h4></div>
							<div id="scheduletoday"></div>
						</div>
						<div id="schedulediv"><h4>일정</h4></div>
					</div>
				</div>
				
				<div class="card card-whole" style="/* float: left; */width: 1200px;">
					<h2>공지사항</h2>
					<div class="fakeimg" style="height: 200px;">
						<jsp:include page="mainPost.jsp"></jsp:include>
					</div>
				</div>
			
			</div> <!-- second row 끝 -->
			
			
			<!-- third row 시작 -->
			<div class="third-row">
			
				<div class="card card-whole" style="width: 1210px;/* float: left; */">
					<h2>새 이메일</h2>
					<div class="fakeimg" style="height: 100px; position: relative; bottom: 4px;">
					<jsp:include page="mainMail.jsp"></jsp:include>
					</div>
				</div>
			
				<div class="card card-whole" style="width: 1200px;/* float: left; */">
					<h2>결재할 문서</h2>
					<div class="fakeimg" style="height: 100px;" id="approvediv">
						<table>
							<tr>
								<th class="appr-table-title">제목</th>
								<th class="appr-table-writer">기안자</th>
								<th class="appr-table-date">기안일자</th>
							</tr>
						</table>
					</div>
				</div>
			
			</div> <!-- third row 끝 -->
			
			 <iframe class="comp" src="" frameborder="0" width="1400px" height="1000px"></iframe>
		</div>
		<!-- rightcolumn끝 -->
	</div>

	<script type="text/javascript" src="<%=request.getContextPath() %>/js/alarm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/weathermodal.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/Main.js"></script>
	<script type="text/javascript">
	$(function() {
		$.getWeather();
		$.openweather2();
		$.scheduletoday();
		$.scheduleList();
		$.documentList();
		setInterval($.scheduletoday, 3000);
		setInterval($.scheduleList, 3000);
	})
    </script>

</body>
</html>