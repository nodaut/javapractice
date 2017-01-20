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


// publisher�� ���� topic�� ���� �޼����� ���� �� ������.
// ������ �޽����� queue�� ���� consumer���� ����
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

        //session ����, topic����
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        
        //publisher�κ��� �޼����� �ޱ�����  Subscriber ����
        messageConsumer = session.createDurableSubscriber(topic, subscriptionName);
        messageConsumer.setMessageListener(this);
        System.out.println("[Subscriber]Created messageConsumer");
        
        //queue ���� �� producer�� ���� -> consumer���� ������ ���� queue
        Destination commandQueue = this.session.createQueue("SubscriberToConsumer");
        messageProducer = this.session.createProducer(commandQueue);
        System.out.println("[Subscriber]Created messageProducer");
        System.out.println("[Subscriber]clientId : "+clientId+"\n[Subscriber]topicName : "+topicName+"\n[Subscriber]subscriptionName : "+subscriptionName);
        
        connection.start();
        System.out.println("============Subscriber end line============\n");

    }
    //publisher�κ��� �޼����� ���� ������ �޼��尡 ����
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				// instanseof - �ش� �Ķ���Ͱ� Ư�� class���� �Ǵ�(http://stackoverflow.com/questions/7526817/use-of-instance-of-in-java)
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