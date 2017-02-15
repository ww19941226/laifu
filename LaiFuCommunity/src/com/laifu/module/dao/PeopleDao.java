package com.laifu.module.dao;

import java.util.List;



import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.People;

/**
 * 测试类
 * @author Raindrops
 * @version 2016/9/1
 */
public interface PeopleDao extends IBaseDao<People, Integer>{
	
	    
	    List<People> query(int pn, int pageSize, People command);

	    int countQuery(People command);


}
