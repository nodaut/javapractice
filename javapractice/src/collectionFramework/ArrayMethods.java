package collectionFramework;

import java.util.Arrays;

public class ArrayMethods {

	public static void main(String[] args) {
		
		//toString�� deepToString �׽�Ʈ
		int[] arr = {0,1,2,3,4};
		int[][] arr2D = {{11,12,13},{21,22,23}};
		System.out.println("arr : " + Arrays.toString(arr));
		System.out.println("arr2D : " + Arrays.deepToString(arr2D));
		
		//copyOf�� copyOfRange �׽�Ʈ
		int[] arr2 = Arrays.copyOf(arr, arr.length);	//��ü ����
		int[] arr3 = Arrays.copyOfRange(arr, 1, 4); //idex 1~3���� ����
		System.out.println("arr2 : " + Arrays.toString(arr2));
		System.out.println("arr3 : " + Arrays.toString(arr3));

		//fill�� setAll �׽�Ʈ
		int[] arr4 = new int[5];
		int[] arr5 = new int[5];
		Arrays.fill(arr4, 7);
		Arrays.setAll(arr5, i->(int)(Math.random()*6)+1);
		System.out.println("arr4 : " + Arrays.toString(arr4));
		System.out.println("arr5 : " + Arrays.toString(arr5));
		
		//equals�� deepEquals�׽�Ʈ
		String[][] str2D = {{"aaa","bbb"},{"AAA","BBB"}};
		String[][] str2D2 = {{"aaa","bbb"},{"AAA","BBB"}};
		System.out.println("Arrays.equals(str2D, str2D2) : " + Arrays.equals(str2D, str2D2));
		System.out.println("Arrays.deepEquals(str2D, str2D2) : " + Arrays.deepEquals(str2D, str2D2));

		//sort�� binarySearch �׽�Ʈ
		
		char[] charArr = {'D','A','Z','C','B','L','M'};
		char key = 'D';
		System.out.println("Before Sort charArr : " + Arrays.toString(charArr));
		System.out.println("Before Sort Binary search result["+key+"]:" + Arrays.binarySearch(charArr, key));	//-6���� �߸��� ����� ����
		
		/*
		���ľ��� ���·� binarySearch�� �ϸ� ������ �ùٸ� ����� ���ü��� ������ (Ư�� �糡���� ã����)�߸��� ����� ����
		���� binarySearch�� �ùٷ� ����ϱ� ���ؼ��� sort�� ������ �� �� ��� ��
		*/
		
		Arrays.sort(charArr);
		System.out.println("After Sort charArr : " + Arrays.toString(charArr));
		System.out.println("After Sort Binary search result["+key+"]:" + Arrays.binarySearch(charArr, 'D'));
		
	}

}
