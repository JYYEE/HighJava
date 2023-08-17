package everyware.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import everyware.mypage.service.IMyPageService;
import everyware.mypage.service.MyPageServiceImpl;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/myPage.do")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		
		evo.setEmp_tel(tel);
		evo.setEmp_addr(addr);
		
		IMyPageService service = MyPageServiceImpl.getInstance();
		int result = service.updateMyInfo(evo);
		
		if(result > 0) {
			request.getSession().setAttribute("userVO", evo);
		}
		
		response.getWriter().write(new Gson().toJson(result));
		response.flushBuffer();
	}

}
