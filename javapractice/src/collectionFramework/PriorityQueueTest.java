package collectionFramework;

import java.util.*;

public class PriorityQueueTest {

	public static void main(String[] args) {
		Queue pq = new PriorityQueue();
		pq.offer(24);	//오토박싱
		pq.offer(1);
		pq.offer(12);
		pq.offer(3);
		pq.offer(5);
		
		System.out.println(pq);	//heap구조 순서대로 저장됨
	
		Object item=null;
		while((item=pq.poll())!=null)	//priority가 가장 낮은 낮은 수부터 출력됨
			System.out.println(item);
	}

}
