<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
%>


<h3>회원 목록 보기</h3>
<table border="1">
	<thead>
		<tr>
			<td colspan="5">
			<input type="button" value="회원추가" onclick="location.href='<%=request.getContextPath() %>/memberInsert.do'"> </td>
		</tr>
		<tr>
			<th>ID</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>전화</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody>
		<%
			if(memList == null || memList.size() == 0){
		%>	
				<tr><td colspan="5">저장된 회원 목록이 하나도 없습니다.</td></tr>
		<% 
			} else {
				for(MemberVO memVO : memList){		
		%>			<tr>
						<td><a href="<%=request.getContextPath() %>/memberDetail.do?memId=<%=memVO.getMem_id()%>"><%=memVO.getMem_id() %></a></td>
						<td><%=memVO.getMem_pass() %></td>
						<td><%=memVO.getMem_name() %></td>
						<td><%=memVO.getMem_tel() %></td>
						<td><%=memVO.getMem_addr() %></td>
					</tr>
		<% 
				}
			}	
		%>				
						
		
	</tbody>	
</table>
</body>
</html>