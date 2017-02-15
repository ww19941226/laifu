package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.PaymentDao;
import com.laifu.module.entity.Payment;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("PaymentDao")
public class PaymentDaoImpl extends BaseDaoImpl<Payment, Integer> implements PaymentDao{
	
	@Override
	public List<Payment> getList() {
		return getSession().createQuery("from Payment").list();
	}
	
	@Override
	public void delete(Payment payment) {
		getSession().delete(payment);
	}
	
	@Override
	public void add(Payment payment) {
		getSession().save(payment);
	}
	
	@Override 
	public void update(Payment payment) {
		getSession().update(payment);
	}
}
