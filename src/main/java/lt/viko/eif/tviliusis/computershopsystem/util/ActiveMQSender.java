package lt.viko.eif.tviliusis.computershopsystem.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ActiveMQSender {

    public static final String QUEUE_NAME = "MY_QUEUE";

    /**
     * Sends an XML message to the ActiveMQ queue.
     *
     * @param args Command line arguments (not used)
     * @throws JMSException If there is an error with the JMS connection or session
     * @throws IOException  If there is an error reading the XML file
     */


    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(destination);
        String filePath = "C:/Users/viliu/IdeaProjects/ComputerShopPD1/generated.xml";
        String xmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
        TextMessage message = session.createTextMessage(xmlContent);
        message.setStringProperty("JMSType", "text/xml");


        producer.send(message);
        System.out.println("Sending the message " + message.getText() + " to the " + QUEUE_NAME);

        connection.close();

    }
}


