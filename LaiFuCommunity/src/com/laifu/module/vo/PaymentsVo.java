package com.laifu.module.vo;

import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Paymenttype;
import com.laifu.module.entity.User;

public class PaymentsVo {
	
	private Payment payment;
	private Paymenttype paymenttype;
	private User user;
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Paymenttype getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PaymentsVo(Payment payment, Paymenttype paymenttype) {
		super();
		this.payment = payment;
		this.paymenttype = paymenttype;
	}
	public PaymentsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
