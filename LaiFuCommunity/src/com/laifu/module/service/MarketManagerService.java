package com.laifu.module.service;

import java.util.List;

import com.laifu.common.pagination.Page;
import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.Order;
import com.laifu.module.entity.OrderItems;
import com.laifu.module.entity.PageData;
import com.laifu.module.entity.Product;
import com.laifu.module.entity.ReturnData;

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

	public Page<Product> getSearchjinkouProducts(String hql, int pn, int i)
			throws Exception;

	public void saveOrder(Order order) throws Exception;

	public int countOrderAll();
	
	public int countProductAll();

	public void saveOrderItems(OrderItems orderItems);

	public Page<Order> findByUid(String hql, Integer pn, int i)
			throws Exception;

	public Order findByOid(Integer order_id) throws Exception;

	public void deleteOrder(Order order) throws Exception;

	ReturnData getAllCategoriesForManage() throws Exception;

	ReturnData addCategory(String category_name) throws Exception;
	
	ReturnData getCategory(int category_id) throws Exception;
	
	ReturnData updateCategory(int category_id,String category_name) throws Exception;

	ReturnData getAllCategorySecondForManage() throws Exception;
	
	ReturnData getAllCategorySecondForManageSp(int category_id) throws Exception;
	
	ReturnData addCategorySecond(String categorysecond_name,int category_id) throws Exception;
	
	ReturnData getCategorySecond(int categorysecond_id) throws Exception;
	
	ReturnData updateCategorySecond(int id,String categorysecond_name,int category_id) throws Exception;
	
	ReturnData getOrderList(PageData pageData) throws Exception;
	
	ReturnData getOrder(int id) throws Exception;
	
	ReturnData updateOrder(int id) throws Exception;
	
	ReturnData getProductList(PageData pageData) throws Exception;
	
	ReturnData saveProduct(Product product) throws Exception;
	
	ReturnData removeProduct(Integer[] ids) throws Exception;
	
	ReturnData updateProduct(Integer id,String prouduct_name_add,Double prouduct_price_add,Integer number_add,
			String product_place_add,String is_imported_add,Integer category_id_add,Integer categorysecond_id_add,
			Integer prouduct_discount_add,String photo_lujing_add) throws Exception;
}
