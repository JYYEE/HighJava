package everyware.service;

import java.util.List;

import everyware.vo.VacationsVO;

public interface IVacationService {
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
	public int deleteVac(String vacId);
	
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
	public int approveVac(String vacId);
}
