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
			System.out.println("outside try-catch");	//������ ������ ������ try-catch���� ó���ϰ� �� ���� ������ ������ ó�� �Ҷ� outside try-catch�� �����
		}

	}

}
