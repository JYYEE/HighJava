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

<script>
<%EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO");%>
empid = '<%=vo.getEmp_id()%>' 
<%-- empid = '<%=request.getAttribute("empId")%>' --%>

path ='<%=request.getContextPath()%>'
console.log(empid)
$(function(){
	$.selectAttByEmpList(empid) // 사원 근태내역 불러오기
	
	// 사원용 근태 상세 내역 입력
	$(document).on('click','.fc-event', function(){ // 근태 상세 선택
		attstate = $(this).find('.fc-event-title').text(); // 정상
		attti = $(this).find('.fc-event-time').text(); // 7:22a
		attdate = $(this).parents('.fc-daygrid-day-frame').find('.fc-daygrid-day-number').attr('aria-label');
		attdate = $.formatDate(attdate) //근태 일자 2023-04-12
		
		// 모달창에 근태 상태 출력
		$('#att-detail-st').val(attdate +' ' +attti + ' '+attstate);
		
		// 근태 메모 출력
		$.selectAttMemo(empid, attdate, empid);
		
		$('#att-modal').show();
		
	})
	// 사원용 근태 내역 작성 - 모달창 작성, 등록, 수정, 확인 누를때 / 
	$('.att-detail-btn').on('click', function(){
		btn = $(this).attr('name');
		if(btn == "submit") { // 등록
			attmemo = $('.att-modal-body textarea').val(); // 근태 이유
			$.ajax({
				url : `${path}/Attendances/updateMemo`, // 백틱쓰려면 elignored 설정!!
				type : 'post',
				data : { "attMemo" : attmemo, "empId" : empid, "attDate" : attdate },
				success : function(res) {
					if(res.flag == "ok"){
						alert("근태 내역이 등록되었습니다.")
						$('#att-modal').hide();
						$('.att-modal-body textarea').val("");
					} else {
						alert("근태 내역 등록이 실패했습니다. 다시 시도해주세요.");
						$('#att-modal').hide();
						$('.att-modal-body textarea').val("");
					}
				},
				error : function(xhr) {
					alert("상태 : " + xhr.status);
				},
				dataType : 'json'
			})
		} else if(btn == "check") { // 확인
			$('.att-modal-body textarea').val("");
			$('#att-modal').hide();
		} else if(btn == "modify"){ // 수정 
			modattmemo = $(this).parents('.att-modal-content').find('#att-detail-rea').val();
			$.ajax({
				url : `${path}/Attendances/updateMemo`, // 백틱쓰려면 elignored 설정!!
				type : 'post',
				data : { "attMemo" : modattmemo, "empId" : empid, "attDate" : attdate },
				success : function(res) {
					if(res.flag == "ok"){
						alert("근태 내역이 수정되었습니다.")
						$('#att-modal').hide();
						$('.att-modal-body textarea').val("");
					} else {
						alert("근태 내역 수정에 실패했습니다. 다시 시도해주세요.");
						$('#att-modal').hide();
						$('.att-modal-body textarea').val("");
					}
				},
				error : function(xhr) {
					alert("상태 : " + xhr.status);
				},
				dataType : 'json'
			}) 
			
		} 
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