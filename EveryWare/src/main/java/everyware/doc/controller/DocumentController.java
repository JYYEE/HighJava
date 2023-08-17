package everyware.doc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupware.emp.vo.EmployeesVO;

@WebServlet("/document/*")
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		HttpSession session = request.getSession();
		EmployeesVO vo = (EmployeesVO)session.getAttribute("userVO");
		
		
		String uri = request.getRequestURI();
		System.out.println("uri: "+uri);
		String conPath = request.getContextPath();
		System.out.println("conPath: "+conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command: "+command);
		
		if("/document/received".equals(command)) {
			request.setAttribute("menu", "received");
			request.getRequestDispatcher("/doc/list.jsp").forward(request, response);
		} else if("/document/uploaded".equals(command)) {
			request.setAttribute("menu", "uploaded");
			request.getRequestDispatcher("/doc/list.jsp").forward(request, response);
		} else if ("/document/approval".equals(command)) {
			request.setAttribute("menu", "approval");
			request.getRequestDispatcher("/doc/list.jsp").forward(request, response);
		} else if ("/document/scrapped".equals(command)) {
			request.setAttribute("menu", "scrapped");
			request.getRequestDispatcher("/doc/list.jsp").forward(request, response);
		} else if ("/document/write".equals(command)) {
			request.getRequestDispatcher("/orgController.do").forward(request, response);
		}
	}
}
