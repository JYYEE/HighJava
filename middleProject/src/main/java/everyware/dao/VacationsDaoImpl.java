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
		SqlSession session = MybatisSqlSessionFactory.getSqlSession();
		int cnt = session.insert("vac.insertVac", vacVO);
		session.commit();
		session.close();
		return cnt;
	}

	@Override
	public List<VacationsVO> selectVacByEmp(String empId) {
		SqlSession session = MybatisSqlSessionFactory.getSqlSession();
		List<VacationsVO> vacList = session.selectList("vac.selectVacByEmp", empId);
		session.commit();
		session.close();
		return vacList;
	}

	@Override
	public int deleteVac(String vacId) {
		SqlSession session = MybatisSqlSessionFactory.getSqlSession();
		int cnt = session.delete("vac.deleteVac", vacId);
		if(cnt>0) {
			System.out.println("휴가 삭제 성공");
		} else {
			System.out.println("휴가 삭제 실패");
		}
		session.commit();
		session.close();
		return cnt;
	}

	@Override
	public List<VacationsVO> selectAllVac() {
		SqlSession session = MybatisSqlSessionFactory.getSqlSession();
		List<VacationsVO> vacList = session.selectList("vac.selectAllVac");
		session.commit();
		session.close();
		return vacList;
	}

	@Override
	public int approveVac(String vacId) {
		SqlSession session = MybatisSqlSessionFactory.getSqlSession();
		int cnt = session.update("vac.approveVac", vacId);
		if(cnt>0) {
			System.out.println("휴가 승인 성공");
		} else {
			System.out.println("휴가 승인 실패");
		}
		session.commit();
		session.close();
		return cnt;
	}

}
