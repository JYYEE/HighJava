package test.java03;

public class MemberVo {
	private String memId;
	private String memName; 
	public int birthYear;
	
	public MemberVo() {}
	
	public MemberVo(String memId, String memName) {
		super();
		this.memId = memId;
		this.memName = memName;
	}
	
	
	public MemberVo(String memId, String memName, int brithYear) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.birthYear = brithYear;
	}

	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int brithYear) {
		this.birthYear = brithYear;
	} 
	
	//@Deprecated
	public void setMember(String memId, String memName, int birthYear) {
		this.memId = memId;
		this.memName = memName;
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memName=" + memName + ", birthYear=" + birthYear + "]";
	}

}
