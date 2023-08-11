package kr.or.ddit.basic.session;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDao {
	private static MemberDao dao;
	private MemberDao() {}
	
	public static MemberDao getInstance() {
		if(dao== null) dao = new MemberDao();
		return dao;
	}
	
	public MemberVO getMember(MemberVO memVO) {
		SqlSession session = null;
		MemberVO loginMemberVO = null;	//반환값이 저장될 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			loginMemberVO = session.selectOne("member.getMember", memVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();	// 세션 닫기
		}
		return loginMemberVO;
	}
}
