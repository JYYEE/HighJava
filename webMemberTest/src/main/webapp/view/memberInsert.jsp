<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.6.4.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$('#checkId').on('click', function(){
		memId = $('#memId').val().trim();
		$.ajax({
			url : '<%=request.getContextPath()%>/checkId.do',
			type : 'get',
			data : {"memId" : memId },
			success : function(res){
				if( res>=1){
					alert("중복된 아이디 입니다. 다른 아이디를 입력해주세요.")
				} else {
					alert("사용가능한 아이디 입니다.")
				}
			},
			error : function(xhr){
				alert("상태 : " + xhr.status + "\ncode : " + xhr.statust);
			}
		})
	})
	$('#memPassch').on('keyup', function(){
		memPass = $('#memPass').val().trim();
		memPassch = $(this).val().trim();
		if(memPass == memPassch){
			$('#memPassch').css('border', '2px solid green')
		} else {
			$('#memPassch').css('border', '2px solid red')
		}
	})
})

</script>
</head>
<body>
<h3>회원정보 입력 폼</h3>
<form action="<%=request.getContextPath() %>/memberInsert.do" method="post" enctype="multipart/form-data">
<table border="1">
	<tr>
		<td>회원ID</td>
		<td><input type="text" name="memId" id="memId" required="required">
		<input type="button" value="중복확인" id="checkId" ></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="memPass" id="memPass" ></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password" name="memPassCh" id="memPassch"></td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td><input type="text" name="memName" id="memName"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="memTel" id="memTel"></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><input type="text" name="memAddr" id="memAddr"></td>
	</tr>
	<tr>
		<td>프로필 사진</td>
		<td><input type="file" value="파일선택" name="memPhoto" id="memPhoto"></td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" value="저장" >
		<input type="button" value="취소" onclick="location.href='<%=request.getContextPath() %>/memberList.do'">
		<input type="button" value="회원목록" onclick="location.href='<%=request.getContextPath() %>/memberList.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>