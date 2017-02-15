package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Paymenttype;

/**
 * 
 * @author Raindrops
 *
 */
public interface PaymenttypeDao extends IBaseDao<Paymenttype, Integer>{
	
	public List<Paymenttype> getPaymenttypeList();
	
	/**
	 * 获取费用类型的前N条数据
	 * @param hql
	 * @return
	 */
	public List<Paymenttype> getTopPaymenttype(int number);

}
