<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<title>Insert title here</title>

<script type="text/javascript">
<%-- $(function(){
	/* $('#modify').on('click', function(){
		
	})
	
	$('#delete').on('click', function(){
		
	}) */
	
	$('#memList').on('click', function(){
		location.href="<%=request.getContextPath()%>/memberList.do";
	})
	
}) --%>
</script>
</head>
<body>
<%
	MemberVO memVO =(MemberVO)request.getAttribute("memVO");

%>
<h3>회원 정보 상세보기</h3>
<table border="1">
	<tr>
		<td colspan="2"><%=memVO.getMem_photo() %></td>		
	</tr>
	<tr>
		<td>회원ID</td>
		<td><%=memVO.getMem_id() %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=memVO.getMem_pass() %></td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td><%=memVO.getMem_name() %></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><%=memVO.getMem_tel() %></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><%=memVO.getMem_addr() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" id="update" onclick="location.href='<%=request.getContextPath() %>/memberUpdate.do'">
			<input type="button" value="삭제" id="delete" onclick="location.href='<%=request.getContextPath() %>/memberDelete.do'">
			<input type="button" value="회원목록" id="memList" onclick="location.href='<%=request.getContextPath()%>/memberList.do'">
		</td>		
	</tr>

</table>
</body>
</html>