package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.FinancialDao;
import com.laifu.module.entity.Financial;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("FinancialDao")
public class FinancialDaoImpl extends BaseDaoImpl<Financial, Integer> implements FinancialDao{
	
	@Override
	public void add(Financial financial) {
		getSession().save(financial);
	}
	
	@Override
	public void update(Financial financial) {
		getSession().update(financial);
	}
	
	@Override
	public void delete(Financial financial) {
		getSession().delete(financial);
	}
	
	@Override
	public List<Financial> getList() {
		return getSession().createQuery("from Financial").list();
	}
	
}
