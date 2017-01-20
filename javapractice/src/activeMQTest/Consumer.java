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

// Subscriber�� ���� queue�� ���� �޼����� ���� �� ���
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

        //session ����
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        //Subscriber�κ��� �޼����� �ޱ�����  Consumer �����ϰ� queue ����
        Destination commandQueue = session.createQueue("SubscriberToConsumer");
        messageConsumer = session.createConsumer(commandQueue);
        messageConsumer.setMessageListener(this);
        System.out.println("[Consumer] Created messageConsumer");
        
        connection.start();
        System.out.println("============Consumer end line============\n");

    }
    //Subscriber�κ��� �޼����� ���� ������ �޼��尡 ����
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				//queue���� ���� �ִ� �����Ͱ� �������� Ȯ���ϰ� ���� ���������� �����Ͱ� Ȥ�� ������ ���� �ƴ��� üũ�� ���� ��
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