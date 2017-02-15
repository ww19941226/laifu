package com.laifu.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 缴费表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_payment")
public class Payment implements Serializable {
	

	private static final long serialVersionUID = 1117785616711439205L;
	
	@Id
    @Column(name = "payment_id", nullable = false)
	private int payment_id;//缴费表ID(主键)
	private int payment_type;//缴费类型(外键)
	private Date payment_starttime;//费用产生起始时间
	private Date payment_endtime;//费用产生结束时间
	private int payment_userid;//用户ID(外键)
	private Date payment_complettime;//缴费完成时间
	private Date payment_deadtime;//缴费截止时间
	private double payment_units;//缴费单位数量
	private int payment_paystate;
	
	
	
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}
	public Date getPayment_starttime() {
		return payment_starttime;
	}
	public void setPayment_starttime(Date payment_starttime) {
		this.payment_starttime = payment_starttime;
	}
	public Date getPayment_endtime() {
		return payment_endtime;
	}
	public void setPayment_endtime(Date payment_endtime) {
		this.payment_endtime = payment_endtime;
	}
	public int getPayment_userid() {
		return payment_userid;
	}
	public void setPayment_userid(int payment_userid) {
		this.payment_userid = payment_userid;
	}
	public Date getPayment_complettime() {
		return payment_complettime;
	}
	public void setPayment_complettime(Date payment_complettime) {
		this.payment_complettime = payment_complettime;
	}
	public Date getPayment_deadtime() {
		return payment_deadtime;
	}
	public void setPayment_deadtime(Date payment_deadtime) {
		this.payment_deadtime = payment_deadtime;
	}
	public double getPayment_units() {
		return payment_units;
	}
	public void setPayment_units(double payment_units) {
		this.payment_units = payment_units;
	}
	public int getPayment_paystate() {
		return payment_paystate;
	}
	public void setPayment_paystate(int payment_paystate) {
		this.payment_paystate = payment_paystate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + payment_id;
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
		Payment other = (Payment) obj;
		if (payment_id != other.payment_id)
			return false;
		return true;
	}
	
	
	
	
}
