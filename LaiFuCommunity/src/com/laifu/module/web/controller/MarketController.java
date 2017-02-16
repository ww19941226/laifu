package com.laifu.module.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laifu.common.pagination.Page;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.Product;
import com.laifu.module.service.MarketManagerService;

@Controller
public class MarketController {
	@Resource(name = "MarketManagerService")
	private MarketManagerService marketManagerService;

	/*
	 * 跳转到小区超市页面
	 */
	@RequestMapping(value = "/market/index", method = { RequestMethod.GET })
	private String gotoMarket(HttpServletRequest request) throws Exception {
		List<Category> categoryList = marketManagerService.getAllCategories();
		List<Product> cuxiaopProducts = marketManagerService
				.getcuxiaoProducts();
		List<Product> remaiProducts = marketManagerService.getremaiProducts();
		List<Product> xinpinProducts = marketManagerService.getxinpinProducts();
		List<Product> jinkouProducts = marketManagerService.getjinkouProducts();
		request.getSession().setAttribute("categoryList", categoryList);
		request.setAttribute("cxProducts", cuxiaopProducts);
		request.setAttribute("rmProducts", remaiProducts);
		request.setAttribute("xpProducts", xinpinProducts);
		request.setAttribute("jkProducts", jinkouProducts);
		return "market/index";
	}

	/*
	 * 跳转到促销页面
	 */
	@RequestMapping(value = "/market/cuxiao", method = { RequestMethod.GET })
	private String gotoMarketcuxiao(HttpServletRequest request)
			throws Exception {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Product  where is_discount=1 order by product_discount";
		Page<Product> page = marketManagerService.cuxiao_listAll(hql, pn, 5);
		request.setAttribute("page", page);

		return "market/cuxiao";
	}

	/*
	 * 跳转到进口页面
	 */
	@RequestMapping(value = "/market/jinkou", method = { RequestMethod.GET })
	private String gotojinkou(HttpServletRequest request) throws Exception {
		List<Product> pList = marketManagerService.getHotJinkou();
		List<Product> pList2 = marketManagerService.getNewJinkou();
		System.out.println(pList2);
		request.setAttribute("jinkouHotList", pList);
		request.setAttribute("jinkoNewList", pList2);
		return "market/jinkou";
	}

	/*
	 * 跳转到新品页面
	 */
	@RequestMapping(value = "/market/newProducts", method = { RequestMethod.GET })
	private String gotoMarketnew(HttpServletRequest request) throws Exception {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Product order by product_creattime desc";
		Page<Product> page = marketManagerService.xinpin_listAll(hql, pn, 10);
		request.setAttribute("page", page);
		return "market/xinpin";
	}

	/*
	 * 搜索 功能页面
	 */
	@RequestMapping(value = "/market/search/", method = { RequestMethod.POST })
	private String gotosearch(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String searchText)
			throws Exception {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hqlString = "from Product where product_name like '%"
				+ searchText + "%' order by product_id,product_deal desc ";
		Page<Product> page = marketManagerService.getSearchProducts(hqlString,
				pn, 10);
		request.setAttribute("page", page);
		System.out.println(page.getItems());
		return "market/jinkou";

	}

	/****************** 帮助中心的页面跳转 **********************************************************************************/
	/*
	 * 跳转到帮助中心
	 */
	@RequestMapping(value = "/market/helpCenter", method = { RequestMethod.GET })
	private String gotohelpCenter(HttpServletRequest request) throws Exception {
		return "market/help";
	}

	/*
	 * 跳转到购物流程
	 */
	@RequestMapping(value = "/market/gouwuliucheng", method = { RequestMethod.GET })
	private String gotogouwuliucheng() throws Exception {
		return "market/gouwuliucheng";
	}

	/*
	 * 跳转到积分制度
	 */
	@RequestMapping(value = "/market/jifenzhidu", method = { RequestMethod.GET })
	private String gotojifenzhidu() throws Exception {
		return "market/jifenzhidu";
	}

	/*
	 * 跳转到办理退款
	 */
	@RequestMapping(value = "/market/tuikuan", method = { RequestMethod.GET })
	private String gototuikuan() throws Exception {
		return "market/tuikuan";
	}

	/*
	 * 跳转到支付方式
	 */
	@RequestMapping(value = "/market/zhifu", method = { RequestMethod.GET })
	private String gotozhifu() throws Exception {
		return "market/zhifufangshi";
	}

	/*
	 * 跳转到送货费用
	 */
	@RequestMapping(value = "/market/yunfei", method = { RequestMethod.GET })
	private String gotoyunfei() throws Exception {
		return "market/yunfei";
	}

	/*
	 * 跳转到配送时间
	 */
	@RequestMapping(value = "/market/shijian", method = { RequestMethod.GET })
	private String gotoshijian() throws Exception {
		return "market/shijian";
	}
}
/******************************************************************************************************/
