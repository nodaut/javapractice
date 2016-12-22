package javapractice;
class SingleTone {
	private static SingleTone one;
	private int var1 =10;
	
	public SingleTone(int var){
		System.out.println("Creating a normal class object!");		
	}
	private SingleTone(){
		System.out.println("Creating a singletone class object!");
	}
	public static SingleTone getInstance(){
		if(one==null){
			one = new SingleTone();
		}
		return one;
	}
}

public class SingleToneTest {
	public static void main(String args[]){
		SingleTone one1 = SingleTone.getInstance();
		SingleTone one2 = SingleTone.getInstance();	//싱글톤 생성자는 두번 호출되지 않음.
		System.out.println(one1==one2);
		
		//new SingleTone(); private 생성자는 다른 class에서 접근할 수 없음
		new SingleTone(1);
		new SingleTone(1);
	}
}