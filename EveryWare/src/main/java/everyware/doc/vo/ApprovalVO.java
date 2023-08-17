package everyware.doc.vo;

public class ApprovalVO {
	private String doc_id     ;
	private String emp_id     ;
	private String emp_name;
	private String dept_name;
	private String position_name;
	private int aprv_order ;
	private String aprv_state ;
	private String aprv_date  ;
	private String aprv_note;
	
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getAprv_order() {
		return aprv_order;
	}
	public void setAprv_order(int aprv_order) {
		this.aprv_order = aprv_order;
	}
	public String getAprv_state() {
		return aprv_state;
	}
	public void setAprv_state(String aprv_state) {
		this.aprv_state = aprv_state;
	}
	public String getAprv_date() {
		return aprv_date;
	}
	public void setAprv_date(String aprv_date) {
		this.aprv_date = aprv_date;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getAprv_note() {
		return aprv_note;
	}
	public void setAprv_note(String aprv_note) {
		this.aprv_note = aprv_note;
	}
	@Override
	public String toString() {
		return "ApprovalVO [doc_id=" + doc_id + ", emp_id=" + emp_id + ", emp_name=" + emp_name + ", dept_name="
				+ dept_name + ", aprv_order=" + aprv_order + ", aprv_state=" + aprv_state + ", aprv_date=" + aprv_date
				+ "]";
	}
	
}
