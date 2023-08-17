package everyware.att.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.att.service.AttendancesServiceImpl;
import everyware.att.service.IAttendancesService;

/**
 * Servlet implementation class InsertAttMemo
 */
@WebServlet("/Attendances/updateMemo")
public class UpdateAttMemo extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// ajax에서 넘긴 데이터
		String attMemo = request.getParameter("attMemo");
		String empId = request.getParameter("empId");
		String attDate = request.getParameter("attDate");

		HashMap<String, String> map = new HashMap<>();
		map.put("empId", empId);
		map.put("attDate", attDate);
		map.put("attMemo", attMemo);

		IAttendancesService service = AttendancesServiceImpl.getService();
		int cnt = service.updateAttMemo(map);
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/att/result.jsp").forward(request, response);
	}

}
