package everyware.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.service.IVacationsService;
import everyware.service.VacationsServiceImpl;
import everyware.vo.VacationsVO;

@WebServlet("/insertVac.do")
public class InsertVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		IVacationsService service = VacationsServiceImpl.getInstance();
		//HttpSession session = request.getSession();
		
		//String empId =(String) session.getAttribute("empId");
		String empId = request.getParameter("empId");
		String vacType = request.getParameter("vacType");
		String vacStart = request.getParameter("vacStart");
		String vacEnd = request.getParameter("vacEnd");
		String vacReason = request.getParameter("vacReason");
		
		VacationsVO vo = new VacationsVO();
		vo.setEmp_id(empId);
		vo.setVac_type(vacType);
		vo.setVac_start(vacStart);
		vo.setVac_end(vacEnd);
		vo.setVac_reason(vacReason);
		
		service.insertVac(vo);
		
		response.sendRedirect(request.getContextPath()+"/everyware/view/vacationsMain.jsp");
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
