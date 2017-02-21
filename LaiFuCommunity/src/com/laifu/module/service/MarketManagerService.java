package com.laifu.module.service;

import java.util.List;

import com.laifu.common.pagination.Page;
import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.Product;

public interface MarketManagerService extends IBaseService<Product, Integer> {

	public List<Category> getAllCategories() throws Exception;

	public List<CategorySecond> getAllCategorySecond(int id) throws Exception;

	public List<Product> getHotJinkou() throws Exception;

	public List<Product> getNewJinkou() throws Exception;

	public List<Product> getcuxiaoProducts() throws Exception;

	public List<Product> getremaiProducts() throws Exception;

	public List<Product> getxinpinProducts() throws Exception;

	public List<Product> getjinkouProducts() throws Exception;

	public Page<Product> cuxiao_listAll(String hql, int pn, int i)
			throws Exception;

	public Page<Product> xinpin_listAll(String hql, int pn, int i)
			throws Exception;

	public Page<Product> getSearchProducts(String hqlString, int pn, int i)
			throws Exception;

	public Product finByPid(Integer id) throws Exception;

	public Page<Product> findByCid(String hqlString, int pn, int i, int id)
			throws Exception;

	public Page<Product> findByCsid(String hql, Integer pn, int i,
			Integer categorySecond_id) throws Exception;

	public Category findBycid(Integer categoty_id) throws Exception;

	public CategorySecond findBycsid(Integer categorysecond_id)
			throws Exception;

}
