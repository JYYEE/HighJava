package everyware.att.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import everyware.att.vo.AttendancesVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class AttendancesDaoImpl implements IAttendancesDao {
	private static IAttendancesDao dao;
	private AttendancesDaoImpl() {}
	
	public static IAttendancesDao getInstance() {
		if(dao == null) dao = new AttendancesDaoImpl();
		return dao;
	}

	@Override
	public int updateAtt(Map<String, String> map) {
		System.out.println(map.toString());
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			System.out.println(session);
			cnt = session.update("att.updateAtt", map);
			System.out.println("try문 cnt : " + cnt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateAttTime dao오류");
		} finally {
			session.commit();
			session.close();
		}if(cnt>0) {
			System.out.println("출근시간 등록 성공");
		} else {
			System.out.println("출근시간 등록 실패");
		} 
		System.out.println("dao 마지막 cnt" + cnt);
		return cnt;
	}

	@Override
	public int updateLeaveTime(Map<String, String> map) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("att.updateLeaveTime", map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateLeaveTime dao 오류");
		} finally {
			session.commit();
			session.close();
		}if(cnt>0) {
			System.out.println("퇴근시간 등록 성공");
		} else {
			System.out.println("퇴근시간 등록 실패");
		} 
		return cnt;
	}

	@Override
	public int updateAttMemo(Map<String, String> map) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("att.updateAttMemo", map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateAttMemo dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		if(cnt>0) {
			System.out.println("근태 상세내역 등록 성공");
		} else {
			System.out.println("근태 상세내역 등록 실패");
		} 
		return cnt;
	}

	@Override
	public List<AttendancesVO> selectAllAtt() {
		SqlSession session = null;
		List<AttendancesVO> attList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			attList = session.selectList("att.selectAllAtt");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllAtt dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return attList;
	}

	@Override
	public List<AttendancesVO> selectAttByEmp(String empId) {
		SqlSession session = null;
		List<AttendancesVO> attList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			attList = session.selectList("att.selectAttByEmp", empId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAttByEmp dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return attList;
	}

	@Override
	public String selectAttMemo(Map<String, String> map) {
		SqlSession session = null;
		String attMemo = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			attMemo = session.selectOne("att.selectAttMemo", map);
			if(attMemo == null) {
				attMemo = "미등록";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selctAttMemo dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("dao : " + attMemo);
		return attMemo;
	}

	@Override
	public List<AttendancesVO> selectAttByDay(String attDate) {
		SqlSession session = null;
		List<AttendancesVO> attList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			attList = session.selectList("att.selectAttByDay", attDate);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAttByDay dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return attList;
	}

}
