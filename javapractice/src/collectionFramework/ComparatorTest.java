package collectionFramework;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {

	public static void main(String[] args) {
		String[] strArr = {"cat", "Dog", "lion", "tiger"};
		
		Arrays.sort(strArr);	//String의 기본 Comparable에 의한 정렬 (대소문자 구분 오름차순)
		System.out.println("[Default sort]strArr : "+Arrays.toString(strArr));
		
		Arrays.sort(strArr,String.CASE_INSENSITIVE_ORDER);	//대소문자 구분없이 정렬
		System.out.println("[CASE_INSENSITIVE_ORDER sort]strArr : "+Arrays.toString(strArr));

		Arrays.sort(strArr, new descOrder());	//descOrder클래스를 Comparator로 한 역순정렬
		System.out.println("[Descend sort]strArr : "+Arrays.toString(strArr));
	}
}

class descOrder implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable ){	//instanceof를 통해 o1과 o2가 comparable이 구현된 class의 객체인지 체크해야 함
			Comparable c1=(Comparable)o1;	//o1과 o2는 Object 파라메터로 들어왔으므로 compareTo메서드를 사용하려면 둘다 Comparable으로 형변환 해줘야 함
			Comparable c2=(Comparable)o2;
			return (c1.compareTo(c2))*-1;
		}
		return -1;
	}
}