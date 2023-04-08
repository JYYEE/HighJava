package everyware.service;

import java.util.List;
import java.util.Map;

import everyware.dao.AttendancesDaoImpl;
import everyware.dao.IAttendancesDao;
import everyware.vo.AttendancesVO;

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
//	@Override
//	public String insertAttTime() {
//		return dao.insertAttTime();
//	}
//
//	@Override
//	public String updateAttState() {
//		return dao.updateAttState();
//	}

	@Override
	public int updateAttTime(Map<String, String> map) {
		return dao.updateAttTime(map);
	}

	@Override
	public int updateAttSta(Map<String, String> map) {
		return dao.updateAttSta(map);
	}

	@Override
	public int updateLeaveTime(Map<String, String> map) {
		return dao.updateLeaveTime(map);
	}

	@Override
	public int insertAttMemo(Map<String, String> map) {
		return dao.insertAttMemo(map);
	}

	@Override
	public List<AttendancesVO> selectAtt() {
		return dao.selectAtt();
	}

}
