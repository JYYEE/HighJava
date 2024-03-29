package everyware.posts.cotroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import everyware.posts.service.IPostsService;
import everyware.posts.service.PostsServiceImpl;
import everyware.posts.vo.PostsVO;
import groupware.emp.vo.EmployeesVO;


/**
 * Servlet implementation class InsertPost
 */
@WebServlet("/InsertPost.do")
public class InsertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(); // 세션 객체 생성
		EmployeesVO evo = (EmployeesVO) session.getAttribute("userVO");
		System.out.println("공지사항 아이디 "+evo.getEmp_id());
		
		String postTitle = request.getParameter("title");
		String postContent = request.getParameter("content");
		String postSubtitle = request.getParameter("subtitle");
		System.out.println(postTitle+" "+postContent+" "+postSubtitle);
		PostsVO vo = new PostsVO();
		
		vo.setBoard_id("B001");
		vo.setEmp_id(evo.getEmp_id()); // 통합하고 세션 생기면 , 로그인한 사용자 아이디로 바꿔주세요
		vo.setPost_title(postTitle);
		vo.setPost_content(postContent);
		vo.setPost_subtitle(postSubtitle);
		
		System.out.println(vo.getBoard_id());
		System.out.println(vo.getPost_content());
		IPostsService service = PostsServiceImpl.getInstance();
		int cnt = service.insertPost(vo);
	  System.out.println(cnt);
	  response.sendRedirect(request.getContextPath() +"/postList.do");
		}

}
