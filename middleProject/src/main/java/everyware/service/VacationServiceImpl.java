package everyware.service;

import java.util.List;

import everyware.dao.IVactionsDao;
import everyware.dao.VacationsDaoImpl;
import everyware.vo.VacationsVO;

public class VacationServiceImpl implements IVacationService {
	private IVactionsDao dao;
	private static IVacationService service;
	private VacationServiceImpl() {
		dao = VacationsDaoImpl.getInstance();
	}
	public static IVacationService getInstance() {
		if(service == null) {
			service = new VacationServiceImpl();
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
	public int deleteVac(String vacId) {
		return dao.deleteVac(vacId);
	}

	@Override
	public List<VacationsVO> selectAllVac() {
		return dao.selectAllVac();
	}

	@Override
	public int approveVac(String vacId) {
		return dao.approveVac(vacId);
	}

}
