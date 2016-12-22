package javapractice;

final class FinalTest{
	final int fvar =10;	//fvar�� ����� �� ���� ����
	FinalTest(){
		System.out.println("FinalTest constructor");
	}
}

/*
class FinalTest2 extends FinalTest{	 FinalTest�� final�� ����Ǿ� �����Ƿ� extend�� �� ����.
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
		//System.out.println(" This is a static method static var is "+normal_var); static �޼��� �������� static ������ �ƴ� �Ϲ� ������ ����� �� ���� 		
	}
}

public class staticFinal{
	public static void main(String args[]){
		
		for(int i=0;i<5;i++){
			staticTest obj = new staticTest();
			//static int varX=10; static�� local ������ ���� �� ����.
		}
		staticTest.showStaticVal();	//��ó�� static method�� ��ü�� �������� �ʰ� ȣ�� ����	
	}
}