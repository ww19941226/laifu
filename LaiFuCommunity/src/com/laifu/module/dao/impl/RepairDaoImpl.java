package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.RepairDao;
import com.laifu.module.entity.Repair;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("RepairDao")
public class RepairDaoImpl extends BaseDaoImpl<Repair, Integer> implements RepairDao{
	
	@Override
	public void add(Repair repair) {
		getSession().save(repair);
	}
	
	@Override
	public void update(Repair repair) {
		getSession().update(repair);
	}
	
	@Override
	public void delete(Repair repair) {
		getSession().delete(repair);
	}
	
	@Override
	public List<Repair> getList() {
		List list = getSession().createQuery("from Repair where repair_state=0").list();
		System.out.println(list.size());
		return list;
	}
	
}
