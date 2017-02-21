package collectionFramework;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorTest {

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
		
		System.out.println("\n"+list);
		
		//���������� �����鼭 �����͸� ����
		while(it.hasNext()){
			it.next();	
			it.remove();	//next()�� ���� ���������� �о�鿴�� ��Ҹ� ������
		}
		
		System.out.println(list);
		
	}

}
