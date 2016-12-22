package javapractice;

/*------------------------------------------------------------------------------------------------------------------------------
interface�� �ٴ� �Ϲ��� Ÿ��
	1. interface���� �Ϲ������� 'abstract'�� ����. 
		- �Ϲ������� �ڵ��� interface�� 'abstract'Ű����� �����Ѵ�.
		   	: ��������� abstract�� ���̴� ���� ���Ĺ��(obsolete)��. �����Ϸ��� �ڵ����� �߰� ���ְ� �ڵ尡 �������� ������� ����.
		   		�� abstract�� �ٿ��� �������� �ǳ�, ������� ����
	
	2. interface ������ �޼��忡�� 'public abstract'�� ����
	3. interface ������ ������ �Ϲ������� public static final�� ����.
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

interface test{	//�����Ͻ�  abstract interface interfacecar�� ��ȯ��.
	void testFunc();		//�����Ͻ�  public abstract void engine()���� ��ȯ��.
	int max =100;		//�����Ͻ� public static final int max = 100���� ��ȯ��
	//int min;			//interface���� ������ ������ staticŸ���̹Ƿ� �׳� ���� �ϸ� ������ ��
}