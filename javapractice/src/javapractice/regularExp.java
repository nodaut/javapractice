package javapractice;

public class regularExp {

	public static void main(String[] args) {
		String test = "1484636223267|select sum(TX_BYTES), sum(RX_BYTES) from system_tp";
		String[] parsed = test.split("\\|");
		for(int i=0; i<parsed.length; i++)
			System.out.println(parsed[i]);
		
		if(test.isEmpty())
			System.out.println("empty");
		else
			System.out.println("not empty");
	}

}
