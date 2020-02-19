package com.test.xbrainorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.xbrainorder.model.DeliveryDetail;

public interface DeliveryRepo extends JpaRepository<DeliveryDetail, Integer> {

}
