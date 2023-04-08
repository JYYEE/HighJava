package everyware.dao;

import java.util.List;
import java.util.Map;

import everyware.vo.AttendancesVO;

public interface IAttendancesDao {
	/**
	 * 출근버튼을 눌렀을 때 찍힌 시간을 가져와 DB에 출근시간을 기록
	 * @param map : 출근시간, empId를 key값으로 갖는 map
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateAttTime(Map<String, String> map);
	
	/**
	 * 출근버튼을 눌렀을 때 찍힌 시간을 가져와 DB에 출근상태 기록
	 * 9시 이전 : 정상, 9시 이후 : 지각
	 * @param map : 출근시간, empId를 key값으로 갖는 map
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateAttSta(Map<String, String> map);
	
	/**
	 * 퇴근버튼을 눌렀을 때 찍힌 시간을 가져와 DB에 퇴근시간을 기록
	 * @param map : 퇴근시간, empId를 key값으로 갖는 map
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateLeaveTime(Map<String, String> map);
	
	/**
	 * 지각사유를 작성
	 * @param map : att_memo와 empId를 key값으로 갖는 map
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int insertAttMemo(Map<String, String> map);
	
	/**
	 * (관리자용) 근태 전체 조회
	 * @return attList
	 */
	public List<AttendancesVO> selectAtt();
	
}
