package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		int count=0;
//		Cookie[] cookieArr = request.getCookies();
//		if (cookieArr != null) {
//			for (Cookie cookie : cookieArr) {
//				String name = cookie.getName();
//				if ("count".equals(name)) {
//					count = Integer.parseInt(cookie.getValue());
//				}
//			}
//		}
//		count++;
//		Cookie countCookie = new Cookie("count", Integer.toString(count));
//		response.addCookie(countCookie);
//		out.println("<html><body><meta charset='utf-8'><title>Cookie 연습</title></head>");
//		out.println("<body>");
//		out.println("<h3>어서오세요. 당신은 " + countCookie.getValue() + "번째 방문입니다.</h3><br><br>");
//		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트 증가하기</a>");
//		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
//		out.println("</body></html>");
		//////////////////////////////////////me
		//tr
		response.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int count = 0; 		// 현재 count값이 저장될 변수
		
		// 쿠키 이름을 count로 한다. ==> 현재 이 쿠키가 있는지 검사한다.
		Cookie[] cookieArr = request.getCookies();
		if(cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) { //'count'라는 쿠키가 있는지 검사
					String value = cookie.getValue();	// 현재의 쿠키값 가져오기
					count = Integer.parseInt(value);
				}
			}
		}
		count++;	// count 증가
		
		// 증가된 값을 쿠키값으로 갖는 Cookie객체 생성
		// count는 int타입이고 Cookie객체의 value의 타입은 String이므로 저장하기 위해서 형변환 필요
		// Cookie countCookie = new Cookie("count", count +""); // 방법1
		Cookie countCookie = new Cookie("count", String.valueOf(count)); // 방법2
		
		// 생성된 쿠키정보 저장하기
		response.addCookie(countCookie);
		
		out.println("<html><body><meta charset='utf-8'><title>Cookie Count연습</title></head>");
		out.println("<body>");
		out.println("<h2>어서오세요. 당신은 " + countCookie.getValue() + "번째 방문입니다.</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
