package kr.ddit.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/memberUpdate.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024
		* 100)
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String memId = request.getParameter("memId");
		System.out.println(memId);
		IMemberService service = MemberServiceImpl.getInstance();
		MemberVO memVO = service.getMember(memId);
		System.out.println(memVO.toString());
		request.setAttribute("memVO", memVO);
		request.getRequestDispatcher("/view/memberUpdate.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String memId = request.getParameter("memId");
		String memPass = request.getParameter("memPass");
		String memName = request.getParameter("memName");
		String memTel = request.getParameter("memTel");
		String memAddr = request.getParameter("memAddr");

		MemberVO memVO = new MemberVO();
		memVO.setMem_id(memId);
		memVO.setMem_pass(memPass);
		memVO.setMem_name(memName);
		memVO.setMem_tel(memTel);
		memVO.setMem_addr(memAddr);

		// 사진 업로드 경로
		String uploadPath = "d:/d_other/uploadFiles";
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = ""; // 사진 이름
		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			if (!"".equals(fileName)) {
				try {
					part.write(uploadPath + File.separator + fileName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		memVO.setMem_photo(fileName);
		IMemberService service = MemberServiceImpl.getInstance();
		int cnt = service.updateMember(memVO);
		response.sendRedirect(request.getContextPath() + "/memberList.do");
	}

	private String extractFileName(Part part) {
		String fileName = ""; // 반환할 파일명이 저장될 변수]
		String headerValue = part.getHeader("content-disposition"); // 헤더의 '키값'을 이용하여 값을 구한다.

		String[] items = headerValue.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) { // ""안에 filename은 소문자로 작성
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}

}
