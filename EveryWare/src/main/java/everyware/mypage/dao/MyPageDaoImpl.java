package everyware.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import everyware.mypage.vo.LogVO;
import groupware.emp.vo.EmployeesVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class MyPageDaoImpl implements IMyPageDao {
	private static IMyPageDao dao;
	
	private MyPageDaoImpl() { }
	
	public static IMyPageDao getInstance() {
		if(dao == null) dao = new MyPageDaoImpl();
		return dao;
	}

	@Override
	public List<LogVO> selectLogList(Map<String, Object> map) {
		String empId = (String) map.get("empId");
		int count = (int) map.get("count");
		
		List<LogVO> list = null;
		
		try (SqlSession session = MybatisSqlSessionFactory.getSqlSession()) {
			list = session.selectList("mypage.selectLogList", empId, new RowBounds(count, 10));
		}
		return list;
	}

	@Override
	public int updateMyInfo(EmployeesVO vo) {
		int cnt = 0;
		
		try (SqlSession session = MybatisSqlSessionFactory.getSqlSession()) {
			cnt = session.update("updateMyInfo", vo);
			session.commit();
		}
		return cnt;
	}

}
