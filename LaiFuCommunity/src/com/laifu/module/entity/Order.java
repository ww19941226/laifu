package com.laifu.module.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @ClassName: 订单表
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jians
 * @date 2016-12-27 下午8:58:39
 * 
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 4453958691451821312L;
	@Id
	@Column(name = "order_id", nullable = false)
	private int order_id;
	private Date order_creattime;
	private double order_money;
	private int order_state;
	private String order_address;
	private String order_phone;
	private String order_receivename;
	@JoinColumn(name = "order_userId")
	@ManyToOne
	private User user;
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "orderItems_orderId")
	private Set<OrderItems> orderItems = new HashSet<OrderItems>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Date getOrder_creattime() {
		return order_creattime;
	}

	public void setOrder_creattime(Date order_creattime) {
		this.order_creattime = order_creattime;
	}

	public double getOrder_money() {
		return order_money;
	}

	public void setOrder_money(double order_money) {
		this.order_money = order_money;
	}

	public int getOrder_state() {
		return order_state;
	}

	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getOrder_phone() {
		return order_phone;
	}

	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}

	public String getOrder_receivename() {
		return order_receivename;
	}

	public void setOrder_receivename(String order_receivename) {
		this.order_receivename = order_receivename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order_id;
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
		Order other = (Order) obj;
		if (order_id != other.order_id)
			return false;
		return true;
	}
}
