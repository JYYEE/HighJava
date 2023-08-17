package everyware.vac.service;

import java.util.List;
import java.util.Map;

import everyware.vac.dao.IVactionsDao;
import everyware.vac.dao.VacationsDaoImpl;
import everyware.vac.vo.VacationsVO;

public class VacationsServiceImpl implements IVacationsService {
	private IVactionsDao dao;
	private static IVacationsService service;
	private VacationsServiceImpl() {
		dao = VacationsDaoImpl.getInstance();
	}
	public static IVacationsService getInstance() {
		if(service == null) {
			service = new VacationsServiceImpl();
		}
		return service;
	}
	@Override
	public int insertVac(VacationsVO vacVO) {
		return dao.insertVac(vacVO);
	}

	@Override
	public List<VacationsVO> selectVacByEmp(String empId) {
		return dao.selectVacByEmp(empId);
	}

	@Override
	public int deleteVac(int vacId) {
		return dao.deleteVac(vacId);
	}

	@Override
	public List<VacationsVO> selectAllVac() {
		return dao.selectAllVac();
	}

	@Override
	public int approveVac(int vacId) {
		return dao.approveVac(vacId);
	}
	@Override
	public int updateRemainVac(Map<String, Integer> map) {
		return dao.updateRemainVac(map);
	}
	@Override
	public VacationsVO selectVacByVacId(int vacId) {
		return dao.selectVacByVacId(vacId);
	}
//	@Override
//	public List<Map<String, String>> selectRemainVac(String empId) {
//		return dao.selectRemainVac(empId);
//	}

}
