package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImplSingleton;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImplSingleton implements IMemberService {
	// 일을 시킬 DAO객체 변수 선언 -- 필드 선언은 인터페이스로 하고 객체 생성은 클래스로 사용
	private IMemberDao dao;

	// 1번
	private static MemberServiceImplSingleton serviceSingleton;

	// 2번 생성자
	private MemberServiceImplSingleton() {
		// dao = new MemberDaoImpl(); // DAO 객체 생성
		dao = MemberDaoImplSingleton.getInstance(); // DAO singleton객체 생성
	}
	
	public static MemberServiceImplSingleton getInstance() {
		if(serviceSingleton == null) serviceSingleton=new MemberServiceImplSingleton();
		return serviceSingleton;
	}

	@Override
	public int insertMember(MemberVO memVO) {
		return dao.insertMember(memVO);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVO) {
		return dao.updateMember(memVO);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		return dao.updateMember3(dataMap);
	}

}
