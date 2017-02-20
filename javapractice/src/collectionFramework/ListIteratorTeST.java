package collectionFramework;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorTeST {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();	//listIterator는 list인터페이스를 구현한 ArrayList,LinkedList등에만 사용가능
		list.add(1);
		list.add(2);
		list.add(3);

		ListIterator it = list.listIterator();
		
		//순방향으로 읽음. cursor는 list의 맨끝에 도달하게 됨
		while(it.hasNext())
			System.out.print(it.next());
		
		System.out.println("");
		
		//역방향으로 읽음. cursor는 list의 맨처음으로 도달하게 됨 
		while(it.hasPrevious())	//일반 iterator에는 hasPrevious() 및 previous()가 없음
			System.out.print(it.previous());
	}

}
