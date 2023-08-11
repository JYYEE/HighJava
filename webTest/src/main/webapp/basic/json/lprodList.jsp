<%@page import="com.google.gson.Gson"%>
<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<LprodVO> lprodList = (List<LprodVO>)request.getAttribute("lprodList");
%>

<table border="1">
  <tr>
    <td>LPROD_ID</td>
    <td>LPROD_GU</td>
    <td>LPROD_NM</td>
  </tr>
<%
	for(LprodVO lvo : lprodList){
%>
	<tr><td><%=lvo.getLprod_id() %></td>
	    <td><%=lvo.getLprod_gu() %></td>
	    <td><%=lvo.getLprod_nm() %></td></tr>
<%   
	}
%>    
</table>

