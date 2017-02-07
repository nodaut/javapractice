package futurePattern;

interface Data{public abstract String getContent();}

class RealData implements Data{
	private final String content;
	public RealData(final int count, final char c){
		System.out.println("Making RealData("+count+","+c+") start");
		char[] buffer = new char[count];
		for(int i=0; i<count; i++){
			buffer[i]=c;
			try{Thread.sleep(1000);}
			catch(Exception e){System.out.println(e);}
		}
		this.content=new String(buffer);
		System.out.println("Making RealData("+count+","+c+") end");
	}
	@Override
	public String getContent(){
		return this.content;
	}
}

class FutureData implements Data{
	private RealData realData=null;
	private boolean Ready = false;
	
	public synchronized void setRealData(final RealData realData){
		if(Ready) return;
		this.realData=realData;
		this.Ready=true;
		notifyAll();	//[point 3]setRealData�� �Ϸ�Ǹ� �� �����带 ��ٸ��� �ִ�(wait �ϰ� �ִ�) �͵��� �����.
	}
	
	public synchronized String getContent(){
		while(!this.Ready){
			try{wait();}	//[point 2]�� �޼��带 ���� ��ü�� �����带 ��ٸ���.
			catch(Exception e){System.out.println(e);}
		}
		return this.realData.getContent();
	}
}

class Host{
	public Data request(final int count, final char c){
		System.out.println("request("+count+","+c+") begin");
		final FutureData future = new FutureData();
		new Thread(){	//Thread�� �߻���Ű�� �κ�
			public void run(){	//�̷��� ��ü�� �����ϸ鼭 �޼��带 ������ �� �ִ�. ��������� Runnable������ ���ϴ� �ڵ尡 ����������.
				final RealData realData = new RealData(count,c);
				future.setRealData(realData); //[point 1]realData�� �غ�� �Ŀ��� setRealData�� ����� �� �ִ�. realData �����ڿ� 1�� ���� �κ��� �����Ƿ� setRealData�� 1�ʵڿ� ����ȴ�
				
			}
		}.start();
		System.out.println("request("+count+","+c+") end");
		return future;
	}
}



public class FuturePattern {

	public static void main(String[] args) {
		//new lazyObjCaller();
		final Host host =new Host();
		final Data data1 = host.request(10, 'A');
		final Data data2 = host.request(20, 'B');
		final Data data3 = host.request(30, 'C');
		
		
		try{
			System.out.println("main another job start");
			Thread.sleep(100);
		}catch(Exception e){;}
		System.out.println("main another job end");
		
		System.out.println("data1 = "+data1.getContent());
		System.out.println("data2 = "+data2.getContent());
		System.out.println("data3 = "+data3.getContent());
	}
}

/*
future pattern

1. ��Ŀ����
	A�� B���� Ư�� �����͸� ��û�Ѵ�. �̴� ������� ����ȴ�. A�� ������ ��û�� ��ٸ��� ���� �ٸ� �۾��� �Ѵ�.
		B���ο��� ��û ������ ���� �޼���setdata�� ����ȴ�. �ٵ� �̰� �� �����ɸ���.
		B������ �����͸� �����ϴ� �޼��� getdata�� setdata�� ���������� ��ٸ���.(wait) 
		B������ ���� �޼���setdata�� ������ ����� �ִ� getdata�� ����� (notify) 
	A�� B���� ��û�� �����͸� �޴´�. 


2. ��������Ʈ
	(1) � �ڵ忡�� Ư�� ��ü�� ������ �̿��Ѵٸ� ��ü ���� ������ �ڵ�� ��ü�� �����Ǿ�߸� ����� �� �ִ�
		ex)
			class lazyObj{
				lazyObj(){
					try{
						System.out.println("In the lazyObj constructor");
						Thread.sleep(2000);
					}catch(Exception e){;}
				}
			}
			
			class lazyObjCaller{
				lazyObjCaller(){
					new lazyObj();	
					System.out.println("In the lazyObjCaller constructor");	//�� �޽����� ��µǷ��� lazyObj ��ü�� ������ �� ���� ��ٷ��� �Ѵ�
				}
			}
	
	(2) wait()�� �� �޼��尡 ȣ��Ǳ� ���� ����� �����带 ��ٸ���.
	(3) notifyAll()�� �� �޼��尡 ȣ��Ǳ� ���� ����� �����带 ��ٸ��� �͵��� �����
--------------------------------------------------------------------------------------------------------------------------------------------
Reference
	http://gomp.tistory.com/401
*/