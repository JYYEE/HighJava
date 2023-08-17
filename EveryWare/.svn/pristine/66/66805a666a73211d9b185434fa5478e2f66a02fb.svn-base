package everyware.doc.service;

import java.util.List;

import everyware.doc.dao.IOrgDao;
import everyware.doc.dao.OrgDaoImpl;
import everyware.doc.vo.DepartmentVO;

public class OrgServiceImpl implements IOrgServicce {
	private static IOrgServicce service;
	private IOrgDao dao;
	
	private OrgServiceImpl() {
		dao = OrgDaoImpl.getInstance();
	}
	
	public static IOrgServicce getInstance() {
		if(service==null) service = new OrgServiceImpl();
		return service;
	}

	@Override
	public List<DepartmentVO> selectAll() {
		return dao.selectAll();
	}
	
}
