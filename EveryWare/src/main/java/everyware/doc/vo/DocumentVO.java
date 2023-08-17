package everyware.doc.vo;

import java.util.List;

public class DocumentVO {
	private int rnum;
	private String doc_id;
	private String doc_no;
	private String emp_id;
	private String emp_name;
	private String dept_name;
	private String doc_date;
	private String aprv_date;
	private String doc_title;
	private String doc_content;
	private String doc_state ;
	private String doc_reject;
	private String my_state;
	private int is_scrap;
	private int is_temp;
	private List<FileOfDocumentVO> files;
	private List<ApprovalVO> approvals;
	private List<RecipientVO> recipients;
	
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}
	public String getAprv_date() {
		return aprv_date;
	}
	public void setAprv_date(String aprv_date) {
		this.aprv_date = aprv_date;
	}
	public String getDoc_title() {
		return doc_title;
	}
	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}
	public String getDoc_content() {
		return doc_content;
	}
	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}
	public String getDoc_state() {
		return doc_state;
	}
	public void setDoc_state(String doc_state) {
		this.doc_state = doc_state;
	}
	public String getDoc_reject() {
		return doc_reject;
	}
	public void setDoc_reject(String doc_reject) {
		this.doc_reject = doc_reject;
	}
	public List<FileOfDocumentVO> getFiles() {
		return files;
	}
	public void setFiles(List<FileOfDocumentVO> files) {
		this.files = files;
	}
	public List<ApprovalVO> getApprovals() {
		return approvals;
	}
	public void setApprovals(List<ApprovalVO> approvals) {
		this.approvals = approvals;
	}
	
	public List<RecipientVO> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<RecipientVO> recipients) {
		this.recipients = recipients;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getMy_state() {
		return my_state;
	}
	public void setMy_state(String my_state) {
		this.my_state = my_state;
	}
	public int getIs_scrap() {
		return is_scrap;
	}
	public void setIs_scrap(int is_scrap) {
		this.is_scrap = is_scrap;
	}
	public int getIs_temp() {
		return is_temp;
	}
	public void setIs_temp(int is_temp) {
		this.is_temp = is_temp;
	}
	@Override
	public String toString() {
		return "DocumentVO [doc_id=" + doc_id + ", doc_no=" + doc_no + ", emp_id=" + emp_id + ", emp_name=" + emp_name
				+ ", dept_name=" + dept_name + ", doc_date=" + doc_date + ", aprv_date=" + aprv_date + ", doc_title="
				+ doc_title + ", doc_content=" + doc_content + ", doc_state=" + doc_state + ", doc_reject=" + doc_reject
				+ ", files=" + files + ", approvals=" + approvals + ", recipients=" + recipients + "]";
	}
	
}
