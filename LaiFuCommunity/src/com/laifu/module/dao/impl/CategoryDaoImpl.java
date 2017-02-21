package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.CategoryDao;
import com.laifu.module.entity.Category;

@Repository("CategoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer> implements
		CategoryDao {

	@Override
	public List<Category> getAllCategories() throws Exception {
		// TODO Auto-generated method stub

		return getSession().createQuery("from Category").list();
	}

	@Override
	public Category findBycid(Integer categoty_id) throws Exception {
		// TODO Auto-generated method stub
		return get(categoty_id);
	}

}
