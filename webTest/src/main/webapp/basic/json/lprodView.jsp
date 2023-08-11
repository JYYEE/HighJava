<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	// controller 데이터 불러오기
	List<LprodVO> lprodList = (List<LprodVO>)request.getAttribute("lprodList");
	Gson gson = new Gson();
	String result = gson.toJson(lprodList);
	out.print(result);
	out.flush();

%>