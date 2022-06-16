/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.client;

import com.example.demo.Configuration;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*

This class receives messages of a topic: Configuration.TOPIC_NAME 
 */
public class Subscriber {

    public void run()
            throws JMSException {

        // create a Connection Factory
        ConnectionFactory connectionFactory
                = new ActiveMQConnectionFactory(
                        ActiveMQConnection.DEFAULT_BROKER_URL);

        // create a Connection
        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        // create a Session
        Session session
                = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create the Topic from which messages will be received
        Topic topic = session.createTopic(Configuration.TOPIC_NAME);

        // create a MessageConsumer for receiving messages
        messageConsumer = session.createConsumer(topic);

        // start the connection in order to receive messages
        connection.start();

        // read a message from the topic destination
        Message message = messageConsumer.receive(timeout);

        // check if a message was received
        if (message != null) {
            // cast the message to the correct type
            TextMessage textMessage = (TextMessage) message;

            // retrieve the message content
            String text = textMessage.getText();
            logger.info("Subscriber: received message with text='{}'",text);

        } else {
            logger.info("Subscriber: no message received");
        }

        connection.close();

    }

    private String clientId="Subscriber1";
    private Connection connection;
    private MessageConsumer messageConsumer;
    
    int timeout = 10000; /*10 seconds*/

    private static final Logger logger  = LoggerFactory.getLogger(Subscriber.class);

}
