package collectionFramework;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList list1 = new ArrayList(10);
		list1.add(new Integer(5));
		list1.add(new Integer(4));
		list1.add(new Integer(2));
		list1.add(new Integer(0));
		list1.add(new Integer(12));
		list1.add(new Integer(22));


		ArrayList list2 = new ArrayList(list1.subList(0, 4));
		print(list1,list2);
		
		Collections.sort(list1);	
		Collections.sort(list2);
		print(list1,list2);
		
		System.out.println("list1.containsAll(list2) : " + list1.containsAll(list2));
		
		list2.add("B");
		list2.add("C");
		list2.add(0,"A");	//list2�� 0�� index�� "A"�� �߰���
		print(list1,list2);
		
		/*
		list1���� list2�� ��ġ�� �κи� ����� �������� ������
		A.retainAll(B) => A-(A��B)'	=> A���� A�� B�� �������� �������� ����
		*/
		System.out.println("list1.retainAll(list2) : "+list1.retainAll(list2));
		print(list1,list2);
		
		for(int i=list2.size()-1 ; i>0 ; i--){
			System.out.println("loop i : "+i);	//list2.size() �� ��ȭ�� ������� i�� ��ó�� ������ ������ 0���� �� ��������. 
			System.out.println("list2.size()-1 : "+(list2.size()-1));
			if(list1.contains(list2.get(i))){
				System.out.println("To remove Item : "+list2.get(i));
			
				list2.remove(i);
				System.out.println("Removed i :"+i);
			}
		}
		
		/*
		array���� ��� ��Ұ� �����Ǹ� ������ ��� ������ ����� index�� �ϳ��� ������
			- ���� �Ʒ��� �ڵ�ó�� i�� ������Ű�鼭 loop�� ������ array�� �̷��� index��ȭ�� �ݴ�� i�� �����ϹǷ� Ư�� ��Ҵ� üũ���� ���ϰ� ��ŵ�ϸ� ������ ���Ե�
			- ���� ���� �ڵ�ó�� i�� ���ҽ�Ű�鼭 loop�� ������ array�� index��ȭ�� �°� �Ǿ� ��� ��Ҹ� ��ŵ���� �ʰ� �� üũ�Ҽ� �ִ�.
		
		for(int i=0 ; i<list2.size()-1 ; i++){	
			System.out.println("loop i : "+i);	//list2.size() �� ��ȭ�� ������� i�� ��ó�� ������ ������ 0���� �� ��������. 
			System.out.println("list2.size()-1 : "+(list2.size()-1));
			if(list1.contains(list2.get(i))){
				System.out.println("To remove Item : "+list2.get(i));
			
				list2.remove(i);
				System.out.println("Removed i :"+i);
			}
		}
		*/
		print(list1,list2);	
	}
	
	static void print(ArrayList list1, ArrayList list2){
		System.out.println("list1 : "+list1);
		System.out.println("list2 : "+list2);
	}

}
