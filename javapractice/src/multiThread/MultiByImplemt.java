package multiThread;

class count2 implements Runnable{
	Thread cntThread;
	count2(){
		cntThread = new Thread(this, "jhlee thread test using implements Runnable");
		System.out.println(this.cntThread);
		cntThread.start();
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

public class MultiByImplemt {

	public static void main(String[] args) {
		
		count2 cnt = new count2();
		//Thread cnt = new count2();	count2는 Runnable을 구현한 것이므로 Thread 타입으로 생성 불가능
		while(cnt.cntThread.isAlive()){
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
 * Thread(Runnable target, String name) : name이라는 이름과, target이라는 Runnable객체로 쓰레드를 만든다.
 * Reference
 * https://www.tutorialspoint.com/java/lang/java_lang_thread.htm
 */