package everyware.doc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import everyware.doc.vo.DocumentVO;
import kr.or.ddit.util.MybatisSqlSessionFactory;

public class DocumentDaoImpl implements IDocumentDao {
	private static IDocumentDao dao;
	
	private DocumentDaoImpl() { }
	
	public static IDocumentDao getInstace() {
		if(dao==null) dao = new DocumentDaoImpl();
		return dao;
	}
	
	@Override
	public int insertDoc(DocumentVO docVo) {
		int cnt = 0;
		
		try (SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			session.insert("doc.insertDoc", docVo);
			cnt = session.selectOne("doc.selectCurrval");
			session.commit();
		}
		return cnt;
	}
	
	/*
	 * 파라미터값, 결과값 확인!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	 */
	@Override
	public DocumentVO selectDoc(Map<String, String> map) {
		DocumentVO vo = null;
		SqlSession session = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			vo = session.selectOne("doc.selectDoc", map);
		} finally {
			session.close();
		}
		return vo;
	}

	@Override
	public int receivedTotalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.receivedTotalCount", map);
		}
		return cnt;
	}

	@Override
	public List<DocumentVO> selectReceivedByPage(Map<String, Object> map) {
		List<DocumentVO> list = null;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			list = session.selectList("doc.selectReceivedByPage", map);
		}
		return list;
	}

	@Override
	public List<DocumentVO> selectUploadedByPage(Map<String, Object> map) {
		List<DocumentVO> list = null;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			list = session.selectList("doc.selectUploadedByPage", map);
		}
		return list;
	}

	@Override
	public int uploadedTotalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.uploadedTotalCount", map);
		}
		return cnt;
	}

	@Override
	public List<DocumentVO> selectApproveByPage(Map<String, Object> map) {
		List<DocumentVO> list = null;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			list = session.selectList("doc.selectApproveByPage", map);
		}
		return list;
	}

	@Override
	public int approveTotalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.approveTotalCount", map);
		}
		return cnt;
	}

	@Override
	public List<DocumentVO> selectScrapByPage(Map<String, Object> map) {
		List<DocumentVO> list = null;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			list = session.selectList("doc.selectScrapByPage", map);
		}
		return list;
	}

	@Override
	public int scrapTotalCount(Map<String, Object> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.scrapTotalCount", map);
		}
		return cnt;
	}

	@Override
	public int isMyApprovalTurn(Map<String, String> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.isMyApprovalTurn", map);
		}
		return cnt;
	}

	@Override
	public int approval(Map<String, String> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.update("doc.approval", map);
			
			session.commit();
		}
		return cnt;
	}

	@Override
	public int isFinalApproval(String docId) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.isFinalApproval", docId);
		}
		return cnt;
	}

	@Override
	public int finalApproval(Map<String,String> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.update("doc.finalApproval", map);
			
			session.commit();
		}
		return cnt;
	}

	@Override
	public int alarmToUploader(Map<String, String> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.insert("doc.alarmToUploader", map);
			
			session.commit();
		}
		return cnt;
	}

	@Override
	public int alarmToApprover(String docId) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.insert("doc.alarmToApprover", docId);
			
			session.commit();
		}
		return cnt;
	}

	@Override
	public int reject(Map<String, String> map) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.update("doc.reject", map);
			session.update("doc.reject2", map);
			
			session.commit();
			System.out.println("dao결과!!!!!!!!" + cnt);
		}
		return cnt;
	}

	@Override
	public int insertScrap(DocumentVO vo) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.insert("doc.insertScrap", vo);
			
			session.commit();
		}
		return cnt;
	}

	@Override
	public int deleteScrap(DocumentVO vo) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.update("doc.deleteScrap", vo);
			
			session.commit();
		}
		return cnt;
	}

	@Override
	public int tempTotalCount(String empId) {
		int cnt = 0;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			cnt = session.selectOne("doc.tempTotalCount", empId);
		}
		return cnt;
	}

	@Override
	public List<DocumentVO> selectTempList(String empId) {
		List<DocumentVO> vo = null;
		
		try(SqlSession session = MybatisSqlSessionFactory.getSqlSession()){
			vo = session.selectList("doc.selectTempList", empId);
		}
		return vo;
	}

}
