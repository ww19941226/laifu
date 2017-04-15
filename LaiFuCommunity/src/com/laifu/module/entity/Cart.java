package com.laifu.module.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {
	// 购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	// 购物总计
	private double total;

	// 商品的总件数
	private int totalcount;

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	public double getTotal() {
		BigDecimal b = new BigDecimal(total);
		total = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return total;
	}

	/* 将商品加入购物车 */
	public void addCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		if (map.containsKey(product_id)) {
			CartItem cartItem2 = map.get(product_id);
			cartItem2.setCount(cartItem2.getCount() + cartItem.getCount());
		} else {
			map.put(product_id, cartItem);
		}
		total += cartItem.getSubtotal();
		totalcount += cartItem.getCount();
	}

	public CartItem addOneCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		CartItem cartItem2 = map.get(product_id);
		cartItem2.setCount(cartItem2.getCount() + cartItem.getCount());
		total += cartItem.getSubtotal();
		totalcount += cartItem.getCount();
		return cartItem2;
	}

	public void addCountCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		removeCart(product_id);
		map.put(product_id, cartItem);
		total += cartItem.getSubtotal();
		totalcount += cartItem.getCount();
	}

	public CartItem subCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		CartItem cartItem2 = map.get(product_id);
		cartItem2.setCount(cartItem2.getCount() - cartItem.getCount());
		total -= cartItem.getSubtotal();
		totalcount -= cartItem.getCount();
		return cartItem2;
	}

	/* 将商品从购物车中移除 */
	public void removeCart(Integer product_id) {
		CartItem cartItem = map.remove(product_id);
		total -= cartItem.getSubtotal();
		totalcount -= cartItem.getCount();
	}

	/* 清空购物车 */
	public void cleanCart() {
		map.clear();
		total = 0;
		totalcount = 0;
	}

}
