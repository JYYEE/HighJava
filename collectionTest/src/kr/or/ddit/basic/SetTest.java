package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SetTest {
/*
 * - List와 Set의 차이점
 * 1. List
 *    - 데이터의 순서(index)가 있다.
 *    - 중복데이터를 저장할 수 있다.
 * 2. Set
 * 	  - 데이터의 순서(index)가 없다.
 *    - 중복되는 데이터를 저장할 수 없다.
 */
	public static void main(String[] args) {
		HashSet hs1 = new HashSet<>();
		
		//Set에 데이터를 추가할 때도 add()메서드를 이용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 hs1 => "+hs1);
		System.out.println("Set 데이터 hs1 => "+hs1); // 데이터의 순서는 없어도 여러번 출력했을 때 나오는 순서는 같음.
		System.out.println("Set의 개수 : " + hs1.size()); //Set의 개수 = size 메소드 이용
		System.out.println();
		
		// Set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd); //true면 Set에 추가됨.
		System.out.println("Set 데이터 hs1 => "+hs1);
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdd); //false면 Set에 추가시키지 않음.
		System.out.println("Set 데이터 hs1 => "+hs1);
		System.out.println();
		
		// list에서는 수정: list.set(index, 새로운 데이터)=> index를 알아야지 가능함.
		// Set에 데이터 수정하기 ==> Set에는 데이터를 수정하는 메서드가 따로 없다.
		// 수정하는 방법 : 해당 자료를 삭제한 후에 추가해주는 방법을 사용한다.(수정하는 효과)
		// Set의 데이터 삭제하기 ==> remove(삭제할 자료), 반환값 : 삭제 성공 : true, 삭제 실패 : false
		//                       ==> clear() : 전체 데이터 삭제 
		// 예) "FF" 데이터를 "EE"로 변경하기 
		hs1.remove("FF");
		System.out.println("FF삭제 후 Set 데이터 hs1 => "+hs1);
		hs1.add("EE");
		System.out.println("EE추가 후 Set 데이터 hs1 => "+hs1);
		System.out.println();
		System.out.println("------------------------------------------");

		//hs1.clear();
		//System.out.println("clear 후 Set 데이터 hs1 => "+hs1);
		
		// list에서는 데이터 불러오기 : list.get(index) => index를 알아야지 가능함.
		/*
		 * Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러올 수 없다.
		 * 그래서 데이터를 하나씩 차례로 얻기 위해서는 Iterator형 객체로 변환해야 한다.
		 * 
		 * - Set형의 데이터들을 Iterator형 객체로 변환하는 메서드 ==> iterator()
		 */
		
		Iterator it = hs1.iterator(); // Set데이터를 Iterator로 변환하기
		
		/*
		 * Iterator의 hasNext() 메서드 ==> Iterator의 포인터가 가리키는 곳의 다음번째에 데이터가 있는지 검사한다.
		 * 								   데이터가 있으면 true, 없으면 false를 반환
		 * 			  next() 메서드 ==> Iterator의 포인터를 다음번째 위치로 이동시킨 후 그 곳의 데이터를 반환한다.
		 */
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("------------------------------------------");
		// 향상된 for문을 이용하여 데이터를 차례로 가져오기
		for (Object obj : hs1) {
			System.out.println(obj);
		}
		System.out.println("------------------------------------------");
		
		// Set : 중복데이터 제거에 자주 활용 
		// 중복 숫자를 뽑지 않기 위해 뽑은 숫자를 Set에 넣고 Set의 크기가 원하는 크기가 될때까지 반복해서 뽑으면 된다.
		
		// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자. 
		// 번호는 1번부터 28번까지 있고, 추첨할 인원이 3명이다.
		// 당첨번호를 출력하시오.
		
		// 어떤 두수  사이에서 뽑고 싶으면 
		// rnd.next(최대값 - 최소값 +1) + 최소값;
		
		Random rnd = new Random();
//		int num = rnd.nextInt(28) + 1;
//		System.out.println(num);
		
		HashSet<Integer> testSet = new HashSet<>();
		while(testSet.size()<3) {
			testSet.add(rnd.nextInt(28)+1);
		}
		
		System.out.println("당첨자 번호 : " + testSet);
		
		// Set은 꺼내오기 불편 ==> Set유형의 자료를 List형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<>(testSet);
		
		System.out.println("List의 데이터 출력");
		for (int i = 0; i < testList.size(); i++) {
			System.out.println(testList.get(i));
			
		}
	}

}
