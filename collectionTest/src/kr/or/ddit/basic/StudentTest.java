//package kr.or.ddit.basic;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//import org.w3c.dom.stylesheets.LinkStyle;
//
///*
// * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student 클래스를 만든다.
// *       이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 인수로 받아서 초기화처리 한다.
// *       이 Student객체는 List에 저장하여 관리한다.
// *       List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고, 
// *       총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준도 
// *       구현하여 정렬된 결과를 출력하시오.
// *       (등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다.)
// */
//
//public class StudentTest {
//
//	public static void main(String[] args) {
//		ArrayList<Student> list = new ArrayList<>();
//		list.add(new Student(1, "홍길동", 80, 90, 70));
//		list.add(new Student(4, "성춘향", 95, 90, 95));
//		list.add(new Student(5, "변학도", 80, 65, 70));
//		list.add(new Student(3, "일지매", 100, 85, 95));
//		list.add(new Student(2, "심청이", 90, 60, 75));
//
//		System.out.println("정렬 전...");
//		for (Student stu : list) {
//			System.out.println(stu);
//		}
//
//		System.out.println("내부 정렬 후...");
//		Collections.sort(list);
//		for (Student stu : list) {
//			System.out.println(stu);
//		}
//		System.out.println("외부 정렬 후...");
//		Collections.sort(list, new SortByTotal());
//		for (int i=0; i<list.size(); i++) {
//			System.out.println(list);
//			
//		}
//		System.out.println("등수...");
////		list.indexOf(list))
//	}
//}

//class Student implements Comparable<Student> {
//	private int stNum;
//	private String name;
//	private int kscore;
//	private int escore;
//	private int mscore;
//	private int rank;
//
//	// 생성자
//	public Student(int stNum, String name, int kscore, int escore, int mscore) {
//		this.stNum = stNum;
//		this.name = name;
//		this.kscore = kscore;
//		this.escore = escore;
//		this.mscore = mscore;
//	}
//
//	@Override
//	public int compareTo(Student stu) {
//
//		return this.getStNum() - stu.getStNum();
//	}
//
//	public int getRank() {
//		return rank;
//	}
//
//	public void setRank(int rank) {
//		this.rank = rank;
//	}
//
//	public int getStNum() {
//		return stNum;
//	}
//
//	public void setStNum(int stNum) {
//		this.stNum = stNum;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getKscore() {
//		return kscore;
//	}
//
//	public void setKscore(int kscore) {
//		this.kscore = kscore;
//	}
//
//	public int getEscore() {
//		return escore;
//	}
//
//	public void setEscore(int escore) {
//		this.escore = escore;
//	}
//
//	public int getMscore() {
//		return mscore;
//	}
//
//	public void setMscore(int mscore) {
//		this.mscore = mscore;
//	}
//
//	@Override
//	public String toString() {
//		return "Student [stNum=" + stNum + ", name=" + name + ", kscore=" + kscore + ", escore=" + escore + ", mscore="
//				+ mscore + "]";
//	}
//
//}
//
//class SortByTotal implements Comparator<Student> {
//
//	@Override
//	public int compare(Student stu1, Student stu2) {
//		int totalScore1 = stu1.getEscore() + stu1.getKscore() + stu1.getMscore();
//		int totalScore2 = stu2.getEscore() + stu2.getKscore() + stu2.getMscore();
//		if (totalScore1 > totalScore2) {
//			return -1;
//		} else if (totalScore1 < totalScore2) {
//			return 1;
//		} else {
//			return stu1.getName().compareTo(stu2.getName());
//		}
//	}
//}