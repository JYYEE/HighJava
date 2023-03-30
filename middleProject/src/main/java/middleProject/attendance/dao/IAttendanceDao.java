package middleProject.attendance.dao;

public interface IAttendanceDao {
	// ip로 로그인시 출근시간 입력
	public String insertAttTime();
	// 출근시간으로 상태 입력
	public String updateAttState();
}
