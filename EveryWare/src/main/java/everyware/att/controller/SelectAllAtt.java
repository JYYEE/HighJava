package everyware.att.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import everyware.att.service.AttendancesServiceImpl;
import everyware.att.service.IAttendancesService;
import everyware.att.vo.AttendancesVO;

/**
 * Servlet implementation class SelectAllAtt
 */
@WebServlet("/Attendances/selectAllAtt")
public class SelectAllAtt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		IAttendancesService service = AttendancesServiceImpl.getService();
		List<AttendancesVO> attList = service.selectAllAtt();

		Gson gson = new Gson();
		String attevtList = gson.toJson(attList);
		response.getWriter().write(attevtList);
		response.flushBuffer();
	}

}
