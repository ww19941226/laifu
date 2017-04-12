package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Category;

public interface CategoryDao extends IBaseDao<Category, Integer> {

	public List<Category> getAllCategories() throws Exception;

	public Category findBycid(Integer categoty_id) throws Exception;

	void addCategory(Category category) throws Exception;
	
	void updateCategory(Category category) throws Exception;

	void removeCategory(Integer id) throws Exception;
}
