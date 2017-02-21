package collectionFramework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//Ŀ���� vector
public class MyVector implements List{
	private Object[] data = null;	//��ü�� ������� ��ü �迭
	private int capacity=0;	//������ �Ѱ��� �뷮
	private int size=0;	//���� Object array�� ��� �ִ� �������� ��

	public MyVector(int capacity){	//�������� ���ڷ� capacity�� �ް� �� ũ�⸸ŭ Object �迭�� ������
		System.out.println("MyVector constructor");
		if(capacity<0)
			throw new IllegalArgumentException("Not valid capacity value : "+capacity);
		this.capacity=capacity;
		data = new Object[capacity];
	}
	
	public MyVector(){
		this.capacity=10;	//capacity�� �������� ������ ����Ʈ�� capacity�� 10�� �Ҵ���
		data = new Object[capacity];
	}
	
	private void ensureCapacity(int minCapaciy){
		if(minCapaciy>data.length)
			setCapacity(minCapaciy);
	}
	
	public void setCapacity(int capacity){
		if(this.capacity==capacity)
			return;
		Object[] tmp = new Object [capacity];	
		System.arraycopy(data, 0, tmp, 0, size);	//�ӽ� Object tmp �迭�� ����� ���⿡ ������ �� 
		data=tmp;	//���簡 ������ data�� tmp�� ���� �����
		this.capacity=capacity;
	}
	
	public void trimToSize(){
		setCapacity(size);	//���� Object array�� ������� �����ϰ� capacity�� ���Ѵ�.
	}

	public int capacity(){
		return capacity;
	}
	
	//for print result
	static void print(MyVector list){
		System.out.print("list : ");
		for(int i=0; i<list.size;i++){
			System.out.print(list.get(i));
			System.out.print(" ");
		}
		System.out.print("\n");
	}
	
	//----------------Overriding methods----------------
	@Override
	public boolean add(Object item) {
		ensureCapacity(size+1);	//item�� �ֱ����� Object�迭�� ����� Ȯ���Ѵ�.
		data[size++]=item;	//size++�� �����������̹Ƿ� item�� data[size]�� �ϴ� ������ �ǰ�, �� �Ŀ� size�� 1�����Ѵ�.
		return true;
	}
	
	@Override
	public Object get(int index) {
		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Index Out Of Bounds Exception!");
		return data[index];
	}

	@Override
	public Object remove(int index) {
		System.out.println("--------------\nremove idx : "+index);

		Object removedItem = null;
		
		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Index Out Of Bounds Exception!");
		
		removedItem = data[index];
		if(index!=size-1){
			System.out.println("arraycopy...");
			System.arraycopy(data, index+1,data, index, size-index-1);	//���� �����ϰ��� �ϴ� ����� index�� �Ǹ����� ��Ұ� �ƴϸ� ������ ����� index+1 ������ �����͸� index ������ �����Ϳ� ���� ����
		}
		data[size-1]=null;	//�Ǹ����� �����ʹ� null�� �����
		size--;	//item�ϳ��� �����Ǿ����Ƿ� size�� 1�� ���ҽ�Ŵ
		System.out.println("remove idx end\n--------------");
		return removedItem;
	}

	@Override
	public boolean remove(Object item) {
		for(int i=0; i<size ; i++){
			if(data[i].equals(item)){	//Object�迭�� ��ȸ�ϴٰ� ������ item�� �߰��ϸ� �ش� index�� ���� remove�� ��
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void clear(){
		for(int i=0; i<size; i++)
			data[i]=null;
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		System.arraycopy(data, 0, result, 0, size);
		return result;
	}
	
	@Override
	public boolean isEmpty() {return size==0;}
	
	@Override
	public int size() {return size;}
	
	//----------------Default methods which wasn't implemented yet----------------

	@Override
	public void add(int index, Object element) {}
	@Override
	public boolean addAll(Collection c) {return false;}
	@Override
	public boolean addAll(int index, Collection c) {return false;}
	@Override
	public boolean contains(Object o) {return false;}
	@Override
	public boolean containsAll(Collection c) {return false;}
	@Override
	public int indexOf(Object o) {return 0;}
	@Override
	public Iterator iterator() {return null;}
	@Override
	public int lastIndexOf(Object o) {return 0;}
	@Override
	public ListIterator listIterator() {return null;}
	@Override
	public ListIterator listIterator(int index) {return null;}
	@Override
	public boolean removeAll(Collection c) {return false;}
	@Override
	public boolean retainAll(Collection c) {return false;}
	@Override
	public Object set(int index, Object element) {return null;}
	@Override
	public List subList(int fromIndex, int toIndex) {return null;}
	@Override
	public Object[] toArray(Object[] a) {return null;}

	
	public static void main(String[] args) {
		MyVector list1 = new MyVector();
		System.out.println("capacity : "+list1.capacity());
		System.out.println("size : "+list1.size());
		
		list1.add(new Integer(5));
		list1.add(new Integer(4));
		list1.add(new Integer(2));
		list1.add(new Integer(0));
		list1.add(new Integer(12));
		list1.add(new Integer(22));
		print(list1);
		
		list1.remove(0);
		print(list1);
		
		list1.remove(3);
		print(list1);

		list1.add(new Integer(100));
		print(list1);
		
		System.out.println("capacity : "+list1.capacity());
		System.out.println("size : "+list1.size());
		list1.trimToSize();
		System.out.println("capacity : "+list1.capacity());
	}
}