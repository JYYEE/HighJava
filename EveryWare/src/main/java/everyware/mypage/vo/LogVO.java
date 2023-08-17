package everyware.mypage.vo;

public class LogVO {
	private int rnum;
	private String log_id      ;
	private String emp_id      ;
	private String log_time    ;
	private String log_ip      ;
	private String log_browser ;
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getLog_time() {
		return log_time;
	}
	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}
	public String getLog_ip() {
		return log_ip;
	}
	public void setLog_ip(String log_ip) {
		this.log_ip = log_ip;
	}
	public String getLog_browser() {
		return log_browser;
	}
	public void setLog_browser(String log_browser) {
		this.log_browser = log_browser;
	}
	@Override
	public String toString() {
		return "LogVO [log_id=" + log_id + ", emp_id=" + emp_id + ", log_time=" + log_time + ", log_ip=" + log_ip
				+ ", log_browser=" + log_browser + "]";
	}
	
}
