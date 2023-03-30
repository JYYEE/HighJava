package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		// ArrayList의 기본적인 사용법은 Vector와 같다.
		
		ArrayList list1 = new ArrayList<>();
		
		// 데이터 추가하기 : add() 메서드를 이용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(true);
		list1.add(123.45);
		
		System.out.println("list1 => " + list1);
		System.out.println("size ==> " + list1.size());
		System.out.println();
		
		// 데이터 꺼내오기 : get() 메서드를 이용해서 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list1.get(1));
		
		// 데이터 끼워넣기
		list1.add(3, "zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 변경하기
		String sTemp = (String) list1.set(3, "yyy");
		System.out.println("list1 => " + list1);
		System.out.println("sTemp => " + sTemp);
		System.out.println();
		
		// 데이터 삭제하기
		list1.remove(3); //index 3번재 삭제
		System.out.println("3번째 자료 삭제 후 list1 => " + list1);
		
		list1.remove("bbb");
		System.out.println("bbb자료 삭제 후 list1 => " + list1);
		list1.remove(Integer.valueOf(123));
		System.out.println("123자료 삭제 후 list1 => " + list1);
		
		// 제네릭 사용
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for(int i=0; i<list2.size(); i++) {
			System.out.println(i + " ==> " + list2.get(i));
		}
		
		for (String str : list2) {
			System.out.println(str);
		}
		System.out.println("---------------------------------------------");
		
		// contains(비교객체) ==> 리스트에 저장된 데이터 중에서 '비교객체'가 있으면 true, 없으면 false를 반환
		System.out.println("DDDD값 존재 여부 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ값 존재 여부 : " + list2.contains("ZZZZ"));
		
		// indexOf(비교객체)
		// lastIndexOf(비교객체) ==> 리스트에 '비교객체'가 있으면 '비교객체'가 저장된 index값을 반환하고 없으면 -1을 반환한다.
		// 두 개의 차이점 : 검색 방향이 다름. indexOf()는 앞에서부터 뒤쪽으로 검색하고, lastIndexOf()는 뒤에서 앞쪽으로 검색한다.
		// 비교객체가 많으면 검색해서 첫번째로 찾아진 데이터의 위치값을 반환한다.
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		System.out.println("list2 => "+list2);
		
		System.out.println("DDDD의 위치값 : " + list2.indexOf("DDDD")); // 앞에서부터 검색해서 제일 먼저 존재하는 'DDDD'의 위치값 반환.
		System.out.println("ZZZZ의 위치값 : " + list2.indexOf("ZZZZ"));
		System.out.println("DDDD의 위치값 : " + list2.lastIndexOf("DDDD")); // 뒤에서부터 검색해서 제일 먼저 존재하는 'DDDD'의 위치값 반환.
		System.out.println("---------------------------------------------");
		
		// toArray() ==> 리스트 안의 데이터를 배열로 변환해서 반환한다. 
		//			 ==> 기본적으로 Object형 배열로 변환한다.
		
		// toArray(new 제네릭타입명[0]) ==> 제네릭 타입의 배열로 변환해서 반환한다. 
		
		Object[] staArr = list2.toArray(); 
		//String[] staArr = (String[]) list2.toArray(); // 스트링으로 캐스팅해도 실행에러 발생. 내부에 있는 데이터는 형변환 안됨. ==> 사용불가!!
		
		System.out.println("List의 개수 : " + list2.size());
		System.out.println("배열의 개수 : " + staArr.length);
		
		for (int i = 0; i < staArr.length; i++) { // 배열에 있는 자료 꺼내올 때 인덱스는 [] 안에 써주기
			System.out.println(i + "번째 자료 : " + staArr[i]);			
		}
		
		// 제네릭 타입의 배열로 변환해서 가져오기
		System.out.println();
		String[] staArr2 = list2.toArray(new String[0]);
		for (String s : staArr2) {
			System.out.println(s);
		}
	}

}
