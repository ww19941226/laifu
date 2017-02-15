package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Payment;

/**
 * 
 * @author Raindrops
 *
 */
public interface PaymentDao extends IBaseDao<Payment, Integer>{
	
	public List<Payment> getList();
	
	public void delete(Payment payment);
	
	public void add(Payment payment);
	
	public void update(Payment payment);
}
