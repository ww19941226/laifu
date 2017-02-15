package com.laifu.module.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.UsertypeDao;
import com.laifu.module.entity.Usertype;

/**
 * 
 * @author Raindrops
 *
 */
@Repository("UsertypeDao")
public class UsertypeDaoImpl extends BaseDaoImpl<Usertype, Integer> implements UsertypeDao{
	
	/**
	 * 得到用户类型列表
	 * @return
	 */
	public List<Usertype> getUsertypeList(){
		String hql = "from Usertype ut where usertype_id != 4";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

}
