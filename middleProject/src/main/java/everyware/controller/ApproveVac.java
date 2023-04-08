package everyware.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.service.IVacationsService;
import everyware.service.VacationsServiceImpl;

/**
 * Servlet implementation class ApproveVac
 */
@WebServlet("/approveVac.do")
public class ApproveVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int vacId = Integer.parseInt(request.getParameter("vacId"));
		IVacationsService service = VacationsServiceImpl.getInstance();
		int appcnt = service.approveVac(vacId);
		request.setAttribute("appcnt", appcnt);
		request.getRequestDispatcher("/everyware/view/vacationList.jsp").forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
