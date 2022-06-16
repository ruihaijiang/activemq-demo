/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.client;

import com.example.demo.server.Consumer;

/**
 *
 * @author ruihaijiang
 */
public class DemoClient {
     public void run() throws Exception {
        Producer producer = new Producer();	        
        producer.run(  );  
        
        Subscriber subscriber = new Subscriber();        
        subscriber.run(  );  
    }
       
}
