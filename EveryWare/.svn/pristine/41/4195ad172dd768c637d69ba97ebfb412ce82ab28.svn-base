package everyware.doc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import everyware.doc.service.DocumentServiceImpl;
import everyware.doc.service.IDocumentService;
import everyware.doc.vo.DocumentVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/scrapDoc.do")
public class ScrapDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		String empId = evo.getEmp_id();
		
		int isScrap = Integer.parseInt(request.getParameter("isScrap"));
		String docId = request.getParameter("id");
		
		DocumentVO vo = new DocumentVO();
		vo.setEmp_id(empId);
		vo.setDoc_id(docId);
		
		IDocumentService service = DocumentServiceImpl.getInstance();
		
		Gson gson = new Gson();
		String result = "";
		if(isScrap > 0) {
			service.deleteScrap(vo);
			result = "delete";
		} else {
			service.insertScrap(vo);
			result = "insert";
		}

		response.getWriter().write(gson.toJson(result));
		response.flushBuffer();
		
	}

}
