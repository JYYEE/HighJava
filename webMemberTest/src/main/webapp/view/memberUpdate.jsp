<%@page import="kr.or.ddit.vo.MemberVO"%>
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
	MemberVO memVO = (MemberVO)request.getAttribute("memVO");
%>
<form action="<%=request.getContextPath() %>/memberUpdate.do" method="post" enctype="multipart/form-data">
<h3>회원 정보 수정 폼</h3>
<table border="1">
	<tr>
		<td colspan="2"><img alt="프로필사진" src="<%=request.getContextPath()%>/images/imageSrcView.do?fileName=<%=memVO.getMem_photo()%>"></td>	
	</tr>
	<tr>
		<td>회원ID</td>
		<td><%=memVO.getMem_id() %>
		<input type="hidden" name ="memId" value="<%=memVO.getMem_id() %>"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="memPass" id="memPass" placeholder="<%=memVO.getMem_pass() %>"></td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td><input type="text" name="memName" id="memName" placeholder="<%=memVO.getMem_name() %>"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="memTel" id="memTel" placeholder="<%=memVO.getMem_tel() %>"></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><input type="text" name="memAddr" id="memAddr" placeholder="<%=memVO.getMem_addr() %>"></td>
	</tr>
	<tr>
		<td>프로필 사진</td>
		<td><input type="file" value="파일선택" name="memPhoto" id="memPhoto"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장" id="save" onclick="location.href='<%=request.getContextPath() %>/memberList.do'">
			<input type="button" value="취소" id="calcel" onclick="location.href='<%=request.getContextPath() %>/memberList.do'">
			<input type="button" value="회원목록" id="memList" onclick="location.href='<%=request.getContextPath()%>/memberList.do'">
		</td>		
	</tr>

</table>
</form>
</body>
</html>