package everyware.doc.dao;

import java.util.List;
import java.util.Map;

import everyware.doc.vo.DocumentVO;

public interface IDocumentDao {
	// 나중에 모든 파라미터값에 사용자 아이디(or 사번) 추가
	// WHERE절에 결재 상태값도 저장
	
	/*
	 * AllDoc: 사용자에게 공개된 모든 문서
	 * DraftDoc: 기안문서
	 * ApprovalDoc: 결재문서
	 * ScrapDoc: 보관문서
	 */
	
	// 수신문서함
	public List<DocumentVO> selectReceivedByPage(Map<String, Object> map);
	
	public int receivedTotalCount(Map<String, Object> map);
	
	
	// 기안문서함
	public List<DocumentVO> selectUploadedByPage(Map<String, Object> map);
	
	public int uploadedTotalCount(Map<String, Object> map);
	
	
	// 결재문서함
	public List<DocumentVO> selectApproveByPage(Map<String, Object> map);
	
	public int approveTotalCount(Map<String, Object> map);
	
	
	// 보관문서함
	public List<DocumentVO> selectScrapByPage(Map<String, Object> map);
	
	public int scrapTotalCount(Map<String, Object> map);
	
	
	// 임시저장 문서
	public int tempTotalCount(String empId);
	
	public List<DocumentVO> selectTempList(String empId);
	
	
	// 새 문서 작성
	public int insertDoc(DocumentVO docVo);
	
	// 한 개의 문서 조회
	public DocumentVO selectDoc(Map<String, String> map);
	
	
	// 결재 로직
	public int isMyApprovalTurn(Map<String, String> map);
	
	public int approval(Map<String, String> map);
	
	public int isFinalApproval(String docId);
	
	public int finalApproval(Map<String,String> map);
	
	public int alarmToUploader(Map<String, String> map);
	
	public int alarmToApprover(String docId);
	
	public int reject(Map<String, String> map);
	
	
	// 문서 보관
	public int insertScrap(DocumentVO vo);
	
	public int deleteScrap(DocumentVO vo);
	
}
