package collectionFramework;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {

	public static void main(String[] args) {
		String[] strArr = {"cat", "Dog", "lion", "tiger"};
		
		Arrays.sort(strArr);	//String�� �⺻ Comparable�� ���� ���� (��ҹ��� ���� ��������)
		System.out.println("[Default sort]strArr : "+Arrays.toString(strArr));
		
		Arrays.sort(strArr,String.CASE_INSENSITIVE_ORDER);	//��ҹ��� ���о��� ����
		System.out.println("[CASE_INSENSITIVE_ORDER sort]strArr : "+Arrays.toString(strArr));

		Arrays.sort(strArr, new descOrder());	//descOrderŬ������ Comparator�� �� ��������
		System.out.println("[Descend sort]strArr : "+Arrays.toString(strArr));
	}
}

class descOrder implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Comparable && o2 instanceof Comparable ){	//instanceof�� ���� o1�� o2�� comparable�� ������ class�� ��ü���� üũ�ؾ� ��
			Comparable c1=(Comparable)o1;	//o1�� o2�� Object �Ķ���ͷ� �������Ƿ� compareTo�޼��带 ����Ϸ��� �Ѵ� Comparable���� ����ȯ ����� ��
			Comparable c2=(Comparable)o2;
			return (c1.compareTo(c2))*-1;
		}
		return -1;
	}
}