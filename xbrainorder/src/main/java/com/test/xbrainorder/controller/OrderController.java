package com.test.xbrainorder.controller;

import org.springframework.stereotype.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.xbrainorder.dao.OrderRepo;
import com.test.xbrainorder.model.OrderDetail;

@Controller
public class OrderController
{
	@Autowired
	OrderRepo repo;
	RabbitTemplate rabbitTemplate;

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
	public String savensend()
	{
		OrderDetail orderExample = new OrderDetail();
		
		orderExample.setId(1);
		orderExample.setAmount(100.00);
		orderExample.setCustomer_id(1);
		orderExample.setDelivery_adress("Rua do Limoeiro, 25");
		orderExample.setProduct_id(10);

        repo.save(orderExample);		
		
		byte[] byteMessage = null;
		
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(orderExample);
			out.flush();
			out.close();
			
			byteMessage = bos.toByteArray();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Message message = MessageBuilder.withBody(byteMessage)
				.setHeader("Order", "request-queue").build();
		
		rabbitTemplate.send("Headers-Exchange", "", message);
		
		return "Success";
	}
}