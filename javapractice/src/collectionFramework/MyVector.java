package collectionFramework;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyVector implements List{
	Object[] data = null;	//��ü�� ������� ��ü �迭
	int capacity=0;	//������ �Ѱ��� �뷮
	int size=0;	//���� Object array�� ��� �ִ� �������� ��

	public MyVector(int capacity){	//�������� ���ڷ� capacity�� �ް� �� ũ�⸸ŭ Object �迭�� ������
		if(capacity<0)
			throw new IllegalArgumentException("Not valid capacity value : "+capacity);
		this.capacity=capacity;
		data = new Object[capacity];
	}
	
	public MyVector(){
		this.capacity=10;	//capacity�� �������� ������ ����Ʈ�� capacity�� 10�� �Ҵ���
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
		System.arraycopy(data, 0, tmp, 0, size);	//�ӽ� Object tmp �迭�� ����� ���⿡ ������ �� 
		data=tmp;	//���簡 ������ data�� tmp�� ���� �����
		this.capacity=capacity;
	}
	
	public void trimToSize(){
		setCapacity(size);	//���� Object array�� ������� �����ϰ� capacity�� ���Ѵ�.
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
		Object removedItem = null;
		
		if(index<0 || index>=size)
			throw new IndexOutOfBoundsException("Index Out Of Bounds Exception!");
		
		removedItem = data[index];
		if(index!=size-1)
			System.arraycopy(data, index+1,data, index, size-index-1);	//���� �����ϰ��� �ϴ� ����� index�� �Ǹ����� ��Ұ� �ƴϸ� ������ ����� index+1 ������ �����͸� index ������ �����Ϳ� ���� ����
		
		data[size-1]=null;	//�Ǹ����� �����ʹ� null�� �����
		size--;	//item�ϳ��� �����Ǿ����Ƿ� size�� 1�� ���ҽ�Ŵ
		
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