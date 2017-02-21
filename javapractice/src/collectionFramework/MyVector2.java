package collectionFramework;

import java.util.*;

public class MyVector2 extends MyVector implements Iterator<Object>{
	int cursor = 0;
	int lastRet = -1;
	
	public MyVector2(int capacity){
		super(capacity);
	}
	
	public MyVector2(){
		this(10);
	}
	
	public Iterator iterator(){
		cursor=0;	//cursor�� lastRet�� �ʱ�ȭ��
		lastRet=-1;
		return this;
	}
	
	public boolean hasNext(){
		return cursor!=size();
	}
	
	public Object next(){
		Object next = get(cursor);	//get(index)�� MyVector���� ������ method��
		lastRet = cursor++;
		return next;
	}
	
	public void remove(){
		if(lastRet==-1)
			throw new IllegalStateException();
			//���� ������ ���� ���ٸ� IllegalStateException�� �߻���Ŵ. IllegalStateException�� runtime exception�̹Ƿ� ���� ����(�߰�)���� �ʿ����
		remove(lastRet);	//remove(index)�� MyVector���� ������ method��
		cursor--;	//���� �� cursor�� ���ҽ�Ŵ
		lastRet=-1;	//���� �� lastRet���� �ʱ�ȭ�ؾ� ��
	}
	
	public String toString(){	//System.out.println(MyVector2)�� �ϸ� �Ϲ������� ����Ǵ� method 
		String tmp = "";
		Iterator it = iterator();
		
		for(int i=0; it.hasNext(); i++){
			if(i!=0) tmp+=", ";
			tmp+=it.next();
		}
		cursor=0;	//�̷��� cursor�� �ʱ�ȭ ���� ������ toString�� ����� �� cursor�� �� ���� ���� �ǹǷ� �� �Ŀ� next()�� remove()�޼��带 ������ �� ����
		return "[" + tmp + "]";
	}
		
	public static void main(String[] args) {
		MyVector2 v = new MyVector2();

		v.add(new Integer(1)); 
		v.add(new Integer(2)); 
		v.add(new Integer(3)); 
		
		System.out.println("Before remove : "+v);	//�׳� System.out.println(v)�� �ϸ� v.toString�� �Ϲ������� �����
		System.out.println("cursor : "+v.cursor);

		//3�� next()�� �� remove()
		v.next();
		v.next();
		v.next();
		v.remove();
		
		System.out.println("After remove : "+v);

	}

}
