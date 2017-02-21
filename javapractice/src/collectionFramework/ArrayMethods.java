package collectionFramework;

import java.util.Arrays;

public class ArrayMethods {

	public static void main(String[] args) {
		
		//toString과 deepToString 테스트
		int[] arr = {0,1,2,3,4};
		int[][] arr2D = {{11,12,13},{21,22,23}};
		System.out.println("arr : " + Arrays.toString(arr));
		System.out.println("arr2D : " + Arrays.deepToString(arr2D));
		
		//copyOf와 copyOfRange 테스트
		int[] arr2 = Arrays.copyOf(arr, arr.length);	//전체 복사
		int[] arr3 = Arrays.copyOfRange(arr, 1, 4); //idex 1~3까지 복사
		System.out.println("arr2 : " + Arrays.toString(arr2));
		System.out.println("arr3 : " + Arrays.toString(arr3));

		//fill과 setAll 테스트
		int[] arr4 = new int[5];
		int[] arr5 = new int[5];
		Arrays.fill(arr4, 7);
		Arrays.setAll(arr5, i->(int)(Math.random()*6)+1);
		System.out.println("arr4 : " + Arrays.toString(arr4));
		System.out.println("arr5 : " + Arrays.toString(arr5));
		
		//equals과 deepEquals테스트
		String[][] str2D = {{"aaa","bbb"},{"AAA","BBB"}};
		String[][] str2D2 = {{"aaa","bbb"},{"AAA","BBB"}};
		System.out.println("Arrays.equals(str2D, str2D2) : " + Arrays.equals(str2D, str2D2));
		System.out.println("Arrays.deepEquals(str2D, str2D2) : " + Arrays.deepEquals(str2D, str2D2));

		//sort와 binarySearch 테스트
		
		char[] charArr = {'D','A','Z','C','B','L','M'};
		char key = 'D';
		System.out.println("Before Sort charArr : " + Arrays.toString(charArr));
		System.out.println("Before Sort Binary search result["+key+"]:" + Arrays.binarySearch(charArr, key));	//-6으로 잘못된 결과가 나옴
		
		/*
		정렬안한 상태로 binarySearch를 하면 운좋게 올바른 결과가 나올수도 있지만 (특히 양끝값을 찾을때)잘못된 결과가 나옴
		따라서 binarySearch를 올바로 사용하기 위해서는 sort로 정렬을 한 후 써야 함
		*/
		
		Arrays.sort(charArr);
		System.out.println("After Sort charArr : " + Arrays.toString(charArr));
		System.out.println("After Sort Binary search result["+key+"]:" + Arrays.binarySearch(charArr, 'D'));
		
	}

}
