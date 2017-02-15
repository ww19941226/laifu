package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Complains;

public interface ComplainsDao extends IBaseDao<Complains, Integer>{

	/**
	 * 通过hql获取投诉列表信息
	 * 
	 * @param hql 查询的hql语句
	 * @return List<Complains> 查询到的投诉列表信息
	 * @throws Exception
	 */
	public List<Complains> getList(String hql) throws Exception;
	
	/**
	 * 通过hql修改投诉信息
	 * 
	 * @param hql 要进行修改的hql语句
	 * @throws Exception
	 */
	public void update(String hql) throws Exception;
	
}
