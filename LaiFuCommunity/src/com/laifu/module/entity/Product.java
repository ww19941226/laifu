package com.laifu.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @ClassName: 商品表
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jians
 * @date 2016-12-27 下午8:26:42
 * 
 */
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 4453958691451821312L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", nullable = false)
	private int product_id; // 商品id
	private String product_name;// //商品名称
	private double product_price;// 商品价格
	private String product_desc;// 商品描述
	private String product_photo1;// 商品图片
	private String product_photo2;
	private String product_photo3;
	private String product_photo4;
	private String product_photo5;
	private String product_place;// 商品产地
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getProduct_place() {
		return product_place;
	}

	public void setProduct_place(String product_place) {
		this.product_place = product_place;
	}

	private int is_imported;// 商品是否进口
	private int is_discount;// 商品是否打折
	private Date product_creattime;// 商品创建时间
	private double product_discount;// 商品打折数
	private int product_deal;// 商品成交量

	@JoinColumn(name = "product_categorysecondId")
	@ManyToOne
	private CategorySecond categorySecond;

	public CategorySecond getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public String getProduct_photo1() {
		return product_photo1;
	}

	public void setProduct_photo1(String product_photo1) {
		this.product_photo1 = product_photo1;
	}

	public String getProduct_photo2() {
		return product_photo2;
	}

	public void setProduct_photo2(String product_photo2) {
		this.product_photo2 = product_photo2;
	}

	public String getProduct_photo3() {
		return product_photo3;
	}

	public void setProduct_photo3(String product_photo3) {
		this.product_photo3 = product_photo3;
	}

	public String getProduct_photo4() {
		return product_photo4;
	}

	public void setProduct_photo4(String product_photo4) {
		this.product_photo4 = product_photo4;
	}

	public String getProduct_photo5() {
		return product_photo5;
	}

	public void setProduct_photo5(String product_photo5) {
		this.product_photo5 = product_photo5;
	}

	public int getIs_imported() {
		return is_imported;
	}

	public void setIs_imported(int is_imported) {
		this.is_imported = is_imported;
	}

	public int getIs_discount() {
		return is_discount;
	}

	public void setIs_discount(int is_discount) {
		this.is_discount = is_discount;
	}

	public Date getProduct_creattime() {
		return product_creattime;
	}

	public void setProduct_creattime(Date product_creattime) {
		this.product_creattime = product_creattime;
	}

	public double getProduct_discount() {
		return product_discount;
	}

	public void setProduct_discount(double product_discount) {
		this.product_discount = product_discount;
	}

	public int getProduct_deal() {
		return product_deal;
	}

	public void setProduct_deal(int product_deal) {
		this.product_deal = product_deal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + product_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (product_id != other.product_id)
			return false;
		return true;
	}

}
