package kr.or.ddit.basic;
// javaDoc 파일 만들기 예제 ==> 프로그램과 메뉴얼을 같이 만드는 방법
/**
 * 
 * @author 홍길동(작성자)
 * @version 1.0
 * 
 * <p> 
 * 파일명 : JavaDocTest.java<br>
 * 설 명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 수정 이력<br>
 * -----------------------------------<br>
 * 수정일자 : 2023-03-17<br>
 * 작 성 자 : 홍길동<br>
 * 수정내용 : 최초 작성<br>
 * -----------------------------------<br>
 * </p>
 */

public interface JavaDocTest {
	/**
	 * 메소드명 :  methodTest<br>
	 * 설 명 : 반환값이 없는 메서드<Br>
	 * 
	 * @param a 첫번째 매개변수(정수형)
	 * @param b 두번재 매개변수(정수형)
	 */
	
	// 메소드 위에서 작성하면 매개변수 param에 대한 설명을 할 수 있게 바로 나옴
	public void methodTest(int a, int b);
	
	/**
	 * 메소드명 :  methodAdd<br>
	 * 설 명 : 반환값이 있는 메서드<Br>
	 *   
	 * @param x 첫번째 정수형 매개변수
	 * @param v 두번재 정수형 매개변수
	 * @return 처리된 결과가 정수형으로 반환된다.
	 */
	public int methodAdd(int x, int v);
	
	/**
	 * 메소드명 :  methodSub<br>
	 * 설 명 : 매개변수가 없는 메서드<Br> 
	 * @return 처리된 결과가 정수형으로 반환된다.
	 */
	public int methodSub();
}
