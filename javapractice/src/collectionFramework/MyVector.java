package collectionFramework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//커스텀 vector
public class MyVector implements List{
	private Object[] data = null;	//객체를 담기위한 객체 배열
	private int capacity=0;	//벡터의 총가용 용량
	private int size=0;	//현재 Object array에 들어 있는 데이터의 수

	public MyVector(int capacity){	//생성자의 인자로 capacity를 받고 이 크기만큼 Object 배열을 생성함
		System.out.println("MyVector constructor");
		if(capacity<0)
			throw new IllegalArgumentException("Not valid capacity value : "+capacity);
		this.capacity=capacity;
		data = new Object[capacity];
	}
	
	public MyVector(){
		this.capacity=10;	//capacity를 지정하지 않으면 디폴트로 capacity에 10을 할당함
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
		System.arraycopy(data, 0, tmp, 0, size);	//임시 Object tmp 배열을 만들고 여기에 복사한 후 
		data=tmp;	//복사가 끝나면 data에 tmp를 덮어 씌운다
		this.capacity=capacity;
	}
	
	public void trimToSize(){
		setCapacity(size);	//현재 Object array의 사이즈와 동일하게 capacity를 정한다.
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
		ensureCapacity(size+1);	//item을 넣기전에 Object배열의 사이즈를 확인한다.
		data[size++]=item;	//size++는 후위연산자이므로 item은 data[size]에 일단 대입이 되고, 그 후에 size가 1증가한다.
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
			System.arraycopy(data, index+1,data, index, size-index-1);	//만약 삭제하고자 하는 요소의 index가 맨마지막 요소가 아니면 삭제할 요소의 index+1 부터의 데이터를 index 부터의 데이터에 덮어 쓴다
		}
		data[size-1]=null;	//맨마지막 데이터는 null로 만든다
		size--;	//item하나가 삭제되었으므로 size는 1을 감소시킴
		System.out.println("remove idx end\n--------------");
		return removedItem;
	}

	@Override
	public boolean remove(Object item) {
		for(int i=0; i<size ; i++){
			if(data[i].equals(item)){	//Object배열을 순회하다가 삭제할 item을 발견하면 해당 index에 대해 remove를 함
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