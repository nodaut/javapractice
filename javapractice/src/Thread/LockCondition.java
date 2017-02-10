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
	final int MAX_FOOD=6;	//���̺� ���� �� �ִ� ������ �ִ� ����
	private ReentrantLock Lock = new ReentrantLock();
	private Condition forCook = Lock.newCondition();
	private Condition forCust = Lock.newCondition();
	
	private ArrayList<String> dishes = new ArrayList<>();
	
	public void add(String dish){
		Lock.lock();	//Lock�� �Ǵ�
		//���̺� ������ ����á����, ��ٸ���
		try{
			while(dishes.size()>=MAX_FOOD){
				System.out.println(Thread.currentThread().getName()+" is waiting..");
				try{
					forCook.await();	//���� ������� forCook ��������� ���ε��Ǿ� Thread pool���� ��� -> �� ��ٷȴٰ� �̵� �丮����..
					Thread.sleep(2000);
				}catch(InterruptedException e){}
			}
			dishes.add(dish);
			forCust.signalAll();	//Thread pool���� ����ϰ� �ִ� forCust ��������� ���ε��� ������鸸 ���������� ����-> �丮�� �����ϱ� �Ծ�~
			System.out.println("Dishes : "+dishes.toString());
		}finally{
			Lock.unlock();	//�� ���������� ������ Lock�� Ǯ��� �ϹǷ� unlock�� finally ������ �־��ش�.
		}
	}
	
	public boolean remove(String dishName){
		Lock.lock();	//Lock �ɱ�
		try{
			while(dishes.size()==0){
				System.out.println(Thread.currentThread().getName()+" is waiting..");
				try{
					forCust.await();	//���� ������� forCust ��������� ���ε��Ǿ� Thread pool���� ��� -> �丮���� �� ��ٸ���..
					Thread.sleep(2000);
				}catch(InterruptedException e){}
			}
			//������ �丮�� ��ġ�ϴ� �丮�� ���̺��� �����Ѵ�
			for(int i=0; i<dishes.size();i++){
				if(dishName.equals(dishes.get(i))){
					dishes.remove(i);
					forCook.signalAll();	//Thread pool���� ����ϰ� �ִ� forCook ��������� ���ε��� ������鸸 ���������� ���� -> �� �ٸԾ���~�䷡��~
					return true;
				}
			}
		}finally{
			Lock.unlock();	//Lock Ǯ��
		}
		return false;
	}

	public int dishNum(){
		return dishNames.length;
	}
}
