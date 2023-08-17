package everyware.vac.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import everyware.vac.service.IVacationsService;
import everyware.vac.service.VacationsServiceImpl;
import everyware.vac.vo.VacationsVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/Vacations/insertVac")
public class InsertVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		IVacationsService service = VacationsServiceImpl.getInstance();
		HttpSession session = request.getSession();
		EmployeesVO empvo = (EmployeesVO) session.getAttribute("userVO");
		String empId = empvo.getEmp_id();
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
		
		int cnt = service.insertVac(vo);
		request.setAttribute("result", cnt);
		
		request.getRequestDispatcher("/Vacations").forward(request, response);
	}
}
