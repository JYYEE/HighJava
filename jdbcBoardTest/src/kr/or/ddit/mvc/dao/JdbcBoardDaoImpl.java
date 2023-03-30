package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

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
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_cnt, board_content) "
					+ " values(board_seq.nextval, ?, ?, sysdate, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoard_title());
			pstmt.setString(2, boardVO.getBoard_writer());
			pstmt.setString(3, boardVO.getBoard_content());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_content = ?, board_date = sysdate "
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoard_title());
			pstmt.setString(2, boardVO.getBoard_content());
			pstmt.setInt(3, boardVO.getBoard_no());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		return cnt;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVO = null; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVO = new BoardVO(); // 데이터를 저장할 VO객체 생성
				boardVO.setBoard_no(rs.getInt("board_no"));
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_date(rs.getString("board_date"));
				boardVO.setBoard_cnt(rs.getInt("board_cnt"));
				boardVO.setBoard_content(rs.getString("board_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (SQLException e2) {	}
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		return boardVO;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = null; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc ";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			boardList = new ArrayList<>();
			
			while(rs.next()) {
				BoardVO boardVO = new BoardVO(); // 데이터를 저장할 VO객체 생성
				boardVO.setBoard_no(rs.getInt("board_no"));
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_date(rs.getString("board_date"));
				boardVO.setBoard_cnt(rs.getInt("board_cnt"));
				boardVO.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (SQLException e2) {	}
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		return boardList;
	}

	@Override
	public List<BoardVO> getSearchBoardList(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> boardList = null; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_title like '%' || ? || '%' order by board_no desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			boardList = new ArrayList<>();
			
			while(rs.next()) {
				BoardVO boardVO = new BoardVO(); // 데이터를 저장할 VO객체 생성
				boardVO.setBoard_no(rs.getInt("board_no"));
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_date(rs.getString("board_date"));
				boardVO.setBoard_cnt(rs.getInt("board_cnt"));
				boardVO.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)try {rs.close();} catch (SQLException e2) {	}
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_cnt = board_cnt +1 "
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();} catch (SQLException e2) {	}
			if(conn!=null)try {conn.close();} catch (SQLException e2) {	}
		}
		return cnt;
	}

}
