package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.CategorySecond;

public interface CategorySecondDao extends IBaseDao<CategorySecond, Integer> {

	public List<CategorySecond> getAllCategorySecond(int id) throws Exception;

	public CategorySecond findBycsid(Integer id) throws Exception;
}
