package collectionFramework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class ArrayListLinkedListTest {

	public static void main(String[] args) {
		ArrayList a1 = new ArrayList(2000000);
		LinkedList l1 = new LinkedList();

		ArrayList a2 = new ArrayList(2000000);
		LinkedList l2 = new LinkedList();
		
		System.out.println("Sequential Add TEST");
		System.out.println("[ArrayList] Sequential Add time :  " + sequentialAdd(a1));
		System.out.println("[LinkedList] Sequential Add time :  " + sequentialAdd(l1));

		System.out.println("Mid Add TEST");
		System.out.println("[ArrayList] Mid Add time :  " + midAdd(a1));
		System.out.println("[LinkedList] Mid Add time :  " + midAdd(l1));
		
		a2=a1; l2=l1;	//sequentialRemove를 하면 데이터가 다 삭제되어 midRemove를 제대로 테스트 할수 없으니까 a2,l2에 임시저장하고 midRemove 테스트에 쓴다.
		
		System.out.println("Sequential Remove TEST");
		System.out.println("[ArrayList] Sequential Remove time :  " + sequentialRemove(a1));
		System.out.println("[LinkedList] Sequential Remove time :  " + sequentialRemove(l1));
				
		System.out.println("Mid Remove TEST");
		System.out.println("[ArrayList] Mid Remove time :  " + midRemove(a2));
		System.out.println("[LinkedList] Mid Remove time :  " + midRemove(l2));
	}

	static long sequentialAdd(List list){
		long startTime = System.currentTimeMillis();
		for(int i=0; i<10000000; i++)
			list.add("data");
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}
	
	static long midAdd(List list){
		long startTime = System.currentTimeMillis();
		for(int i=0; i<10000; i++)
			list.add(5000,"data");
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}
	
	static long sequentialRemove(List list){
		long startTime = System.currentTimeMillis();
		for(int i=list.size()-1; i>=0; i--)
			list.remove(i);
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}
	
	static long midRemove(List list){
		long startTime = System.currentTimeMillis();
		for(int i=0; i<list.size(); i++)
			list.remove(i);
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}

}
