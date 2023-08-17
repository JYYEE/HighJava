<%@page import="java.util.List"%>
<%@page import="everyware.posts.vo.PostsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 450px
}

/* Set gray background color and 100% height */

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
.move-board{
text-decoration: none;
color: black;

}
.subboard-title{
font-size: 18px;
font-weight: bold;
background-color: #f3f3f3;
   
}


.table-bordered>thead>tr>td{
border: 1px solid #06010152;
  
}
	button{
	color: white;
	background : #04AA6D ;
	border-radius : 10px;
	width: 130px;
	height: 40px;
	border: none;
	}
	button:active{
	background: white;
	color: #1286D7 ;
	
	}
a{
 float: left;
}
.col-sm-8 {
    width: 90%;
}
.col-sm-2 {
    width: 22%;
}

p{
 font-size: 30px;
}
h2 {
    font-size: calc(1.325rem + .9vw);
    margin-left: 6px;
    font-weight: 500;
}
  .btn-group {
        white-space: nowrap;
        float: left;
    }
   #date{
   width: 25%;
   }
   #board{
   width : 60%;
   text-align: center;
   }
   #count{
   width : 15%;
   }
/* td {
	text-decoration: underline;
} */

</style>
</head>
<body>
	<%
	List<PostsVO> list = (List<PostsVO>) request.getAttribute("PostsList");
	
	%>
	

	<div class="container-fluid text-center">
		<div class="row content">
		 	<div class="btn-group">
    <a href="<%=request.getContextPath()%>/postList.do" style="display: inline-block;  color: white; background: #04AA6D;  padding: 10px 24px; border: none; border-radius: 4px; margin: 3px;">공지사항</a>
    <a href="<%=request.getContextPath()%>/freeposts.do" style="display: inline-block;  color: white; background: #04AA6D;  padding: 10px 24px; border: none; border-radius: 4px; margin: 3px;">자유 게시판</a>
    <a href="<%=request.getContextPath()%>/Posts/post/FilePost.jsp" style="display: inline-block;  color: white;  background: #04AA6D; padding: 10px 24px; border: none; border-radius: 4px; margin: 3px;">서류 양식함</a>
</div>
			<div class="col-sm-8 text-left">
				<h2>게시판</h2>
		<hr style="height: 1px; background: lightgray; border: 0;">
				<p style=" font-size: calc(0.9rem + .9vw); margin-left: 6px;">공지사항</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<td class="subboard-title" align="center">번 호</td>
							<td class="subboard-title" align="center">제 목</td>
							<td class="subboard-title" align="center">날 짜</td>
						</tr>
					</thead>
					<tbody>
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
						%>
						<tr>
							<td id="count" align="center" style="font-size: 20px"><%=count++ %></td>
							<td id="board"align="center" style="font-size: 20px; text-align: center;" ><a class="move-board" href="<%=request.getContextPath()%>/NoticeFree.do?postId=<%=vo.getPost_id()%>"> <%=vo.getPost_title()%> </a></td>
							<td id="date" align="center"  style="font-size: 20px"><%=vo.getPost_date()%></td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
					<button type="button" style="float: right;" onclick="location.href='<%=request.getContextPath()%>/Posts/post/inserFree.jsp'">글쓰기</button>
			</div>
		</div>
	</div>

</body>
</html>