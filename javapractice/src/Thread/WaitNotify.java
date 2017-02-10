package Thread;

import java.util.ArrayList;

public class WaitNotify {

	public static void main(String[] args) {
		Table table = new Table();
		new Thread(new Cook(table)).start();
		new Thread(new Customer(table, "burger")).start();
		new Thread(new Customer(table, "donut")).start();

	}
}

class Customer implements Runnable{
	private Table table;
	private String food;
	Customer(Table table, String food){
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

class Cook implements Runnable{
	private Table table;
	
	Cook(Table table){
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

class Table{
	String[] dishNames = {"donut","donut","burger"};
	final int MAX_FOOD=6;	//테이블에 놓을 수 있는 음식의 최대 개수
	
	private ArrayList<String> dishes = new ArrayList<>();
	
	public synchronized void add(String dish){
		//테이블에 음식이 가득찼으면, 기다린다.
		while(dishes.size()>=MAX_FOOD){
			System.out.println(Thread.currentThread().getName()+" is waiting..");
			try{
				wait();
				Thread.sleep(2000);
			}catch(InterruptedException e){}

		}
		dishes.add(dish);
		System.out.println("Dishes : "+dishes.toString());
		notifyAll();
		/*
		현재 Thread pool에서 대기중인 모든 쓰레드에게 notify신호를 보낸다. 즉, notify 신호를 받는것은 Customer 쓰레드일 수도 Cook 쓰레드 일수도 있다.
		신호는 모든 쓰레드에게 가긴하지만  Thread pool에서 실제로 탈출할 수 있는 쓰레드는 단 1개 뿐이다
		*/
	}
	
	public synchronized boolean remove(String dishName){
		while(dishes.size()==0){
			System.out.println(Thread.currentThread().getName()+" is waiting..");
			try{
				wait();	
				/*
				remove 메서드를 사용하는 쓰레드에게 현재 쥐고 있는 자원(lock)을 반환하고  Thread pool에서 기다리게 한다. 
				이 쓰레드는 다른 쓰레드로부터 notify()신호를 받아야 Thread pool에서 나올 수 있다.
				*/
				Thread.sleep(2000);
			}catch(InterruptedException e){}
			
		}
		//지정된 요리와 일치하는 요리를 테이블에서 제거한다
		for(int i=0; i<dishes.size();i++){
			if(dishName.equals(dishes.get(i))){
				dishes.remove(i);
				notifyAll();	
				/*
				현재 Thread pool에서 대기중인 모든 쓰레드에게 notify신호를 보낸다. 즉, notify 신호를 받는것은 Customer 쓰레드일 수도 Cook 쓰레드 일수도 있다.
				신호는 모든 쓰레드에게 가긴하지만  Thread pool에서 실제로 탈출할 수 있는 쓰레드는 단 1개 뿐이다
				*/
				return true;
			}
		}
		return false;
	}

	public int dishNum(){
		return dishNames.length;
	}
}
