/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.client;

import com.example.demo.Configuration;
import com.example.demo.server.Publisher;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*

This class sends messages to a queue: Configuration.QUEUE_NAME 

 */
public class Producer {

    public void run() {
        try { // Create a connection factory.
            logger.info("Producer: Create a connection factory");
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(Configuration.JMS_URL);

            //Create connection.
            Connection connection = factory.createConnection();

            // Start the connection
            connection.start();

            // Create a session which is non transactional
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create Destination queue
            Destination queue = session.createQueue(Configuration.QUEUE_NAME);

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String text = "Hello World " + dateFormat.format(date);

            // insert message
            TextMessage tmessage = session.createTextMessage(text);
            logger.info("Producer: sent message: " + text);
            producer.send(tmessage);

            session.close();
            connection.close();
        } catch (Exception ex) {
            logger.error("Exception Occured,Exception = " + ex.toString());
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
}
