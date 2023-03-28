package lt.viko.eif.tviliusis.computershopsystem.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.FileOutputStream;
import java.io.IOException;


public class ActiveMQReceiver {

    public static final String QUEUE_NAME = "MY_QUEUE";

    /**
     * Main method of the ActiveMQReceiver class, which creates a connection to the message broker,
     * starts a session and creates a consumer to receive messages. If the received message is of type
     * TextMessage, the message content is extracted and saved to a file named "new.xml".
     *
     * @param 'args[]' command-line arguments
     * @throws JMSException if an error occurs while creating the connection or session
     * @throws IOException  if an error occurs while writing to the output file
     */

    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(destination);

        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String xmlContent = textMessage.getText();
            byte[] array = xmlContent.getBytes();
            FileOutputStream fout = new FileOutputStream("new.xml");
            fout.write(array);
            fout.close();
            System.out.println("Received message " + textMessage.getText() + " from " + QUEUE_NAME);
        }

        connection.close();

    }
}
