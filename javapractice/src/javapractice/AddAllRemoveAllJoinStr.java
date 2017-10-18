package javapractice;

import java.util.*;
public class AddAllRemoveAllJoinStr {

	public static void main(String[] args) {
		List<String> strList1 = new ArrayList<String>();
		List<String> strList2 = new ArrayList<String>();	
		List<String> strList3 = new ArrayList<String>();			
		Set<String> removeStrList = new HashSet<String>();
		
		//addAll은 해당 list에서 파라메터 list/set의 모든 요소들을 삽입함
		System.out.println("addAll to removeStrList: " + removeStrList.addAll(Arrays.asList("1","2")));
		//Arrays.asList를 통해 요소들을 array형으로 바로 변환 가능함
		//asList를 사용할때는 이와 같이 Arrays static변수로 call해야 함
		System.out.println("addAll to strList1: " + strList1.addAll(Arrays.asList("1","","2","3","4")));
		
		//removeAll은 해당 list에서 파라메터 list/set의 모든 요소들을 삭제함
		//removeAll후 삭제한 요소가 1개 이상이면 true를, 삭제한 요소가 한개도 없다면 false를 리턴함
		System.out.println("removeAll from strList1: " +strList1.removeAll(removeStrList));
		System.out.println("removeAll from strList2: " +strList2.removeAll(removeStrList));
		
		//join은 주어진 list의 각 요소를 delim으로 구분한 하나의 string을 리턴함 
		//만약 list가 빈 list라면 빈 string ""을 리턴함 
		//join를 사용할때는 이와 같이 String static변수로 call해야 함
		
		String joinedStr1 = String.join("/", strList1);
		String joinedStr2 = String.join("/", strList2);
		System.out.println("joinedStr1 : "+ joinedStr1 + "\njoinedStr1 len : "+joinedStr1.length());
		System.out.println("joinedStr2 : "+ joinedStr2 + "\njoinedStr2 len : "+joinedStr2.length());
		
		//join은 빈 list라면 빈 string ""을 리턴하는데, 길이가 0이더라도 어찌됐든 string을 리턴하므로 strList3의 사이즈는 2가 됨
		strList3.add(joinedStr2);
		strList3.add(joinedStr1);
		System.out.println("strList3 : " + strList3);
		System.out.println("strList3 len : " + strList3.size());

	}

}
