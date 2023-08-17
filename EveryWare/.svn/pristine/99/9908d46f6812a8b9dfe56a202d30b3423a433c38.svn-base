<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="everyware.posts.vo.PostsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- 	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	
	 -->

<script src="<%=request.getContextPath()%>/Posts/js/summernote-lite.js"></script>
<script src="<%=request.getContextPath()%>/Posts/js/summernote-ko-KR.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">


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

.move-board {
	text-decoration: none;
	color: black;
}

.subboard-title {
	font-size: 18px;
	font-weight: bold;
	background-color: #ddd;
}

/* .table-bordered>thead>tr>td {
	border: 1px solid #06010152;
} */
table{
   margin: 5px;
   border-collapse : collapse;
   width: 99%;
}
table td,
table th{
  /*  border: 1px solid lightgray; */
   height: 30px;
   padding: 0.5em;
   text-align: center;
}

.list-table th:first-child,
.list-table td:first-child {
   border-left: 0;
}
.list-table th:last-child,
.list-table td:last-child {
   border-right: 0;
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

a {
	float: left;
}

.col-sm-8 {
	width: 90%;
}

.col-sm-2 {
	width: 22%;
}

.ts {
	background-color: #f3f3f3;
	width: 10%;
	font-size: 18px;
	font-weight: bold;
}

h2 {
	font-size: 50px;
}

p {
	font-size: 15px;
}
#button{
float : right;
}
hr{
height: 1px;
background: lightgray;
border: 0;
}
#ids{
 font-size : 15px;
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


/* td {
	text-decoration: underline;
} */
</style>
<script type="text/javascript">
	$(function() {

		$("delete_btn").on('click', function() {

		})
		$('#summernote')
				.summernote(
						{
							lang : 'ko-KR', // default: 'en-US'
							height : 540, // set editor height
							minHeight : null, // set minimum height of editor
							maxHeight : null, // set maximum height of editor
							focus : true, // set focus to editable area after initializing summernote

							toolbar : [
									[ 'fontname', [ 'fontname' ] ],
									[ 'fontsize', [ 'fontsize' ] ],
									[
											'style',
											[ 'bold', 'italic', 'underline',
													'clear' ] ],
									[ 'color', [ 'color' ] ],
									[ 'table', [ 'table' ] ],
									[ 'para', [ 'paragraph' ] ],
									[ 'insert', [ 'picture', 'link', 'video' ] ],
									[ 'view', [ 'codeview' ] ] ],
							fontNames : [ '맑은 고딕', '굴림', '돋움', '궁서', '바탕',
									'HY견고딕', '휴먼둥근헤드라인', 'Arial',
									'Arial Black', 'Comic Sans MS',
									'Courier New', 'Helvetica neue',
									'Helvetica', 'Impact' ],
							fontNamesIgnoreCheck : [ '맑은 고딕', '굴림', '돋움', '궁서',
									'바탕', 'HY견고딕', '휴먼둥근헤드라인', 'Arial',
									'Arial Black', 'Comic Sans MS',
									'Courier New', 'Helvetica neue',
									'Helvetica', 'Impact' ],
							lineHeights : [ '0.2', '0.3', '0.4', '0.5', '0.6',
									'0.8', '1.0', '1.2', '1.4', '1.5', '2.0',
									'3.0' ]
						});
	})
</script>
</head>
<body>
	<%
	PostsVO vo = (PostsVO) request.getAttribute("subtitle");
	EmployeesVO evo = (EmployeesVO) session.getAttribute("userVO");
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

					<%
					
					
					if (vo == null) {
					%>
					<tr>
						<td colspan="5" style="text-align: center">글이 하나도 없습니다</td>
					</tr>
					<%
					} else {
					%>
					<tr>
						<td class="ts" align="center">제목</td>
						<td><%=vo.getPost_title()%></td>
						<td class="ts" align="center">소제목</td>
						<td colspan="3"><%=vo.getPost_subtitle()%></td>
					</tr>

					<tr>
						<td class="ts" align="center">글쓴이</td>
						<td><%=vo.getEmp_id()%></td>
						<td class="ts" align="center">날 짜</td>
						<td><%=vo.getPost_date()%></td>
						<td class="ts" align="center">조회수</td>
						<td align="center"><%=vo.getPost_cnt()%></td>
					</tr>
				</table>
				<table class="table table-bordered">
					<tr>
						<td class="ts" style="width: 55px">내용</td>
						<td><div id="board-memo"><%=vo.getPost_content()%></div></td>
					</tr>

				</table>

				<form action="<%=request.getContextPath()%>/DeletePost.do">
					<input type="hidden" name="postId" value="<%=vo.getPost_id()%>">
					<table style="width: 100%">
						<tr style="border: none">
							<td id="button" colspan="2" align="right" style="float: right;">
								<!-- <button type="button" id='modity_btn'>수정</button> -->
								<%if(evo.getEmp_id().equals("admin")){ %>
								<button type="button" data-toggle="modal" data-target="#myModal">수정</button>
								<button type="submit">삭제</button>
								<%} %>
							</td>
						</tr>
					</table>
				</form>


				<div class="modal fade" id="myModal" role="dialog" >
					<div class="modal-dialog" style="width: 55%">

						<!-- Modal content-->
						<div class="modal-content" style="width: 1200px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">게시글 수정</h4>
							</div>
							<div class="modal-body">
								<form action="<%=request.getContextPath()%>/UpdateRePosts">
									<input type="hidden" name="updatePostId"
										value="<%=vo.getPost_id()%>">
									<table class="table table-bordered" >
										<tr>
											<td class="ts" align="center">제목</td>
											<td><input type="text" id="title" name="title"
												value="<%=vo.getPost_title()%>"></td>
											<td class="ts" align="center">소제목</td>
											<td><input type="text" id="subtitle" name="subtitle"
												value="<%=vo.getPost_subtitle()%>"></td>
										</tr>
										<tr>
											<td style="width: 50px">내용</td>
											<td colspan="4" align="left"><textarea
													class="board-memo" name="content" id="summernote"><%=vo.getPost_content()%></textarea>

												<button type="submit" style="float: right;">수 정</button></td>
										</tr>
									</table>
								</form>
							</div>
						</div>

					</div>
				</div>
				<%
				}
				%>


			</div>

		</div>
	</div>



</body>
</html>
