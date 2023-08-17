package everyware.att.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.att.service.AttendancesServiceImpl;
import everyware.att.service.IAttendancesService;

/**
 * Servlet implementation class UpdateLeaveTime
 */
@WebServlet("/Attendances/updateLeaveTime")
public class UpdateLeaveTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ajax에서 넘긴 데이터
		String empId = request.getParameter("empId");
		String leaveTime = request.getParameter("leaveTime");
		
		IAttendancesService service = AttendancesServiceImpl.getService();
		Map<String, String> leaveMap = new HashMap<>(); // 매개변수 map 생성. empId, leaveTime 가져감
		
		leaveMap.put("empId", empId);
		leaveMap.put("leaveTime", leaveTime);
		
		int cnt = service.updateLeaveTime(leaveMap);
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/att/result.jsp").forward(request, response);
	}

}
