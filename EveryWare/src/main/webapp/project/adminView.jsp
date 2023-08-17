<%@page import="groupware.emp.vo.EmployeesVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.4.min.js"
	type="text/javascript"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	//회원가입승인
   $(document).on('click','#btn',function(){
      apps=[]; // 승인될 vac_id가 저장될 배열
      $('.setApprove:checked').each(function(){
         app = $(this).closest('tr').find('td:first-child').text();
         apps.push(app);
         //console.log(apps);
      })
      $.ajax({
         url : '<%=request.getContextPath()%>/empApprove.do',
         type : 'get',
         data : {"list" : apps},
         success : function(res){
            location.reload();
            
         },
         error : function(xhr){
            //alert("상태 : " + xhr.status + "\ncode : " + xhr.status)
        	location.reload();
         },
         dataType : 'json'
      })

   }) //회원가입승인
   
//    // 직급변경
//    $(document).on('click', '#btn3', function(){
// 	   $.ajax({
<%-- 		   url : '<%=request.getContextPath()%>/SetPosi.do', --%>
// 		   type : 'post',
// 		   data : {}
// 	   })
//    })
 
// 	  $.ajax({
// 	    type: 'POST',
// 	    url: 'servlet_url',
// 	    data: { emp_id: emp_id, emp_name: emp_name, dept_id: dept_id, emp_tel: emp_tel, emp_mail: emp_mail },
// 	    success: function(response) {
// 	      alert('수정이 완료되었습니다.');
// 	      location.reload();
// 	    },
// 	    error: function(xhr, status, error) {
// 	      console.log(xhr.responseText);
// 	    }
// 	  });
	  
})
	// 사원정보를 수정하는 modal 창을열고 해당사원의 id값을 가져옴
	$(document).ready(function(){
		  $("a[data-id]").click(function(){
		    var id = $(this).data('id');
		    $('#modalValue').text("수정할 회원 아이디: "+id);

		    $(document).on('click', '#btn-set', function(){
			   $.ajax({
				   url : '<%=request.getContextPath()%>/SetPosi.do',
				   type : 'post',
				   data : $('#form').serialize() + '&id=' + id,
				   success : function(res){
					   console.log(res);
					   alert("전송이 완료되었습니다.");
					   $('#myModal').modal('hide');
				   },
				   error: function(xhr){
					   console.log("상태: "+ xhr.status);
				   },
				   dataType: 'json'
			   })
		   })
		   $('#myModal').on('hidden.bs.modal', function () {
			      alert('모달이 닫혔습니다.');
			  });
		  });	 
		  
	});
</script>
<style>
input[type="checkbox"] {
	display: none;
}

input[type="checkbox"]+label {
	display: inline-block;
	width: 25px;
	height: 25px;
	border: 2px solid rgb(32, 148, 250);
	position: relative;
}

input[id^="ck"]:checked+label::after {
	content: '✔';
	font-size: 25px;
	width: 30px;
	height: 30px;
	position: absolute;
	text-align: center;
	left: -3px;
	right: -5px;
	top: -10px;
	bottom: 0px;
}

#result3 {
	display: none;
}

button {
	height: 40px;
	width: 130px;
	background: #04AA6D;
	color: white;
	border-radius: 10px;
	border: 0;
	font-size: 18px;
	overflow: hidden;
	font-weight: bold;
}

input[type="text"] {
	width: 100%;
	padding: 12px;
	margin: 8px 0;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	background-color: #f8f8f8;
	font-size: 16px;
	border-radius: 4px;
}

button:active {
	background: white;
	color: #162938;
}


#setApproveArea, #setEmpArea {
	padding-top: 20px;
	width: 1400px;
}

.setApprove {
	width: 20px;
	height: 20px;
	border: 1px solid #ccc;
	background-color: #fff;
	outline: none;
	cursor: pointer;
}

textarea {
	resize: none;
}

table {
   margin: 5px;
   border-collapse : collapse;
   width: 99%;
}

td {
	width: 25%;
	/* text-align: center; */
	
	font-size: 18px;
	height: 30px;
	color: rgb(82,82,84);
	border: 1px solid lightgray;
   	height: 30px;
  	padding: 0.5em;
   	text-align: center;
}
th {
	width: 25%;
	/* text-align: center; */
	font-size: 20px;
	height: 30px;
	color: black;
	border: 1px solid lightgray;
   	height: 30px;
  	padding: 0.5em;
   	text-align: center;
	background: #F3F3F3;
}

table th:first-child,
table td:first-child {
   border-left: 0;
}

table th:last-child,
table td:last-child {
   border-right: 0;
}

.modal-title{
	color: white;
	font-weight: bold;
	text-align: center;
}

td input, textarea {
	width: 100%;
	border: none;
	background-color: transparent;
	outline: none;
}

.menu {
	width: 25%;
	background: #F3F3F3;
	border: 1px solid lightgray;
	height: 30px;
	padding: 0.5em;
	text-align: center;
}
#ckbox{
	text-align: center;
}
.modal-header{
	background-color: rgb(10, 132, 255);
	color: white;
	border-top-right-radius: 19px;
	border-top-left-radius: 19px; 
}
.modal-content{
	border-radius: 20px;
	background-color: rgb(229, 229, 234);
}
#myModal{
	font-weight: bold;
	color: rgb(0, 122, 255);
}
.btn-secondary{
	height: 40px;
	width: 130px;
	background: #04AA6D;
	color: white;
	border-radius: 10px;
	border: 0;
	font-size: 18px;
	overflow: hidden;
	font-weight: bold;
}
#empMail{
	border-radius: 10px;
}
h4{
	color: rgb(0, 122, 255);
	font-weight: bold;
}
.btn btn-primary{
	font-weight: bold;
	font-size: 24px;
}

a {
  	  text-decoration: none;
  	  font-weight: bold;
  }
a:hover { background-color: gray; } 
</style>
</head>
<body>
<!-- 	<button type="button" id='btn4' value='가입 승인'>가입 승인</button> -->
<!-- 	<button type="button" id='receiveMail' value='사원 관리'>사원 관리</button> -->
	
	<%
	int i = 1;
	%>
	<%
	List<EmployeesVO> list = (List<EmployeesVO>) request.getAttribute("list");
	%>

	<div id="setApproveArea" class="approveArea">
	<div>
		<h4> &nbsp;&nbsp; 회원가입 신청자 목록 <img src="../img/user.png" alt=""> </h4>
		<p></p>
		<table class="" border="0">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>부서</th>
					<th>전화번호</th>
					<th>메일</th>
					<!-- 				<th>가입승인</th> -->
					<th><button type="button" id="btn" class="btn btn-primary">승인</button>
					</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (EmployeesVO emp : list) {
				%>
				<tr>
					<td><%=emp.getEmp_id()%></td>
					<td><%=emp.getEmp_name()%></td>
					<td><%=emp.getDept_name()%></td>
					<td><%=emp.getEmp_tel()%></td>
					<td><%=emp.getEmp_mail()%></td>
					<td id="ckbox"><input type="checkbox" name="setApprove"
						class="setApprove" id="ck<%=i%>"> <label
						for="ck<%=i%>"></label></td>
				</tr>
			</tbody>
			<%
			i++;
			%>
			<%
			}
			%>
		</table>
		
	</div>
	<br>
	<br>


	<!-- --------------------------------------------------------------------------------------------- -->

	<%
	List<EmployeesVO> list2 = (List<EmployeesVO>) request.getAttribute("list2");
	%>
	<%
	int j = 1;
	%>
	<table class="">
		<h4>&nbsp;&nbsp; 사원관리</h4>
		</tr>
		<tr>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>부서</th>
				<th>직급</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (EmployeesVO emp2 : list2) {
			%>
			<tr>
				<td>
					<a data-toggle="modal" href="#myModal" id="modal<%=j%>"
					data-id="<%=emp2.getEmp_id()%>"><%=emp2.getEmp_id()%></a>
				</td>
				<td><%=emp2.getEmp_name()%></td>
				<!-- 부서 -->
				<td><%=emp2.getDept_name()%></td>
				<td><%=emp2.getPosition_name()%></td>

			</tr>
			<%
			j++;
			%>
			<%
			}
			%>
		</tbody>
	</table>

	<div id="setEmpArea" class="empArea">

		<!-- The Modal -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog modal-dialog-centered">

				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">사원 정보 수정</h4>
						<button type="button" class="close" data-dismiss="modal" hidden>&times;</button>
					</div>

					<!-- Modal body -->
					<form method="post" id="form">
						<div class="modal-body">
							<p id="modalValue">사원의 정보 수정</p>
							<select name="dept" id="dept" class="form-select form-select-lg">
								<option value="null">부서 수정</option>
								<option value="D001">인사팀</option>
								<option value="D002">총무팀</option>
								<option value="D003">재정팀</option>
								<option value="D004">개발팀</option>
								<option value="D005">영업팀</option>
							</select> <br>
							<br> <select name="posi" id="posi" class="form-select form-select-lg">
								<option value="null">직급 수정</option>
								<option value="P002">부장</option>
								<option value="P003">차장</option>
								<option value="P004">과장</option>
								<option value="P005">대리</option>
								<option value="P006">사원</option>
							</select> <br>
							<br> 
							<label for="">이메일</label>
							<input type="text" required id="empMail" name="empMail"/>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" id="btn-set">수정완료</button>
							<button type="button" class="btn btn-secondary" id="btn-set"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>