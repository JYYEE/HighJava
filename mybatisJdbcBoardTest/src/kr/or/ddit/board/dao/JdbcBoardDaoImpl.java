package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	// 1번
	private static JdbcBoardDaoImpl dao;
	
	// 2번
	private JdbcBoardDaoImpl() {};
	
	// 3번
	public static JdbcBoardDaoImpl getInstance() {
		if(dao == null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	@Override
	public int insertBoard(BoardVO boardVO) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("board.insertBoard", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.delete("board.deleteBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("board.updateBoard", boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO boardVO = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			boardVO = session.selectOne("board.getBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return boardVO;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		SqlSession session = null;
		List<BoardVO> boardList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			boardList = session.selectList("board.getAllBoardList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> getSearchBoardList(String title) {
		SqlSession session = null;
		List<BoardVO> boardList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			boardList = session.selectList("board.getSearchBoardList", title);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("board.setCountIncrement", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
}
