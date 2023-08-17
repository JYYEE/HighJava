package everyware.vac.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.emp.vo.EmployeesVO;


/**
 * Servlet implementation class VacController
 */
@WebServlet("/Vacations")
public class VacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 세션
		HttpSession session = request.getSession();
		EmployeesVO vo = (EmployeesVO) session.getAttribute("userVO"); 
	    String empId = vo.getEmp_id();
		
	    if(empId.equals("admin")) {
	    	request.getRequestDispatcher("/Vacations/selectAllVac").forward(request, response);
	    } else {
	    	request.getRequestDispatcher("/Vacations/selectVac").forward(request, response);
	    }
	}
}