package com.test.xbrainorder.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OrderDetail implements Serializable{
	
	@Id
	@Column
	private int id;
	@Column
	private double amount;
	@Column
	private int customer_id;
	@Column
	private String delivery_adress;
	@Column
	private int product_id;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}
	
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDelivery_adress() {
		return delivery_adress;
	}
	
	public void setDelivery_adress(String delivery_adress) {
		this.delivery_adress = delivery_adress;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
