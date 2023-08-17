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
import groupware.emp.vo.EmployeesVO;

@WebServlet("/checkApprover.do")
public class CheckApprover extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		String empId = evo.getEmp_id();
		
		String docId = request.getParameter("id");
		
		Map<String,String> map = new HashMap<>();
		
		map.put("doc_id", docId);
		map.put("emp_id", empId);
		
		
		IDocumentService service = DocumentServiceImpl.getInstance();
		int isTurn = service.isMyApprovalTurn(map);
		
		Gson gson = new Gson();
		String result = "";
		if(isTurn > 0) {
			result = "ok";
		}
		
		response.getWriter().write(gson.toJson(result));
		response.flushBuffer();
	}

}
