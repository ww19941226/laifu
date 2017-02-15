
/**
 * 该vo类用来进行支付的时候传递参数用的,主要是封装缴费订单和金额俩个数据
 * auto: zepeng
 * date: 2016-10-10
 */
package com.laifu.module.vo;

import java.util.Date;

public class PayMoneyVo {
	private int paymentid; //用户订单
	private double paymoney; //付款金额
	private Date  paytime;  //付款完成时间
	
	public PayMoneyVo(int paymentid, double paymoney) {
		super();
		this.paymentid = paymentid;
		this.paymoney = paymoney;
	}
	
	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public PayMoneyVo() {
		super();
	}
	
	public int getPaymentid() {
		return paymentid;
	}
	
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	
	public double getPaymoney() {
		return paymoney;
	}
	
	public void setPaymoney(double paymoney) {
		this.paymoney = paymoney;
	}
	
}
