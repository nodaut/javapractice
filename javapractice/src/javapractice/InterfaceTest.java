package javapractice;

/*------------------------------------------------------------------------------------------------------------------------------
interface에 붙는 암묵적 타입
	1. interface에는 암묵적으로 'abstract'가 붙음. 
		- 일반적으로 코딩시 interface에 'abstract'키워드는 생략한다.
		   	: 명시적으로 abstract를 붙이는 것은 구식방식(obsolete)임. 컴파일러가 자동으로 추가 해주고 코드가 쓸데없이 길어지기 때문.
		   		☞ abstract를 붙여도 컴파일을 되나, 권장되지 않음
	
	2. interface 내부의 메서드에는 'public abstract'가 붙음
	3. interface 내부의 변수는 암묵적으로 public static final이 붙음.
------------------------------------------------------------------------------------------------------------------------------*/

public class InterfaceTest implements test{
	public void testFunc(){
		System.out.println("Implemented testFunc()!!");
	}

	public static void main(String[] args){
		InterfaceTest it = new InterfaceTest();
		it.testFunc();
	}
}

interface test{	//컴파일시  abstract interface interfacecar로 변환됨.
	void testFunc();		//컴파일시  public abstract void engine()으로 변환됨.
	int max =100;		//컴파일시 public static final int max = 100으로 변환됨
	//int min;			//interface내부 변수는 무조건 static타입이므로 그냥 선언만 하면 에러가 남
}