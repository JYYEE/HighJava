package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.dao.IJdbcBoardDao;
import kr.or.ddit.mvc.dao.JdbcBoardDaoImpl;
import kr.or.ddit.mvc.vo.BoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao dao;
	
	// 1번
	private static JdbcBoardServiceImpl service;
	
	// 2번
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	// 3번
	public static JdbcBoardServiceImpl getInstance() {
		if(service == null) service = new JdbcBoardServiceImpl();
		return service;
	}
	
	@Override
	public int insertBoard(BoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return dao.updateBoard(boardVO);
	}

	@Override
	public BoardVO getBoard(int boardNo) { 
		// 게시글 보면 조회수가 증가하는 작업을 같이 수행해야 한다.
		int cnt = dao.setCountIncrement(boardNo);
		if(cnt == 0) { // 조회수 증가 실패
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<BoardVO> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
