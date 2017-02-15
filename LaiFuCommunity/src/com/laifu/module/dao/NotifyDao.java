package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Notify;
/**
 * 
 * @author Raindrops
 *
 */
public interface NotifyDao extends IBaseDao<Notify, Integer>{
	
	/**
	 * 获取最新的number条通知
	 * @param hql
	 * @param number
	 * @return
	 */
	List<Notify> getTopNotify(int number);
}
