package com.laifu.module.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.NotifyDao;
import com.laifu.module.entity.Notify;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("NotifyDao")
public class NotifyDaoImpl extends BaseDaoImpl<Notify, Integer> implements NotifyDao{
	
	/**
	 * 获取最新的number条通知
	 * @param hql
	 * @param number
	 * @return
	 */
	public List<Notify> getTopNotify(int number){
		String hql = "from Notify order by notify_datetime ";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);   //从第0条开始
		q.setMaxResults(number);//一共取number条
		return q.list();	
		
	}
}
