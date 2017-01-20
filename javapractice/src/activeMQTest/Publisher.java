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
 * ActiveMQ topic을 테스트 하기 위한
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

			//session 생성
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//topic 생성후 producer에 세팅
			Topic topic = session.createTopic(topicName);
			messageProducer = session.createProducer(topic);
			
			//메세지 생성
			String messageId = String.valueOf(System.currentTimeMillis());	//currentTimeMillis : 현재 시간을 밀리세컨드 long value로 리턴해줌
			TextMessage textMessage = session.createTextMessage(messageId+" This message come from publisher");	
			
			//메세지 send
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