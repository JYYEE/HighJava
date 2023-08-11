<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$
</script>
<style type="text/css">
label {
	display: inline-block;
	width: 50px;
}
</style>
</head>
<%
	// 로그인한 id를 쿠키에서 읽어온다.
	String cookieUserId = "";	// 로그인한 ID가 저장될 변수
	String chk=""; 				// CheckBox의 체크 여부를 지정할 변수
	Cookie[] cookieArr = request.getCookies();
	if(cookieArr != null){
		for(Cookie cookie : cookieArr){
			if("USERID".equals(cookie.getName())){
				cookieUserId = cookie.getValue();
				chk = "checked";
			}
		}
	}
%>
<body>
	<!-- <form action="<%=request.getContextPath() %>/cookieLoginServlet.do" method="post">
<h2>Login</h2><hr><br>
<fieldset>
<label for="id">ID : </label>
<input type="text" name = "id" id="id" text placeholder="ID를 입력하세요."><br>

<label for="pass">PASS : </label>
<input type="text" name = "pass" id="pass" text placeholder="PassWord를 입력하세요."><br>

<input type="checkbox" name = "idrem"> id 기억하기 <br>
<input type="button" value="Login">
</fieldset>
</form> -->

	<form action="<%=request.getContextPath()%>/cookieLoginServlet.do"
		method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="userid" value="<%=cookieUserId%>" placeholder="ID를 입력하세요.">
				</td>
			</tr>
			<tr>
				<td>PASS :</td>
				<td><input type="password" name="userpass" placeholder="PassWord를 입력하세요."></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" name="chkid" value="check" <%=chk %>> ID 기억하기</td>
			</tr>
			<td colspan="2" style="text-align: center"><input type="submit" value="Login"></td>
		</table>


	</form>
</body>
</html>