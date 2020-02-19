package com.test.xbrainorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.xbrainorder.model.OrderDetail;

public interface OrderRepo extends JpaRepository<OrderDetail, Integer> {
	
}
