package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.Product;

public interface ProductDao extends IBaseDao<Product, Integer> {

	public List<Product> getHotJinkou() throws Exception;

	public List<Product> getNewJinkou() throws Exception;

	public List<Product> getcuxiaoProducts() throws Exception;

	public List<Product> getremaiProducts() throws Exception;

	public List<Product> getxinpinProducts() throws Exception;

	public List<Product> getjinkouProducts() throws Exception;

	public Product findByPid(Integer id) throws Exception;
	
	void addProduct(Product product) throws Exception;
	
	void updateProduct(Product product) throws Exception;
	
	void removeProduct(Integer id) throws Exception;

}
