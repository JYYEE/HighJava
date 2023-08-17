package everyware.doc.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import everyware.doc.service.DocumentServiceImpl;
import everyware.doc.service.IDocumentService;
import everyware.doc.vo.ApprovalVO;
import everyware.doc.vo.DocumentVO;
import everyware.doc.vo.FileOfDocumentVO;
import everyware.doc.vo.RecipientVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/insertDoc.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100)
public class InsertDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		// 문서VO 생성
		DocumentVO dvo = new DocumentVO();
		
		
		// 문서 기본정보
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int isTemp = Integer.parseInt(request.getParameter("isTemp"));
		
		dvo.setEmp_id(evo.getEmp_id());
		dvo.setDept_name(evo.getDept_name());
		
		dvo.setDoc_title(title);
		dvo.setDoc_content(content);
		dvo.setIs_temp(isTemp);
		
		
		
		// 문서 첨부파일
		String uploadPath = "d:/d_other/uploadFiles";
		
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		String fileName = "";
		
		List<FileOfDocumentVO> fileList = new ArrayList<>();	
		
		for(Part part : request.getParts()) {
			fileName = extractFileName(part);
			
			if(!"".equals(fileName)) {
				
				String saveFileName = UUID.randomUUID().toString();
				String filePath = uploadPath + File.separator + saveFileName;
				
				FileOfDocumentVO fvo = new FileOfDocumentVO();
				
				fvo.setFile_name(fileName);
				fvo.setFile_size((long)Math.ceil(part.getSize() / 1024.0));
				fvo.setFile_path(filePath);
				
				try {
					part.write(filePath);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				fileList.add(fvo);
			}
		}
		
		dvo.setFiles(fileList);
		
		
		// 결재자, 수신처 
		String strApprovals = request.getParameter("approvals");
		String strRecipients = request.getParameter("recipients");
		
		Gson gson = new Gson();
		
		Type typeApproval = new TypeToken<List<ApprovalVO>>(){}.getType();
		List<ApprovalVO> approvals = gson.fromJson(strApprovals, typeApproval);
		
		dvo.setApprovals(approvals);
		
		Type typeRecipient = new TypeToken<List<RecipientVO>>(){}.getType();
		List<RecipientVO> recipients = gson.fromJson(strRecipients, typeRecipient);
		
		dvo.setRecipients(recipients);
		
		
		// 서비스 생성 및 실행
		IDocumentService service = DocumentServiceImpl.getInstance();
		int result = service.insertDoc(dvo);
		
		if(isTemp == 0) {
			service.alarmToApprover(result + "");
		}
		
		System.out.println(result);
		
		response.sendRedirect(request.getContextPath()+"/document/uploaded");
		
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
