package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImplSingleton implements IMemberDao {
	// 1번
	private static MemberDaoImplSingleton daoSingleton;
	// 2번
	private MemberDaoImplSingleton() {
		
	}
	// 3번
	public static MemberDaoImplSingleton getInstance() {
		if(daoSingleton == null) daoSingleton = new MemberDaoImplSingleton();
		return daoSingleton;
	}
	
	
	@Override
	public int insertMember(MemberVO memVO) { // DB와 연결된 부분만 가져옴.
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_pass());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_tel());
			pstmt.setString(5, memVO.getMem_addr());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ "where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVO.getMem_pass());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_tel());
			pstmt.setString(4, memVO.getMem_addr());
			pstmt.setString(5, memVO.getMem_id());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}

		return cnt;
	}

	// Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId) 
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set " + paramMap.get("field") + " = ? where mem_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수

		String temp = "";
		// Map의 정보 ==> key값 : 수정할 컬럼명(검색할 ID는 'memId'),
		// value값 : 수정할 데이터값 (검색할 ID값 포함)

		try {
			conn = DBUtil3.getConnection();
//			for (String fieldName : dataMap.keySet()) {
//			if(!"memId".equals(fieldName)) { 
//				if(!"".equals(temp)) {
//					temp += ", ";
//				}
//				temp += fieldName + " = '" + dataMap.get(fieldName)+"'"; // 주의! 데이터 직접 넣어줄 때에는 ''안에 데이터 포함해줘야함!
//			}
//		}
//			String sql = "update mymember set " + temp + "where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dataMap.get(fieldName));

			for (String fieldName : dataMap.keySet()) {
				// Map의 key값 중 'memId'는 검색할 ID값에 대한 정보이기 때문에 수정할 컬럼을 설정할 때는 포함하지 않는다.
				if (!"memId".equals(fieldName)) {
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = ? ";
				}
			}

			String sql = "update mymember set " + temp + "where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) {
					pstmt.setString(num++, dataMap.get(fieldName));
				}
			}
			pstmt.setString(num, dataMap.get("memId"));
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null; // 반환값이 저장될 변수

		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			memList = new ArrayList<>(); // List객체 생성
			while (rs.next()) {
				MemberVO memVO = new MemberVO(); // 1개의 레코드가 저장될 VO객체 생성

				// Result Set의 데이터를 꺼내와 VO객체에 셋팅한다.
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_pass(rs.getString("mem_pass"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_tel(rs.getString("mem_tel"));
				memVO.setMem_addr(rs.getString("mem_addr"));

				// VO객체를 List에 추가한다.
				memList.add(memVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0; // 반환값이 저장될 변수 선언
		try {
			conn = DBUtil3.getConnection();
			String sql = "select count(*) cnt from mymember where mem_id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e2) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e2) {
				}
		}
		return count;
	}


}
