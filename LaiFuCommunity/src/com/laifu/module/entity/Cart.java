package com.laifu.module.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	// 购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	// 购物总计
	private double total;

	public Collection<CartItem> getCartItems() {
		return map.values();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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
	}

	public void removeCart(Integer product_id) {
		CartItem cartItem = map.remove(product_id);
		total -= cartItem.getSubtotal();

	}

	public void cleanCart() {
		map.clear();
		total = 0;
	}

}
