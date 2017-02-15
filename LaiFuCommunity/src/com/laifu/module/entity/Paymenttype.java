package com.laifu.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 缴费类型表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_paymenttype")
public class Paymenttype implements Serializable{
	
	
	private static final long serialVersionUID = -4482940834084288337L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymenttype_id", nullable = false)
	private int paymenttype_id;//缴费表类型ID(主键)
	private String paymenttype_name;//缴费类型名
	private double paymenttype_money;//缴费单价
	private String paymenttype_unit; //费用单位
	public int getPaymenttype_id() {
		return paymenttype_id;
	}
	public void setPaymenttype_id(int paymenttype_id) {
		this.paymenttype_id = paymenttype_id;
	}
	public String getPaymenttype_name() {
		return paymenttype_name;
	}
	public void setPaymenttype_name(String paymenttype_name) {
		this.paymenttype_name = paymenttype_name;
	}
	public double getPaymenttype_money() {
		return paymenttype_money;
	}
	public void setPaymenttype_money(double paymenttype_money) {
		this.paymenttype_money = paymenttype_money;
	}
	public String getPaymenttype_unit() {
		return paymenttype_unit;
	}
	public void setPaymenttype_unit(String paymenttype_unit) {
		this.paymenttype_unit = paymenttype_unit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + paymenttype_id;
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
		Paymenttype other = (Paymenttype) obj;
		if (paymenttype_id != other.paymenttype_id)
			return false;
		return true;
	}
	
	
}
