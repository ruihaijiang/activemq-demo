/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.server;

import com.example.demo.Configuration;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
This class reads messages from a queue: Configuration.QUEUE_NAME 
 */
public class QueueConsumer implements Runnable {

    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(Configuration.JMS_URL);

            //Create Connection
            logger.info("Consumer: Create Connection");
            Connection connection = factory.createConnection();

            // Start the connection
            logger.info("Consumer: Start the connection");
            connection.start();

            // Create Session
            logger.info("Consumer: Create the session");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //Create queue
            Destination queue = session.createQueue( Configuration.QUEUE_NAME );

            logger.info("Consumer: Create the consumer");
            MessageConsumer consumer = session.createConsumer(queue);

            for (int i = 0; i < 100; i++) {
                /* the server just loops for 100 times, then quits*/
                Message message = consumer.receive(1000);

                if (message != null) {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        logger.info("Consumer: Received: " + text);
                    } else {
                        logger.info("Consumer: message received , not a TextMessage: " + message.toString());
                    }
                }
                Thread.sleep(1000 * 5);/* sleep for 5 seconds */
            }
            session.close();
            connection.close();
        } catch (Exception ex) {
            logger.error("Consumer: Exception Occured: Exception = " + ex.toString());
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

}
