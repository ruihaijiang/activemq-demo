package com.example.demo.server;

import com.example.demo.Configuration;
import com.example.demo.server.QueueConsumer;
import java.net.URI;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

public class DemoServer {

    public void run() throws Exception {
        startActiveMQBroker();    
        startThreads();
    }
    

    
    /*
    start the embeded ActiveMQ broker
    */
    void startActiveMQBroker() throws Exception {
        broker = new BrokerService();
        connector = new TransportConnector();
        connector.setUri(new URI( Configuration.JMS_URL ));
        broker.addConnector(connector);
        broker.setPersistent(false);
        broker.start();

    }
    
    void startThreads() {
        //start the publisher thread
        new Thread( new TopicProducer() ).start();
        
        //start the consuer thread
        new Thread( new QueueConsumer() ).start();
    }
    
    BrokerService broker;
    TransportConnector connector;
}
