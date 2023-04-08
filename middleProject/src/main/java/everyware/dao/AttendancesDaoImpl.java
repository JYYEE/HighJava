package everyware.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import config.MybatisSqlSessionFactory;
import everyware.vo.AttendancesVO;

public class AttendancesDaoImpl implements IAttendancesDao {
	private static IAttendancesDao dao;
	private AttendancesDaoImpl() {}
	
	public static IAttendancesDao getInstance() {
		if(dao == null) dao = new AttendancesDaoImpl();
		return dao;
	}

	@Override
	public int updateAttTime(Map<String, String> map) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("att.updateAttTime", map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateAttTime dao오류");
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateAttSta(Map<String, String> map) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("att.updateAttSta", map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateAttSta dao오류");
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateLeaveTime(Map<String, String> map) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("att.updateLeaveTime", map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateLeaveTime dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int insertAttMemo(Map<String, String> map) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("att.insertAttMemo", map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertAttMemo dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public List<AttendancesVO> selectAtt() {
		SqlSession session = null;
		List<AttendancesVO> attList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			attList = session.selectList("att.selectAtt");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAtt dao 오류");
		} finally {
			session.commit();
			session.close();
		}
		return attList;
	}

//	@Override
//	public String insertAttTime() {
//		InetAddress ip = null;
//		String attTime = null;
//		try {
//			ip = InetAddress.getLocalHost();
//			String ipaddr = ip.toString();
//			//System.out.println(ipaddr);
//			if (ipaddr.contains("192.168.146")) {
//				// 현재 시간을 LocalDateTime 객체로 가져옵니다.
//				LocalDateTime now = LocalDateTime.now();
//
//				// LocalDateTime 객체를 원하는 형식의 문자열로 변환합니다.
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//				attTime = now.format(formatter);
//
//				// 출력합니다.
//				System.out.println(attTime+"에 출근하였습니다.");
//			} else {
//				System.out.println("출근시간 등록에 실패하였습니다. 회사 내부에서 다시 시도해주세요.");
//			}
//		} catch (UnknownHostException e) {
//			System.out.println("ip주소를 찾지 못했습니다.");
//			e.printStackTrace();
//		}
//		return attTime;
//	}
//
//	@Override
//	public String updateAttState() {
////		// 현재 시간을 LocalDateTime 객체로 가져옵니다.
////		LocalDateTime now = LocalDateTime.now();
////
////		// LocalDateTime 객체를 원하는 형식의 문자열로 변환합니다.
////		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////		String attTime = now.format(formatter);
////		String attState = "";
//		
//		 // 한국 시간대로 현재 시간을 가져옵니다.
//        ZoneId seoul = ZoneId.of("Asia/Seoul");
//        LocalTime now = LocalTime.now(seoul);
//        
//        // 9시와 현재 시간을 비교합니다.
//        LocalTime nineAM = LocalTime.of(9, 0, 59); // 9시 0분 59초
//        String attStatus = now.isBefore(nineAM) ? "정상" : "지각";
//        
//        // 출력합니다.
//        System.out.println("출근 상태: " + attStatus);
//		return attStatus;
//	}
}
