package everyware.vac.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.vac.service.IVacationsService;
import everyware.vac.service.VacationsServiceImpl;
import everyware.vac.vo.VacationsVO;

/**
 * Servlet implementation class SelectAllVac
 */
@WebServlet("/Vacations/selectAllVac")
public class SelectAllVac extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		IVacationsService service = VacationsServiceImpl.getInstance();
		List<VacationsVO> vacList = service.selectAllVac();
		request.setAttribute("vacList", vacList);
		request.getRequestDispatcher("/vac/vactionListad.jsp").forward(request, response);

	}

}
