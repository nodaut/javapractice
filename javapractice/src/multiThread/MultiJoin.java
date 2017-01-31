package multiThread;

class drawLine extends Thread{
	private String drawChar;
	drawLine (String drawChar){
		this.drawChar = drawChar;		
	}
	
	@Override
	public void run(){
		System.out.println(this);
		for(int i=0; i<100; i++){
			System.out.print(this.drawChar);
			try{Thread.sleep(10);}
			catch(InterruptedException e){System.out.println(e);}
		}
		System.out.println("\n"+this.getName());
	}
}

public class MultiJoin {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		long endTime;
		
		Thread trd1 = new drawLine("-");
		Thread trd2 = new drawLine("+");
		
		trd1.start();
		trd2.start();
		
		try{
			trd1.join();
			trd2.join();			
		}catch (InterruptedException e){
			System.out.println(e);
		}

		//�Ʒ� �ڵ�� ���� thread���� ��� ���� �Ϸ�Ǿ�� �����
		endTime = System.currentTimeMillis();
		System.out.println("\nElapsed time is " + (endTime-startTime));
		
	}
}

/* 
 * Reference
 * java�� ���� 763
*/
