package kr.or.ddit.basic.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.LprodVO;

@WebServlet("/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json; charset=utf-8"); 	// JSON으로 응답할 때 ContentType설정  -> view로 보낼 필요 없어짐
		
		ILprodService service = LprodServiceImpl.getInstance();
		List<LprodVO> lprodList = service.getLprod();
		
		////tr 추가 : view 페이지에서 gson으로 바꿔서 보내지 않고 한꺼번에 처리 가능
		Gson gson = new Gson();
		String jsonData = gson.toJson(lprodList);
		response.getWriter().write(jsonData);
		response.flushBuffer();
		
		
		////me
//		request.setAttribute("lprodList", lprodList);
//		RequestDispatcher rd = request.getRequestDispatcher("/basic/json/lprodView.jsp");
//		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
