package javapractice;

final class FinalTest{
	final int fvar =10;	//fvar는 변경될 수 없는 변수
	FinalTest(){
		System.out.println("FinalTest constructor");
	}
}

/*
class FinalTest2 extends FinalTest{	 FinalTest는 final로 선언되어 있으므로 extend될 수 없음.
	FinalTest2(){
		System.out.println("FinalTest2 constructor");
	}
}*/

class staticTest{
	static int static_var=100;
	int normal_var=100;
	
	staticTest(){
		static_var++;
		normal_var++;
		System.out.println("This is a static_var "+static_var);
		System.out.println("This is a normal_var  "+normal_var);
	}
	
	static void showStaticVal(){	//static method
		System.out.println("This is a static method static var is "+static_var);
		//System.out.println(" This is a static method static var is "+normal_var); static 메서드 내에서는 static 변수가 아닌 일반 변수는 사용할 수 없음 		
	}
}

public class staticFinal{
	public static void main(String args[]){
		
		for(int i=0;i<5;i++){
			staticTest obj = new staticTest();
			//static int varX=10; static은 local 변수에 붙일 수 없다.
		}
		staticTest.showStaticVal();	//이처럼 static method는 객체를 생성하지 않고도 호출 가능	
	}
}