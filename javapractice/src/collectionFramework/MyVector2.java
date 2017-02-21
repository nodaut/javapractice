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
		cursor=0;	//cursor와 lastRet를 초기화함
		lastRet=-1;
		return this;
	}
	
	public boolean hasNext(){
		return cursor!=size();
	}
	
	public Object next(){
		Object next = get(cursor);	//get(index)는 MyVector에서 정의한 method임
		lastRet = cursor++;
		return next;
	}
	
	public void remove(){
		if(lastRet==-1)
			throw new IllegalStateException();
			//만약 삭제할 것이 없다면 IllegalStateException을 발생시킴. IllegalStateException은 runtime exception이므로 따로 정의(추가)해줄 필요없음
		remove(lastRet);	//remove(index)는 MyVector에서 정의한 method임
		cursor--;	//삭제 후 cursor를 감소시킴
		lastRet=-1;	//삭제 후 lastRet값을 초기화해야 함
	}
	
	public String toString(){	//System.out.println(MyVector2)를 하면 암묵적으로 실행되는 method 
		String tmp = "";
		Iterator it = iterator();
		
		for(int i=0; it.hasNext(); i++){
			if(i!=0) tmp+=", ";
			tmp+=it.next();
		}
		cursor=0;	//이렇게 cursor를 초기화 하지 않으면 toString이 실행된 후 cursor가 맨 끝에 가게 되므로 그 후에 next()나 remove()메서드를 수행할 수 없음
		return "[" + tmp + "]";
	}
		
	public static void main(String[] args) {
		MyVector2 v = new MyVector2();

		v.add(new Integer(1)); 
		v.add(new Integer(2)); 
		v.add(new Integer(3)); 
		
		System.out.println("Before remove : "+v);	//그냥 System.out.println(v)를 하면 v.toString이 암묵적으로 실행됨
		System.out.println("cursor : "+v.cursor);

		//3번 next()한 후 remove()
		v.next();
		v.next();
		v.next();
		v.remove();
		
		System.out.println("After remove : "+v);

	}

}
