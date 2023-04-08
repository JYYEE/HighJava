<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" value="a001" name=empId>
<a href="<%=request.getContextPath()%>/insertVac.do">휴가 신청</a>
<a href="<%=request.getContextPath()%>/everyware/view/vacationList.jsp">휴가 조회</a>

</body>
</html>