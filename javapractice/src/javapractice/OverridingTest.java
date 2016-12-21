package javapractice;

//�θ� class
class ParentClass {
	public void testfunc(){
		System.out.println("This is Parent class!");
	}
}

//�ڽ� class
public class OverridingTest extends ParentClass{
	
	@Override	//�������̵� ������̼�
	public void testfunc(){
		System.out.println("This is Child class!");
		super.testfunc();	//�θ� class�� ���ǵ� testfunc �޼��带 ȣ��
	}

	public static void main(String[] args) {
		OverridingTest testObj = new OverridingTest();
		testObj.testfunc();		
	}
}


/*	 
 * super Ű����
	- ������� ������ class���� �θ� class�� ����Ű�� ��ü
	- �ַ� �θ� class�� �޼��带 override ������ �θ�class�� ���� ���ǵǾ� �־��� �޼��带 �����Ҷ� ���
		
	@Override ������̼� 
	- �����Ϸ����� �̰��� �θ�class�� �ִ� �޼��带 override�� ���̶�� �˷���
	- Override ������̼��� ���̴°��� �ʼ��� �ƴϳ�, Override�� �߸��Ǿ����� �����Ϸ��� ������ �ټ��ְ�, �ڵ尡������ �������� ������ ���̴°��� �����		
	
	Reference
		https://docs.oracle.com/javase/tutorial/java/annotations/predefined.html
*/