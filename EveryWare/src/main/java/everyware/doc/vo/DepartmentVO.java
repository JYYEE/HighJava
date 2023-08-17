package everyware.doc.vo;

import java.util.List;

import groupware.emp.vo.EmployeesVO;

public class DepartmentVO {
	private String code;
	private String code_name;
	private List<EmployeesVO> emps;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public List<EmployeesVO> getEmps() {
		return emps;
	}
	public void setEmps(List<EmployeesVO> emps) {
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "DepartmentVO [code=" + code + ", code_name=" + code_name + ", emps=" + emps + "]";
	}
	
}
