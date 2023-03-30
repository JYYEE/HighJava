package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * Vector, Hashtable등의 예전부터 존재하던 Collection 객체들은
 * 내부에 동기화 처리가 되어 있다.
 * 
 * 그런데 최근에 새로 구성된 Collection들은 동기화 처리가 되어 있지 않다. 
 * 그래서 동기화가 필요한 프로그램에서 이런 Collection 객체들을 사용하려면
 * 동기화 처리를 한 후에 사용해야 한다.
 * 
 */
public class ThreadTest17 {
	private static Vector<Integer> vec = new Vector<>(); // 정수가 저장될 벡터
	
	// 동기화 처리가 되어 있지 않은 List --> 오류발생
	// 이유? 내부에 배열이 존재 -> 데이터를 배열에 하나씩 저장 
	// -> 배열이 다차면 이미 존재하고 있는 배열보다 2배정도 큰 배열 만들어서 기존 데이터를 옮겨와서 다시 저장
	// -> 배열을 새로 만들어서 옮길 때 문제가 생김.
	// -> 배열을 옮길 때 또는 배열 크게 만드는 과정에서 쓰레드 제어가 넘어가면 이 과정에서 데이터 손실이 생김
	// -> 동기화 처리 필요
	private static List<Integer> list1 = new ArrayList<>();
	
	// 동기화 처리를 한 List
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		// 익명 구현체로 쓰레드 구현
		 Runnable r = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10000; i++){
					//vec.add(i);
					//list1.add(i);
					list2.add(i);					
				}
			}
		};
		//-----------------
		Thread[] thArr = new Thread[] {
			new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r)	
		};
		for (Thread th : thArr) {
			th.start();
		}
		for (Thread th : thArr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		//System.out.println("vec의 개수 : " + vec.size());
		//System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
