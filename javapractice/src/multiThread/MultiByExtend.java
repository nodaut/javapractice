package multiThread;

class count extends Thread{
	count(){
		super("jhlee thread test using extends Thread");	//실행될 쓰레드의 이름을 지정
		System.out.println(this);
		System.out.println("Thread start");
		this.start();
	}
	public void run(){
		for(int i=0; i<10; i++){
			System.out.println("i is "+i);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println(e);
			}
		}
		System.out.println("Thread ended");
	}
}

public class MultiByExtend extends Thread{
	
	public static void main(String[] args) {
		Thread cnt = new count();		
		while(cnt.isAlive()){
			System.out.println("Test thread is running..");
			try{
				Thread.sleep(1500);
			}catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}

}


/*
 * super : 부모 class 생성자를 호출
 * this : 현재의 객체에 대한 참조자 (a reference to the current object) 
 * 
 * Reference
 * http://beginnersbook.com/2013/03/multithreading-in-java/
 * http://stackoverflow.com/questions/3767365/super-in-java
 */
