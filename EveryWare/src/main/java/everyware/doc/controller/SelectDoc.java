package everyware.doc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

@WebServlet("/selectDoc.do")
public class SelectDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		String docId = request.getParameter("id");
		String menu = request.getParameter("menu");
		
		int isTemp = 0;
		String tmp = request.getParameter("isTemp");
		
		if(tmp != null) {
			isTemp = Integer.parseInt(tmp);
		}
		
		int page = 0;
		String spage = request.getParameter("page");
		
		if(spage != null) {
			page = Integer.parseInt(spage);
		}
		
		Map<String,String> map = new HashMap<>();
		map.put("emp_id", evo.getEmp_id());
		map.put("doc_id", docId);
		
		
		IDocumentService service = DocumentServiceImpl.getInstance();
		DocumentVO vo = service.selectDoc(map);
		
		if(isTemp > 0) {
			response.getWriter().write(new Gson().toJson(vo));
			response.flushBuffer();
			
		} else {
			request.setAttribute("vo", vo);
			request.setAttribute("menu", menu);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/doc/doc.jsp").forward(request, response);
		}
		
		
	}

}
