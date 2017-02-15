package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Category;

public interface CategoryDao extends IBaseDao<Category, Integer> {

	public List<Category> getAllCategories() throws Exception;
}
