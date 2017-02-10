package exception;

class customException extends Exception{
	public customException(){
		System.out.println("This is a customException");
	}
}

public class ThrowException {

	public void throwTest(int a, int b) throws customException{ 
		if (b==0){
			System.out.println("Catched exception");
			throw new customException();	//�޼��� ����ο� throws ���� �߰��� ���� ����  �߻� �κп� throw new ������ �߰��Ͽ� �ͼ��� class�� ������ �Ѵ�. 
		}
	}
	
	public void throwTest2(int a, int b) throws ArithmeticException{ 
		if (b==0){
			System.out.println("Catched ArithmeticException");
			throw new ArithmeticException();	 
		}
	}
	
	public static void main(String[] args) {
		
		new ThrowException().throwTest2(3, 0);
		
		try {
			new ThrowException().throwTest(3, 0);	
			//�޼��� ����ο� Ŀ���� �ͼ��ǿ� ���� throws�� �ִ� �޼��带 ȣ���� ���� ���� �޼���(main)�� throws ���� �߰��ϰų� try-catch�� ���ξ� �Ѵ�.
		} catch (customException e) {
			//e.printStackTrace();
		}
		
		

	}
}

/*
 * Reference
 * http://beginnersbook.com/2013/04/throw-in-java/
 * https://www.mkyong.com/java/java-custom-exception-examples/
 */