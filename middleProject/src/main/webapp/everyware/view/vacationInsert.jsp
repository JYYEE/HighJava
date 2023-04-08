<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Vacation</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/everyware/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
<%-- $(function(){
	empId = $('#empId').val();
	vacType=$('#vacType option:selected').val()
	$('#vacSub').on('click', function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/insertVac.do",
			type : 'post',
			data : { "empId" : empId, "vacType" : vacType },
			success : function(res){
				alert("성공")
			},
			error : function(xhr){
				alert("상태 : " + xhr.status + "\ncode : " + xhr.statust);
			},
			dataType : 'json'
		})
	})	
}) --%>

</script>
<style type="text/css">
div {
	border: 2px solid black;
}

.vac-menu {
	display : inline-block;
	width: 130px;
	height: 25px;
	vertical-align : top;
	
}

</style>
</head>
<body>
<%request.setAttribute("emp_id", "a001"); %>
	<div class="page-header">
		<h1>휴가신청</h1>
	</div>
	<div class="page-content">
		<form action="<%=request.getContextPath() %>/insertVac.do" method="get">
			<div class="vac-form">
				<input type="hidden" name=empId value="a001" id="empId">
				<label class="vac-menu">휴가 종류</label> 
				<select name="vacType" id="vacType">
					<option value="main" selected>선택하세요</option>
					<!-- 선택했을 때 option추가되는 이벤트 같음 -> 연차 일자도 같이 나와서... 일단 보류 -->
					<option value="연차">연차</option>
					<option value="예비군/민방위">예비군/민방위</option>
					<option value="병가">병가</option>
				</select>
			</div>
			<div class="vac-form">
				<label class="vac-menu">휴가 일자</label> 
				<input type="date" id="vacStart" name="vacStart" value=<%=LocalDate.now()%>> ~ <input type="date" id="vac-end" name="vacEnd" value=<%=LocalDate.now()%>>

			</div>
			<div class="vac-form" >
				<label class="vac-menu vac-textarea" for="vac-reason">사유<br>(30자 내외)</label>
				<textarea name="vacReason" rows="8" cols="50"></textarea>
			</div>
			<div class="vac-form">
				<label class="vac-menu vac-textarea" for="vac-approver">휴가 승인자<input type="button" value="조직도"></label>
				
				<textarea name="vacApprove" rows="10" cols="50"></textarea>
			</div>
			<div class="vac-form">
				<input type="submit" id="vacSub" value="휴가 신청">
			</div>
		</form>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/selectVacByEmp.do?empId=<%=request.getAttribute("emp_id")%>">휴가 조회</a>
	</div>
</body>
</html>