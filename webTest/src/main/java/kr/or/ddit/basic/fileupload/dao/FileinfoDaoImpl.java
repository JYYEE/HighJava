package kr.or.ddit.basic.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoDaoImpl implements IFileinfoDao {
	private static FileinfoDaoImpl dao;
	private FileinfoDaoImpl() {};
	public static FileinfoDaoImpl getInstance() {
		if(dao == null) dao = new FileinfoDaoImpl();
		return dao;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVO) {
		SqlSession session = null;
		int cnt = 0;	// 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		SqlSession session = null;
		List<FileinfoVO> fileList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			fileList = session.selectList("fileinfo.getAllFileinfo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return fileList;
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		SqlSession session = null;
		FileinfoVO fileVO = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			fileVO = session.selectOne("fileinfo.getFileinfo", fileNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
		return fileVO;
	}
}
