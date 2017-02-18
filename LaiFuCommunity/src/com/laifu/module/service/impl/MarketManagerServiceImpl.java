package com.laifu.module.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CategoryDao;
import com.laifu.module.dao.CategorySecondDao;
import com.laifu.module.dao.ProductDao;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.Product;
import com.laifu.module.service.MarketManagerService;

@Service("MarketManagerService")
public class MarketManagerServiceImpl extends BaseServiceImpl<Product, Integer>
		implements MarketManagerService {
	private CategoryDao categoryDao;
	private CategorySecondDao categorySecondDao;
	private ProductDao productDao;

	@Resource(name = "ProductDao")
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Resource(name = "CategorySecondDao")
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	@Resource(name = "CategoryDao")
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void setBaseDaoImpl(IBaseDao<Product, Integer> baseDao) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Category> getAllCategories() throws Exception {
		// TODO Auto-generated method stub

		return categoryDao.getAllCategories();
	}

	@Override
	public List<CategorySecond> getAllCategorySecond(int id) throws Exception {
		// TODO Auto-generated method stub
		return categorySecondDao.getAllCategorySecond(id);
	}

	@Override
	public List<Product> getHotJinkou() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getHotJinkou();
	}

	@Override
	public List<Product> getNewJinkou() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getNewJinkou();
	}

	@Override
	public List<Product> getcuxiaoProducts() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getcuxiaoProducts();
	}

	@Override
	public List<Product> getremaiProducts() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getremaiProducts();
	}

	@Override
	public List<Product> getxinpinProducts() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getxinpinProducts();
	}

	@Override
	public List<Product> getjinkouProducts() throws Exception {
		// TODO Auto-generated method stub
		return productDao.getjinkouProducts();
	}

	@Override
	public Page<Product> cuxiao_listAll(String hql, int pn, int i) {
		Integer count = productDao.countAll("select count(*)" + hql);
		List<Product> products = productDao.listAll(hql, pn, i);
		return PageUtil.getPage(count, pn, products, i);
	}

	@Override
	public Page<Product> xinpin_listAll(String hql, int pn, int i)
			throws Exception {
		// TODO Auto-generated method stub
		Integer countInteger = productDao.countAll("select count(*)" + hql);
		List<Product> products = productDao.listAll(hql, pn, i);
		return PageUtil.getPage(countInteger, pn, products, i);
	}

	@Override
	public Page<Product> getSearchProducts(String hqlString, int pn, int i) {
		// TODO Auto-generated method stub
		Integer count = productDao.countAll("select count(*)" + hqlString);
		List<Product> products = productDao.listAll(hqlString, pn, i);
		return PageUtil.getPage(count, pn, products, i);
	}

	@Override
	public Product finByPid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return productDao.findByPid(id);
	}

}
