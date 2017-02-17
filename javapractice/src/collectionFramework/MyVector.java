package collectionFramework;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyVector implements List{
	Object[] data = null;	//객체를 담기위한 객체 배열
	int capacity=0;	//벡터의 총가용 용량
	int size=0;	//현재 Object array에 들어 있는 데이터의 수

	public MyVector(int capacity){	//생성자의 인자로 capacity를 받고 이 크기만큼 Object 배열을 생성함
		if(capacity<0)
			throw new IllegalArgumentException("Not valid capacity value : "+capacity);
		this.capacity=capacity;
		data = new Object[capacity];
	}
	
	public MyVector(){
		this.capacity=10;	//capacity를 지정하지 않으면 디폴트로 capacity에 10을 할당함
		//data = new Object[capacity];
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
		Object removedItem = null;
		
		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Index Out Of Bounds Exception!");
		
		removedItem = data[index];
		if(index!=size-1)
			System.arraycopy(data, index+1,data, index, size-index-1);	//만약 삭제하고자 하는 요소의 index가 맨마지막 요소가 아니면 삭제할 요소의 index+1 부터의 데이터를 index 부터의 데이터에 덮어 쓴다
		
		data[size-1]=null;	//맨마지막 데이터는 null로 만든다
		size--;	//item하나가 삭제되었으므로 size는 1을 감소시킴
		
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
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}


/*
Reference
	(arrayCopy)
	http://journals.ecs.soton.ac.uk/java/tutorial/java/system/misc.html


*/