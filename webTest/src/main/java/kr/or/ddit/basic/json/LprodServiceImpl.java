package kr.or.ddit.basic.json;

import java.util.List;

import kr.or.ddit.vo.LprodVO;

public class LprodServiceImpl implements ILprodService{
	private ILprodDao dao;
	private static LprodServiceImpl service;
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	public static LprodServiceImpl getInstance() {
		if(service == null) service= new LprodServiceImpl();
		return service;
	}
	
	@Override
	public List<LprodVO> getLprod() {
		return dao.getLprod();
	}
	
}
