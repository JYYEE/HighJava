package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDao {
	public List<MemberVO> getAllMember();
	
	public MemberVO getMember(String memId);
	
	public int insertMember(MemberVO memVO);
	
	public int updateMember(MemberVO memVO);
	
	public int deleteMember(String memId);
}
