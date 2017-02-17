package com.laifu.module.entity;

public class CartItem {
	private Product product;
	private int count;// 购买商品的数量
	private double subtotal;// 购物项的小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 小计自动计算
	public double getSubtotal() {
		return count * product.getProduct_price()
				* product.getProduct_discount() / 10;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
