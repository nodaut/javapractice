package activeMQTest;
import javax.jms.JMSException;

public class ActiveMQTest{
	
	public static void main(String[] args) {
		try{
			String messageId = String.valueOf(System.currentTimeMillis());
			new Subscriber("subscriber-"+messageId, "LeeTopic", "LeeSubscription");
			Thread.sleep(2000);
			new Publisher("LeePublisher", "LeeTopic");
			Thread.sleep(2000);
			new Consumer();

		}catch(JMSException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}