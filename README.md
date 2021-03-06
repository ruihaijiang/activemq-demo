# How this works

## The server

- The server creates and runs a broker.
- The server then creates a TopicProducer and a QueueConsumer.
- The TopicProducer keeps sending messages to a topic.
- The QueueConsumer keeps reading messages from a queue.

## The client
- The client sends a message to the queue.
- The client subscribes to the topic and then tries to read a message of the topic.


```               
┌──────────────────────────────────────────┐                      ┌─────────────────────┐
│ Server                                   │                      │ Client              │
│                                          │                      │                     │                                
│                  ┌────────────────────┐  │                      │                     │
│                  │ Embedded Broker    │  │                      │                     │
│                  │                    │  │                      │                     │
│                  │    ┌────────────┐  │  │                      │                     │
│                  │    │   Topic    │  │  │                      │                     │
│[Topic Producer]──────>│            │ ─────────────────────────────> [Topic Consumer]  │
│                  │    │            │  │  │                      │                     │
│                  │    └────────────┘  │  │                      │                     │
│                  │                    │  │                      │                     │              
│                  │    ┌────────────┐  │  │                      │                     │
│                  │    │  Queue     │  │  │                      │                     │
│[Queue Consumer]<──────│            │ <───────────────────────────── [Queue Producer]  │
│                  │    │            │  │  │                      │                     │
│                  │    └────────────┘  │  │                      │                     │
│                  │                    │  │                      │                     │     
│                  └────────────────────┘  │                      │                     │  
│                                          │                      │                     │
│                                          │                      │                     │  
└──────────────────────────────────────────┘                      └─────────────────────┘ 
                  
```


# How to run the test

After building, a package is created: activemq-demo-1.0-jar-with-dependencies.jar.

To start the server, just run this,
```
java -jar ./target/activemq-demo-1.0-jar-with-dependencies.jar server
```

To start the client, just run this,
```
java -jar ./target/activemq-demo-1.0-jar-with-dependencies.jar client
```