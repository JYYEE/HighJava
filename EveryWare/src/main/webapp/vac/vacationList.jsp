<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="java.util.List"%>
<%@page import="everyware.vac.vo.VacationsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/vac/css/vac.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/vac/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
<%
	List<VacationsVO> vacList = (List<VacationsVO>)request.getAttribute("vacList");
	 
	EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO");
	String empid = vo.getEmp_id();
%>

$(function(){
	$(document).on('click', '#delvac', function(){
		vac_delids=[]; // 삭제할 vac_id가 저장될 배열
		
		$('.vac-delch:checked').each(function(){ // 체크된 체크박스들을 찾음
			vac_delid = $(this).closest('tr').find('td:first-child').text(); // 체크된 체크박스의 부모 tr 요소 찾아서 첫번째 td요소 값 추출
			vac_delids.push(vac_delid);// 추출한 값을 배열에 저장.
		})
		if(vac_delids[0]==null){
			$('#delvac').prop('disabled', true);
			location.reload();
		} else {
		    $('#delvac').prop('disabled', false);
	   
		$.ajax({
			url : '<%=request.getContextPath()%>/Vacations/deleteVac',
			type : 'post',
			data : { "vacdelIdList" : vac_delids },
			success : function(res){
				
				if(res.flag =="ok"){
					if(vac_delids.length == 0){
						alert("삭제할 휴가를 선택해주세요.")
					} else {
						alert("신청하신 휴가가 삭제되었습니다.")
						location.reload();
					}
				}
			},
			error : function(xhr){
				alert("상태 : " + xhr.status + "\ncode : " + xhr.statust);
			}, 
			dataType : 'json'
		})
		}
	})
})
</script>
</head>
<body>


<div class="page-header">
	
	
	<h2>휴가 사용 내역</h2>
	<div class="vac-btn-zone">
	<input type="button" name="insert" class="vac-btn" value="휴가 신청" onclick="location.href='<%=request.getContextPath()%>/vac/vacationInsert.jsp'">
	<input type="button" name="manage" class="vac-btn" value="휴가 관리 대장" onclick="location.href='<%=request.getContextPath()%>/vac/vacationManager.jsp'">
	</div>
</div>
<hr>

<div class="page-content">
	<table border="1" class="vac-list">
		<thead>
			<tr>
				<td colspan="7" >사원ID : <%=empid %></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>휴가종류</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>사유</th>
				<th>승인상태</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<%if(vacList == null || vacList.size() == 0){%>
				<tr><td colspan="7">휴가 사용 내역이 존재 하지 않습니다.</td></tr>
			<% 	 
			} else {
				for(VacationsVO vacVO : vacList){
				%>	
				<tr>
				<td><%=vacVO.getVac_id() %></td>
				<td><%=vacVO.getVac_type() %></td>
				<td><%=vacVO.getVac_start()%></td>
				<td><%=vacVO.getVac_end()%></td>
				<td>
				<% if(vacVO.getVac_reason() == null){%>
					미작성
				<% } else {%>
				<%=vacVO.getVac_reason() %></td>
				<%} %>
				
				<td>
				<%if(vacVO.getVac_approve() == null){ %>
					승인대기중
				<%} else {%>
					<%=vacVO.getVac_approve() %></td>
				<%}%>
				<td>
				<%if("승인".equals(vacVO.getVac_approve())){%>
					<input type="checkbox" name="vac-del" class="vac-delch" disabled></td>
				<% } else {%> 
					<input type="checkbox" name="vac-del" class="vac-delch" ></td>
				<% 	} %>
				</tr>
				<% 
				}
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<input type="button" name="delete" class="vac-btn" value="휴가삭제" id="delvac">
				</td>
			</tr>
		</tfoot>
	</table>	
</div>
</body>
</html>