package com.test.xbrainorder.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.xbrainorder.dao.OrderRepo;
import com.test.xbrainorder.model.OrderDetail;
import com.test.xbrainorder.service.RabbitmqSender;

@Controller
public class OrderController
{
	@Autowired
	OrderRepo repo;
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	RabbitmqSender rabbitMqSender;

	@RequestMapping("/")
	@ResponseBody
	public List<OrderDetail> getDefault()
	{
		return repo.findAll();
	}

	@RequestMapping("/orders")
	@ResponseBody
	public List<OrderDetail> getOrders()
	{
		return repo.findAll();
	}
	
	@RequestMapping("/order/{id}")
	@ResponseBody
	public Optional <OrderDetail> getOrder(@PathVariable("id")int id)
	{
		return repo.findById(id);
	}
	
	@RequestMapping("/savensend")
	@ResponseBody
	public String savensend() throws MessagingException, JsonProcessingException
	{
		OrderDetail orderExample = new OrderDetail();
		
		orderExample.setAmount(100.00);
		orderExample.setCustomer_id(1);
		orderExample.setDelivery_adress("Rua do Limoeiro, 25");
		orderExample.setProduct_id(10);

        repo.save(orderExample);		
								
		rabbitMqSender.sendToRabbitmq(orderExample);
	
		return "Success";
	}
}