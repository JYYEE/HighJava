<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="everyware.vac.vo.VacationsVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/vac/css/vac.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/vac/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
<%	List<VacationsVO> vacList = (List<VacationsVO>)request.getAttribute("vacList");
	//String empid = (String)request.getAttribute("empId");
	
	EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO");
	String empid = vo.getEmp_id(); 
%>

$(function(){
	$(document).on('click','#appvac',function(){
		vac_appids=[]; // 승인될 vac_id가 저장될 배열
		vac_apptypes=[];
		$('.vac-appch:checked').each(function(){
			vac_appid = $(this).closest('tr').find('td:first-child').text();
			vac_appids.push(vac_appid);
			
			vac_apptype = $(this).closest('tr').find('td:nth-child(3)').text();
			vac_apptypes.push(vac_apptype);
		})
		vac_appidsData = vac_appids || [];
		vac_apptypesData = vac_apptypes || [];
		
		console.log('jsp vacappIdList : ' + vac_appidsData) //[]
		if(vac_appids == null || vac_appids.length == 0){
			$('#appvac').prop('disabled', true);
			location.reload();
		} else {
		    $('#appvac').prop('disabled', false);
	   
			$.ajax({
				url : '<%=request.getContextPath()%>/Vacations/approveVac',
				type : 'post',
				data : {"vacappIdList" : vac_appidsData},
				success : function(res){
					if(res.flag == "no"){
						alert("선택한 내역이 없습니다. 다시 시도해주세요.")
					} else {
						alert("신청하신 휴가가 승인처리 되었습니다.");
						location.reload();
					}
				},
				error : function(xhr){
					alert("상태 : " + xhr.status + "\ncode : " + xhr.statust);
				},
				dataType : 'json'
			})
			
			
			
			
			$.ajax({
				url : '<%=request.getContextPath()%>/Vacations/updateRemainVac',
				type : 'post',
				data : {"vacappIdList" : vac_appidsData, "vacappTypeList" : vac_apptypesData},
				success : function(res){
					if(res.flag == "no"){
						//alert("연차일 업데이트 실패")
					} else {
						//alert("연차일 업데이트 완료")
						location.reload();
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
<style type="text/css">

</style>
</head>
<body>
<div class="page-header">
	<h2>휴가 사용 내역</h2>
</div>
<hr>
<div class="page-content">
	<table border="1" class="vac-list">
		<thead>
			<tr>
				<td colspan="8" >관리자ID : <%=empid %></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>사번</th>
				<th>휴가종류</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>사유</th>
				<th>승인상태</th>
				<th>승인</th>
			</tr>
		</thead>
		<tbody>
			<%if(vacList == null || vacList.size() == 0){%>
				<tr><td colspan="8">휴가 내역이 존재 하지 않습니다.</td></tr>
			<% 	 
			} else {
				for(VacationsVO vacVO : vacList){ %>
					
				<tr>
				<td><%=vacVO.getVac_id() %></td>
				<td><%=vacVO.getEmp_id() %></td>
				<td><%=vacVO.getVac_type() %></td>
				<td><%=vacVO.getVac_start()%></td>
				<td><%=vacVO.getVac_end() %></td>
				<td>
				<% if(vacVO.getVac_reason() == null){ // 사유 미작성%>
					미작성
				<% } else {%>
				<%=vacVO.getVac_reason() %></td>
				<%} %>
				
				<td>
				<%if(vacVO.getVac_approve() == null){ // 미승인 %>
					승인대기중
				<%} else {%>
					<%=vacVO.getVac_approve() %></td>
				<%}%>
				<td>
				<%if("승인".equals(vacVO.getVac_approve())){%>
					<input type="checkbox" name="vac-app" class="vac-appch" disabled></td>
				<% } else {%> 
					<input type="checkbox" name="vac-app" class="vac-appch" ></td>
				<% 	} %>
				</tr>
				<% 
				}
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<input type="button" id="appvac" name="approve" class="vac-btn" value="승인">
				</td>
			</tr>
		</tfoot>
	</table>
</div>
</body>
</html>