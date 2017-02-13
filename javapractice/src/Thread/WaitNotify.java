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
	final int MAX_FOOD=6;	//���̺� ���� �� �ִ� ������ �ִ� ����
	
	private ArrayList<String> dishes = new ArrayList<>();
	
	public synchronized void add(String dish){
		//���̺� ������ ����á����, ��ٸ���.
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
		���� Thread pool���� ������� ��� �����忡�� notify��ȣ�� ������. ��, notify ��ȣ�� �޴°��� Customer �������� ���� Cook ������ �ϼ��� �ִ�.
		��ȣ�� ��� �����忡�� ����������  Thread pool���� ������ Ż���� �� �ִ� ������� �� 1�� ���̴�
		*/
	}
	
	public synchronized boolean remove(String dishName){
		while(dishes.size()==0){
			System.out.println(Thread.currentThread().getName()+" is waiting..");
			try{
				wait();	
				/*
				remove �޼��带 ����ϴ� �����忡�� ���� ��� �ִ� �ڿ�(lock)�� ��ȯ�ϰ�  Thread pool���� ��ٸ��� �Ѵ�. 
				�� ������� �ٸ� ������κ��� notify()��ȣ�� �޾ƾ� Thread pool���� ���� �� �ִ�.
				*/
				Thread.sleep(2000);
			}catch(InterruptedException e){}
			
		}
		//������ �丮�� ��ġ�ϴ� �丮�� ���̺��� �����Ѵ�
		for(int i=0; i<dishes.size();i++){
			if(dishName.equals(dishes.get(i))){
				dishes.remove(i);
				notifyAll();	
				/*
				���� Thread pool���� ������� ��� �����忡�� notify��ȣ�� ������. ��, notify ��ȣ�� �޴°��� Customer �������� ���� Cook ������ �ϼ��� �ִ�.
				��ȣ�� ��� �����忡�� ����������  Thread pool���� ������ Ż���� �� �ִ� ������� �� 1�� ���̴�
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
