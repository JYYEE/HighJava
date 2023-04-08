package everyware.vo;

public class AttendancesVO {
	private String att_date;
	private String emp_id;
	private String att_time;
	private String leave_time;
	private String att_state;
	private String att_memo;
	
	public String getAtt_memo() {
		return att_memo;
	}
	public void setAtt_memo(String att_memo) {
		this.att_memo = att_memo;
	}
	public String getAtt_date() {
		return att_date;
	}
	public void setAtt_date(String att_date) {
		this.att_date = att_date;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getAtt_time() {
		return att_time;
	}
	public void setAtt_time(String att_time) {
		this.att_time = att_time;
	}
	public String getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}
	public String getAtt_state() {
		return att_state;
	}
	public void setAtt_state(String att_state) {
		this.att_state = att_state;
	}

}
