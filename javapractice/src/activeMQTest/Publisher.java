package activeMQTest;
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
 * ActiveMQ topic�� �׽�Ʈ �ϱ� ����
 * publisher class
 */
public class Publisher {

    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

	public Publisher(String clientId, String topicName) {
		try {
			System.out.println("============publisher start line============");
			//connection
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.setClientID(clientId);

			//session ����
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//topic ������ producer�� ����
			Topic topic = session.createTopic(topicName);
			messageProducer = session.createProducer(topic);
			
			//�޼��� ����
			String messageId = String.valueOf(System.currentTimeMillis());	//currentTimeMillis : ���� �ð��� �и������� long value�� ��������
			TextMessage textMessage = session.createTextMessage(messageId+" This message come from publisher");	
			
			//�޼��� send
			this.messageProducer.send(textMessage);
			System.out.println("[publisher] "+textMessage.getText());
			System.out.println("============publisher end line============\n");
			this.connection.close();
			System.out.println("[publisher] connection closed");
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}