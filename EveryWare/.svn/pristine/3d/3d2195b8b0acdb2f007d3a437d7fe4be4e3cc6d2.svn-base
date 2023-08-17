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

@WebServlet("/approveDoc.do")
public class ApproveDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		String empId = evo.getEmp_id();
		String deptName = evo.getDept_name();
		
		String DocId = request.getParameter("id");
		String aprvNote = request.getParameter("note");
		
		Map<String,String> map = new HashMap<>();
		map.put("doc_id", DocId);
		map.put("emp_id", empId);
		map.put("aprv_note", aprvNote);
		map.put("alarm_type", "결재");
		map.put("dept_name", deptName);
		
		IDocumentService service = DocumentServiceImpl.getInstance();
		
		int result = service.approval(map);
		
		int isFinal = service.isFinalApproval(DocId);
		if(isFinal > 0) {
			service.alarmToApprover(DocId);
		} else {
			service.finalApproval(map);
			service.alarmToUploader(map);
		}
		
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(result));
		response.flushBuffer();
	}

}
