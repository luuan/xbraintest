package com.test.xbrainorder.listener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.xbrainorder.dao.DeliveryRepo;
import com.test.xbrainorder.model.DeliveryDetail;
import com.test.xbrainorder.model.OrderDetail;

@Component
public class DeliveryListener {
	
	@Autowired
	DeliveryRepo repo;
	
	@RabbitListener(queues = "request-queue")
	public String getAndSaveDelivery(byte[] message) {
		OrderDetail order = new OrderDetail();
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(message);
			ObjectInput in = new ObjectInputStream(bis);
			order = (OrderDetail) in.readObject();
			in.close();
			bis.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DeliveryDetail delivery = new DeliveryDetail();
		
		delivery.setId(1);
		delivery.setOrderId(order.getId());
		delivery.setDelivery_adress(order.getDelivery_adress());

        repo.save(delivery);
		
		System.out.println(order);
		
		return "Delivery saved successfully";
	}
}
