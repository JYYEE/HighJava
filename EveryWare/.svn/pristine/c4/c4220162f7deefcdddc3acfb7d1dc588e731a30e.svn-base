package everyware.vac.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import everyware.vac.service.IVacationsService;
import everyware.vac.service.VacationsServiceImpl;
import everyware.vac.vo.VacationsVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/Vacations/selectVac")
public class SelectVacByEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 세션
		HttpSession session = request.getSession();
		EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO"); 
		String empId = vo.getEmp_id();
		
		IVacationsService service = VacationsServiceImpl.getInstance();
		List<VacationsVO> vacList = service.selectVacByEmp(empId);
		
		request.setAttribute("vacList", vacList);
		
		request.getRequestDispatcher("/vac/vacationList.jsp").forward(request, response);
		
	}

}
