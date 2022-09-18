package com.learn.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;


public class RabbitMQProducer {
    public static final String queue_name="HELLO_WORLD";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (Connection connection=connectionFactory.newConnection();
             Channel channel =connection.createChannel()){

                channel.queueDeclare(queue_name,false,false,false,null);
                String message="Hello World";
                channel.basicPublish("",queue_name,null,message.getBytes(StandardCharsets.UTF_8));
                System.out.println("[x] Message Sent: "+message );


        }
    }
}
