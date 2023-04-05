package everyware.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import everyware.service.IVacationService;
import everyware.service.VacationServiceImpl;
import everyware.vo.VacationsVO;

@WebServlet("/insertVac.do")
public class InsertVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		
		IVacationService service = VacationServiceImpl.getInstance();
		HttpSession session = request.getSession();
		
		String empId =(String) session.getAttribute("empId");
		String vacType = request.getParameter("vacType");
		String vacStart = request.getParameter("vacStart");
		String vacEnd = request.getParameter("vacEnd");
		String vacReason = request.getParameter("vacReason");
		
		VacationsVO vo = new VacationsVO();
		vo.setEmp_id(empId);
		vo.setVac_type(vacEnd);
		vo.setVac_start(vacStart);
		vo.setVac_end(vacEnd);
		vo.setVac_reason(vacReason);
		
		int cnt = service.insertVac(vo);
		
		request.setAttribute("insertcnt", cnt);
		
		Gson gson = new Gson();
		String data = gson.toJson(cnt);
		response.getWriter().write(Integer.parseInt(data));
		response.flushBuffer();
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
