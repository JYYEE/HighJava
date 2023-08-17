package everyware.doc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.doc.service.DocumentServiceImpl;
import everyware.doc.service.IDocumentService;
import everyware.doc.service.IOrgServicce;
import everyware.doc.service.OrgServiceImpl;
import everyware.doc.vo.DepartmentVO;
import everyware.doc.vo.DocumentVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/orgController.do")
public class OrgController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		String empId = evo.getEmp_id();
		
		IOrgServicce orgService = OrgServiceImpl.getInstance();
		List<DepartmentVO> list = orgService.selectAll();
		
		IDocumentService service = DocumentServiceImpl.getInstance();
		int tempCnt = service.tempTotalCount(empId);
		List<DocumentVO> tempList = service.selectTempList(empId);
		
		
		request.setAttribute("list", list);
		request.setAttribute("tempList", tempList);
		request.setAttribute("tempCnt", tempCnt);
		
		request.getRequestDispatcher("doc/write.jsp").forward(request, response);
	}

}
