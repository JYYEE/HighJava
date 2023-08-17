package everyware.att.service;

import java.util.List;
import java.util.Map;

import everyware.att.dao.AttendancesDaoImpl;
import everyware.att.dao.IAttendancesDao;
import everyware.att.vo.AttendancesVO;

public class AttendancesServiceImpl implements IAttendancesService {
	private IAttendancesDao dao;
	
	private static AttendancesServiceImpl service;
	
	private AttendancesServiceImpl() {
		dao = AttendancesDaoImpl.getInstance();
	}
	
	public static AttendancesServiceImpl getService() {
		if(service == null) service = new AttendancesServiceImpl();
		return service;
	}

	@Override
	public int updateAtt(Map<String, String> map) {
		return dao.updateAtt(map);
	}

	@Override
	public int updateLeaveTime(Map<String, String> map) {
		return dao.updateLeaveTime(map);
	}

	@Override
	public int updateAttMemo(Map<String, String> map) {
		return dao.updateAttMemo(map);
	}

	@Override
	public List<AttendancesVO> selectAllAtt() {
		return dao.selectAllAtt();
	}

	@Override
	public List<AttendancesVO> selectAttByEmp(String empId) {
		return dao.selectAttByEmp(empId);
	}

	@Override
	public String selectAttMemo(Map<String, String> map) {
		return dao.selectAttMemo(map);
	}

	@Override
	public List<AttendancesVO> selectAttByDay(String attDate) {
		return dao.selectAttByDay(attDate);
	}

}
