package everyware.doc.service;

import java.util.List;
import java.util.Map;

import everyware.doc.dao.DocumentDaoImpl;
import everyware.doc.dao.IDocumentDao;
import everyware.doc.vo.DocumentVO;
import everyware.doc.vo.PageVO;
import groupware.emp.vo.EmployeesVO;

public class DocumentServiceImpl implements IDocumentService {
	private static IDocumentService service;
	private IDocumentDao dao;
	
	private DocumentServiceImpl() {
		dao = DocumentDaoImpl.getInstace();
	}
	
	public static IDocumentService getInstance() {
		if(service==null) service = new DocumentServiceImpl();
		return service;
	}

	@Override
	public PageVO pageInfo(int page, Map<String,Object> map, String menu, EmployeesVO empvo) {
		// 전체 글 개수
		
		int count = 0;
		switch (menu) {
		case "received":
			map.put("dept_id", empvo.getDept_id());
			count = this.receivedTotalCount(map);
			break;
		case "uploaded":
			map.put("emp_id", empvo.getEmp_id());
			count = this.uploadedTotalCount(map);
			break;
		case "approval":
			map.put("emp_id", empvo.getEmp_id());
			count = this.approveTotalCount(map);
			break;
		case "scrapped":
			map.put("emp_id", empvo.getEmp_id());
			count = this.scrapTotalCount(map);
			break;
		}
		
		// 전체 페이지수 구하기
		int totalPage = (int) Math.ceil((double) count / PageVO.getPerList());

		// start, end 구하기
		int start = (page - 1) * PageVO.getPerList() + 1;
		int end = start + PageVO.getPerList() - 1;

		if (end > count) {
			end = count;
		}

		// 시작페이지와 끝페이지
		int perPage = PageVO.getPerPage();
		int startPage = ((page - 1) / perPage * perPage) + 1;
		int endPage = startPage + perPage - 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		PageVO vo = new PageVO();
		vo.setStart(start);
		vo.setEnd(end);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setTotalPage(totalPage);

		return vo;
	}

	@Override
	public int receivedTotalCount(Map<String, Object> map) {
		return dao.receivedTotalCount(map);
	}

	@Override
	public List<DocumentVO> selectReceivedByPage(Map<String, Object> map) {
		return dao.selectReceivedByPage(map);
	}

	@Override
	public List<DocumentVO> selectUploadedByPage(Map<String, Object> map) {
		return dao.selectUploadedByPage(map);
	}

	@Override
	public int uploadedTotalCount(Map<String, Object> map) {
		return dao.uploadedTotalCount(map);
	}

	@Override
	public List<DocumentVO> selectApproveByPage(Map<String, Object> map) {
		return dao.selectApproveByPage(map);
	}

	@Override
	public int approveTotalCount(Map<String, Object> map) {
		return dao.approveTotalCount(map);
	}

	@Override
	public List<DocumentVO> selectScrapByPage(Map<String, Object> map) {
		return dao.selectScrapByPage(map);
	}

	@Override
	public int scrapTotalCount(Map<String, Object> map) {
		return dao.scrapTotalCount(map);
	}

	@Override
	public DocumentVO selectDoc(Map<String, String> map) {
		return dao.selectDoc(map);
	}

	@Override
	public int insertDoc(DocumentVO docVo) {
		return dao.insertDoc(docVo);
	}

	@Override
	public int isMyApprovalTurn(Map<String, String> map) {
		return dao.isMyApprovalTurn(map);
	}

	@Override
	public int approval(Map<String, String> map) {
		return dao.approval(map);
	}

	@Override
	public int isFinalApproval(String docId) {
		return dao.isFinalApproval(docId);
	}

	@Override
	public int finalApproval(Map<String,String> map) {
		return dao.finalApproval(map);
	}

	@Override
	public int alarmToUploader(Map<String, String> map) {
		return dao.alarmToUploader(map);
	}

	@Override
	public int alarmToApprover(String docId) {
		return dao.alarmToApprover(docId);
	}

	@Override
	public int reject(Map<String, String> map) {
		return dao.reject(map);
	}

	@Override
	public int insertScrap(DocumentVO vo) {
		return dao.insertScrap(vo);
	}

	@Override
	public int deleteScrap(DocumentVO vo) {
		return dao.deleteScrap(vo);
	}

	@Override
	public int tempTotalCount(String empId) {
		return dao.tempTotalCount(empId);
	}

	@Override
	public List<DocumentVO> selectTempList(String empId) {
		return dao.selectTempList(empId);
	}

}
