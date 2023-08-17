package everyware.att.controller;

import java.io.IOException;
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
 * Servlet implementation class SelectAttMemo
 */
@WebServlet("/Attendances/selMemo")
public class SelectAttMemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// ajax에서 넘긴 데이터
		String empId = request.getParameter("empId"); 
		String attDate = request.getParameter("attDate"); 
		Map<String, String> map = new HashMap<>();
		map.put("empId", empId);
		map.put("attDate", attDate);
		
		IAttendancesService service = AttendancesServiceImpl.getService();
		String attMemo = service.selectAttMemo(map);
		response.getWriter().write(attMemo);
		response.flushBuffer();
	}

}
