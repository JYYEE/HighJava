<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vacation</title>
<script type="text/javascript">
$(function(){
	
})

</script>
<style type="text/css">
div {
	border: 2px solid black;
}

.vac-menu {
	display : inline-block;
	width: 130px;
	height: 25px;
}
.vac-textarea {
  position: relative;
  top: -100px; /* 높이 조절 */
  /* left: 10px; /* 왼쪽 여백 추가 */ */
}
</style>
</head>
<body>
	<div class="page-header">
		<h1>휴가신청</h1>
	</div>
	<div class="page-content">
		<form action="<%=request.getContextPath() %>/insertVac.do" method="get">
			<div class="vac-form">
				<label class="vac-menu">휴가 종류</label> <select name="vac-type">
					<option value="main" selected>선택하세요</option>
					<!-- 선택했을 때 option추가되는 이벤트 같음 -> 연차 일자도 같이 나와서... 일단 보류 -->
					<option value="annual-leave">연차</option>
					<option value="military-leave">예비군/민방위</option>
					<option value="sick-leave">병가</option>

				</select>
			</div>
			<div class="vac-form">
				<label class="vac-menu">휴가 일자</label> <input type="date" id="vac-start" value=<%=LocalDate.now()%>> ~ <input type="date" id="vac-end" value=<%=LocalDate.now()%>>

			</div>
			<div class="vac-form" >
				<label class="vac-menu vac-textarea" for="vac-reason">사유</label>
				<textarea rows="8" cols="50"></textarea>
			</div>
			<div class="vac-form">
				<label class="vac-menu vac-textarea" for="vac-approver">휴가 승인자<input type="button" value="조직도"></label>
				
				<textarea rows="10" cols="50"></textarea>
			</div>
			<div class="vac-form">
				<input type="button" value="휴가 신청">
			</div>
		</form>
	</div>

</body>
</html>