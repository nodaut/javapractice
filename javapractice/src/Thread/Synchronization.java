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
	public synchronized void withDraw(int money){	//synchronized�� �ɸ� �� method�� �ϳ��� thread�� ���� ������
		if(balance()>=money){	//�ܰ� �����Ϸ��� ���̻��� �ִٸ� ���� ����.
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
			int money = (int)(Math.random()*3+1)*100;	//�ܰ� ���� ���������� �ѹ��� 100 or 200 or 300���� ������.. 
			ac.withDraw(money);
			System.out.println("money : "+money);
			System.out.println("balance : "+ac.balance());
		}
		System.out.println("test ended");
	}
}


/* 
 * Reference
 * java�� ���� 767~770
*/