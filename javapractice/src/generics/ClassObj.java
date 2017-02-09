package generics;

class testClass{
	public void testMethod(){
		System.out.println("<<This is a test method>>");
	}
}

class ClassGenerics{
	//�Ϲ� ���׸��� �޼���
	public static <T> T ClassGenTest1(T t) {	//�޼��� ����� Ȥ�� class ����ο� <T>�� �� �ٿ��� �Ѵ�. �Ⱥ��̸� ������
		return (T)t;
	}

	//java.lang.Class�� �̿��� ���׸��� �޼���
	public static testClass ClassGenTest2(Class<? extends testClass> t) throws InstantiationException, IllegalAccessException {	//�Ķ���Ϳ� �� �� �ִ� Ÿ���� testClassȤ�� testClass�� ����� class�θ� ���ѵ�
		System.out.println("t.getClass() : "+t.getClass());
		System.out.println("t.newInstance().getClass() : " + t.newInstance().getClass());
		System.out.println("testClass.class : " + testClass.class);
		
		if(t.newInstance().getClass().equals(testClass.class))
			System.out.println("'t.newInstance().getClass()' is the same type as 'testClass.class'");
		
		/*
		�Ķ���� t�� newInstance()�޼���� ��ü�� ������ �޸𸮿� �ε����� ������ java�� �Ķ���� t�� �׳� java.lang.Class Ÿ������ �����Ѵ�.
		���� �Ķ���� t�� newInstance()�޼��带 �Ἥ testClass Ÿ���� ��ü�� ������ testClass Ÿ������ ������ �� �ִ�.
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
	���׸��� ���ϵ� ī��
		<? extends T> : ���ϵ�ī�� ���� - T�� �� �ڼո� ����
		<? super T> : ���ϵ�ī�� ���� - T�� �� ���� ����
		<?> : ���� ����. ��� Ÿ���� ���� - <? extends Object>�� ����
	-------------------------------------------------------
	java.lang.Class
		- ���� �����ϰ� �ִ� ��� ��ü�� �����ϴ� Ŭ����
		- ���� ���׸��� ���ϵ� ī��� �Բ� ���Ǿ�, ���׸��� �޼����� �Ķ���� Ÿ���� Ư�� class�� �ڼ�/�������� �����Ҷ� ���Ǵ� ����
	-------------------------------------------------------
	Reference
	http://stackoverflow.com/questions/8422842/in-java-syntax-class-extends-something
	http://stackoverflow.com/questions/2350558/whats-the-meaning-of-the-question-mark-inside-the-angle-brackets-extends-ja
	https://www.tutorialspoint.com/java/lang/java_lang_class.htm
	�ڹ��� ���� 678p
*/
