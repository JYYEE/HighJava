package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	public List<MemberVO> getAllMember();

	public MemberVO getMember(String memId);

	public int insertMember(MemberVO memVO);

	public int updateMember(MemberVO memVO);

	public int deleteMember(String memId);
	
	public int checkId(String memId);
}
