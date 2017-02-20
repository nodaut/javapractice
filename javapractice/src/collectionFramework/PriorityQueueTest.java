package collectionFramework;

import java.util.*;

public class PriorityQueueTest {

	public static void main(String[] args) {
		Queue pq = new PriorityQueue();
		pq.offer(24);	//����ڽ�
		pq.offer(1);
		pq.offer(12);
		pq.offer(3);
		pq.offer(5);
		
		System.out.println(pq);	//heap���� ������� �����
	
		Object item=null;
		while((item=pq.poll())!=null)	//priority�� ���� ���� ���� ������ ��µ�
			System.out.println(item);
	}

}
