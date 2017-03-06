package com.laifu.module.web.controller;

import java.util.List;

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
	
	/*一级分类相关接口*/
	
	//获取一级分类列表
	@RequestMapping(value = "/marketManage/categories/getList")
	@ResponseBody
	public ReturnData getList() throws Exception {
		return marketManagerService.getAllCategoriesForManage();
	}
	
	/*添加一级分类*/
	@RequestMapping(value = "/marketManage/categories/add", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnData addCategories(String category_name) throws Exception {
		return marketManagerService.addCategory(category_name);
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/marketManage/categorySecond/getList")
	@ResponseBody
	public ReturnData getListForSecond() throws Exception {
		return marketManagerService.getAllCategorySecondForManage();
	}
}
