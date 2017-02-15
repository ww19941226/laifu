package com.laifu.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: 订单明细表实体类
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jians
 * @date 2016-12-27 下午9:13:22
 * 
 */
@Entity
@Table(name = "tb_orderItems")
public class OrderItems implements Serializable {
	private static final long serialVersionUID = 4453958691451821312L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderItems_id", nullable = false)
	private int orderItems_id;
	private int orderItems_productId;
	private int orderItems_orderId;
	private int orderItems_count;
	private double orderItems_subtotal;

	public int getOrderItems_id() {
		return orderItems_id;
	}

	public void setOrderItems_id(int orderItems_id) {
		this.orderItems_id = orderItems_id;
	}

	public int getOrderItems_productId() {
		return orderItems_productId;
	}

	public void setOrderItems_productId(int orderItems_productId) {
		this.orderItems_productId = orderItems_productId;
	}

	public int getOrderItems_orderId() {
		return orderItems_orderId;
	}

	public void setOrderItems_orderId(int orderItems_orderId) {
		this.orderItems_orderId = orderItems_orderId;
	}

	public int getOrderItems_count() {
		return orderItems_count;
	}

	public void setOrderItems_count(int orderItems_count) {
		this.orderItems_count = orderItems_count;
	}

	public double getOrderItems_subtotal() {
		return orderItems_subtotal;
	}

	public void setOrderItems_subtotal(double orderItems_subtotal) {
		this.orderItems_subtotal = orderItems_subtotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderItems_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItems other = (OrderItems) obj;
		if (orderItems_id != other.orderItems_id)
			return false;
		return true;
	}
}
