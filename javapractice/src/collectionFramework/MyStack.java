package collectionFramework;

import java.util.*;

//Ŀ���� stack
public class MyStack extends Vector{

	/*
		--------------------------------------------------------
		stack�� vector�迭�� �Ǹ����� index�� header�� ����Ű�� ���⿡ �����Ͱ� ���� ����������
		stack�� �Ǹ����� ����� 1������ �Ͽ� ������ �ű�. �迭�� index������ �ݴ���
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
	
	//search�޼���� �ش� ����� stack ��ȣ�� ����
	public int search(Object item){
		int idx=lastIndexOf(item);	//item�� ���� �Ǹ����� index�� ���� 
		if(idx>-1)
			return size()-idx;	//stack�� �Ǹ����� ����� 1������ �Ͽ� ������ �ű�
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
