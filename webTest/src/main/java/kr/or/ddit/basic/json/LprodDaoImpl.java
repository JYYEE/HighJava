package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao{
	private static LprodDaoImpl dao;
	private LprodDaoImpl() {}
	public static LprodDaoImpl getInstance() {
		if(dao == null) dao = new LprodDaoImpl();
		return dao;
	}
	
	public List<LprodVO> getLprod(){
		SqlSession session = null;
		List<LprodVO> lprodList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			lprodList = session.selectList("lprod.getLprod");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return lprodList;
	}
}
