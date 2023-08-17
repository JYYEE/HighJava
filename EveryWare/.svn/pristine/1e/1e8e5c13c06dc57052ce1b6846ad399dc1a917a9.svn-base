package everyware.mypage.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import groupware.emp.vo.EmployeesVO;

@WebServlet("/updateSign.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100)
public class UpdateSignature extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		String empId = evo.getEmp_id();
		
		String uploadPath = "D:\\A_TeachingMeterial\\webpro\\EveryWare\\src\\main\\webapp\\mypage\\img\\sign";
		System.out.println("이미지 저장 경로" + uploadPath);
//		String uploadPath = "./resources/img/sign";
		
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		String fileName = "";
		
		for(Part part : request.getParts()) {
			fileName = extractFileName(part);
			
			if(!"".equals(fileName)) {
				
				String filePath = uploadPath + File.separator + empId;
				
				try {
					part.write(filePath);
					
				} catch (Exception e) {
					response.getWriter().write(new Gson().toJson("관리자에게 문의하세요."));
					response.flushBuffer();
				}
				
			}
		}
		
		response.getWriter().write(new Gson().toJson("정상적으로 수정되었습니다."));
		response.flushBuffer();
		
	}
	
	private String extractFileName(Part part) {
		String fileName = "";  //파일명이 저장될 변수
		String headerValue = part.getHeader("content-disposition");  // 헤더의 '키값'을 이용하여 값을 구한다.
		
		String[] items = headerValue.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
		}
		
		return fileName;
	}

}
