<%@page import="java.util.List"%>
<%@page import="everyware.vo.VacationsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/everyware/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$(document).on('click', '.vac-approve', function(){
		
	})
})
</script>
</head>
<body>
<%
	List<VacationsVO> vacList = (List<VacationsVO>)request.getAttribute("vacList");

%>
<div class="page-header">
	<h1>휴가 사용 내역</h1>
</div>
<div class="page-content">
	<table border="1">
	<% String id = request.getParameter("empId"); 
	   if(id == null){%> <!-- 관리자 id로 바꿀 부분 -->
		<thead>
			<tr>
				<td colspan="8" >관리자ID : <%=request.getParameter("empId") %></td>
			</tr>
			<tr>
				<th>휴가id</th>
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
				for(VacationsVO vacVO : vacList){
				%>	
				<tr>
				<td><%=vacVO.getVac_id() %></td>
				<td><%=vacVO.getEmp_id() %></td>
				<td><%=vacVO.getVac_type() %></td>
				<td><%=vacVO.getVac_start()%></td>
				<td><%=vacVO.getVac_end()%></td>
				<td><%=vacVO.getVac_reason() %></td>
				<td><%=vacVO.getVac_approve() %></td>
				<td><input type="button" class="vac-approve" value="승인" ></td>
				</tr>
				<% 
				}
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<input type="button" class="vac-approve" value="승인" >
				</td>
			</tr>
		</tfoot>
	<%} else { %>
		<thead>
			<tr>
				<td colspan="7" >사원ID : <%=request.getParameter("empId") %></td>
			</tr>
			<tr>
				<th>휴가id</th>
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
				<td><%=vacVO.getVac_reason() %></td>
				<td><%=vacVO.getVac_approve() %></td>
				<td><input type="checkbox" name="delcheck"></td>
				</tr>
				<% 
				}
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<input type="button" name="insert" value="신청" onclick="<%=request.getContextPath()%>/everyware/view/vacationInsert.jsp">
					<input type="button" name="delete" value="삭제">
				</td>
			</tr>
		</tfoot>
	<%}
	%>
	</table>	
</div>
</body>
</html>