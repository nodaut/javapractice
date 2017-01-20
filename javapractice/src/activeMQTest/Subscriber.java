package activeMQTest;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


// publisher로 부터 topic을 통해 메세지를 받은 후 가공함.
// 가공한 메시지를 queue를 통해 consumer에게 보냄
class Subscriber implements MessageListener {

	//connection
    private ActiveMQConnection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private MessageProducer messageProducer;
    
    public Subscriber(String clientId, String topicName, String subscriptionName) throws JMSException {
    	System.out.println("============Subscriber start line============");
    	//connection
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = (ActiveMQConnection)connectionFactory.createConnection();
        connection.setClientID(clientId);

        //session 생성, topic생성
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        
        //publisher로부터 메세지를 받기위한  Subscriber 생성
        messageConsumer = session.createDurableSubscriber(topic, subscriptionName);
        messageConsumer.setMessageListener(this);
        System.out.println("[Subscriber]Created messageConsumer");
        
        //queue 생성 후 producer에 세팅 -> consumer에게 보내기 위한 queue
        Destination commandQueue = this.session.createQueue("SubscriberToConsumer");
        messageProducer = this.session.createProducer(commandQueue);
        System.out.println("[Subscriber]Created messageProducer");
        System.out.println("[Subscriber]clientId : "+clientId+"\n[Subscriber]topicName : "+topicName+"\n[Subscriber]subscriptionName : "+subscriptionName);
        
        connection.start();
        System.out.println("============Subscriber end line============\n");

    }
    //publisher로부터 메세지가 오면 다음의 메서드가 실행
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				// instanseof - 해당 파라메터가 특정 class인지 판단(http://stackoverflow.com/questions/7526817/use-of-instance-of-in-java)
				TextMessage txtMsg = (TextMessage) message;
				String messageText = txtMsg.getText();
				System.out.println("[Subscriber] receive Msg : " + messageText);
				//modify original message
				messageText+=" appended Text By subscriver";
				System.out.println("[Subscriber] modified Msg : " + messageText);
				
				//make result message
				TextMessage newtxtMsg = this.session.createTextMessage(messageText);
				System.out.println("[Subscriber2] modified Msg : " + newtxtMsg.getText());
			
				this.messageProducer.send(newtxtMsg);
				this.connection.close();
				System.out.println("[Subscriber] connection closed");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}	