package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 * 정렬과 관련된 interface는 Comparable, Comparator 2가지 존재.
 * - Comparable : Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때 구현하는 인터페이스.
 * 				 (내부 정렬 기준을 작성할 때 사용 -> 만들어진 객체에 우리가 만든 정렬기준 넣고싶을 때 사용)
 * - Comparator : 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스.
 * 				 (외부 정렬 기준)
 * - Comparable에는 compareTo()메서드를 재정의하고, Comparator에서는 compare()메서드를 재정의 해야 한다.
 * - String 클래스, Wrapper 클래스, Data 클래스, File 클래스에는 내부 정렬 기준이 구현되어 있다. 
 *   (내부 정렬 기준은 오름차순으로 처리되도록 구현되어 있다.) 
 */

public class ListSortTest01 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
		/*
		 * List 정렬은 Collections.sort() 메서드를 이용해서 정렬한다.
		 * Collections.sort() 메서드는 기본적으로 내부 정렬 기준으로 정렬한다. 
		 */
		
		Collections.sort(list);
		System.out.println("정렬 후 : " + list);// 오름차순 정렬(사전식 정렬)
		
		Collections.shuffle(list); //자료 섞기
		System.out.println("자료 섞기 후 : " + list);
		
		//외부 정렬 기준을 적용해서 정렬하기
		Collections.sort(list, new Desc()); 
		System.out.println("내림차순 정렬 후 : " + list);
	}

}

// 정렬 방식을 정해주는 class를 작성한다. (이것을 외부 정렬 기준 클래스라고 한다.)
// Comparator 인터페이스를 구현해서 작성한다.
class Desc implements Comparator<String>{
	// compare() 메서드를 이용해서 정렬하고자 하는 기준을 정해준다. 
	
	// compare() 메서드의 반환값과 그 의미
	// - 0 : 두 값이 같다(순서는 바뀌지 않는다.)
	// - 양수 : 앞, 뒤의 순서를 바꾼다.
	// - 음수 : 앞, 뒤의 순서를 바꾸지 않는다.
	
	// 예) 오름차순일 경우 ==> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 구현하면 된다.
	//     내림차순일 경우 ==> 앞의 값이 크면 음수, 같으면 0, 앞의 값이 작으면 양수를 반환하도록 구현하면 된다.
	@Override
	public int compare(String str1, String str2) {
		// 내림차순으로 구현
		if(str1.compareTo(str2)>0) {// 앞의 값이 크다는 얘기 ==> 내림차순 상태 ==> 그대로 유지 ==> 음수반환
			return -1;
		} else if(str1.compareTo(str2)<0) {// 뒤의 값이 크다는 얘기 ==> 내림차순 상태 ==> 자리 바꿔줘야함 ==> 양수반환
			return 1;
		} else {
			return 0;
		}
	}
}
