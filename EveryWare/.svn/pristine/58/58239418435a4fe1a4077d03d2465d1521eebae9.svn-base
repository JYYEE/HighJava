package everyware.vac.dao;

import java.util.List;
import java.util.Map;

import everyware.vac.vo.VacationsVO;

public interface IVactionsDao {
	/**
	 * 사원이 등록한 휴가 일정을 DB에 insert하는 메소드
	 * @param vacVO DB에 insert할 자료가 저장된 VacationsVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertVac(VacationsVO vacVO);
	
	/**
	 * 사원의 id를 매개변수로 받아 사원의 휴가 현황을 조회하는 메소드
	 * @param empId : 휴가를 조회할 사원의 id
	 * @return VacationsVO객체를 갖고 있는 List객체
	 */
	public List<VacationsVO> selectVacByEmp(String empId);
	
	/**
	 * 휴가id를 매개변수로 받아 사원의 휴가일정을 삭제하는 메소드
	 * @param vacId : 휴가를 조회할 휴가id
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteVac(int vacId);
	
	/**
	 * 관리자가 전 사원의 휴가일정을 조회하는 메소드
	 * @return VacationsVO객체를 갖고 있는 List객체 
	 */
	public List<VacationsVO> selectAllVac();
	
	/**
	 * 관리자가 휴가를 등록한 사원의 일정을 승인하는 메소드
	 * @param vacId 휴가승인을 신청한 vacId
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int approveVac(int vacId);
	
	/**
	 * 사원의 잔여 연차 계산하는 메소드
	 * @param 휴가를 신청한 휴가 id, 휴가 일수
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateRemainVac(Map<String, Integer> map);
	
	/**
	 * 휴가id를 매개변수로 받아 사원의 휴가일정을 가져오는 메소드
	 * @param vacId : 휴가를 조회할 휴가 id
	 * @return VacationsVO 객체
	 */
	public VacationsVO selectVacByVacId(int vacId);
	
//	/**
//	 * 사원id를 매개변수로 받아 사원의 잔여연차일을 가져오는 메소드
//	 * @param empId : 연차일을 조회할 사원 id
//	 * @return map 객체를 가지고 있는 List객체
//	 */
//	public List<Map<String, String>> selectRemainVac(String empId);
}
