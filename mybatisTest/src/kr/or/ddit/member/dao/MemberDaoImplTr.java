package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImplTr implements IMemberDao{
	private static MemberDaoImplTr dao;
	private MemberDaoImplTr() {}
	
	public static MemberDaoImplTr getInstance() {
		if(dao == null) dao = new MemberDaoImplTr();
		return dao;
	}
	@Override
	public int insertMember(MemberVO memVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("memberTr.insertMember", memVO);
			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패~~~");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("memberTr.deleteMember", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}	
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("memberTr.updateMember", memVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}	
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> memList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			memList = session.selectList("memberTr.getAllMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); // select문에서는 commit없어도 됨
		}	
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.selectOne("memberTr.getMemberCount", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}	
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("memberTr.updateMember2", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}	
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("memberTr.updateMember3", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}	
		return cnt;
	}

}
