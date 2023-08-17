package everyware.posts.cotroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import everyware.posts.service.IPostsService;
import everyware.posts.service.PostsServiceImpl;
import everyware.posts.vo.PostsVO;

/**
 * Servlet implementation class NoticeFree
 */
@WebServlet("/NoticeFree.do")
public class NoticeFree extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		System.out.println(postId);
		
		IPostsService service = PostsServiceImpl.getInstance();
		int cnt = service.updateCnt(postId);
//		System.out.println(cnt);
		
		List<PostsVO> list = service.getfreePosts();
		
		for (PostsVO vo : list) {
			if (vo.getPost_id() == postId) {
				request.setAttribute("subtitle", vo);
			}
		}

		request.getRequestDispatcher("/Posts/post/NoticeFree.jsp").forward(request, response);
		
		
	}

}
