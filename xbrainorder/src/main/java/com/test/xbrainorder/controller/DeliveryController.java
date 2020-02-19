package com.test.xbrainorder.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.xbrainorder.dao.DeliveryRepo;
import com.test.xbrainorder.model.DeliveryDetail;

@Controller
public class DeliveryController {

	@Autowired
	DeliveryRepo repo;
	
	@RequestMapping("/delivery")
	@ResponseBody
	public List<DeliveryDetail> getDelivery()
	{
		return repo.findAll();
	}
	
	@RequestMapping("/delivery/{id}")
	@ResponseBody
	public Optional <DeliveryDetail> getDelivery(@PathVariable("id")int id)
	{
		return repo.findById(id);
	}
	
}
