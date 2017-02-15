package com.laifu.module.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.PaymenttypeDao;
import com.laifu.module.entity.Paymenttype;

/**
 * 
 * @author Raindrops
 *
 */
@Repository("PaymenttypeDao")
public class PaymenttypeDaoImpl extends BaseDaoImpl<Paymenttype, Integer> implements PaymenttypeDao{


	public List<Paymenttype> getPaymenttypeList() {
		return getSession().createQuery("from Paymenttype").list();
	}
	
	/**
	 * 获取费用类型的前N条数据
	 * @param hql
	 * @return
	 */
	public List<Paymenttype> getTopPaymenttype(int number){
		String hql = " from Paymenttype";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);   //从第0条开始
		q.setMaxResults(number);//一共取number条
		return q.list();
	}
}
