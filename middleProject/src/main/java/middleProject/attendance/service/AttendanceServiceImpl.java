package middleProject.attendance.service;

import middleProject.attendance.dao.AttendanceDaoImpl;
import middleProject.attendance.dao.IAttendanceDao;

public class AttendanceServiceImpl implements IAttendanceService {
	private IAttendanceDao dao;
	
	private static AttendanceServiceImpl service;
	
	private AttendanceServiceImpl() {
		dao = AttendanceDaoImpl.getDao();
	}
	
	public static AttendanceServiceImpl getService() {
		if(service == null) service = new AttendanceServiceImpl();
		return service;
	}
	@Override
	public String insertAttTime() {
		return dao.insertAttTime();
	}

	@Override
	public String updateAttState() {
		return dao.updateAttState();
	}

}
