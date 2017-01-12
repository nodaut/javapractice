package javapractice;

//---------����-------------------------------------------------------------------
//interface
interface InterfaceType{
	public void InterFunc();
}

//interface ���� class
class ImplementType implements InterfaceType{
	public void InterFunc(){System.out.println("I am implemented(chlid) class!");}
}

//abstract class
abstract class AbstractType{
	public abstract void AbstractFunc();
}

//abstract class�� ����� class
class ExtendType extends AbstractType{
	public void AbstractFunc(){System.out.println("I am extended(chlid) class!");}
}
//-------------------------------------------------------------------------------


public class ImplementExtendType {	
	public static void main(String[] args) {

/*
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	�ڽ� Ŭ����(extends)�� ��ü ������ class Ÿ�� ����
		- ��ü�� Ÿ���� �θ� class�� �ڽ� classŸ�� ��� ���� new ������ �ڸ� �ڽ� class�̸� �ȴ�.
		 	-> �Ʒ� ������ ExtendType�� AbstractType�̶�� �θ� class�κ��� ��ӹ��� class�ε�, ��ü�� ���鶧 'AbstractType test1' ó�� ���� class�ε� ���� �� �ְ� 'ExtendType test2'ó�� �ڽ� class�ε� ���� �� �ִ�. 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
		AbstractType test1 =  new ExtendType();
		ExtendType test2 =  new ExtendType();
		test1.AbstractFunc();
		test2.AbstractFunc();
		
/*
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	���� Ŭ����(implements)�� ��ü ������ class Ÿ�� ����
		- ��ü�� Ÿ���� interface class�� ���� classŸ�� ��� ���� new ������ �ڸ� ���� class�̸� �ȴ�.
		 	-> �Ʒ� ������ ImplementType�� InterfaceType��� interface�� ������ class�ε�, ��ü�� ���鶧 'InterfaceType test3' ó�� interface�ε� ���� �� �ְ� 'ImplementType test4'ó�� ���� class�ε� ���� �� �ִ�. 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
		InterfaceType test3 =  new ImplementType();
		ImplementType test4 =  new ImplementType();
		test3.InterFunc();
		test4.InterFunc();
	}
}
