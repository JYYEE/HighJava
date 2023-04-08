package everyware.service;

import java.util.List;

import everyware.dao.IVactionsDao;
import everyware.dao.VacationsDaoImpl;
import everyware.vo.VacationsVO;

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

}
