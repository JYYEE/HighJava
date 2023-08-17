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
 * Servlet implementation class SelectAttByDay
 */
@WebServlet("/Attendances/selectAttByDay")
public class SelectAttByDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		String attdate = request.getParameter("attDate"); // ajax에서 넘긴 데이터
		
		IAttendancesService service = AttendancesServiceImpl.getService();
		List<AttendancesVO> attList = service.selectAttByDay(attdate);
		
		Gson gson = new Gson();
		String attdayList = gson.toJson(attList);
		
		response.getWriter().write(attdayList);
		response.flushBuffer();
	}

}
