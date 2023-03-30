package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashcodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setNum(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setNum(2); 
//		p2.setName("일지매"); 
		p2.setNum(1); 
		p2.setName("홍길동"); 
		
		
		Person p3 = p1;
		
		System.out.println(p1 == p2); // false, false
		System.out.println(p1.equals(p2)); // false, false (object 객체에서 equals는 주소값을 비교하도록 되어있음)  ==> equals 재정의 이후 true
		System.out.println("------------------------------");
		
		HashSet<Person> testSet = new HashSet<>();
		
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("Set의 개수 : " + testSet.size()); // 결과값 : 2
		// equals로는 두 객체를 같다고 파악했는데 set에서는 각각 객체를 다르다고 인식해서 set의 개수가 2개
		// hashCode는 참조값을 기반으로 만들어짐. ==> 참조값이 다르면 hashCode도 달라짐. ==> Set에서는 hashCode도 비교해서 같은 것으로 인식x.
		// set의 개수도 1개로 나오게 하려면 hashCode 메서드도 재정의해야함... (그래서 메소드 재정의할 때 equals와 hashCode가 같이 묶여있구만...)
		// hashCode 메서드 재정의 이후 set개수 1개, hashCode값 다 같아짐.
		System.out.println("------------------------------");
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode());
	}

}


class Person{
	private int num;
	private String name;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) // 참조값이 같으면
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Person other = (Person) obj; // 매개변수의 값의 현재 객체 유형으로 형변환한다.
//		
//		if(this.name == null && other.name != null)
//			return false;
//		if(this.num == other.num && this.name == other.name)
//			return true;
//		if(this.num == other.num && this.name.equals(other.name))
//			return true;
//		return false;
//	}
//	
	@Override
	public int hashCode() {
		return Objects.hash(name, num);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && num == other.num;
	}
	
}