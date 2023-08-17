<%@page import="everyware.doc.vo.DepartmentVO"%>
<%@page import="everyware.doc.vo.DocumentVO"%>
<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/doc/js/summernote/summernote-lite.js"></script>
<script src="<%=request.getContextPath() %>/doc/js/summernote/lang/summernote-ko-KR.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/doc/css/summernote/summernote-lite.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/doc/css/write.css">

<script type="text/javascript">
$('*').removeAttr('autofocus');
$(':focus').blur();  

function moveUp() {
  var selectedSpan = $(".selected");
  if (selectedSpan.prev().length > 0) {
    selectedSpan.insertBefore(selectedSpan.prev());
  	}
}

function moveDown() {
  var selectedSpan = $(".selected");
  if (selectedSpan.next().length > 0) {
    selectedSpan.insertAfter(selectedSpan.next());
  }
}

$(function(){
	$('*').removeAttr('autofocus');
	$(':focus').blur();  
	
	$('#summernote').summernote({
		lang : 'ko-KR',              // default: 'en-US'
		height: 650,                 // set editor height
		minHeight: null,             // set minimum height of editor
		maxHeight: null,             // set maximum height of editor
		focus: true,                 // set focus to editable area after initializing summernote
		width: 750,
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
	
	$('#info').on('click', function(){
		title = $('input[name="title"]').val();
		content = $('textarea[name="content"]').html($('#summernote').summernote('code'));
		console.log(title);
		console.log(content);
	})
	
	$('.element').on('dblclick', function(){
		idv = $(this).attr('id');
		if($(this).parents('.container').find('.select-box #' + idv).length == 0){
			$(this).parents('.container').find('.select-box').append($(this).clone());
		}
	})
	
	$(document).on('dblclick', '.select-box .element', function(){
		$(this).remove();
	})
	
	$(document).on('click', '.select-box .emp-name', function(){
		 $('.select-box .emp-name').removeClass("selected");
		 $(this).addClass("selected");
	})
	
	submit = function(isTemp){
		approvals = [];
		recipients = [];
			
		$('.selected-approval .select-box .element').each(function(i){
			emp_id = $(this).attr('id');
			aprv_order = i;
			obj = {};
			obj["emp_id"] = emp_id;
			obj["aprv_order"] = aprv_order;
			approvals.push(obj);
		})
			
		$('.selected-recipients .select-box .element').each(function(){
			dept_id = $(this).attr('id');
			obj = {};
			obj["dept_id"] = dept_id;
			recipients.push(obj);
		})
		
		japprovals = JSON.stringify(approvals);
		jrecipients = JSON.stringify(recipients);
		
		$('#approvals').val(japprovals);
		$('#recipients').val(jrecipients);
		$('#isTemp').val(isTemp);
		
		$('form').submit();
	}
	
	tempList = function(){
		$('.modal').css('display', 'block');
	}
	
	$('.close').on('click', function(){
		$('.modal').css('display', 'none');
	})
	
	$(document).mouseup(function (e){
		var container = $('.modal');

		if( container.has(e.target).length === 0){
			container.css('display','none');
		}
	})
	
	$('.temp-item').mouseover(function(){
		$('.temp-delete-btn').css('display', 'inline');
	})
	
	$('.temp-item').mouseout(function(){
		$('.temp-delete-btn').css('display', 'none');
	})
	
	$('.temp-item').on('click', function(){
		idv = $(this).attr('id');
		console.log(idv);
		
		$.ajax({
			url: `<%=request.getContextPath()%>/selectDoc.do`,
			type: 'get',
			data: { "id" : idv,
							"isTemp" : 1 },
			success: function(res){
				console.log(res);
				
				title = res.doc_title;
				content = res.doc_content;
				
				$('#doc-title').val(title);
				console.log(content);
				$('#summernote').summernote('pasteHTML', content);
				
				$('.modal').css('display', 'none');
			},
			error: function(xhr){
				alert('상태 : ' + xhr.status);
			},
			dataType: 'json'
			})
	})


});

//서머노트에 text 쓰기
$('#summernote').summernote('insertText');


// 서머노트 쓰기 비활성화
//$('#summernote').summernote('disable');

// 서머노트 쓰기 활성화
$('#summernote').summernote('enable');

/* 
// 서머노트 리셋
$('#summernote').summernote('reset');


// 마지막으로 한 행동 취소 ( 뒤로가기 )
$('#summernote').summernote('undo');
// 앞으로가기
$('#summernote').summernote('redo'); */
</script>
</head>
<body>
<%
	int tempCnt = (Integer)request.getAttribute("tempCnt");
%>
<div class="insert-doc-main">
	
<div class="container-wrap">
		<div class="emp-select-box">
		<div class="emp-select-header">
			<span><strong>결재자 선택</strong></span>
		</div>
		<div class="container">
		<div class="select-emp select">
		<%
			List<DepartmentVO> list = (List<DepartmentVO>)request.getAttribute("list");
		%>
				<ul class="tree dept">
		<%
			for(DepartmentVO vo : list){
		%>
					<li><span class="tree dept-name" id="<%=vo.getCode()%>"><%=vo.getCode_name() %></span>
						<ul class="tree emp">
		<%
				for(EmployeesVO evo : vo.getEmps()){
		%>
							<li>
								<div class="emp-container">
									<span class="emp-icon"></span>
									<span class="tree emp-name element" id="<%=evo.getEmp_id() %>"><%=evo.getEmp_name() %> <%=evo.getPosition_name() %></span>
								</div>
							</li>
		<%
				}
		%>
						</ul>
					</li>
		<%		
			}
		%>
				</ul>
			</div>
			<div class="line"></div>
			<div class="selected-approval selected-box">
				<div class="select-box"></div>
				<div class="button-wrap">
					<span onclick="moveUp()">▲</span><br>
					<span onclick="moveDown()">▼</span>
				</div>
			</div>
		</div>
	
	</div>
	
	<div class="dept-select-box">
		<div class="dept-select-header"><strong>수신처 선택</strong></div>
		<div class="container">
			<div class="select-dept select">
				<ul class="tree dept">
			<%
				for(DepartmentVO vo : list){
			%>
						<li><span class="team-icon"></span><span class="tree dept-name element" id="<%=vo.getCode()%>"><%=vo.getCode_name() %></span></li>
			<%		
				}
			%>
					</ul>
			</div>
			<div class="line"></div>
			<div class="selected-recipients selected-box">
				<div class="select-box"></div>
			</div>
		</div>
	</div>
</div>
	<div class="container-form">

		<!-- form 안에 에디터를 사용하는 경우 (보통 이경우를 많이 사용하는듯)-->
		<form action="<%=request.getContextPath() %>/insertDoc.do" method="post" onsubmit="false"  enctype="multipart/form-data">
			<div class="form-header">
				<label for="title">제 목</label>
				<input type="text" id="doc-title" name="title">
				<div class="end-btn-wrap">
					<span class="save-btn-wrap">
						<span class="save-btn" onclick="submit(1)">저장</span>
						<span class="temp-count-btn" onclick="tempList()"><%=tempCnt %></span>
					</span>
				</div>
			</div>
		  <textarea id="summernote" id="doc-content" name="content"></textarea>
		  <input type="file" name="file" id="doc-files" multiple="multiple"><br>
		  <input type="hidden" name="recipients" id="recipients">
		  <input type="hidden" name="approvals" id="approvals">
		  <input type="hidden" name="isTemp" id="isTemp">
	  </form>
	  <span class="upload-btn" onclick="submit(0)">완료 &#x1F4C4;</span>
	</div>
</div>

<!-- 임시저장 리스트 모달 -->
<div class="temp-modal modal">
  <!-- Modal content -->
  <div class="temp-modal-content modal-content">
		<span class="close">&times;</span>
		<div class="modal-content-container">
			<span class="temp-caption">임시저장 문서</span>
			<ul class="temp-list">
<%
	List<DocumentVO> tempList = (List<DocumentVO>)request.getAttribute("tempList");
	
	for(DocumentVO dvo : tempList){
		String docId = dvo.getDoc_id();
		String docTitle = dvo.getDoc_title();
		String docDate = dvo.getDoc_date();
%>
			<li id="<%=docId %>" class="temp-item">
				<span class="temp-title"><%=docTitle %></span><br>
				<span class="temp_date"><%=docDate %></span>
				<span class="temp-delete-btn">
					<i class="fa fa-trash"></i>
				</span>
			</li>
<%
	}
%>
			</ul>
		</div>
  </div>
</div>

<script type="text/javascript">
var toggler = $('.select-emp .dept-name');
var i;

for (i = 0; i < toggler.length; i++) {
  toggler[i].addEventListener("click", function() {
    this.parentElement.querySelector(".emp").classList.toggle("active");
    this.classList.toggle("dept-name-down");
  });
}
</script>
</body>
</html>