package collectionFramework;

import java.util.*;

//커스텀 stack
public class MyStack extends Vector{

	/*
		--------------------------------------------------------
		stack은 vector배열의 맨마지막 index를 header로 가리키고 여기에 데이터가 들어가고 빠져나간다
		stack은 맨마지막 요소을 1번으로 하여 순서를 매김. 배열의 index순서와 반대임
		ex) [1, 2, 3, 4, 5] <-head
		--------------------------------------------------------
	*/
	public Object pop(){
		Object item = peek();
		removeElementAt(size()-1);
		return item;
	}
	
	public void push(Object item){
		add(item);
	}


	public Object peek(){
		if(size()==0)
			throw new EmptyStackException();
		return elementAt(size()-1);
	}
	
	public boolean empty(){
		return size()==0;
	}
	
	//search메서드는 해당 요소의 stack 번호를 리턴
	public int search(Object item){
		int idx=lastIndexOf(item);	//item에 대한 맨마지막 index를 리턴 
		if(idx>-1)
			return size()-idx;	//stack은 맨마지막 요소을 1번으로 하여 순서를 매김
		else
			throw new NoSuchElementException();
	}
	
	public static void main(String[] args) {
		MyStack ms = new MyStack();
		ms.add(1);
		ms.add(2);
		ms.add(3);
		ms.add(3);
		ms.add(3);
		ms.add(4);
		
		System.out.println(ms);
		System.out.println("peek() : "+ms.peek());
		System.out.println("pop() : " + ms.pop());
		System.out.println(ms);
		System.out.println("empty() : " +ms.empty());
		System.out.println("search(3) : " +ms.search(3));
	}

}
