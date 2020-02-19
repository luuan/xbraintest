package com.test.xbrainorder.config;

import com.test.xbrainorder.listener.DeliveryListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

	 public final static String REQUEST_QUEUE = "request-queue";

	  @Bean
	  Queue queue() {
	    return new Queue(REQUEST_QUEUE, false);
	  }

	  @Bean
	  TopicExchange exchange() {
	    return new TopicExchange("spring-boot-exchange");
	  }

	  @Bean
	  Binding binding(Queue queue, TopicExchange exchange) {
	    return BindingBuilder.bind(queue).to(exchange).with(REQUEST_QUEUE);
	  }

	  @Bean
	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	      MessageListenerAdapter listenerAdapter) {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames(REQUEST_QUEUE);
	    container.setMessageListener(listenerAdapter);
	    return container;
	  }

	  @Bean
	  MessageListenerAdapter listenerAdapter(DeliveryListener receiver) {
	    return new MessageListenerAdapter(receiver, "receiveMessage");
	  }
}