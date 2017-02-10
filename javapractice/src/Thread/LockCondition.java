package Thread;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class LockCondition {

	public static void main(String[] args) {
		Table2 table = new Table2();
		new Thread(new Cook2(table)).start();
		new Thread(new Customer2(table, "burger")).start();
		new Thread(new Customer2(table, "donut")).start();

	}
}

class Customer2 implements Runnable{
	private Table2 table;
	private String food;
	Customer2(Table2 table, String food){
		this.table=table;
		this.food=food;
	}
	public void run(){
		while(true){
			try{Thread.sleep(10);}catch(InterruptedException e){}
			String name = Thread.currentThread().getName();
			try {
				if(eatFood()) System.out.println(name + " ate a "+food);
				else System.out.println(name + " fail to eat");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	boolean eatFood() throws InterruptedException{return table.remove(food);}
}

class Cook2 implements Runnable{
	private Table2 table;
	
	Cook2(Table2 table){
		this.table=table;
	}
	
	public void run(){
		while(true){
			try{Thread.sleep(10);}catch(InterruptedException e){}
			int idx = (int)(Math.random()*table.dishNum());
			table.add(table.dishNames[idx]);
			
		}
	}
}

class Table2{
	String[] dishNames = {"donut","donut","burger"};
	final int MAX_FOOD=6;	//테이블에 놓을 수 있는 음식의 최대 개수
	private ReentrantLock Lock = new ReentrantLock();
	private Condition forCook = Lock.newCondition();
	private Condition forCust = Lock.newCondition();
	
	private ArrayList<String> dishes = new ArrayList<>();
	
	public void add(String dish){
		Lock.lock();	//Lock을 건다
		//테이블에 음식이 가득찼으면, 기다린다
		try{
			while(dishes.size()>=MAX_FOOD){
				System.out.println(Thread.currentThread().getName()+" is waiting..");
				try{
					forCook.await();	//현재 쓰레드는 forCook 컨디션으로 바인딩되어 Thread pool에서 대기 -> 좀 기다렸다가 이따 요리하자..
					Thread.sleep(2000);
				}catch(InterruptedException e){}
			}
			dishes.add(dish);
			forCust.signalAll();	//Thread pool에서 대기하고 있는 forCust 컨디션으로 바인딩된 쓰레드들만 선별적으로 깨움-> 요리다 됬으니까 먹어~
			System.out.println("Dishes : "+dishes.toString());
		}finally{
			Lock.unlock();	//맨 마지막에는 무조건 Lock을 풀어야 하므로 unlock은 finally 구문에 넣어준다.
		}
	}
	
	public boolean remove(String dishName){
		Lock.lock();	//Lock 걸기
		try{
			while(dishes.size()==0){
				System.out.println(Thread.currentThread().getName()+" is waiting..");
				try{
					forCust.await();	//현재 쓰레드는 forCust 컨디션으로 바인딩되어 Thread pool에서 대기 -> 요리없네 좀 기다리자..
					Thread.sleep(2000);
				}catch(InterruptedException e){}
			}
			//지정된 요리와 일치하는 요리를 테이블에서 제거한다
			for(int i=0; i<dishes.size();i++){
				if(dishName.equals(dishes.get(i))){
					dishes.remove(i);
					forCook.signalAll();	//Thread pool에서 대기하고 있는 forCook 컨디션으로 바인딩된 쓰레드들만 선별적으로 깨움 -> 나 다먹었어~요래해~
					return true;
				}
			}
		}finally{
			Lock.unlock();	//Lock 풀기
		}
		return false;
	}

	public int dishNum(){
		return dishNames.length;
	}
}
