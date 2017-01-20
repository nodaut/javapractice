package activeMQTest;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

// Subscriber로 부터 queue를 통해 메세지를 받은 후 출력
class Consumer implements MessageListener {

	//connection
    private ActiveMQConnection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    
    public Consumer() throws JMSException {
    	System.out.println("============Consumer start line============");
    	//connection
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = (ActiveMQConnection)connectionFactory.createConnection();

        //session 생성
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        //Subscriber로부터 메세지를 받기위한  Consumer 생성하고 queue 세팅
        Destination commandQueue = session.createQueue("SubscriberToConsumer");
        messageConsumer = session.createConsumer(commandQueue);
        messageConsumer.setMessageListener(this);
        System.out.println("[Consumer] Created messageConsumer");
        
        connection.start();
        System.out.println("============Consumer end line============\n");

    }
    //Subscriber로부터 메세지가 오면 다음의 메서드가 실행
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				//queue에서 남아 있는 데이터가 무엇인지 확인하고 현재 가져오려는 데이터가 혹시 이전의 것은 아닌지 체크해 봐야 함
				TextMessage txtMsg = (TextMessage) message;
				String messageText = txtMsg.getText();
				System.out.println("[Consumer] receive Msg : " + messageText);
				this.connection.close();
				System.out.println("[Consumer] connection closed");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}	