package com.laifu.module.entity;

import java.io.Serializable;
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
		return total;
	}

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

	public void addCountCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		map.put(product_id, cartItem);
		total += cartItem.getSubtotal();
		totalcount += cartItem.getCount();
	}

	public void subCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		CartItem cartItem2 = map.get(product_id);
		cartItem.setCount(cartItem2.getCount() - 1);
		total -= cartItem.getSubtotal();
		totalcount -= cartItem.getCount();
	}

	public void addOneCart(CartItem cartItem) {
		Integer product_id = cartItem.getProduct().getProduct_id();
		CartItem cartItem2 = map.get(product_id);
		cartItem.setCount(cartItem2.getCount() + 1);
		total += cartItem.getSubtotal();
		totalcount += cartItem.getCount();
	}

	public void removeCart(Integer product_id) {
		CartItem cartItem = map.remove(product_id);
		total -= cartItem.getSubtotal();
		totalcount -= cartItem.getCount();
	}

	public void cleanCart() {
		map.clear();
		total = 0;
		totalcount = 0;
	}

}
