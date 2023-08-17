package everyware.mypage.service;

import java.util.List;
import java.util.Map;

import everyware.mypage.dao.IMyPageDao;
import everyware.mypage.dao.MyPageDaoImpl;
import everyware.mypage.vo.LogVO;
import groupware.emp.vo.EmployeesVO;

public class MyPageServiceImpl implements IMyPageService {
	private static IMyPageService service;
	private IMyPageDao dao;
	
	private MyPageServiceImpl() {
		dao = MyPageDaoImpl.getInstance();
	}
	
	public static IMyPageService getInstance() {
		if(service==null) service = new MyPageServiceImpl();
		return service;
	}

	@Override
	public List<LogVO> selectLogList(Map<String, Object> map) {
		return dao.selectLogList(map);
	}

	@Override
	public int updateMyInfo(EmployeesVO vo) {
		return dao.updateMyInfo(vo);
	}

}
