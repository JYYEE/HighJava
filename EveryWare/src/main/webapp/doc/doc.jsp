<%@page import="everyware.doc.vo.FileOfDocumentVO"%>
<%@page import="everyware.doc.vo.ApprovalVO"%>
<%@page import="everyware.doc.vo.RecipientVO"%>
<%@page import="everyware.doc.vo.DocumentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="<%=request.getContextPath() %>/doc/js/doc.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/doc/css/doc.css">


<title>Insert title here</title>
<script type="text/javascript">
<%
String menu = (String)request.getAttribute("menu");
int paramPage = (Integer)request.getAttribute("page");

DocumentVO dvo = (DocumentVO)request.getAttribute("vo");

String id = dvo.getDoc_id();
String no = dvo.getDoc_no();
String date = dvo.getDoc_date();
String finalDate = dvo.getAprv_date();
String empName = dvo.getEmp_name();
String empId = dvo.getEmp_id();
String title = dvo.getDoc_title();
String content = dvo.getDoc_content();
String docState = dvo.getDoc_state();
String docReject = dvo.getDoc_reject();
String myState = dvo.getMy_state();
int isScrap = dvo.getIs_scrap();

List<RecipientVO> rlist = (List<RecipientVO>)dvo.getRecipients();
List<ApprovalVO> alist = (List<ApprovalVO>)dvo.getApprovals();
List<FileOfDocumentVO> flist = (List<FileOfDocumentVO>)dvo.getFiles();

String recipients = "";
for(int i=0; i<rlist.size(); i++){
	if(rlist.size() == 5) {
		recipients = "전체";
		break;
	}
	if(i > 0){
		recipients += ", ";
	}
	recipients += rlist.get(i).getDept_name();
}
%>

mypath = '<%=request.getContextPath()%>';
console.log(mypath)
docId  = '<%=id %>';
isScrap = '<%=isScrap %>';


console.log(isScrap);


$(function(){
	
	if('<%=myState %>' != '진행중'){
		$('.approval-btn').css('display', 'none');
	}
	
	if(isScrap == 1){
		$('#scrap-star').attr('class', 'fa fa-star');
	} 
	
	
	approval = function() {
		  document.getElementById("approval-box").classList.toggle("show");
		  document.getElementById("approval-box").classList
		}
		
	reject = function() {
		  document.getElementById("reject-box").classList.toggle("show");
		}
	
	$('.approval-confirm-btn').on('click', function(){
		vtype = $(this).parent().find('input:checked').val();
		vnote = $(this).parent().find('textarea').val();
		$.checkServer();
	})
	
	// When the user clicks the button, open the modal 
	$('.approval-btn').on('click', function(){
		$('.approve-modal').css('display', 'block');
	})
	
	$('.note-btn').on('click', function(){
		$('.note-modal').css('display', 'block');
	})
		
	// When the user clicks on <span> (x), close the modal
	$('.close').on('click', function(){
		$('.modal').css('display', 'none');
	})
	
	$('.scrap-btn').on('click', function(){
		$.scrapServer();
	})
	
	$(document).mouseup(function (e){
		container = $('.modal');

		if( container.has(e.target).length === 0){
			container.css('display','none');
		}
	})
	
	showSnackbar = function() {
	  // Get the snackbar DIV
	  var x = document.getElementById("snackbar");
	
	  // Add the "show" class to DIV
	  x.className = "show";
	
	  // After 3 seconds, remove the show class from DIV
	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
	}
	
	$('.print-btn').on('click', function(){
		window.print();
	})
	
	backToList = function(){
		 location.href='<%=request.getContextPath()%>/document/<%=menu%>?page=<%=paramPage%>';
	}
	
})
	


</script>
</head>
<body>

<!-- 	<button type="button" class="approval-btn" onclick="approval()">결재</button>
	<div id="approval-box" class="dropdown-content">
	  <input type="password" placeholder="비밀번호를 입력하세요.">
	  <button type="button" class="confirm">확인</button>
  </div>
	<button type="button" class="reject-btn" onclick="reject()">반려</button>
	<div id="reject-box" class="dropdown-content">
		의견작성<br>
		<textarea rows="5" cols="25"></textarea><br>
	  <input type="password" placeholder="비밀번호를 입력하세요.">
	  <button type="button" class="confirm">확인</button>
  </div> -->
<div class="doc-content">
	<div class="btn-container">
		<button class="scrap-btn" style="font-size:24px; color:#FFCC33;"><i id="scrap-star" class="fa fa-star-o"></i></button>
		<button class="print-btn" style="font-size:24px; color:gray;"><i class="fa fa-print"></i></button>
	</div>
	<table>
		<tr>
			<td class="doc-header">문서번호</td>
			<td colspan="2" class="left-cell"><%=no %></td>
			<td class="doc-header">기안자</td>
			<td colspan="2" class="left-cell"><%=empName %></td>
		</tr>
		<tr>
			<td class="doc-header">기안일자</td>
			<td colspan="2" class="left-cell"><%=date %></td>
			<td class="doc-header">최종결재일자</td>
			<td colspan="2" class="left-cell"><%=finalDate %></td>
		</tr>
	</table>
	<table>
		<tr>
			<td class="doc-header">수신처</td>
			<td colspan="5" class="left-cell"><%=recipients %></td>
		</tr>
	</table>
	<br>
	<span>&nbsp;결재선</span> <button type="button" class="note-btn">결재의견</button>
	<table>
		<tr>
			<!-- <td class="doc-header">결재</td> -->
			<td colspan="6">
			
<%
	for(ApprovalVO avo : alist){
		String EmpId = avo.getEmp_id();
		String deptName = avo.getDept_name();
		String aprvState = avo.getAprv_state();
		String EmpName = avo.getEmp_name();
		String positionName = avo.getPosition_name();
		String aprvDate = avo.getAprv_date();
		
		
		if(aprvState.equals("결재")){
			aprvState = "<img src='" + request.getContextPath() + "/mypage/img/sign/" + EmpId + "?v=1' onerror='this.src=\"" + request.getContextPath() + "/mypage/img/승인.png\";'></a>";
		}
		
%>
				<table class="approve-table">
					<tr><td><%=positionName %></td><tr>
					<tr>
						<td class="approve-status">
							<%=aprvState %>
						</td>
					<tr>
					<tr><td class="approver-name"><%=EmpName %></td><tr>
					<tr><td class="approve-date"><%=aprvDate %></td><tr>
				</table>
<%		
	}
%>
			</td>
		</tr>
	</table>	
	<br>	
	<table>
		<tr>
			<td class="doc-header">제목</td>
			<td colspan="5" class="left-cell"><%=title %></td>
		</tr>
		<tr>
			<td colspan="6" class="content">
				<div><%=content %></div>
			</td>
		</tr>
		<tr>
			<td class="doc-header">첨부파일</td>
			<td colspan="5" class="left-cell">
<%
	for(FileOfDocumentVO fvo : flist){
		String fname = fvo.getFile_name();
		String fpath =  fvo.getFile_path();
		long fsize = fvo.getFile_size();
%>
				<a href="<%=request.getContextPath()%>/fileDownload.do?path=<%=fpath %>&name=<%=fname %>">
					<%=fname %> (<%=Math.round(fsize/1024.0*100.0)/100.0 %>MB)
				</a>
<%		
	}
%>
			</td>
		</tr>
	</table>
	<span class="list-btn" onclick="backToList()">뒤로</span>
	<span class="approval-btn">결재</span>
</div>

<!-- The Modal -->
<div class="note-modal modal">
  <!-- Modal content -->
  <div class="note-modal-content modal-content">
		<span class="close">&times;</span>
		<table class="aprv-note-table">
			<caption>결재의견</caption>
<%
	for(ApprovalVO avo : alist){
		String empNameNote = avo.getEmp_name();
		String empPositionNote = avo.getPosition_name();
		String aprvNote = avo.getAprv_note();
		String aprvDateNote = avo.getAprv_date();
		String aprvStateNote = avo.getAprv_state();
		
		if("결재".equals(aprvStateNote) || "반려".equals(aprvStateNote)){
%>
	<tr>
			<td class="note-table-header">
				<%=empNameNote %> <%=empPositionNote %><br>
				<span class="note-date"><%=aprvDateNote %></span>
			</td>
			<td class="note-table-content"><%=aprvNote %></td>
	</tr>
<%
		}
	}
%>
		</table>
  </div>
</div>

<div class="approve-modal modal">
  <!-- Modal content -->
  <div class="approve-modal-content modal-content">
    <span class="close">&times;</span>
    <div class="modal-radio">
    <label class="container">승인
		  <input type="radio" checked="checked" name="approval-type" value="confirm">
		  <span class="checkmark"></span>
		</label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label class="container">반려
		  <input type="radio" name="approval-type" value="reject">
		  <span class="checkmark"></span>
		</label>
		</div>
    <br>결재의견<br>
    <textarea rows="5" cols="42" style="resize: none;"></textarea><br>
    <span class="approval-confirm-btn">확인</span>
  </div>
</div>

<div id="snackbar"></div>
</body>
</html>