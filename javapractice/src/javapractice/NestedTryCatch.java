package javapractice;

public class NestedTryCatch {

	public static void main(String[] args) {
		try{
			try{
				int a = 3/0;
			}catch(Exception e){
				System.out.println("inside try-catch");
			}
			int a = 3/0;

		}catch(Exception e){
			System.out.println("outside try-catch");	//내부의 문제는 내부의 try-catch에서 처리하고 그 외의 자잘한 문제를 처리 할때 outside try-catch를 쓰면됨
		}

	}

}
