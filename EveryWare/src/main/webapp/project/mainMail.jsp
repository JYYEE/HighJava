<%@page import="groupware.email.vo.EmailVO"%>
<%@page import="java.util.List"%>
<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="groupware.email.service.EmailServiceImpl"%>
<%@page import="groupware.email.service.IEmailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.mail-detail, .mailTiel {
	/* border: 1px solid blue; */
	margin: 5px;
	font-size: 14px;
	font-weight: bold;
	border-radius: 5px
}
.mail-list{
	margin: 5px;
	font-size: 14px;
	border-radius: 5px
} 
.mailTiel td{
	font-weight: bold;
}
.mail-list, .mailTiel {
	color: black;
}

.mail-list {
	padding: 3px;
}

table {
	border-radius: 5px;
	width: 100%;
	/* border-collapse: collapse; */
}

td {
	/* width: 25%; */
	/* text-align: center; */
	/* border: 1px solid #f1f1f1; */
	font-size: 18px;
	height: 14px;
}


.menu {
	width: 25%;
	/* background: #F3F3F3; */
	border-bottom: 1px solid gray;
	height: 12px;
	padding: 3px;
	text-align: center;
}

</style>

</head>
<body>
	<%
	IEmailService service = EmailServiceImpl.getInstance();
	 EmployeesVO evo = (EmployeesVO) session.getAttribute("userVO"); 
	List<EmailVO> receiveList = service.selectReceiveEmails(evo.getEmp_id());
	%>


<div id='receiveMailArea' class='mailarea'>
			<div class='mailTiel'>
			<table>
					<tr>
						<!-- <td class ="menu" >발신자 이메일</td> -->
						<td class ="menu" style="width: 140px">발신자</td>
						<td class ="menu" style="width: 280px" >제 목</td>
						<td class ="menu" >날 짜</td>
					</tr>
				</table></div>
			<%
			int cnt =0;
			for (EmailVO vo : receiveList) {
				
				if(cnt<3){
			%>
			<div class='mail-list' id="<%=vo.getEmail_id()%>">
			<table>
			<tr>
				<%-- <td align="center"><%=vo.getEmail_sender_mail()%></td> --%>
				<td align="center" style="width: 140px"><%=vo.getEmail_sender_name()%></td> 
				<td align="center" style="width: 280px"><%=vo.getEmail_title()%></td>
				<td align="center"> <%=vo.getEmail_date()%></td>
				
				</tr>
				</table>
		 	
			</div>
			<%
				}
				cnt++;
			}
			%>
		</div>
</body>
</html>