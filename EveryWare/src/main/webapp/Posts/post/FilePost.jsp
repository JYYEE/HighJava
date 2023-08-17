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
	.ids{
	background-color: #f3f3f3;
	    width: 10%;
    font-size: 18px;
    font-weight: bold;
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
h2{
font-size : 50px;
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

/* td {
	text-decoration: underline;
} */

</style>
<script type="text/javascript">
	$(function() {
		$("#downloadFile").on('click', function() {
			var selectedOption = $('#choiceFile').find('option:selected').val();
			console.log(selectedOption);
			if(selectedOption!=0){
			var link = document.createElement('a');
			link.href = " ../downloadFiles/" + selectedOption;
			link.download = selectedOption;
			link.click();
			}
			
	})
	
	 $('#choiceFile').on('change', function() {
	      var selectedOption = $(this).find('option:selected');
	      var selectedValue = selectedOption.val();
	      console.log(selectedOption);
	      $('#result').text(selectedValue);
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
				<p style=" font-size: calc(0.9rem + .9vw); margin-left: 6px;">양식함</p>
				<table class="table table-bordered">

					<tbody>
						<tr>
							<td class="ids" align="center" style="width: 20%;">문서 양식 선택</td>
							<td style="width: 20%; ">
							<select id="choiceFile" style="width: 100%;text-align: center; height : 30px;">
									<option value=0 selected>- 양식선택 -</option>
									<option value="인사.doc">인사</option>
									<option value="품질관리.doc">품질관리</option>
									<option value="휴가.doc">휴가</option>
									
							</select>
							</td>
								<td class="ids"  align="center" style="width: 10%;">파 일</td>
							<td><div id='result' style="display: inline; font-size: 20px;"></div>
							<button type="button" id="downloadFile" style="float: right;">다운로드</button>
							</td>
							
						</tr>
					
						<tr>
							<td class="ids" align="center" style="width: 40px;">기안자</td>
							<td colspan="3" style = "font-size : 15px;" >EveryWare  인사팀</td>
						</tr>
						<tr>
							<td align="center" colspan="4" style="height: 400px; font-size : 30px; ">
							해당 문서는 EVERY WARE에 저작물 입니다.
					
							</td>
							
						</tr>
					</tbody>
				</table>
			</div>

			
		</div>
	</div>





</body>
</html>