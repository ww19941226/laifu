package com.laifu.module.vo;

import java.util.List;

import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Paymenttype;

public class PaymentVo {
	private String payment_starttime;
	private String payment_endtime;
	private String payment_deadtime;
	private Double payment_units;
	private String phone_number;
	private Paymenttype paymenttype;
	private Integer payment_id;
	private List<Paymenttype> paymenttypelist;
	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public List<Paymenttype> getPaymenttypelist() {
		return paymenttypelist;
	}

	public void setPaymenttypelist(List<Paymenttype> paymenttypelist) {
		this.paymenttypelist = paymenttypelist;
	}

	public PaymentVo(String payment_starttime, String payment_endtime,
			String payment_deadtime, Double payment_units,
			String phone_number, Paymenttype paymenttype) {
		super();
		this.payment_starttime = payment_starttime;
		this.payment_endtime = payment_endtime;
		this.payment_deadtime = payment_deadtime;
		this.payment_units = payment_units;
		this.phone_number = phone_number;
		this.paymenttype = paymenttype;
	}
	
	public PaymentVo() {
		super();
	}
	
	public String getPayment_starttime() {
		return payment_starttime;
	}
	public void setPayment_starttime(String payment_starttime) {
		this.payment_starttime = payment_starttime;
	}
	public String getPayment_endtime() {
		return payment_endtime;
	}
	public void setPayment_endtime(String payment_endtime) {
		this.payment_endtime = payment_endtime;
	}
	public String getpayment_deadtime() {
		return payment_deadtime;
	}
	public void setpayment_deadtime(String payment_deadtime) {
		this.payment_deadtime = payment_deadtime;
	}
	public Double getPayment_units() {
		return payment_units;
	}
	public void setPayment_units(Double payment_units) {
		this.payment_units = payment_units;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Paymenttype getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(Paymenttype paymenttype) {
		this.paymenttype = paymenttype;
	}
		
}
