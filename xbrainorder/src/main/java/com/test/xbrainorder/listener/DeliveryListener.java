package com.test.xbrainorder.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.xbrainorder.dao.DeliveryRepo;
import com.test.xbrainorder.model.DeliveryDetail;
import com.test.xbrainorder.model.OrderDetail;

@Component
public class DeliveryListener {
	
	@Autowired
	DeliveryRepo repo;
	@RabbitListener(queues = "Order-Queue")
	public void getAndSaveDelivery(String order) throws JsonMappingException, JsonProcessingException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
		
		OrderDetail orderDetail = mapper.readValue(order, OrderDetail.class);
		
		DeliveryDetail delivery = new DeliveryDetail();
		
		delivery.setOrderId(orderDetail.getId());
		delivery.setDelivery_adress(orderDetail.getDelivery_adress());

        repo.save(delivery);
        
        
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(" [x] Received Order: '" + orderDetail.getId() + "'\n");
        doWork(order);
        watch.stop();
        System.out.println(" [x] Done in " + watch.getTotalTimeSeconds() + "s\n");
		System.out.println(delivery.getOrderId() + " " + delivery.getDelivery_adress()+"\n");
	    
		System.out.println("Delivery saved successfully \n");
	}
	
    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(3000);
            }
        }
    }
}
