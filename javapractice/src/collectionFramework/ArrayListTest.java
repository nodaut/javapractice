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
		list2.add(0,"A");	//list2의 0번 index에 "A"를 추가함
		print(list1,list2);
		
		/*
		list1에서 list2와 겹치는 부분만 남기고 나머지는 삭제함
		A.retainAll(B) => A-(A∩B)'	=> A에서 A와 B의 교집합의 여집합을 뺀다
		*/
		System.out.println("list1.retainAll(list2) : "+list1.retainAll(list2));
		print(list1,list2);
		
		for(int i=list2.size()-1 ; i>0 ; i--){
			System.out.println("loop i : "+i);	//list2.size() 값 변화와 관계없이 i는 맨처음 정해진 값부터 0까지 쭉 내려간다. 
			System.out.println("list2.size()-1 : "+(list2.size()-1));
			if(list1.contains(list2.get(i))){
				System.out.println("To remove Item : "+list2.get(i));
			
				list2.remove(i);
				System.out.println("Removed i :"+i);
			}
		}
		
		/*
		array에서 어떠한 요소가 삭제되면 삭제된 요소 이후의 요소의 index는 하나씩 감소함
			- 만약 아래의 코드처럼 i를 증가시키면서 loop을 돌리면 array의 이러한 index변화와 반대로 i가 증가하므로 특정 요소는 체크하지 못하고 스킵하며 루프가 돌게됨
			- 따라서 위의 코드처럼 i를 감소시키면서 loop을 돌려야 array의 index변화에 맞게 되어 모든 요소를 스킵하지 않고 다 체크할수 있다.
		
		for(int i=0 ; i<list2.size()-1 ; i++){	
			System.out.println("loop i : "+i);	//list2.size() 값 변화와 관계없이 i는 맨처음 정해진 값부터 0까지 쭉 내려간다. 
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
