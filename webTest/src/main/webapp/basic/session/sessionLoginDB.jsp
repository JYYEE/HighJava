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
	// Session정보를 가져온다. --> JSP문서에서 HttpSession객체는 'session'이라는 이름으로 저장되어 있다.
	MemberVO loginMemVO = (MemberVO) session.getAttribute("loginMember");
%>

<%
	if(loginMemVO == null){

%>
<!--  로그인이 안되었을 때 화면 -->
<form action="<%=request.getContextPath() %>/sessionLoginDB.do" method="post">
	<table style="margin : 0 auto;" border="1">
		<tr>
			<td>ID :</td>
			<td><input type="text" name="userid" placeholder="ID를 입력하세요."></td>
		</tr>
		<tr>
			<td>PASS :</td>
			<td><input type="password" name="userpass" placeholder="PassWord를 입력하세요."></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;">
			<input type="submit" value="Login"></td>
		</tr>
	</table>


</form>
<%
} else {

%>
<!-- 로그인이 되었을 때 화면 -->
	<h3><%=loginMemVO.getMem_name() %>님 반갑습니다.</h3>
	<a href="<%=request.getContextPath()%>/sessionLogoutDB.do">로그아웃</a>
<%
}
%>	

<%-- <form action="<%=request.getContextPath()%>/sessionLoginServlet.do" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="userid" placeholder="ID를 입력하세요.">
				</td>
			</tr>
			<tr>
				<td>PASS :</td>
				<td><input type="password" name="userpass"
						placeholder="PassWord를 입력하세요."></td>
			</tr>
			<td colspan="2" style="text-align: center"><input type="submit"
					value="Login"></td>
		</table> --%>
		
		
</body>
</html>