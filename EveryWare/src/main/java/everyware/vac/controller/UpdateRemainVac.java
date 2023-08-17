package everyware.vac.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.vac.service.IVacationsService;
import everyware.vac.service.VacationsServiceImpl;
import everyware.vac.vo.VacationsVO;

/**
 * Servlet implementation class UpdateRemainVac
 */
@WebServlet("/Vacations/updateRemainVac")
public class UpdateRemainVac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String[] vacappids = request.getParameterValues("vacappIdList[]"); // 배열을 넘길 때는 [] 붙어서 넘어옴
		String[] vacapptypes = request.getParameterValues("vacappTypeList[]");
		IVacationsService service = VacationsServiceImpl.getInstance();
		int cnt = 0;
		for (int i = 0; i < vacappids.length; i++) {
			int vacId = Integer.parseInt(vacappids[i]);
			
			if(vacapptypes[i].equals("연차")) {
				VacationsVO vacVO = service.selectVacByVacId(vacId);
				Map<String,Integer> map = new HashMap<>();
				map.put("vacId", vacId);
				map.put("vacTotal", vacVO.getVac_total());
				
				cnt = service.updateRemainVac(map);
				
			} 
		}
		request.setAttribute("result", cnt);
		
		
		/*
		 * for (String vacidst : vacappids) { int vacId = Integer.parseInt(vacidst);
		 * 
		 * VacationsVO vacVO = service.selectVacByVacId(vacId); Map<String,Integer> map
		 * = new HashMap<>(); map.put("vacId", vacId); map.put("vacTotal",
		 * vacVO.getVac_total());
		 * 
		 * int cnt = service.updateRemainVac(map);
		 * 
		 * request.setAttribute("result", cnt); }
		 */
		request.getRequestDispatcher("/vac/result.jsp").forward(request, response);
	}

}
