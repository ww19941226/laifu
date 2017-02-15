package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Financial;
/**
 * 
 * @author Raindrops
 *
 */
public interface FinancialDao extends IBaseDao<Financial, Integer>{

	public void add(Financial financial);
	
	public void update(Financial financial);
	
	public void delete(Financial financial);
	
	public List<Financial> getList();
}
