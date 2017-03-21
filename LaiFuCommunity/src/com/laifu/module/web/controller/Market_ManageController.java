package com.laifu.module.web.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.utils.UploadPicture;
import com.laifu.module.dao.CategorySecondDao;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.PageData;
import com.laifu.module.entity.Product;
import com.laifu.module.entity.ReturnData;
import com.laifu.module.entity.User;
import com.laifu.module.service.MarketManagerService;

@Controller
public class Market_ManageController {
	@Resource(name = "MarketManagerService")
	private MarketManagerService marketManagerService;

	/* 后台一级分类相关接口 */

	// 获取一级分类列表
	@RequestMapping(value = "/marketManage/categories/getList")
	@ResponseBody
	public ReturnData getList() throws Exception {
		return marketManagerService.getAllCategoriesForManage();
	}

	/* 添加一级分类 */
	@RequestMapping(value = "/marketManage/categories/add", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData addCategories(String category_name) throws Exception {
		return marketManagerService.addCategory(category_name);
	}
	
	/* 获取单条一级分类数据 */
	@RequestMapping(value = "/marketManage/categories/get", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData getCategories(int id) throws Exception {
		return marketManagerService.getCategory(id);
	}
	
	/* 更新单条一级分类数据 */
	@RequestMapping(value = "/marketManage/categories/update", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData updateCategories(int id,String category_name) throws Exception {
		return marketManagerService.updateCategory(id,category_name);
	}
	
	/*后台一级分类相关接口完*/

	
	/*后台二级分类相关接口*/
	/*获取二级分类列表*/
	@RequestMapping(value = "/marketManage/categorySecond/getList")
	@ResponseBody
	public ReturnData getListForSecond() throws Exception {
		return marketManagerService.getAllCategorySecondForManage();
	}
	
	/* 添加二级分类 */
	@RequestMapping(value = "/marketManage/categorySecond/add", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData addCategorySecond(String categorysecond_name,int category_id) throws Exception {
		return marketManagerService.addCategorySecond(categorysecond_name, category_id);
	}
	 /*更新*/
	@RequestMapping(value = "/marketManage/categorySecond/update", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData updateCategorySecond(int id,String categorysecond_name,int category_id) throws Exception {
		return marketManagerService.updateCategorySecond(id,categorysecond_name, category_id);
	}
	
	/* 获取单条数据*/
	@RequestMapping(value = "/marketManage/categorySecond/get", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData getCategorySecond(int id) throws Exception {
		return marketManagerService.getCategorySecond(id);
	}
	/*后台二级分类相关接口完*/
	
	/*后台订单管理相关接口*/
	/*获取订单管理列表*/
	@RequestMapping(value = "/marketManage/order/getList", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData getOrderList(@RequestParam(value = "page", defaultValue = "1", required = false) int page) throws Exception {
		PageData pageData = new PageData();
		pageData.setPageNo(page);
		return marketManagerService.getOrderList(pageData);
	}
	
	/* 获取一个订单的详情 */
	@RequestMapping(value = "/marketManage/order/get", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData getOrder(int id) throws Exception {
		return marketManagerService.getOrder(id);
	}
	 /*接单发货等操作*/
	@RequestMapping(value = "/marketManage/order/update", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData updateOrder(int id) throws Exception {
		return marketManagerService.updateOrder(id);
	}
	/*后台订单管理相关接口完*/
	
	
	/*后台商品管理相关接口*/
	/*获取商品管理列表*/
	@RequestMapping(value = "/marketManage/product/getList", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData getProductList(@RequestParam(value = "page", defaultValue = "1", required = false) int page) throws Exception {
		PageData pageData = new PageData();
		pageData.setPageNo(page);
		return marketManagerService.getProductList(pageData);
	}
	
	/* 添加商品数据 */
	@RequestMapping(value = "/marketManage/product/add", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData addProduct(String prouduct_name_add,Double prouduct_price_add,Integer number_add,
			String product_place_add,String is_imported_add,Integer category_id_add,Integer categorysecond_id_add,
			Integer prouduct_discount_add,String photo_lujing_add) throws Exception {
		ReturnData returnData = new ReturnData();
		Product product = new Product();
		product.setProduct_name(prouduct_name_add);
		product.setProduct_price(prouduct_price_add);
		product.setNumber(number_add);
		product.setProduct_place(product_place_add);
		if(is_imported_add.equals("是")){
			product.setIs_imported(1);
		}else{
			product.setIs_imported(0);
		}
		
		CategorySecond categorySecond = marketManagerService.findBycsid(categorysecond_id_add);
		product.setCategorySecond(categorySecond);
		product.setProduct_discount(prouduct_discount_add);
		if(prouduct_discount_add == 10){
			product.setIs_discount(0);
		}
		else {
			product.setIs_discount(1);
		}
		product.setProduct_creattime(new Date());
		product.setProduct_photo1(photo_lujing_add);
		returnData = marketManagerService.saveProduct(product);
		return returnData;
	}
	
	/**
	 * 上传商品图品
	 */
	@RequestMapping(value = "/marketManage/uploadPicture", method = { RequestMethod.POST, RequestMethod.GET })
	public void uploadPicture(HttpServletResponse response, HttpServletRequest request,
			@RequestParam MultipartFile file) {
		try {
			String imagepath = UploadPicture.uploadHead(request, file,"product_photo.png");
			response.getWriter().print(imagepath);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 /*更新商品数据*/
	@RequestMapping(value = "/marketManage/product/update", method = { RequestMethod.POST})
	@ResponseBody
	public ReturnData updateProduct(Integer id,String prouduct_name_add,Double prouduct_price_add,Integer number_add,
			String product_place_add,String is_imported_add,Integer category_id_add,Integer categorysecond_id_add,
			Integer prouduct_discount_add,String photo_lujing_add) throws Exception {
		return marketManagerService.updateProduct(id, prouduct_name_add, prouduct_price_add, number_add, product_place_add, is_imported_add, category_id_add, categorysecond_id_add, prouduct_discount_add, photo_lujing_add);
	}
	
	 /*更新商品数据*/
		@RequestMapping(value = "/marketManage/product/remove", method = { RequestMethod.POST})
		@ResponseBody
		public ReturnData removeProduct(Integer[] ids) throws Exception {
			return marketManagerService.removeProduct(ids);
		}
	
	/*获取二级分类列表*/
	@RequestMapping(value = "/marketManage/categorySecond/getListForSp")
	@ResponseBody
	public ReturnData getCategorySecondListForSp(int category_id) throws Exception {
		return marketManagerService.getAllCategorySecondForManageSp(category_id);
	}
	
	/* 获取单条数据*/
	@RequestMapping(value = "/marketManage/product/get", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData getProduct(int id) throws Exception {
		ReturnData returnData = new ReturnData();
		try {
			Product product = marketManagerService.finByPid(id);
			
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonOne = new JSONObject();
			jsonObject.put("product_id", product.getProduct_id());
			jsonObject.put("prouduct_name", product.getProduct_name());
			jsonObject.put("prouduct_price", product.getProduct_price());
			jsonObject.put("number", product.getNumber());
			jsonObject.put("product_place", product.getProduct_place());
			jsonObject.put("is_imported", product.getIs_imported());
			jsonOne.put("category_id", product.getCategorySecond().getCategory().getCategory_id());
			jsonOne.put("category_name", product.getCategorySecond().getCategory().getCategory_name());
			jsonObject.put("category", jsonOne);
			jsonOne.clear();
			jsonOne.put("categorySecond_id", product.getCategorySecond().getCategorysecond_id());
			jsonOne.put("categorySecond_name", product.getCategorySecond().getCategorysecond_name());
			jsonObject.put("categorySecond", jsonOne);
			jsonOne.clear();
			jsonObject.put("prouduct_discount", product.getProduct_discount());
			jsonObject.put("product_photo1", product.getProduct_photo1());
			returnData.setReturnData(jsonObject);
			returnData.setReturnResult(200);
		} catch (Exception e) {
			returnData.setReturnResult(300);
			returnData.setReturnDetail("获取商品数据失败！");
		}
		return returnData;
	}
	/*后台商品管理相关接口完*/
}
