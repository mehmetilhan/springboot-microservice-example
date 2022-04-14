package com.todomanagement.service;

import com.todomanagement.entity.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mehmet
 */
@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.routing.name}")
    private String routingName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToQueue(Todo todo) {
        rabbitTemplate.convertAndSend(exchangeName, routingName, todo);
        System.out.println("Todo sent : " + todo);

    }
}