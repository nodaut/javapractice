package javapractice;

//---------정의-------------------------------------------------------------------
//interface
interface InterfaceType{
	public void InterFunc();
}

//interface 구현 class
class ImplementType implements InterfaceType{
	public void InterFunc(){System.out.println("I am implemented(chlid) class!");}
}

//abstract class
abstract class AbstractType{
	public abstract void AbstractFunc();
}

//abstract class를 상속한 class
class ExtendType extends AbstractType{
	public void AbstractFunc(){System.out.println("I am extended(chlid) class!");}
}
//-------------------------------------------------------------------------------


public class ImplementExtendType {	
	public static void main(String[] args) {

/*
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	자식 클래스(extends)의 객체 생성시 class 타입 선택
		- 객체의 타입은 부모 class와 자식 class타입 모두 가능 new 생성자 뒤만 자식 class이면 된다.
		 	-> 아래 예에서 ExtendType은 AbstractType이라는 부모 class로부터 상속받은 class인데, 객체를 만들때 'AbstractType test1' 처럼 무모 class로도 만들 수 있고 'ExtendType test2'처럼 자식 class로도 만들 수 있다. 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
		AbstractType test1 =  new ExtendType();
		ExtendType test2 =  new ExtendType();
		test1.AbstractFunc();
		test2.AbstractFunc();
		
/*
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	구현 클래스(implements)의 객체 생성시 class 타입 선택
		- 객체의 타입은 interface class와 구현 class타입 모두 가능 new 생성자 뒤만 구현 class이면 된다.
		 	-> 아래 예에서 ImplementType은 InterfaceType라는 interface를 구현한 class인데, 객체를 만들때 'InterfaceType test3' 처럼 interface로도 만들 수 있고 'ImplementType test4'처럼 구현 class로도 만들 수 있다. 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
		InterfaceType test3 =  new ImplementType();
		ImplementType test4 =  new ImplementType();
		test3.InterFunc();
		test4.InterFunc();
	}
}
