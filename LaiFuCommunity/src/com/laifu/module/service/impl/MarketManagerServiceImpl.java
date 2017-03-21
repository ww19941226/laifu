package com.laifu.module.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.laifu.module.entity.PageData;
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
	
	
	/*订单管理后台管理系统相关*/
	@Override
	public ReturnData getOrderList(PageData pageData) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			String hql = "from Order o order by o.order_id desc";
			List<Order> list = orderDao.listAll(hql, pageData.getPageNo(), 20);
			JSONArray jsonArray = new JSONArray();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = "";
			for(int i=0;i<list.size();i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", list.get(i).getOrder_id());
				dateString = formatter.format(list.get(i).getOrder_creattime());
				jsonObject.put("order_creattime", dateString);
				jsonObject.put("order_money", list.get(i).getOrder_money());
				jsonObject.put("order_state", list.get(i).getOrder_state());
				jsonObject.put("order_receivename", list.get(i).getOrder_receivename());
				jsonObject.put("order_phone", list.get(i).getOrder_phone());
				jsonObject.put("order_address", list.get(i).getOrder_address());
				jsonArray.add(jsonObject);
			}
			pageData.setRecordCount(countOrderAll());
			pageData.setData(jsonArray);
			returnData.setReturnData(pageData);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("数据获取失败");
		}
		return returnData;
	}

	@Override
	public ReturnData getOrder(int id) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			List<OrderItems> list = orderItemsDao.getAllOrderItems(id);
			JSONArray jsonArray = new JSONArray();
			for(int i=0;i<list.size();i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("prouduct_photo1", list.get(i).getProduct().getProduct_photo1());
				jsonObject.put("prouduct_name", list.get(i).getProduct().getProduct_name());
				jsonObject.put("prouduct_price", list.get(i).getProduct().getProduct_price()*list.get(i).getProduct().getProduct_discount()*0.1);
				jsonObject.put("orderItems_count", list.get(i).getOrderItems_count());
				jsonObject.put("orderItems_subtotal", list.get(i).getOrderItems_subtotal());
				jsonArray.add(jsonObject);
			}
			returnData.setReturnData(jsonArray);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("获取数据失败");
		}
		return returnData;
	}

	@Override
	public ReturnData updateOrder(int id) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Order order = orderDao.get(id);
			order.setOrder_state(order.getOrder_state()+1);
			orderDao.update(order);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("更新数据失败");
		}
		return returnData;
	}
	/*订单管理后台管理系统相关完*/
	
	/*商品管理后台管理系统相关*/
	@Override
	public ReturnData getProductList(PageData pageData) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			String hql = "from Product order by product_id desc";
			List<Product> list = productDao.listAll(hql, pageData.getPageNo(), 20);
			JSONArray jsonArray = new JSONArray();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = "";
			for(int i=0;i<list.size();i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", list.get(i).getProduct_id());
				dateString = formatter.format(list.get(i).getProduct_creattime());
				jsonObject.put("prouduct_creattime", dateString);
				jsonObject.put("prouduct_name", list.get(i).getProduct_name());
				jsonObject.put("prouduct_price", list.get(i).getProduct_price());
				jsonObject.put("number", list.get(i).getNumber());
				jsonObject.put("product_place", list.get(i).getProduct_place());
				if(list.get(i).getIs_imported() == 0){
					jsonObject.put("is_imported", "否");
				}else{
					jsonObject.put("is_imported", "是");
				}
				jsonObject.put("prouduct_discount", list.get(i).getProduct_discount());
				jsonObject.put("prouduct_deal", list.get(i).getProduct_deal());
				jsonObject.put("product_category_name", list.get(i).getCategorySecond().getCategory().getCategory_name());
				jsonObject.put("product_categorySecond_name", list.get(i).getCategorySecond().getCategorysecond_name());
				jsonArray.add(jsonObject);
			}
			pageData.setRecordCount(countProductAll());
			pageData.setData(jsonArray);
			returnData.setReturnData(pageData);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("数据获取失败");
		}
		return returnData;
	}
	
	@Override
	public ReturnData saveProduct(Product product) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			productDao.addProduct(product);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("添加商品失败");
		}
		return returnData;
	}
	
	@Override
	public ReturnData removeProduct(Integer[] ids) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			for(int i=0;i<ids.length;i++){
				productDao.removeProduct(ids[i]);
			}
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("删除商品失败");
		}
		return returnData;
	}
	
	@Override
	public ReturnData updateProduct(Integer id,String prouduct_name_add,Double prouduct_price_add,Integer number_add,
			String product_place_add,String is_imported_add,Integer category_id_add,Integer categorysecond_id_add,
			Integer prouduct_discount_add,String photo_lujing_add) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Product findProduct = productDao.findByPid(id);
			findProduct.setProduct_name(prouduct_name_add);
			findProduct.setProduct_price(prouduct_price_add);
			findProduct.setNumber(number_add);
			findProduct.setProduct_place(product_place_add);
			if(is_imported_add.equals("是")){
				findProduct.setIs_imported(1);
			}else{
				findProduct.setIs_imported(0);
			}
			
			CategorySecond categorySecond = findBycsid(categorysecond_id_add);
			findProduct.setCategorySecond(categorySecond);
			findProduct.setProduct_discount(prouduct_discount_add);
			if(prouduct_discount_add == 10){
				findProduct.setIs_discount(0);
			}
			else {
				findProduct.setIs_discount(1);
			}
			findProduct.setProduct_creattime(new Date());
			findProduct.setProduct_photo1(photo_lujing_add);
			productDao.updateProduct(findProduct);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("更新数据失败");
		}
		return returnData;
	}
	/*商品管理后台管理系统相关完*/
	
	/*获取二级分类列表*/
	@Override
	public ReturnData getAllCategorySecondForManageSp(int category_id) throws Exception {
		ReturnData returnData = new ReturnData();
		List<CategorySecond> list=categorySecondDao.getAllCategorySecondForManageSp(category_id);
		JSONArray jsonArray = new JSONArray();
		try {
			for(int i=0;i<list.size();i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("categorysecond_id", list.get(i).getCategorysecond_id());
				jsonObject.put("categorysecond_name", list.get(i).getCategorysecond_name());
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
	public int countProductAll() {
		// TODO Auto-generated method stub
		return productDao.countAll();
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
