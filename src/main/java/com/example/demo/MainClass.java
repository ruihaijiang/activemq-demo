/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.example.demo.client.DemoClient;
import com.example.demo.client.QueueProducer;
import com.example.demo.server.DemoServer;

/**
 *
 * @author ruihaijiang
 */
public class MainClass {
    
    /*
    run the server:
        java -jar activemq-demo-1.0-jar-with-dependencies.jar  server
    
    run the client
    java -jar activemq-demo-1.0-jar-with-dependencies.jar  client
    */
    static public void main(String [] args) throws Exception 
    {
        if( args == null || args.length == 0 ) {
            printUsage();
            return;
        }
        System.out.println("args[0]="+args[0]);
  
        if("server".equals(args[0])) {
        
            runServer();
        } 
        else if("client".equals(args[0]))
        {
            runClient();
        } 
        else 
        {   
            System.out.println("unknown parameter: "+ args[0]);
            printUsage();
        }
    }   
    
    static void runServer() throws Exception 
    {
    
        DemoServer server  = new DemoServer();
        server.run();    
    }
    
    static void runClient() throws Exception 
    {
        DemoClient client = new DemoClient();	
        
        client.run();    
    }
      
    static void printUsage() 
    {
        System.out.println("Usage:");
        System.out.println("\tjava -jar activemq-demo-1.0-jar-with-dependencies.jar  server");
        System.out.println("\tjava -jar activemq-demo-1.0-jar-with-dependencies.jar  client");
    }
}
