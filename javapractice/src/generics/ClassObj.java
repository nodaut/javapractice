package generics;

class testClass{
	public void testMethod(){
		System.out.println("<<This is a test method>>");
	}
}

class ClassGenerics{
	//일반 지네릭스 메서드
	public static <T> T ClassGenTest1(T t) {	//메서드 선언부 혹은 class 선언부에 <T>를 꼭 붙여야 한다. 안붙이면 에러남
		return (T)t;
	}

	//java.lang.Class를 이용한 지네릭스 메서드
	public static testClass ClassGenTest2(Class<? extends testClass> t) throws InstantiationException, IllegalAccessException {	//파라메터에 올 수 있는 타입은 testClass혹은 testClass를 상속한 class로만 제한됨
		System.out.println("t.getClass() : "+t.getClass());
		System.out.println("t.newInstance().getClass() : " + t.newInstance().getClass());
		System.out.println("testClass.class : " + testClass.class);
		
		if(t.newInstance().getClass().equals(testClass.class))
			System.out.println("'t.newInstance().getClass()' is the same type as 'testClass.class'");
		
		/*
		파라메터 t에 newInstance()메서드로 객체를 생성해 메모리에 로드하지 않으면 java는 파라메터 t를 그냥 java.lang.Class 타입으로 간주한다.
		따라서 파라메터 t에 newInstance()메서드를 써서 testClass 타입의 객체를 만들어야 testClass 타입으로 리턴할 수 있다.
		*/
		
		return t.newInstance();	
	}
}


public class ClassObj {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		testClass t = new testClass();
		
		System.out.println("-----Normal generics method-----");
		(ClassGenerics.ClassGenTest1(t)).testMethod(); 
		
		System.out.println("\n-----Generics method using java.lang.Class-----");
		(ClassGenerics.ClassGenTest2(t.getClass())).testMethod(); 
	}

}
/*
	지네릭스 와일드 카드
		<? extends T> : 와일드카드 상한 - T와 그 자손만 가능
		<? super T> : 와일드카드 하한 - T와 그 조상만 가능
		<?> : 제한 없음. 모든 타입이 가능 - <? extends Object>와 동일
	-------------------------------------------------------
	java.lang.Class
		- 현재 동작하고 있는 모든 객체를 참조하는 클래스
		- 보통 지네릭스 와일드 카드와 함께 사용되어, 지네릭스 메서드의 파라메터 타입을 특정 class의 자손/조상으로 제한할때 사용되는 듯함
	-------------------------------------------------------
	Reference
	http://stackoverflow.com/questions/8422842/in-java-syntax-class-extends-something
	http://stackoverflow.com/questions/2350558/whats-the-meaning-of-the-question-mark-inside-the-angle-brackets-extends-ja
	https://www.tutorialspoint.com/java/lang/java_lang_class.htm
	자바의 정석 678p
*/
