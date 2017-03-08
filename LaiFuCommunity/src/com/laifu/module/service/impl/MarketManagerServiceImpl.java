package com.laifu.module.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laifu.common.dao.IBaseDao;
import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CategoryDao;
import com.laifu.module.dao.CategorySecondDao;
import com.laifu.module.dao.OrderDao;
import com.laifu.module.dao.OrderItemsDao;
import com.laifu.module.dao.ProductDao;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.Order;
import com.laifu.module.entity.OrderItems;
import com.laifu.module.entity.Product;
import com.laifu.module.entity.ReturnData;
import com.laifu.module.service.MarketManagerService;

@Service("MarketManagerService")
public class MarketManagerServiceImpl extends BaseServiceImpl<Product, Integer>
		implements MarketManagerService {
	private CategorySecondDao categorySecondDao;
	private ProductDao productDao;
	private OrderDao orderDao;
	private OrderItemsDao orderItemsDao;
	private CategoryDao categoryDao;

	@Resource(name = "OrderItemsDao")
	public void setOrderItemsDao(OrderItemsDao orderItemsDao) {
		this.orderItemsDao = orderItemsDao;
	}

	@Resource(name = "OrderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

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
	
	
	/*一级分类后台管理相关功能*/
	/*获取列表*/
	@Override
	public ReturnData getAllCategoriesForManage() throws Exception {
		ReturnData returnData = new ReturnData();
		List<Category> list=categoryDao.getAllCategories();
		Category category = new Category();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			category.setCategory_id(list.get(i).getCategory_id());
			category.setCategory_name(list.get(i).getCategory_name());
			jsonArray.add(category);
		}
		returnData.setReturnData(jsonArray);
		//returnData.setReturnData(list);
		returnData.setReturnResult(200);
		return returnData;
	}
	
	/*添加一级分类*/
	@Override
	public ReturnData addCategory(String category_name) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Category category = new Category();
			category.setCategory_name(category_name);
			categoryDao.addCategory(category);
			//returnData.setReturnData(list);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			// TODO: handle exception
			returnData.setReturnResult(300);
			returnData.setReturnDetail("添加失败！");
		}
		return returnData;
	}
	
	//获取一条一级分类数据
	@Override
	public ReturnData getCategory(int id) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Category category = categoryDao.findBycid(id);
			Category returnCategory = new Category();
			returnCategory.setCategory_id(category.getCategory_id());
			returnCategory.setCategory_name(category.getCategory_name());
			returnData.setReturnData(returnCategory);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			// TODO: handle exception
			returnData.setReturnResult(300);
			returnData.setReturnDetail("读取失败！");
		}
		return returnData;
	}
	
	//更新一条一级分类数据
	@Override
	public ReturnData updateCategory(int id,String category_name) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Category category = findBycid(id);
			category.setCategory_name(category_name);
			categoryDao.updateCategory(category);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			// TODO: handle exception
			returnData.setReturnResult(300);
			returnData.setReturnDetail("更新失败！");
		}
		return returnData;
	}
	
	/*二级分类后台管理系统相关*/
	/*获取二级分类列表*/
	@Override
	public ReturnData getAllCategorySecondForManage() throws Exception {
		ReturnData returnData = new ReturnData();
		List<CategorySecond> list=categorySecondDao.getAllCategorySecondForManage();
		JSONArray jsonArray = new JSONArray();
		try {
			for(int i=0;i<list.size();i++){
				JSONObject jsonObject = new JSONObject();
				JSONObject jsonOne = new JSONObject();
				jsonOne.put("category_id", list.get(i).getCategory().getCategory_id());
				jsonOne.put("category_name", list.get(i).getCategory().getCategory_name());
				jsonObject.put("categorysecond_id", list.get(i).getCategorysecond_id());
				jsonObject.put("categorysecond_name", list.get(i).getCategorysecond_name());
				jsonObject.put("category", jsonOne);
				jsonArray.add(jsonObject);
			}
			returnData.setReturnData(jsonArray);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("读取数据失败，请重试");
		}
		return returnData;
	}
	
	@Override
	public ReturnData addCategorySecond(String categorysecond_name,
			int category_id) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Category category = categoryDao.findBycid(category_id);
			CategorySecond categorySecond = new CategorySecond();
			categorySecond.setCategory(category);
			categorySecond.setCategorysecond_name(categorysecond_name);
			categorySecondDao.addCategory(categorySecond);
			//returnData.setReturnData(list);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			// TODO: handle exception
			returnData.setReturnResult(300);
			returnData.setReturnDetail("添加失败！");
		}
		return returnData;
	}
	
	@Override
	public ReturnData getCategorySecond(int categorysecond_id) throws Exception {
		ReturnData returnData = new ReturnData();
		CategorySecond categorySecond = categorySecondDao.findBycsid(categorysecond_id);
		try {
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonOne = new JSONObject();
			jsonOne.put("category_id", categorySecond.getCategory().getCategory_id());
			jsonOne.put("category_name", categorySecond.getCategory().getCategory_name());
			jsonObject.put("categorysecond_id", categorySecond.getCategorysecond_id());
			jsonObject.put("categorysecond_name", categorySecond.getCategorysecond_name());
			jsonObject.put("category", jsonOne);
			returnData.setReturnData(jsonObject);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("读取数据失败，请重试");
		}
		return returnData;
	}
	
	@Override
	public ReturnData updateCategorySecond(int id,String categorysecond_name,int category_id) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			CategorySecond categorySecond = categorySecondDao.get(id);
			Category category = categoryDao.get(category_id);
			categorySecond.setCategorysecond_name(categorysecond_name);
			categorySecondDao.updateCategory(categorySecond);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			// TODO: handle exception
			returnData.setReturnResult(300);
			returnData.setReturnDetail("更新失败！");
		}
		return returnData;
	}
	
	/*二级分类后台管理系统相关完*/
	
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
	public Page<Product> getSearchjinkouProducts(String hql, int pn, int i)
			throws Exception {
		// TODO Auto-generated method stub
		Integer count = productDao.countAll("select count(*)" + hql);
		List<Product> products = productDao.listAll(hql, pn, i);
		return PageUtil.getPage(count, pn, products, i);
	}

	@Override
	public Product finByPid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return productDao.findByPid(id);
	}

	@Override
	public Page<Product> findByCid(String hqlString, int pn, int i, int id)
			throws Exception {
		// TODO Auto-generated method stub
		String hqlString2 = "select count(*) from Product  p where p.categorySecond.category.category_id = "
				+ id;
		Integer count = productDao.countAll(hqlString2);
		List<Product> products = productDao.listAll(hqlString, pn, i);
		return PageUtil.getPage(count, pn, products, i);
	}

	@Override
	public Page<Product> findByCsid(String hql, Integer pn, int i,
			Integer categorySecond_id) throws Exception {
		// TODO Auto-generated method stub
		String hql1 = "select count(*) from Product p where p.categorySecond.categorysecond_id="
				+ categorySecond_id;
		Integer count = productDao.countAll(hql1);
		List<Product> products = productDao.listAll(hql, pn, i);

		return PageUtil.getPage(count, pn, products, i);
	}

	@Override
	public Page<Order> findByUid(String hql, Integer pn, int i)
			throws Exception {
		// TODO Auto-generated method stub
		Integer count = orderDao.countAll("select count(*)" + hql);
		List<Order> orders = orderDao.listAll(hql, pn, i);
		return PageUtil.getPage(count, pn, orders, i);
	}

	@Override
	public Category findBycid(Integer categoty_id) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.findBycid(categoty_id);
	}

	@Override
	public CategorySecond findBycsid(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return categorySecondDao.findBycsid(id);
	}

	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	@Override
	public int countOrderAll() {
		// TODO Auto-generated method stub
		return orderDao.countAll();
	}

	@Override
	public void saveOrderItems(OrderItems orderItems) {
		// TODO Auto-generated method stub
		orderItemsDao.save(orderItems);
	}

	@Override
	public Order findByOid(Integer order_id) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.get(order_id);
	}

	@Override
	public void deleteOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.deleteObject(order);

	}

	

}
