package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.CategorySecondDao;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;

@Repository("CategorySecondDao")
public class CategorySecondDaoImpl extends BaseDaoImpl<CategorySecond, Integer>
		implements CategorySecondDao {

	@Override
	public List<CategorySecond> getAllCategorySecond(int id) throws Exception {
		// TODO Auto-generated method stub

		String hql = "from CategorySecond cs where cs.categorysecond_categoryId="
				+ id;
		return getSession().createQuery(hql).list();
	}
	
	@Override
	public List<CategorySecond> getAllCategorySecondForManage() throws Exception {
		// TODO Auto-generated method stub

		String hql = "from CategorySecond";
		return getSession().createQuery(hql).list();
	}
	
	@Override
	public List<CategorySecond> getAllCategorySecondForManageSp(int category_id) throws Exception {
		// TODO Auto-generated method stub

		String hql = "from CategorySecond where category.category_id="+category_id;
		return getSession().createQuery(hql).list();
	}

	@Override
	public CategorySecond findBycsid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return get(id);
	}

	@Override
	public void addCategory(CategorySecond categorySecond) throws Exception {
		getSession().save(categorySecond);
	}

	@Override
	public void updateCategory(CategorySecond categorySecond) throws Exception {
		getSession().update(categorySecond);
	}

	@Override
	public void removeCategory(Integer id) throws Exception {
		// TODO Auto-generated method stub
		CategorySecond categorySecond = findBycsid(id);
		getSession().delete(categorySecond);
	}
}
