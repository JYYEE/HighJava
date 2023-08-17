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
 * Servlet implementation class UpdateAtt
 */
@WebServlet("/Attendances/updateAtt")
public class UpdateAtt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 출근 퇴근 상태 입력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ajax에서 넘긴 데이터
		String empId = request.getParameter("empId");
		String attTime = request.getParameter("attTime");
		
		IAttendancesService service = AttendancesServiceImpl.getService();
		Map<String, String> attMap = new HashMap<>(); // 매개변수 map 생성. empId, attTime, attState 가져감
		
		attMap.put("empId", empId);
		attMap.put("attTime", attTime);
		String attState = "";
		LocalTime time = LocalTime.parse(attTime);
		LocalTime deadLineTime = LocalTime.of(9, 0, 59);
		if(time.isBefore(deadLineTime)) {
			attState = "정상";
		} else {
			attState = "지각";
		}
		attMap.put("attState", attState);
		int cnt = service.updateAtt(attMap);
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/att/result.jsp").forward(request, response);
	}

}
