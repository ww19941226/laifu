package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.ComplainsDao;
import com.laifu.module.entity.Complains;


@Repository("ComplainsDao")
public class ComplainsDaoImpl extends BaseDaoImpl<Complains, Integer> implements ComplainsDao{

	/**
	 * 通过hql获取投诉列表信息
	 * 
	 * @param hql 查询的hql语句
	 * @return List<Complains> 查询到的投诉列表信息
	 * @throws Exception
	 */
	@Override
	public List<Complains> getList(String hql) throws Exception {
		return getSession().createQuery(hql).list();
	}
	
	/**
	 * 通过hql修改投诉信息
	 * 
	 * @param hql 要进行修改的hql语句
	 * @throws Exception
	 */
	@Override
	public void update(String hql) throws Exception {
		getSession().createQuery(hql).executeUpdate();
	}
	
}
