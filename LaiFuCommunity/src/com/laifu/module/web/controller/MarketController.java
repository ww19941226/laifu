package com.laifu.module.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.laifu.common.pagination.Page;
import com.laifu.module.entity.Cart;
import com.laifu.module.entity.CartItem;
import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;
import com.laifu.module.entity.Order;
import com.laifu.module.entity.OrderItems;
import com.laifu.module.entity.Product;
import com.laifu.module.entity.User;
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
	@RequestMapping(value = "/market/search", method = { RequestMethod.GET })
	private String gotosearch(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String searchText)
			throws Exception {
		searchText = java.net.URLDecoder.decode(
				request.getParameter("searchText"), "utf-8");
		searchText = new String(searchText.getBytes("ISO-8859-1"), "utf-8");
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hqlString = "from Product where product_name like '%"
				+ searchText + "%' order by product_id,product_deal desc ";
		Page<Product> page = marketManagerService.getSearchProducts(hqlString,
				pn, 10);
		request.getSession().setAttribute("searchWord", searchText);
		request.setAttribute("page", page);
		return "market/list";
	}

	/* 按产地查询进口商品 */
	@RequestMapping(value = "/market/searchjinkou", method = { RequestMethod.GET })
	private String gotosearchjinkou(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String search)
			throws Exception {
		search = URLDecoder.decode(request.getParameter("search"), "utf-8");
		search = new String(search.getBytes("ISO-8859-1"), "utf-8");
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Product where is_imported=1 and product_place='"
				+ search + "'order by product_deal desc";
		Page<Product> page = marketManagerService.getSearchjinkouProducts(hql,
				pn, 10);
		request.getSession().setAttribute("search", search);
		request.setAttribute("page", page);
		return "market/jinkoulist";
	}

	/* 根据一级分类查询商品 */
	@RequestMapping(value = "/market/findbycategory/{category_id}", method = { RequestMethod.GET })
	private String findProductByCid(
			@PathVariable("category_id") Integer categoty_id,
			HttpServletRequest request, Map<String, Object> map)
			throws Exception {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hqlString = "select p from Product p join p.categorySecond cs join cs.category c where c.category_id="
				+ categoty_id;
		Page<Product> page = marketManagerService.findByCid(hqlString, pn, 10,
				categoty_id);
		Category category = marketManagerService.findBycid(categoty_id);
		map.put("category_id", categoty_id);
		map.put("category_name", category.getCategory_name());
		request.setAttribute("page", page);
		return "market/Categorylist";
	}

	/* 根据二级分类查询商品 */
	@RequestMapping(value = "/market/findbycategorysecond/{categorysecond_id}", method = { RequestMethod.GET })
	private String findProductByCsid(
			@PathVariable("categorysecond_id") Integer categorysecond_id,
			HttpServletRequest request, Map<String, Object> map)
			throws Exception {
		Integer pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "select p  from Product p ,CategorySecond cs where p.categorySecond.categorysecond_id = cs.categorysecond_id and cs.categorysecond_id = "
				+ categorysecond_id;
		Page<Product> page = marketManagerService.findByCsid(hql, pn, 10,
				categorysecond_id);
		CategorySecond categorySecond = marketManagerService
				.findBycsid(categorysecond_id);
		map.put("categorysecond_id", categorysecond_id);
		map.put("categorysecond_name", categorySecond.getCategorysecond_name());
		request.setAttribute("page", page);
		return "market/CategorySecondlist";
	}

	/*********************************
	 * 订单功能
	 * 
	 * @throws Exception
	 **************************************************************/
	/* 查询我的所有订单* */
	@RequestMapping(value = "/market/myDingdan", method = { RequestMethod.GET })
	public String gotoMydingdan(HttpServletRequest request,@RequestParam Integer state) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Integer pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql;
		if(state==0){
			hql= "from Order o where o.user.user_id=" + user.getUser_id()
					+ "order by o.order_creattime desc";
		}
		else{
			hql = "from Order o where o.order_state="+state+" and o.user.user_id="
					+ user.getUser_id() + "order by o.order_creattime";
		}
		Page<Order> page = marketManagerService.findByUid(hql, pn, 10);
		request.setAttribute("page", page);
		request.setAttribute("dState", state);
		return "market/MyDingdan";
	}

	/* 查询我的未付款订单 */
	@RequestMapping(value = "/market/myDingdan/1", method = { RequestMethod.GET })
	public String gotoMyNoPaydingdan(HttpServletRequest request)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Integer pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Order o where o.order_state=1 and o.user.user_id="
				+ user.getUser_id() + "order by o.order_creattime";
		Page<Order> page = marketManagerService.findByUid(hql, pn, 10);
		return "market/mydingdan";
	}

	/* 查询已付款 待发货订单 */
	@RequestMapping(value = "/market/myDingdan/2", method = { RequestMethod.GET })
	public String gotoMyNoFaHuodingdan(HttpServletRequest request)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Integer pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Order o where o.order_state=2 and o.user.user_id="
				+ user.getUser_id() + "order by o.order_creattime";
		Page<Order> page = marketManagerService.findByUid(hql, pn, 10);
		return "market/mydingdan";
	}

	/* 查询已发货订单 */
	@RequestMapping(value = "/market/myDingdan/3", method = { RequestMethod.GET })
	public String gotoMyFaHuodingdan(HttpServletRequest request)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Integer pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Order o where o.order_state=3 and o.user.user_id="
				+ user.getUser_id() + "order by o.order_creattime";
		Page<Order> page = marketManagerService.findByUid(hql, pn, 10);
		return "market/mydingdan";
	}

	/* 查询已确认收货订单 */
	@RequestMapping(value = "/market/myDingdan/4", method = { RequestMethod.GET })
	public String gotoMyShouHuodingdan(HttpServletRequest request)
			throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Integer pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Order o where o.order_state=4 and o.user.user_id="
				+ user.getUser_id() + "order by o.order_creattime";
		Page<Order> page = marketManagerService.findByUid(hql, pn, 10);
		return "market/mydingdan";
	}

	/* 查询用户地址电话姓名等信息跳转到提交订单界面 */
	@RequestMapping(value = "/market/confirmDD", method = { RequestMethod.GET })
	public String gotoConfirmDingdan(HttpServletRequest request)
			throws Exception {
		if (((Cart) request.getSession().getAttribute("cart")).getTotalcount() == 0) {
			return "market/cart";
		}
		return "market/dingdan";
	}

	/* 根据订单id查询 */
	@RequestMapping(value = "/market/findByOid/{order_id}", method = { RequestMethod.GET })
	public String findByOid(HttpServletRequest request,
			@PathVariable("order_id") Integer order_id) throws Exception {
		Order order = marketManagerService.findByOid(order_id);
		request.setAttribute("order", order);
		return "market/mydingdan";
	}

	/* 跳转到支付页面 */
	@RequestMapping(value = "/market/saveOrder", method = { RequestMethod.POST })
	private String gotoSaveOrder(HttpServletRequest request, String realname,
			String phone, String address) throws Exception {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		User user = (User) request.getSession().getAttribute("user");
		if (cart.getCartItems().size() == 0) {
			return "redirect:myCart";
		}
		Order order = new Order();
		order.setOrder_id(getOrder_id());
		order.setOrder_money(cart.getTotal());
		order.setOrder_state(1);
		order.setOrder_creattime(new Date());
		order.setUser(user);
		Set<OrderItems> sets = new HashSet<OrderItems>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItems orderItems = new OrderItems();
			orderItems.setOrderItems_count(cartItem.getCount());
			orderItems.setOrderItems_subtotal(cartItem.getSubtotal());
			orderItems.setProduct(cartItem.getProduct());
			orderItems.setOrder(order);
			sets.add(orderItems);
			/* order.getOrderItems().add(orderItems); */
		}
		order.setOrderItems(sets);
		order.setOrder_address(address);
		order.setOrder_phone(phone);
		order.setOrder_receivename(realname);
		marketManagerService.saveOrder(order);
		cart.cleanCart();
		request.setAttribute("order", order);

		return "market/index";
	}

	public int getOrder_id() throws Exception {
		int count = marketManagerService.countOrderAll() + 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(format.format(new Date())) + count;
	}

	/***********************************************************************************************/

	/*************************** 购物车功能 ********************************************************************************/
	/* 跳转到购物车页面 */
	@RequestMapping(value = "/market/myCart", method = { RequestMethod.GET })
	private String myCart() {
		return "market/cart";
	}

	/* 添加到购物车 */
	@RequestMapping(value = "/market/addCart", method = { RequestMethod.POST })
	private void addCart(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer product_id)
			throws Exception {
		Product product = marketManagerService.finByPid(product_id);
		CartItem cartItem = new CartItem();
		cartItem.setCount(1);
		cartItem.setProduct(product);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addCart(cartItem);
		response.getWriter().println("1");
	}

	/* 购物车减一操作 */
	@RequestMapping(value = "/market/subCart", method = { RequestMethod.POST })
	private void subCart(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer product_id)
			throws Exception {
		Product product = marketManagerService.finByPid(product_id);
		CartItem cartItem = new CartItem();
		cartItem.setCount(1);
		cartItem.setProduct(product);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		CartItem cartItem2 = cart.subCart(cartItem);
		int totalCount = cart.getTotalcount();
		double total = cart.getTotal();
		Integer count = cartItem2.getCount();
		double subtotal = cartItem2.getSubtotal();
		Map map1 = new HashMap();
		map1.put("totalCount", totalCount);
		map1.put("total", total + "");
		map1.put("count", count);
		map1.put("subtotal", subtotal + "");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map1);
		response.getWriter().print(jsonArray.toString());
	}

	/* 购物车加一操作 */
	@RequestMapping(value = "/market/addOneCart", method = { RequestMethod.POST })
	private void addOneCart(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer product_id)
			throws Exception {
		Product product = marketManagerService.finByPid(product_id);
		System.out.println(product_id);
		CartItem cartItem = new CartItem();
		cartItem.setCount(1);
		cartItem.setProduct(product);
		cartItem.setCount(1);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		CartItem cartItem2 = cart.addOneCart(cartItem);
		int totalCount = cart.getTotalcount();
		double total = cart.getTotal();
		Integer count = cartItem2.getCount();
		double subtotal = cartItem2.getSubtotal();
		Map map1 = new HashMap();
		map1.put("totalCount", totalCount);
		map1.put("total", total + "");
		map1.put("count", count);
		map1.put("subtotal", subtotal + "");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map1);
		response.getWriter().print(jsonArray.toString());
	}

	/* 购物车数量填写操作 */
	@RequestMapping(value = "/market/addCountCart", method = { RequestMethod.POST })
	private void addCountCart(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer product_id,
			@RequestParam Integer count) throws Exception {
		Product product = marketManagerService.finByPid(product_id);
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(product);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.addCountCart(cartItem);
		int totalCount = cart.getTotalcount();
		double total = cart.getTotal();
		double subtotal = cartItem.getSubtotal();
		Map map1 = new HashMap();
		map1.put("totalCount", totalCount);
		map1.put("total", total + "");
		map1.put("subtotal", subtotal + "");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map1);
		response.getWriter().print(jsonArray.toString());
	}

	/* 移除购物车的购物项 */
	@RequestMapping(value = "/market/removeCart", method = { RequestMethod.POST })
	private void removeCart(HttpServletRequest request,
			@RequestParam Integer product_id, HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.removeCart(product_id);
		int totalCount = cart.getTotalcount();
		double total = cart.getTotal();
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("total", total + "");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map);
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* 清空购物车 */
	@RequestMapping(value = "/market/cleanCart", method = { RequestMethod.POST })
	private void cleanCart(HttpServletRequest request,
			HttpServletResponse response) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.cleanCart();
		int totalCount = cart.getTotalcount();
		double total = cart.getTotal();
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("total", total + "");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(map);
		try {
			response.getWriter().write(jsonArray.toString());
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
	/*
	 * private Cart getCart(HttpServletRequest request) { Cart cart = (Cart)
	 * request.getSession().getAttribute("cart"); if (cart == null) { cart = new
	 * Cart(); request.getSession().setAttribute("cart", cart); } return cart; }
	 */

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
