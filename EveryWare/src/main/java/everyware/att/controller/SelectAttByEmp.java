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
 * Servlet implementation class SelectAttByEmp
 */
@WebServlet("/Attendances/selectAttByEmp")
public class SelectAttByEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String empId = request.getParameter("empId"); // ajax에서 넘긴 데이터
		IAttendancesService service = AttendancesServiceImpl.getService();
		List<AttendancesVO> attList = service.selectAttByEmp(empId);
		
		Gson gson = new Gson();
		String attempList = gson.toJson(attList);
		response.getWriter().write(attempList);
		response.flushBuffer();
		
	}

}
