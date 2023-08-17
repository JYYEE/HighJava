package everyware.doc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import everyware.doc.vo.DepartmentVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class OrgDaoImpl implements IOrgDao {
	private static IOrgDao dao;
	
	private OrgDaoImpl() { }
	
	public static IOrgDao getInstance() {
		if(dao==null) dao = new OrgDaoImpl();
		return dao;
	}

	@Override
	public List<DepartmentVO> selectAll() {
		List<DepartmentVO> list = null;
		
		try (SqlSession session = MybatisSqlSessionFactory.getSqlSession()) {
			list = session.selectList("org.selectAll");
		}
		return list;
	}
	
}
