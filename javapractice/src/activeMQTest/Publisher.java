package activeMQTest;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/*
 * ActiveMQ topic을 테스트 하기 위한
 * publisher class
 */
public class Publisher {

    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

	public Publisher(String clientId, String topicName) {
		try {
			//connection
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.setClientID(clientId);

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(topicName);
			messageProducer = session.createProducer(topic);
			
			String messageId = String.valueOf(System.currentTimeMillis());
			TextMessage textMessage = session.createTextMessage(messageId+"|select sum(TX_BYTES), sum(RX_BYTES) from system_tp");	
			
			this.messageProducer.send(textMessage);
			System.out.println(textMessage.toString());
			
		} catch (JMSException e) {
			// Handle the exception appropriately
		}
	}

    public static void main (String[] args) {
    	new Publisher("publisher-command", "LogProcessing");
    }
}