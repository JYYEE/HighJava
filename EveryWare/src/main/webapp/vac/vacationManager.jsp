<%@page import="everyware.vac.vo.VacationsVO"%>
<%@page import="java.util.List"%>
<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/vac/css/vac.css">
<script src='<%=request.getContextPath() %>/vac/js/vacations.js' type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/vac/js/jquery-3.6.4.min.js"></script>
<script>
<%
/*  List<VacationsVO> vacList = (List<VacationsVO>)request.getAttribute("vacList"); */
 
EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO");
String empid = vo.getEmp_id();
String empdatetime = vo.getEmp_date();
String empdate = empdatetime.split(" ")[0];
int emprevac = vo.getEmp_remain_vac();
int empvac = vo.getEmp_vac();
%>
console.log(<%=emprevac%>)
console.log(<%=empvac%>)
$(function(){
	sicode ='<tr id="vac-man-si"><td>추가</td><td>병가</td><td class="si" id="si-to">0</td><td class="si" id="si-jan">0</td>'
	sicode +='<td class="si" id="si-feb">0</td><td class="si" id="si-mar">0</td><td class="si" id="si-apr">0</td><td class="si" id="si-mar">0</td>'
	sicode +='<td class="si" id="si-jun">0</td><td class="si" id="si-jul">0</td><td class="si" id="si-aug">0</td><td class="si" id="si-sep">0</td>'
	sicode +='<td class="si" id="si-oct">0</td><td class="si" id="si-nov">0</td><td class="si" id="si-dec">0</td><td class="si" id="si-re">0</td></tr>'
	socode ='<tr id="vac-man-so"><td>추가</td><td>예비군/민방위</td><td class="so" id="so-to">0</td><td class="so" id="so-jan">0</td><td class="so" id="so-feb">0</td><td class="so" id="so-mar">0</td><td class="so" id="so-apr">0</td><td class="so" id="so-mar">0</td>'
	socode +='<td class="so" id="so-jun">0</td><td class="so" id="so-jul">0</td><td class="so" id="so-aug">0</td><td class="so" id="so-sep">0</td><td class="so" id="so-oct">0</td><td class="so" id="so-nov">0</td><td class="so" id="so-dec">0</td><td class="so" id="so-re">0</td></tr>'
	vsi = $(sicode);
	vso = $(socode);
	$.ajax({
		url : '<%=request.getContextPath()%>/Vacations/manage',
		type : 'get',
		data : {"empId" : "<%=empid%>"},
		success : function(res){
			//console.log(res)
			//alert("성공");
			 var vacationList = res;
		      var vacationCount = {};
		      for (var i = 0; i < vacationList.length; i++) {
		    	
			        var startDate = new Date(vacationList[i].vac_start);
			        var endDate = new Date(vacationList[i].vac_end);
			        //var months = getMonthsBetween(startDate, endDate);
			        vtype = vacationList[i].vac_type;
			        //console.log("months",months)
			        if('vac_approve' in vacationList[i]){
			        //console.log(vtype)
			        if(vtype == '연차'){
			        	var vacationCountan = {};
			        	var monthsan =getMonthsBetween(startDate, endDate);
			        		for (var j = 0; j < monthsan.length; j++) {
			        			//console.log(mons)
						          var monthan = monthsan[j];
						         // console.log(monthan)
						          var businessDays = getBusinessDays(startDate, endDate);
						          //console.log(businessDays)
						          if (vacationCountan[monthan] === undefined) {
						        	  vacationCountan[monthan] = businessDays;
						          } else {
						        	  vacationCountan[monthan] += businessDays;
						          }
						        }
			        	for (var monthan in vacationCountan) {
						//console.log(monthan + "에 사용한 휴가 일수: " + vacationCountan[monthan] + "종류 : " + vtype);
							  
						// 현재 반복 중인 month와 일치하는 월을 찾음
						var td = document.querySelector("#vac-man-month td:nth-child(" + monthan + ")");
						if (td) {
							// 일치하는 월의 값을 가져옴
							var value = vacationCountan[monthan];
							//console.log("일치하는 월의 값: " + value);
							var anTd = document.querySelector("#vac-man-an td:nth-child(" + (parseInt(monthan) + 3) + ")");
							//console.log(anTd)
						    if (anTd) {
							      anTd.textContent = parseInt(anTd.textContent) + value;
							      //console.log(anTd.textContent)
							    } // if(anTd) 끝 	
							}
			       		 }
			        }// vtype 연차 끝
			        else if(vtype == '병가'){
			        	var vacationCountsi = {};
			        	var monthssi =getMonthsBetween(startDate, endDate);
			        	for (var j = 0; j < monthssi.length; j++) {
					          var monthsi = monthssi[j];
					          var businessDays = getBusinessDays(startDate, endDate);
					          if (vacationCountsi[monthsi] === undefined) {
					        	  vacationCountsi[monthsi] = businessDays;
					          } else {
					        	  vacationCountsi[monthsi] += businessDays;
					          }
					     }
			        	for (var monthsi in vacationCountsi) {
							//console.log(monthsi + "에 사용한 휴가 일수: " + vacationCountsi[monthsi] + "종류 : " + vtype);
								  
							// 현재 반복 중인 month와 일치하는 월을 찾음
							var td = document.querySelector("#vac-man-month td:nth-child(" + monthsi + ")");
							if (td) {
								// 일치하는 월의 값을 가져옴
								var value = vacationCountsi[monthsi];
								//console.log("일치하는 월의 값: " + value);
								$('#vac-man-an').after(vsi);
						    	var siTd = document.querySelector("#vac-man-si td:nth-child(" + (parseInt(monthsi) + 3) + ")");
								    if (siTd) {
								      siTd.textContent = parseInt(siTd.textContent) + value;
								    	} // if(siTd) 끝
								}
				       	 }
			        }   	
			        else if(vtype == '예비군/민방위'){
			        	var vacationCountso = {};
			        	var monthsso = getMonthsBetween(startDate, endDate);
			        	for (var j = 0; j < monthsso.length; j++) {
					          var monthso = monthsso[j];
					          var businessDays = getBusinessDays(startDate, endDate);
					          if (vacationCountso[monthso] === undefined) {
					        	  vacationCountso[monthso] = businessDays;
					          } else {
					        	  vacationCountso[monthso] += businessDays;
					          }
					        }
			        	for (var monthso in vacationCountso) {
							//console.log(monthso + "에 사용한 휴가 일수: " + vacationCountso[monthso] + "종류 : " + vtype);
								  
							// 현재 반복 중인 month와 일치하는 월을 찾음
							var td = document.querySelector("#vac-man-month td:nth-child(" + monthso + ")");
							if (td) {
								// 일치하는 월의 값을 가져옴
								var value = vacationCountso[monthso];
								//console.log("일치하는 월의 값: " + value);
								$('#vac-man-an').after(vso);
						    	var soTd = document.querySelector("#vac-man-so td:nth-child(" + (parseInt(monthso) + 3) + ")");
							    if (soTd) {
							      soTd.textContent = parseInt(soTd.textContent) + value;
						   			 }   	
								}
				       		 }
			       		 }	
			        }    
		      }   
		},
		error : function(xhr){
			alert('상태 : ' + xhr.status + "\ncode : " +xhr.statust);	
		},
		dataType : 'json'
	})
	
	$(document).ready(function() {
		var antdarr = [];
		var sitdarr = [];
		var sotdarr = [];
		var totdarr = [];
		sitdarr.push(parseInt(document.querySelectorAll('.si')))
		sotdarr.push(parseInt(document.querySelectorAll('.so')))
		console.log(sitdarr)
		console.log(sotdarr)
		//$('#vac-man-si').find('#apr2').text();
		//$('#vac-man-si').find('#apr2').text('id');
	}
	
})
</script>
<style>
#tr-hid{
	display : none;
}
</style>
</head>
<body>

	<div class="page-header">
		<h2>휴가관리대장</h2>
		<div class="vac-btn-zone">
		<input type="button" name="insert" class="vac-btn" value="휴가 신청" onclick="location.href='<%=request.getContextPath()%>/vac/vacationInsert.jsp'">
		<span><input type="button" id="vacChk" class="vac-btn" value="휴가 사용 내역" onclick="location.href='<%=request.getContextPath()%>/Vacations'"></span>
		</div>
	</div>
	<hr>
	<div class="page-content">
		<div>
			<label>입사일</label>
			<input type="text" value="<%=empdate%>" disabled>
		</div>
		<div>
			<label></label>
			<table border="1">
				<thead>
					<tr>
						<th rowspan="2">유형</th>
						<th rowspan="2">휴가별</th>
						<th rowspan="2">일수</th>
						<th colspan="12">사용일수</th>
						<th rowspan="2">잔여일수</th>
					</tr>
					
					<tr id="vac-man-month">
						<td>1월</td>
						<td>2월</td>
						<td>3월</td>
						<td>4월</td>
						<td>5월</td>
						<td>6월</td>
						<td>7월</td>
						<td>8월</td>
						<td>9월</td>
						<td>10월</td>
						<td>11월</td>
						<td>12월</td>
					</tr>
				</thead>
				<tbody>
					<tr id="vac-man-an">
						<td>기본</td>
						<td>연차</td>
						<td id="empvac"><%=empvac %></td>
					    <td class="an" id="jan">0</td>
					    <td class="an" id="feb">0</td>
					    <td class="an" id="mar">0</td>
					    <td class="an" id="apr">0</td>
					    <td class="an" id="may">0</td>
						<td class="an" id="jun">0</td>
					    <td class="an" id="jul">0</td>
					    <td class="an" id="aug">0</td>
					    <td class="an" id="sep">0</td>
					    <td class="an" id="oct">0</td>
					    <td class="an" id="nov">0</td>
					    <td class="an" id="dec">0</td>
					    <td class="an" id="emprevac"><%=emprevac %></td>
					</tr>
				</tbody>
				
				<tfoot>
					<tr id="vac-man-to">
						<td colspan="2">총계</td>
						<td class="to" id="vac-to">0</td>
						<td class="to" id="to-jan">0</td>
						<td class="to" id="to-feb">0</td>
						<td class="to" id="to-mar">0</td>
						<td class="to" id="to-apr">0</td>
						<td class="to" id="to-may">0</td>
						<td class="to" id="to-jun">0</td>
						<td class="to" id="to-jul">0</td>
						<td class="to" id="to-aug">0</td>
						<td class="to" id="to-sep">0</td>
						<td class="to" id="to-oct">0</td>
						<td class="to" id="to-nov">0</td>
						<td class="to" id="to-dev">0</td>
						<td class="to" id="to-revac"><%=emprevac%></td>
					</tr>
				</tfoot>
			</table>
		</div>
		<div></div>
	</div>
	
	<!-- <script type="text/javascript">
	$(document).
		aa = 0;
		bb = 0;
		cc = 0;
		var apr2Value = $('#vac-man-si').find('#apr2').text('id');
		var apr3Value = $('#vac-man-si').find('#apr2').text('id');
		console.log(apr2Value); // 출력 결과: "apr2"
		console.log(apr3Value); // 출력 결과: "apr2"

		aa = parseInt($('#apr').text());
		bb = parseInt($('#apr2').text());
		cc = parseInt($('#apr3').text());
		console.log(aa);
		console.log(bb);
		console.log(cc);
		vbb = $('#si-to').text(bb);
		vcc =$('#so-to').text(cc);
		console.log(vbb);
		console.log(vcc);
		sum = aa+bb+cc;
		console.log(sum)
		$('#sumapr').text(sum);
	})
	</script> -->
</body>
</html>