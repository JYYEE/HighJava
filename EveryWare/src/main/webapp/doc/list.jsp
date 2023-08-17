<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/doc/js/list.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/doc/css/list.css">
<title>Everyware</title>
<script type="text/javascript">

<%
	String sPage = request.getParameter("page");
	int paramPage = 1;
	if(sPage != null){
		paramPage = Integer.parseInt(sPage);
	}
	
%>
currentpage = 1;
paramPage = <%=paramPage%>;
if(paramPage > 0){
	currentpage = paramPage;
}

mypath = '/EveryWare';
mymenu = "<%=request.getAttribute("menu")%>";


$(function(){
	if(mymenu == "received"){
		$('.list_doc_date').remove();
		$('.list_doc_state').remove();
		$('.currentMenu').text('수신문서함');
		
	} else if(mymenu == "approval"){
		$('.currentMenu').text('결재문서함');
		
	} else if(mymenu== "scrapped"){
		$('.list_doc_date').remove();
		$('.list_doc_state').remove();
		$('.currentMenu').text('보관문서함');
	
	} else if(mymenu== "uploaded"){
		$('.currentMenu').text('기안문서함');
		
	}
	
	
	
	$.listPageServer(1);
	
	// 페이지번호 클릭
	$(document).on('click', '.pageno', function(){
		currentpage = $(this).text().trim();
		$.listPageServer(currentpage);
	})
	
	// 다음 클릭
	$(document).on('click', '#next', function(){
		currentpage = parseInt($('.pageno').last().text().trim()) + 1;
		$.listPageServer(currentpage);
	})
	
	// 이전 클릭
	$(document).on('click', '#prev', function(){
		currentpage = parseInt($('.pageno').first().text().trim()) - 1;
		$.listPageServer(currentpage);
	})
	
	//----------------------------------------
	
 	$('#searchBtn').on('click', function(){
 		$.listPageServer(1);
	}) 
	
	$(document).on('click', '.doc-title', function(){
		id = $(this).attr('id');
		location.href='<%=request.getContextPath()%>/selectDoc.do?id='+id+'&menu='+mymenu+'&page='+currentpage;
	})
})
</script>
</head>
<body>
<!-- <a href="http://localhost/EveryWare/document/write">새 문서 작성</a>
<a href="http://localhost/EveryWare/document/received">수신문서함</a>
<a href="http://localhost/EveryWare/document/uploaded">기안문서함</a>
<a href="http://localhost/EveryWare/document/approval">결재문서함</a>
<a href="http://localhost/EveryWare/document/scrapped">보관문서함</a> -->
<div class="container">
	<h2 class="currentMenu"></h2>
	<div class="search-container">
		<table class="search-table">
			<tr>
				<td class="searchDate" colspan="2">
					<label>기안일자</label>
					<input type="date" id="sDateFrom">~
					<input type="date" id="sDateTo">
				</td>
			</tr>
			<tr>
				<td class="searchState">
					<label for="sStateType">구분</label>
					<select id="sStateType" class="selectType">
						<option value="" selected>전체</option>
						<option value="진행중">진행중</option>
						<option value="결재">결재</option>
						<option value="반려">반려</option>
					</select>
				</td>
				<td class="searchWord">
					<label for="stype">검색</label>
					<select id="stype" class="selectType">
						<option value="" selected>전체</option>
						<option value="doc_title">제목</option>
						<option value="emp_name">작성자</option>
						<option value="dept_name">부서</option>
					</select>
						<input type="text" id="sword" placeholder="검색어를 입력하세요.">
				</td>
			</tr>
		</table>
		<div class="wrapper">
			<span id="searchBtn">검색</span>
		</div>
	</div>
  <table class="list-table">
    <thead>
      <tr class="list_title">
        <th class="list_no">번호</th>
        <th class="list_doc_no">문서번호</th>
        <th class="list_doc_title">제목</th>
        <th class="list_doc_writer">기안자</th>
        <th class="list_doc_date">기안일자</th>
        <th class="list_aprv_date">결재일자</th>
        <th class="list_doc_state">구분</th>
      </tr>
    </thead>
    <tbody id="resultTable"></tbody>
  </table>
  <div id="pagelist"></div>
<div></div>
</div>
</body>
</html>