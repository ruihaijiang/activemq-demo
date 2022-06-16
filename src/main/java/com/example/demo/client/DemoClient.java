/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.client;

import com.example.demo.server.QueueConsumer;

/**
 *
 * @author ruihaijiang
 */
public class DemoClient {
     public void run() throws Exception {
        QueueProducer producer = new QueueProducer();	        
        producer.run(  );  
        
        TopicConsumer subscriber = new TopicConsumer();        
        subscriber.run(  );  
    }
       
}
