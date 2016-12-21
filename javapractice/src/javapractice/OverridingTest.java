package javapractice;

//부모 class
class ParentClass {
	public void testfunc(){
		System.out.println("This is Parent class!");
	}
}

//자식 class
public class OverridingTest extends ParentClass{
	
	@Override	//오버라이딩 어노테이션
	public void testfunc(){
		System.out.println("This is Child class!");
		super.testfunc();	//부모 class에 정의된 testfunc 메서드를 호출
	}

	public static void main(String[] args) {
		OverridingTest testObj = new OverridingTest();
		testObj.testfunc();		
	}
}


/*	 
 * super 키워드
	- 상속으로 구현된 class에서 부모 class를 가리키는 객체
	- 주로 부모 class의 메서드를 override 했을때 부모class에 원래 정의되어 있었던 메서드를 참고할때 사용
		
	@Override 어노테이션 
	- 컴파일러에게 이것이 부모class에 있던 메서드를 override한 것이라고 알려줌
	- Override 어노테이션을 붙이는것은 필수는 아니나, Override가 잘못되었을때 컴파일러가 에러를 줄수있고, 코드가독성이 좋아지기 때문에 붙이는것이 권장됨		
	
	Reference
		https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html
*/