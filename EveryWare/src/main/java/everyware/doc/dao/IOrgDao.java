package everyware.doc.dao;

import java.util.List;

import everyware.doc.vo.DepartmentVO;

public interface IOrgDao {
	public List<DepartmentVO> selectAll();
}
