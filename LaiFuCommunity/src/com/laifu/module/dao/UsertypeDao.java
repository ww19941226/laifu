package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Usertype;

public interface UsertypeDao extends IBaseDao<Usertype, Integer>{

	/**
	 * 得到用户类型列表
	 * @return
	 */
	public List<Usertype> getUsertypeList();
}
