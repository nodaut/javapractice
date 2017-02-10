package Thread;

public class Synchronization{
	
	public static void main(String[] args) {
	Runnable r = new myrun();
	Thread tr1 = new Thread(r,"First thread");
	Thread tr2 = new Thread(r,"Second thread");
	tr1.start();
	tr2.start();
	}
}

class account {
	private int balance = 1000;
	public account(){
		System.out.println("created account instance");
	}
	
	public int balance(){
		return this.balance;
	}

/*	public void withDraw(int money){
		if(balance()>=money){
			try{Thread.sleep(10);}
			catch(InterruptedException e){System.out.println(e);}
			this.balance-=money;
		}
	}*/
	public synchronized void withDraw(int money){	//synchronized를 걸면 이 method는 하나의 thread만 실행 가능함
		if(balance()>=money){	//잔고에 인출하려는 돈이상이 있다면 돈을 빼라.
			try{Thread.sleep(10);}
			catch(InterruptedException e){System.out.println(e);}
			this.balance-=money;
		}
	}
}

class myrun implements Runnable{
	account ac = new account();
	public void run(){
		while (ac.balance()>0){	
			int money = (int)(Math.random()*3+1)*100;	//잔고에 돈이 없을때까지 한번에 100 or 200 or 300원을 빼간다.. 
			ac.withDraw(money);
			System.out.println("money : "+money);
			System.out.println("balance : "+ac.balance());
		}
		System.out.println("test ended");
	}
}


/* 
 * Reference
 * java의 정석 767~770
*/