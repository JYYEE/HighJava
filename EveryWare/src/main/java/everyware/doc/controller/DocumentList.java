package everyware.doc.controller;

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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import everyware.doc.service.DocumentServiceImpl;
import everyware.doc.service.IDocumentService;
import everyware.doc.vo.DocumentVO;
import everyware.doc.vo.PageVO;
import groupware.emp.vo.EmployeesVO;

@WebServlet("/documentList.do")
public class DocumentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		EmployeesVO evo = (EmployeesVO)request.getSession().getAttribute("userVO");
		
		int page = 0;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		String sDateFrom = request.getParameter("sDateFrom");
		String sDateTo = request.getParameter("sDateTo");
		String sStateType = request.getParameter("sStateType");
		String menu = request.getParameter("menu");
		
		Map<String,Object> map = new HashMap<>();
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("sDateFrom", sDateFrom);
		map.put("sDateTo", sDateTo);
		map.put("sStateType", sStateType);
		
		IDocumentService service = DocumentServiceImpl.getInstance();
		PageVO vo = service.pageInfo(page, map, menu, evo);
		
		map.put("start", vo.getStart());
		map.put("end", vo.getEnd());
		map.put("empVo", evo);
		
		List<DocumentVO> list = null;
		switch (menu) {
		case "received":
			list = service.selectReceivedByPage(map);
			break;
		case "uploaded":
			list = service.selectUploadedByPage(map);
			break;
		case "approval":
			list = service.selectApproveByPage(map);
			break;
		case "scrapped":
			list = service.selectScrapByPage(map);
			break;
		}
		
//		List<DocumentVO> list = service.selectAllDoc();
		
//		request.setAttribute("list", list);
//		request.setAttribute("menuName", "all");
//		request.getRequestDispatcher("/list.jsp").forward(request, response);
		
		JsonObject obj = new JsonObject();
		obj.addProperty("sp", vo.getStartPage());
		obj.addProperty("ep", vo.getEndPage());
		obj.addProperty("tp", vo.getTotalPage());
		
		Gson gson = new Gson();
		JsonElement result = gson.toJsonTree(list);
		obj.add("datas", result);
		
		response.getWriter().write(new Gson().toJson(obj));
		response.flushBuffer();
	}
	
	

}
