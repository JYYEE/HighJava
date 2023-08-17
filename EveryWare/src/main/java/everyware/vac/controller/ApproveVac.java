package everyware.vac.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.vac.service.IVacationsService;
import everyware.vac.service.VacationsServiceImpl;

/**
 * Servlet implementation class ApproveVac
 */
@WebServlet("/Vacations/approveVac")
public class ApproveVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String[] vacappids = request.getParameterValues("vacappIdList[]"); // 배열을 넘길 때는 [] 붙어서 넘어옴
		IVacationsService service = VacationsServiceImpl.getInstance();

		for (String vacidst : vacappids) {
			int vacId = Integer.parseInt(vacidst);
			int cnt = service.approveVac(vacId);
			
			request.setAttribute("result", cnt);
		}
		request.getRequestDispatcher("/vac/result.jsp").forward(request, response);

	}
}	
