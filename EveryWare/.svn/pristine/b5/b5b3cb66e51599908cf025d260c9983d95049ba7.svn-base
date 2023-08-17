<%@page import="everyware.posts.service.PostsServiceImpl"%>
<%@page import="everyware.posts.vo.PostsVO"%>
<%@page import="java.util.List"%>
<%@page import="everyware.posts.service.IPostsService"%>
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
	IPostsService service = PostsServiceImpl.getInstance();
	List<PostsVO> list = service.getAllPosts();
	%>
	<table >
	<tr style="text-align: center; border-bottom: 1px solid gray;">
	<th  align="center" style="width: 80px">목록</th>
	<th  align="center" style="width: 50%">제목</th>
	<th  align="center">날짜</th>
	</tr>
	<%
	if (list == null || list.size() == 0) {
	%>
	
	
	<tr>
		<td colspan="5" style="text-align: center;">저장된 게시글이 하나도 없습니다</td>
	</tr>

	<%
	} else {
	int count = 1;
	for (PostsVO vo : list) {
		if(count <=5){
	%>
	
	
	<tr style="text-align: center;" >
		<td  id="count" align="center"><%=count++%></td>
		<td  id="board" align="left" ><%=vo.getPost_title()%></td>
		<td id="date" align="center"><%=vo.getPost_date()%></td>
	</tr>

	<%
		}

	}
	}
	%>
	</table>

</body>
</html>