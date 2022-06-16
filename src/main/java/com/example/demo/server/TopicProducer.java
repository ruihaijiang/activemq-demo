/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.server;

import com.example.demo.Configuration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*

This class sends messages to a topic: Configuration.TOPIC_NAME 
*/

public class TopicProducer implements Runnable {

    @Override
    public void run() {
        try {
            internalRun();
        } catch (Exception e) {
            logger.error("Publisher, got exception: " + e.toString() );
        }
    }

    public void internalRun()
            throws JMSException, InterruptedException {

        // create a Connection Factory
        logger.info( "Publisher: create a Connection Factory");
        ConnectionFactory connectionFactory
                = new ActiveMQConnectionFactory(
                        Configuration.JMS_URL);

        // create a Connection
        logger.info( "Publisher: create a Connection");
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        //create a Session
        session
                = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //create the Topic
        logger.info( "Publisher: create the Topic");
        Topic topic = session.createTopic(Configuration.TOPIC_NAME);

        // create a MessageProducer for sending messages
        logger.info( "Publisher: create a MessageProducer");
        messageProducer = session.createProducer(topic);

        for (int i = 0; i < 100; i++) {
            /* the server just loops for 100 times, then quits*/
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String text = dateFormat.format(date);

            // create a JMS TextMessage
            TextMessage textMessage = session.createTextMessage(text);

            // send the message to the topic destination
            messageProducer.send(textMessage);

            logger.info(clientId + ": sent message with text='{}'", text);

            Thread.sleep(1000 * 5);
            /* sleep for 5 seconds */
        }

        connection.close();
    }

    private String clientId = "publisher1";
    private Connection connection;
    private Session session;
    private MessageProducer messageProducer;

    private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);

}
