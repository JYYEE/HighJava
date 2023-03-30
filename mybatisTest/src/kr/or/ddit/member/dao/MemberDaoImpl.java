package kr.or.ddit.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	@Override
	public int insertMember(MemberVO memVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("member.insertMember", memVO);
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
			cnt = session.insert("member.deleteMember", memId);
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
			cnt = session.insert("member.updateMember", memVO);
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
			memList = session.selectList("member.getAllMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.selectOne("member.getMemberCount", memId);
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
			cnt = session.update("member.update2", paramMap);
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
		String temp = "";
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			for (String fieldName : dataMap.keySet()) {
				if (!"memId".equals(fieldName)) {
					if (!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " =  " + "'" + dataMap.get(fieldName) + "'";
				}
			}
			dataMap.put("str", temp); 
			// dataMap에는 새로 수정한 정보도 같이 들어있음 key값이 str인 것을 가져왔으니 상관없지만 중복되지 않도록 주의
			cnt = session.update("member.update3", dataMap);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

}
