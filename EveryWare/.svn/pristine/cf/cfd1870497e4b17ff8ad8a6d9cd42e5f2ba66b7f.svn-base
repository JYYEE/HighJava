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

	<script src="<%=request.getContextPath()%>/Posts/js/summernote-lite.js"></script>
<script src="<%=request.getContextPath()%>/Posts/js/summernote-ko-KR.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	

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


@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}


th {
	width: 65px;
}

#form-group {
	width: 65px;
}

.form-control {
	width: 950px;
}

.ts {
background-color: #6c636352;
	    width: 10%;
    font-size: 18px;
    font-weight: bold;
	}

.board-memo {
	font-size: 18px;
	width: 100%;
	height: 500px;
 	resize: none;
 	/* outline: none; */
/*	pointer-events: none;
	background-color: #eee; */
}
#title , #subtitle{
width: 100%;
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
    button {
	color: white;
	background: #04AA6D;
	border-radius: 7px;
	width: 130px;
	height: 40px;
	border: none;
}

button:active {
	background: white;
	color: #1286D7;
}

</style>

<script type="text/javascript">
$(function(){
	$('#summernote').summernote({
	      lang : 'ko-KR',              // default: 'en-US'
	      height: 500,                 // set editor height
	      minHeight: null,             // set minimum height of editor
	      maxHeight: null,             // set maximum height of editor
	      focus: true,                 // set focus to editable area after initializing summernote

	      toolbar: [
	         ['fontname', ['fontname']],
	         ['fontsize', ['fontsize']],
	         ['style', ['bold', 'italic', 'underline', 'clear']],
	         ['color', ['color']],
	         ['table', ['table']],
	         ['para', ['paragraph']],
	         ['insert', ['picture', 'link', 'video']],
	         ['view', ['codeview']]
	      ],
	      fontNames: [ '맑은 고딕', '굴림', '돋움', '궁서', '바탕', 'HY견고딕', '휴먼둥근헤드라인', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Helvetica neue', 'Helvetica', 'Impact'],
	      fontNamesIgnoreCheck: [ '맑은 고딕', '굴림', '돋움', '궁서', '바탕', 'HY견고딕', '휴먼둥근헤드라인', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Helvetica neue', 'Helvetica', 'Impact'],
	      lineHeights: ['0.2', '0.3', '0.4', '0.5', '0.6', '0.8', '1.0', '1.2', '1.4', '1.5', '2.0', '3.0']
	   });

})
</script>
</head>
<body>


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
				
			<form action="<%=request.getContextPath()%>/InsertFree.do">
				<table class="table table-bordered">
					<tr>
						<td class="ts" align="center" >제목</td>
						<td><input type="text" id="title" name="title"></td>
						<td class="ts" align="center" >소제목</td>
						<td><input type="text" id="subtitle" name="subtitle"></td>
					</tr>
                     <tr>
						<td class = "ts" style="width: 50px;  text-align: center;  vertical-align: inherit;  "   >내용</td>
						<td colspan="4"><textarea class="board-memo" name="content"id="summernote"></textarea>
						
						<button type="submit" style="float: right;">저 장</button>
						</td>
					</tr>
				</table>
			</form>
			</div>

		</div>
	</div>

</body>
</html>