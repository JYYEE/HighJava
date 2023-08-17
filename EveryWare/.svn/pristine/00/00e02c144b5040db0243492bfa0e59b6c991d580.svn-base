package groupware.emp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.emp.service.EmpServiceImpl;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/SetPosi.do")
public class SetPosi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String empId = request.getParameter("id");
		String posi = request.getParameter("posi");
		String dept = request.getParameter("dept");
		String mail = request.getParameter("empMail");
		String pass = "0000";
		
		EmployeesVO vo = new EmployeesVO();
		vo.setEmp_id(empId);
		vo.setEmp_pass(pass);
		System.out.println(empId + "," +posi + "," + dept + "," + mail);
		EmployeesVO vo2 = EmpServiceImpl.getInstance().getEmp(vo);
		System.out.println("oldDept: " + vo2.getDept_id());
		System.out.println("oldPosi: " + vo2.getPosition_id());
		
		String oldDept = vo2.getDept_id();
		String oldPosi = vo2.getPosition_id();
		
		vo2.setEmp_id(empId);
		
		if(posi.equals("null")) {
			vo2.setPosition_id(oldPosi);
		}else {
			vo2.setPosition_id(posi);	
		}
		
		if(dept.equals("null")) {
			vo2.setDept_id(oldDept);
		}else {
			vo2.setDept_id(dept);			
		}

		vo2.setEmp_mail(mail);
				
		EmpServiceImpl.getInstance().setPosi(vo2);
//		Gson gson = new Gson();
//		String res = null;
//		res = gson.toJson("ok");
//		
//		response.getWriter().write(res);
//		response.flushBuffer();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
