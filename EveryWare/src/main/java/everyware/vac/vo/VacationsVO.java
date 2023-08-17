package everyware.vac.vo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VacationsVO {
	private int vac_id;
	private String emp_id;
	private String vac_type;
	private String vac_start;
	private String vac_end;
	private String vac_approve;
	private String vac_reason;
	private int vac_total ;
	
	public int getVac_total() {
		String startDatestr = vac_start.split(" ")[0];
		String endDatestr = vac_end.split(" ")[0];
        LocalDate startDate = LocalDate.parse(startDatestr);
        LocalDate endDate = LocalDate.parse(endDatestr);
        
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        // ChronoUnit 클래스는 두 날짜/시간 값 사이의 차이를 계산하는 데 사용
        int weekends = 0;
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekends++;
            }
        }
        this.vac_total = days - weekends;
		return vac_total;
	}
	public void setVac_total(int vac_total) {
		this.vac_total = vac_total;
	}
	public String getVac_reason() {
		return vac_reason;
	}
	public void setVac_reason(String vac_reason) {
		this.vac_reason = vac_reason;
	}
	public String getVac_approve() {
		return vac_approve;
	}
	public void setVac_approve(String vac_approve) {
		this.vac_approve = vac_approve;
	}
	public int getVac_id() {
		return vac_id;
	}
	public void setVac_id(int vac_id) {
		this.vac_id = vac_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getVac_type() {
		return vac_type;
	}
	public void setVac_type(String vac_type) {
		this.vac_type = vac_type;
	}
	public String getVac_start() {
		return vac_start;
	}
	public void setVac_start(String vac_start) {
		this.vac_start = vac_start;
	}
	public String getVac_end() {
		return vac_end;
	}
	public void setVac_end(String vac_end) {
		this.vac_end = vac_end;
	}

	
}
