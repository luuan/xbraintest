package com.test.xbrainorder.service;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.xbrainorder.model.OrderDetail;

@Service
public class RabbitmqSender {
	
	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;
	
	
	public void sendToRabbitmq(OrderDetail order) throws MessagingException, JsonProcessingException{
		
		ObjectMapper mapper = new ObjectMapper();
				
		this.rabbitMessagingTemplate.convertAndSend("Order-Queue",mapper.writeValueAsString(order));
	        
		System.out.println(" [x] Sent '" + mapper.writeValueAsString(order) + "'\n");
	}
}
