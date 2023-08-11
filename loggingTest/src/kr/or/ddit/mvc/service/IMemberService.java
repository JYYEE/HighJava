package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 만들어진 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 결과를 Controller에게 보내주는 역할을 한다.
 * 
 * (자바고급과정에서는 보통 DAO와 메서드의 구조가 같게 한다.) 
 * @author PC-03
 */
public interface IMemberService {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo insert할 데이터가 저장된 MemberVO 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertMember(MemberVO memVO);
	// 매개변수 담는 것은 1개만 오도록 하는 것이 좋음 -> VO
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 삭제성공 : 1, 삭제실패 : 0
	 */
	public int deleteMember(String memId);
	// 데이터가 1개일 때는 객체에 담아서 오는 것보다 그 데이터 자체가 오는 것이 좋음
	
	/**
	 * MemberVO객체에 담겨진 자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVO update할 회원 정보가 저장된 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO memVO);
	
	/**
	 * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO 객체가 저장된 List객체
	 */
	public List<MemberVO> getAllMember();
	// select문의 반환값은 대부분 List, VO
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원ID의 개수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * 매개변수로 받은 Map을 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드 ==> 수정 항목이 1개
	 * Map의 정보 ==> key값 : 수정할 컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId) 
	 * 
	 * @param paramMap
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	// 가져갈 데이터가 2개이상이면 묶어줘야함
	// VO 객체를 먼저 생각해보고 어려우면 Collection객체 : 주로 Map을 이용 
	public int updateMember2(Map<String, String> paramMap);
	
	/**
	 * 매개변수로 받은 Map을 이용하여 원하는 항목을 수정하는 메서드 - 수정항목이 여러개
	 * Map의 정보 ==> key값 : 수정할 컬럼명, value값 : 수정할 데이터값
	 * 
	 * @param dataMap 수정할 정보가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember3(Map<String, String> dataMap);

}
