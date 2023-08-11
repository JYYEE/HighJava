<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../js/jquery-3.6.4.min.js" type="text/javascript"></script>
<%-- src="<%=request.getContextPath() %>/js/jquery-3.6.4.min.js" 
	 request.getContextPath() 는 webapp를 의미함.--%>
<script type="text/javascript">
$(function(){
	$('#lprodBtn').on('click', function(){
		$.ajax({
			url : '<%=request.getContextPath()%>/lprodList.do',
			type : 'get',
			dataType : 'json',
			success : function(res){
				code ="<table border='1'><tr><td>아이디</td><td>상품코드</td><td>상품명</td></tr>"
				$.each(res, function(i,v){
					code +="<tr><td>"+v.lprod_id +"</td><td>"+v.lprod_gu+"</td><td>"+v.lprod_nm +"</td></tr>"
				})
				code +="</table>"
				$('#result').html(code);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status + "\ncode : " + xhr.statust);
			}
		})
	})
	
	$('#lprodBtn2').on('click', function(){
		location.href="<%=request.getContextPath()%>/lprodList2.do";
	})
})
</script>
</head>
<body>
<!-- 
	아래의 'Lprod자료 가져오기'버튼을 클릭하면 DB의 LPROD테이블의 전체 데이터를 가져와 
	id가 'result'인 <div>태그에 표로 출력하시오.
	(Ajax이용, MVC패턴 사용, 서블릿의 URL패턴 : /lprodList.do)
 -->

<h3>Lprod 자료 목록</h3>
<form>
	<input type="button" id="lprodBtn" value="Lprod자료 가져오기(Ajax이용)"><br>
	<input type="button" id="lprodBtn2" value="Lprod자료 가져오기(Non Ajax)"><br>
</form>
<div id="result"></div>
</body>
</html>