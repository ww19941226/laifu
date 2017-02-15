package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Repair;

/**
 * 
 * @author Raindrops
 *
 */
public interface RepairDao extends IBaseDao<Repair, Integer>{

	public void add(Repair repair);
	
	public void update(Repair repair);
	
	public void delete(Repair repair);
	
	public List<Repair> getList();
}
