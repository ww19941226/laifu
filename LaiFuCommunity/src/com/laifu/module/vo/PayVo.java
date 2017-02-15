package com.laifu.module.vo;

import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Paymenttype;

public class PayVo {

	private Payment payment;
	private Paymenttype paymenttype;
	
	
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
	
	
	public PayVo(Payment payment, Paymenttype paymenttype) {
		super();
		this.payment = payment;
		this.paymenttype = paymenttype;
	}
	
	public PayVo() {
		super();
	}
	
	
}
