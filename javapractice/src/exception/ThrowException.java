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
			throw new customException();	//메서드 선언부에 throws 문을 추가한 경우는 에러  발생 부분에 throw new 구문을 추가하여 익셉션 class에 던져야 한다. 
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
			//메서드 선언부에 커스텀 익셉션에 대한 throws가 있는 메서드를 호출할 때는 현재 메서드(main)에 throws 문을 추가하거나 try-catch로 감싸야 한다.
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