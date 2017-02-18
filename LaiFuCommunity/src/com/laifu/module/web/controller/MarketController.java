package com.laifu.module.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laifu.common.pagination.Page;
import com.laifu.module.entity.Cart;
import com.laifu.module.entity.CartItem;
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

	/*************************** 购物车功能 ********************************************************************************/
	/* 添加到购物车 */
	@RequestMapping(value = "/market/addCart", method = { RequestMethod.POST })
	private void addCart(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer product_id,
			Map<String, Object> map) throws Exception {
		Product product = marketManagerService.finByPid(product_id);
		CartItem cartItem = new CartItem();
		cartItem.setCount(1);
		cartItem.setProduct(product);
		Cart cart = getCart(request);
		cart.addCart(cartItem);
		response.getWriter().println("1");
		System.out.println(cart);
	}

	/* 移除购物车的购物项 */
	@RequestMapping(value = "/market/removeCart/{product_id}", method = { RequestMethod.GET })
	private void removeCart(HttpServletRequest request,
			@PathVariable Integer product_id, HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.removeCart(product_id);
		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* 清空购物车 */
	@RequestMapping(value = "/market/cleanCart", method = { RequestMethod.GET })
	private void cleanCart(HttpServletRequest request,
			HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.cleanCart();
		try {
			response.getWriter().write("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * 
	 * @return
	 */
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}

	/*************************** 购物车功能 ********************************************************************************/

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
