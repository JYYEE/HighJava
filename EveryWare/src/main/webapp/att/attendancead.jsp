<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="everyware.att.vo.AttendancesVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page isELIgnored="true" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src='<%=request.getContextPath() %>/att/js/jquery-3.6.4.min.js'></script>
<script src='<%=request.getContextPath() %>/att/js/index.global.min.js'></script>
<script src='<%=request.getContextPath() %>/att/js/attendance.js' type="text/javascript"></script>
<script srt='<%=request.getContextPath() %>/att/js/bootstrap-datetimepicker.min.js' type="text/javascript"></script>
<script srt='<%=request.getContextPath() %>/att/js/moment.min.js' type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/att/css/att.css" rel="stylesheet">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
<%EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO");%>

empid = '<%=vo.getEmp_id()%>'
<%-- empid = '<%=request.getAttribute("empId")%>' --%>
path ='<%=request.getContextPath()%>' 
console.log(empid)
// 차트 보기 - 메인
$(document).on('click', '.fc-daygrid-day-number', function(){
	vdiv = $('<div id="piechart_3d" style="width: 900px; height: 500px ; margin-left: 200px;"></div>')
	$('.fc-daygrid-day-frame').append(vdiv);
	date = $(this).attr('title').slice(6);
	attdate = $.formatDate(date)
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		  var data = google.visualization.arrayToDataTable([
		    ['근태 현황', 'Days per Month'],
		    ['정상',  0],
		    ['지각',  0],
		    ['연차',  0],
		    ['병가',  0],
		    ['예비군/민방위', 0]
		  ]); 
		  
		  $.ajax({
		    url : `${path}/Attendances/selectAttByDay`,
		    type : 'post',
		    data : {"attDate" : attdate},
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

		      var options = {
		        title: '사원 근태 현황', // 차트 타이틀
		        is3D: true, // 3D 구현
		        pieSliceText: 'percentage',
		        slices : { // 색상 지정
		             0 : {"color" : "#0054ff"}, // 정상 : 파랑
		             1 : {"color" : "#FFE400"}, // 지각 : 노랑
		             2 : {"color" : "#FF007F"}, // 연차 : 분홍
		             3 : {"color" : "#b342f5"}, // 병가 : 보라
		             4 : {"color" : "#257e4a"}  // 예비군/민방위 : 초록
		        },
		      };
		      
		      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		      chart.draw(data, options);
		      if(attstno+attstla+attstan+attstsi+attstso == 0){
		    	  $(vdiv).remove();
		      }
		    },
		    error : function(xhr){
		      alert("상태 : " + xhr.status + "\ncode : " + xhr.statust)
		    }, 
		    dataType : 'json'
		  });
		}
})
// 차트 보기 - 이전달
$(document).on('click', '.fc-prev-button', function(){
	vdiv = $('<div id="piechart_3d" style="width: 900px; height: 500px ; margin-left: 200px;"></div>')
	$('.fc-daygrid-day-frame').append(vdiv);
	date = $(this).next().text();
	attdate = $.formatDate(date)
	console.log(attdate)
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		  var data = google.visualization.arrayToDataTable([
		    ['근태 현황', 'Days per Month'],
		    ['정상',  0],
		    ['지각',  0],
		    ['연차',  0],
		    ['병가',  0],
		    ['예비군/민방위', 0]
		  ]); 
		  
		  $.ajax({
		    url : `${path}/Attendances/selectAttByDay`,
		    type : 'post',
		    data : {"attDate" : attdate},
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

		      var options = {
		        title: '사원 근태 현황', // 차트 타이틀
		        is3D: true, // 3D 구현
		        pieSliceText: 'percentage',
		        slices : { // 색상 지정
		             0 : {"color" : "#0054ff"}, // 정상 : 파랑
		             1 : {"color" : "#FFE400"}, // 지각 : 노랑
		             2 : {"color" : "#FF007F"}, // 연차 : 분홍
		             3 : {"color" : "#b342f5"}, // 병가 : 보라
		             4 : {"color" : "#257e4a"}  // 예비군/민방위 : 초록
		        },
		      };
		      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		      chart.draw(data, options);
		      if(attstno+attstla+attstan+attstsi+attstso == 0){
		    	  $(vdiv).remove();
		      }
		    },
		    error : function(xhr){
		      alert("상태 : " + xhr.status + "\ncode : " + xhr.statust)
		    }, 
		    dataType : 'json'
		  });
		}
})
// 차트 보기 - 다음달
$(document).on('click', '.fc-next-button', function(){
	vdiv = $('<div id="piechart_3d" style="width: 900px; height: 500px; margin-left: 200px;"></div>')
	$('.fc-daygrid-day-frame').append(vdiv);
	date = $(this).prev().text();
	attdate = $.formatDate(date)
	google.charts.load("current", {packages:["corechart"]});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		  var data = google.visualization.arrayToDataTable([
		    ['근태 현황', 'Days per Month'],
		    ['정상',  0],
		    ['지각',  0],
		    ['연차',  0],
		    ['병가',  0],
		    ['예비군/민방위', 0]
		  ]); 
		  
		  $.ajax({
		    url : `${path}/Attendances/selectAttByDay`,
		    type : 'post',
		    data : {"attDate" : attdate},
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

		      var options = {
		        title: '사원 근태 현황', // 차트 타이틀
		        is3D: true, // 3D 구현
		        pieSliceText: 'percentage',
		        slices : { // 색상 지정
		             0 : {"color" : "#0054ff"}, // 정상 : 파랑
		             1 : {"color" : "#FFE400"}, // 지각 : 노랑
		             2 : {"color" : "#FF007F"}, // 연차 : 분홍
		             3 : {"color" : "#b342f5"}, // 병가 : 보라
		             4 : {"color" : "#257e4a"}  // 예비군/민방위 : 초록
		        },
		      };
		      var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
		      chart.draw(data, options);
		      if(attstno+attstla+attstan+attstsi+attstso == 0){
		    	  $(vdiv).remove();
		      }
		    },
		    error : function(xhr){
		      alert("상태 : " + xhr.status + "\ncode : " + xhr.statust)
		    }, 
		    dataType : 'json'
		  });
		}
})

</script>
<script>
$(function(){
	$.selectAllAttList() // 전체 근태내역 불러오기
	
	// 관리자용 근태 상세 내역 확인
	$(document).on('click','.fc-event', function(){ // 근태 상세 선택
		attst = $(this).find('.fc-event-title').text(); // a001 정상
		attti = $(this).find('.fc-event-time').text(); // 7:22a
		attstate = attst.split(' ')[1]; // 근태 상태
		attemp = attst.split(' ')[0]; // 사원명
		par = $(this).parents('.fc-popover'); // 조상 중 클래스가 'fc-popover'인것. +5more눌렀을 때만 나옴
		if(par.length>0){ // length : 선택된 요소의 개수를 반환.  
			attdate = $(this).parents('.fc-more-popover').attr('data-date')// 2023년 4월 12일
		} else {
			attdate = $(this).parents('.fc-daygrid-day-frame').find('.fc-daygrid-day-number').attr('title');
		}
		attdate = $.formatDate(attdate) //2023-04-12
		
		// 근태 내용 출력
		$.selectAttMemo(attemp, attdate, empid);
		$('#att-detail-sub').css('display', 'none');
		$('#att-detail-mod').css('display', 'none');
		$('#att-modal').show();
		if($('.fc-popover').css('display') != 'none'){
			$('.fc-popover').hide();
		}
		$('#att-detail-st').val(attdate +' ' +attti + ' '+attstate);
		
		// 확인 버튼 누를때
		$('.att-detail-btn').on('click', function(){
			$('#att-modal').hide();
			$('.att-modal-body textarea').val("");
		})
	})
	
	// 이벤트 불러오기
	cal = function(evts) {
   		var calendarEl = document.getElementById('calendar');
   		var eventsData = evts || [];
   		var calendar = new FullCalendar.Calendar(calendarEl, {
   			 locale: 'ko', // 한국어 버전. 날짜도 일이 나오네 아쉽. // 달력 칸 좀 늘려줘야함.
		     headerToolbar: {
		        left: 'today',
		        center: 'prev title next',
		        right: 'dayGridMonth' //,timeGridWeek,timeGridDay,listMonth'// 상단 오른쪽 버튼  
		      },
		      dayMaxEvents : 5,
		      initialDate: new Date(),//, // 시작일 : 오늘
		      navLinks: true, // 날짜/주 이름을 클릭하여 다른 보기로 이동할 수 있도록 설정
		      businessHours: true, // 업무 시간을 표시할지 여부를 설정
		      editable: true, //이벤트를 편집하거나 이동할 수 있는지 여부를 설정
		      selectable: true, // 사용자가 새 이벤트를 추가할 수 있는지 여부 설정
		      events : eventsData // JSON객체가 들어간 배열이 올 수 있음.
		});
	calendar.render() 
	} 
})

</script>
<style type="text/css">
/* 모달 창 배경 (흐리게 검어지는 부분) The Modal (background) */
.att-modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 300px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color : 투명도 설정이 안되는 브라우저에서 설정됨 */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* 모달 창 내용 Modal Content */
.att-modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid #888;
  width: 500px;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
  -webkit-animation-name: animatetop; /* animation 설정이 안되는 브라우저에서 사용*/
  -webkit-animation-duration: 0.7s;
  animation-name: animatetop;
  animation-duration: 0.7s
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {top:-300px; opacity:0} 
  to {top:0; opacity:1}
}

@keyframes animatetop {
  from {top:-300px; opacity:0}
  to {top:0; opacity:1}
}

/* The Close Button */
.att-close { /* 모달 취소버튼 */
  color: white;
  float: right;
  font-size: 36px;
  font-weight: bold;
}

.att-close:hover,
.att-close:focus { /*모달 취소버튼 호버*/
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.att-modal-header { /*모달 헤더*/
  padding: 4px 4px;
  background-color: #5cb85c;
  color: white;
  font-size : 18px;
}
.att-modal-header h3 {
	margin : 6px;
}
.att-modal-body { /*모달 바디*/
	padding: 2px 16px;
}

.att-modal-footer { /*모달 풋터*/
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
.att-detail-btn { /*모달창 작성, 등록 버튼*/
	width : 50px;
	height : 30px;
	margin : 10px 5px;
}
.att-modal-body label{ /*근태 상태, 근태 내역 라벨*/
	font-weight: bold;
} 
.att-modal-body #att-rea{ /*근태 내역 라벨*/
	vertical-align: top;
}
.att-modal-body #att-detail-st {
	width : 200px;
}
.att-modal-body textarea{ /*근태 내역 작성창*/
	/* visibility:hidden; */
}
</style>
</head>
<body>

<div class="page-header">
	<h2 class="att-cal">근태 현황</h2>
	<hr>
</div>
<div id="calendar" style="color:black;"></div>

<!-- The Modal -->
<div id="att-modal" class="att-modal">

  <!-- Modal content -->
  <div class="att-modal-content">
    <div class="att-modal-header">
      <span class="att-close">&times;</span>
      <h3>근태 상세 내역</h3>
      
    </div>
    <div class="att-modal-body">
      <div>
      	<br>
      	<label>근태 상태 :</label>
    	<input type="text" name="att-state" id="att-detail-st" disabled>
      </div>
      <br><hr><br>
      <div>
      <label id="att-rea">근태 내역 : </label>
      <textarea rows="5" cols="40" name="att-memo" id="att-detail-rea" ></textarea>
      </div>
      <br>
    </div>
    <div class="att-modal-footer">
      <input type="button" value="등록" name="submit" class="att-detail-btn" id="att-detail-sub">
      <input type="button" value="수정" name="modify" class="att-detail-btn" id="att-detail-mod">
      <input type="button" value="확인" name="check" class="att-detail-btn" id="att-detail-chk">
      
    </div>
  </div>

</div>
<script type="text/javascript">
//모달용 JavaScript
//Get the modal
var modal = document.getElementById("att-modal");

//Get the <span> element that closes the modal
var span = document.getElementsByClassName("att-close")[0];

//When the user clicks on <span> (x), close the modal
span.onclick = function() {
modal.style.display = "none";
}

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
if (event.target == modal) {
 modal.style.display = "none";
	}
}
</script>
</body>
</html>