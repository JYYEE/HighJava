package everyware.vac.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.vac.service.IVacationsService;
import everyware.vac.service.VacationsServiceImpl;

@WebServlet("/Vacations/deleteVac")
public class DeleteVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String[] vacdelids = request.getParameterValues("vacdelIdList[]");
		IVacationsService service = VacationsServiceImpl.getInstance();
		for (String vacidst : vacdelids) {
			int vacId = Integer.parseInt(vacidst);
			
			int cnt = service.deleteVac(vacId);
			request.setAttribute("result", cnt);
		}
		request.getRequestDispatcher("/vac/result.jsp").forward(request, response);
	}

}
