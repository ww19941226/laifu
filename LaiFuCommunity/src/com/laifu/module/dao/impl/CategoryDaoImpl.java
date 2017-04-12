package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.CategoryDao;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.Product;

@Repository("CategoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer> implements
		CategoryDao {
/*	@Autowired
	protected HibernateTemplate ht;*/

	@Override
	public List<Category> getAllCategories() throws Exception {
		// TODO Auto-generated method stub

		return getSession().createQuery("from Category").list();
	}
	
	@Override
	public void addCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		getSession().save(category);
		
	}
	
	/*@Override
	public List<Category> getAllCategoriesForManage() throws Exception {
		// TODO Auto-generated method stub
		return (List<Category>)ht.find("from Category");
	}*/
	
	@Override
	public Category findBycid(Integer categoty_id) throws Exception {
		// TODO Auto-generated method stub
		return get(categoty_id);
	}

	@Override
	public void updateCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		getSession().update(category);
	}
	
	@Override
	public void removeCategory(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Category category = findBycid(id);
		getSession().delete(category);
	}

}
