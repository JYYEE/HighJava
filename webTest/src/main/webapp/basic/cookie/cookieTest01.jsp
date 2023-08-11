<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Cookie</h2>
<br><hr><br>
<a href="<%=request.getContextPath() %>/cookieAdd.do">Cookie 정보 저장하기</a><br><br>
<a href="<%=request.getContextPath() %>/cookieRead.do">Cookie 정보 확인하기</a><br><br>
<a href="<%=request.getContextPath() %>/cookieDel.do">Cookie 정보 삭제하기</a><br><br>
</body>
</html>