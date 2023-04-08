package everyware.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.MybatisSqlSessionFactory;
import everyware.vo.VacationsVO;

public class VacationsDaoImpl implements IVactionsDao {
	private static IVactionsDao dao;
	private VacationsDaoImpl() {}
	public static IVactionsDao getInstance() {
		if(dao == null) {
			dao = new VacationsDaoImpl();
		}
		return dao;
	}
	
	
	@Override
	public int insertVac(VacationsVO vacVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("vac.insertVac", vacVO);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertVac dao오류");
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public List<VacationsVO> selectVacByEmp(String empId) {
		SqlSession session = null;
		List<VacationsVO> vacList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vacList = session.selectList("vac.selectVacByEmp", empId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectVacByEmp dao오류");
		} finally {
			session.commit();
			session.close();
		}
		return vacList;
	}

	@Override
	public int deleteVac(int vacId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.delete("vac.deleteVac", vacId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteVac dao오류");
		} finally {
			session.commit();
			session.close();
		}
		if(cnt>0) {
			System.out.println("휴가 삭제 성공");
		} else {
			System.out.println("휴가 삭제 실패");
		} 
		return cnt;
	}

	@Override
	public List<VacationsVO> selectAllVac() {
		SqlSession session = null;
		List<VacationsVO> vacList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vacList = session.selectList("vac.selectAllVac");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllVac dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return vacList;
	}

	@Override
	public int approveVac(int vacId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			 session = MybatisSqlSessionFactory.getSqlSession();
			 cnt = session.update("vac.approveVac", vacId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("approveVac dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		if(cnt>0) {
			System.out.println("휴가 승인 성공");
		} else {
			System.out.println("휴가 승인 실패");
		}
		return cnt;
	}

}
