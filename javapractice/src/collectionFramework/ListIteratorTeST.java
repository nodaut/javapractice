package collectionFramework;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorTeST {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();	//listIterator�� list�������̽��� ������ ArrayList,LinkedList��� ��밡��
		list.add(1);
		list.add(2);
		list.add(3);

		ListIterator it = list.listIterator();
		
		//���������� ����. cursor�� list�� �ǳ��� �����ϰ� ��
		while(it.hasNext())
			System.out.print(it.next());
		
		System.out.println("");
		
		//���������� ����. cursor�� list�� ��ó������ �����ϰ� �� 
		while(it.hasPrevious())	//�Ϲ� iterator���� hasPrevious() �� previous()�� ����
			System.out.print(it.previous());
	}

}
