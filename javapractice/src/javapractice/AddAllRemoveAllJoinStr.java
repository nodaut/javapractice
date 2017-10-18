package javapractice;

import java.util.*;
public class AddAllRemoveAllJoinStr {

	public static void main(String[] args) {
		List<String> strList1 = new ArrayList<String>();
		List<String> strList2 = new ArrayList<String>();	
		List<String> strList3 = new ArrayList<String>();			
		Set<String> removeStrList = new HashSet<String>();
		
		//addAll�� �ش� list���� �Ķ���� list/set�� ��� ��ҵ��� ������
		System.out.println("addAll to removeStrList: " + removeStrList.addAll(Arrays.asList("1","2")));
		//Arrays.asList�� ���� ��ҵ��� array������ �ٷ� ��ȯ ������
		//asList�� ����Ҷ��� �̿� ���� Arrays static������ call�ؾ� ��
		System.out.println("addAll to strList1: " + strList1.addAll(Arrays.asList("1","","2","3","4")));
		
		//removeAll�� �ش� list���� �Ķ���� list/set�� ��� ��ҵ��� ������
		//removeAll�� ������ ��Ұ� 1�� �̻��̸� true��, ������ ��Ұ� �Ѱ��� ���ٸ� false�� ������
		System.out.println("removeAll from strList1: " +strList1.removeAll(removeStrList));
		System.out.println("removeAll from strList2: " +strList2.removeAll(removeStrList));
		
		//join�� �־��� list�� �� ��Ҹ� delim���� ������ �ϳ��� string�� ������ 
		//���� list�� �� list��� �� string ""�� ������ 
		//join�� ����Ҷ��� �̿� ���� String static������ call�ؾ� ��
		
		String joinedStr1 = String.join("/", strList1);
		String joinedStr2 = String.join("/", strList2);
		System.out.println("joinedStr1 : "+ joinedStr1 + "\njoinedStr1 len : "+joinedStr1.length());
		System.out.println("joinedStr2 : "+ joinedStr2 + "\njoinedStr2 len : "+joinedStr2.length());
		
		//join�� �� list��� �� string ""�� �����ϴµ�, ���̰� 0�̴��� ����Ƶ� string�� �����ϹǷ� strList3�� ������� 2�� ��
		strList3.add(joinedStr2);
		strList3.add(joinedStr1);
		System.out.println("strList3 : " + strList3);
		System.out.println("strList3 len : " + strList3.size());

	}

}
