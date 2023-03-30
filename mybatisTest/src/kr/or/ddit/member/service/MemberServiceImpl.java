package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;


public class MemberServiceImpl implements IMemberService {
	// 일을 시킬 DAO객체 변수 선언 -- 필드 선언은 인터페이스로 하고 객체 생성은 클래스로 사용 
	private IMemberDao dao;
	
	
	// 생성자
	public MemberServiceImpl() {
		dao = new MemberDaoImpl(); // DAO 객체 생성
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
