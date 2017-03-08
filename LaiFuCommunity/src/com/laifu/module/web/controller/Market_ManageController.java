package com.laifu.module.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laifu.module.entity.Category;
import com.laifu.module.entity.ReturnData;
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
}
