package everyware.mypage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import everyware.mypage.service.IMyPageService;
import everyware.mypage.service.MyPageServiceImpl;
import everyware.mypage.vo.LogVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/logList.do")
public class LogList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		String empId = evo.getEmp_id();
		
		int count = Integer.parseInt(request.getParameter("count"));
		
		Map<String,Object> map = new HashMap<>();
		map.put("empId", empId);
		map.put("count", count);
		
		IMyPageService service = MyPageServiceImpl.getInstance();
		List<LogVO> list = service.selectLogList(map);
		System.out.println(list.toString());
		
		response.getWriter().write(new Gson().toJson(list));
		response.flushBuffer();
		
	}

}
