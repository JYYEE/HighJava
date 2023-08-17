package everyware.mypage.dao;

import java.util.List;
import java.util.Map;

import everyware.mypage.vo.LogVO;
import groupware.emp.vo.EmployeesVO;

public interface IMyPageDao {
	
	public List<LogVO> selectLogList(Map<String, Object> map);
	
	public int updateMyInfo(EmployeesVO vo);
	
}
