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
		notifyAll();	//[point 3]setRealData가 완료되면 이 쓰레드를 기다리고 있는(wait 하고 있는) 것들을 깨운다.
	}
	
	public synchronized String getContent(){
		while(!this.Ready){
			try{wait();}	//[point 2]이 메서드를 가진 객체의 쓰레드를 기다린다.
			catch(Exception e){System.out.println(e);}
		}
		return this.realData.getContent();
	}
}

class Host{
	public Data request(final int count, final char c){
		System.out.println("request("+count+","+c+") begin");
		final FutureData future = new FutureData();
		new Thread(){	//Thread를 발생시키는 부분
			public void run(){	//이렇게 객체를 생성하면서 메서드를 정의할 수 있다. 명시적으로 Runnable구현을 안하니 코드가 간결해진다.
				final RealData realData = new RealData(count,c);
				future.setRealData(realData); //[point 1]realData가 준비된 후에야 setRealData가 실행될 수 있다. realData 생성자에 1초 쉬는 부분이 있으므로 setRealData는 1초뒤에 실행된다
				
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

1. 매커니즘
	A는 B에게 특정 데이터를 요청한다. 이는 쓰레드로 실행된다. A는 데이터 요청을 기다리는 동안 다른 작업을 한다.
		B내부에서 요청 데이터 세팅 메서드setdata가 실행된다. 근데 이게 좀 오래걸린다.
		B내부의 데이터를 리턴하는 메서드 getdata는 setdata가 끝날때까지 기다린다.(wait) 
		B내부의 세팅 메서드setdata가 끝날때 기디라고 있던 getdata를 깨운다 (notify) 
	A는 B에게 요청한 데이터를 받는다. 


2. 구현포인트
	(1) 어떤 코드에서 특정 객체를 생성해 이용한다면 객체 생성 이후의 코드는 객체가 생성되어야만 실행될 수 있다
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
					System.out.println("In the lazyObjCaller constructor");	//이 메시지가 출력되려면 lazyObj 객체가 생성될 때 까지 기다려야 한다
				}
			}
	
	(2) wait()는 이 메서드가 호출되기 전에 실행된 쓰레드를 기다린다.
	(3) notifyAll()은 이 메서드가 호출되기 전에 실행된 쓰레드를 기다리는 것들을 깨운다
--------------------------------------------------------------------------------------------------------------------------------------------
Reference
	http://gomp.tistory.com/401
*/