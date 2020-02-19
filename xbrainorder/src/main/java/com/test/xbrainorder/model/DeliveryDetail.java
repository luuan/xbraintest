package com.test.xbrainorder.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class DeliveryDetail {
	
	@Id
	@Column
	private int id;
	@Column
	private String delivery_adress;
	@Column
	private int order_id;

	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDelivery_adress() {
		return delivery_adress;
	}
	
	public void setDelivery_adress(String delivery_adress) {
		this.delivery_adress = delivery_adress;
	}
	
	public int getOrderId() {
		return order_id;
	}
	
	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
