package com.laifu.module.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @ClassName: 产地表
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jians
 * @date 2016-12-27 下午8:50:28
 * 
 */
@Entity
@Table(name = "tb_categorysecond")
public class CategorySecond implements Serializable {
	private static final long serialVersionUID = 4453958691451821312L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categorysecond_id", nullable = false)
	private int categorysecond_id;
	private String categorysecond_name;
	/* private int categorysecond_categoryId; */
	// 所属一级分类.存的是一级分类的对象.
	@JoinColumn(name = "categorysecond_categoryId")
	@ManyToOne
	private Category category;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_categorysecondId")
	private Set<Product> products = new HashSet<Product>();

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCategorysecond_id() {
		return categorysecond_id;
	}

	public void setCategorysecond_id(int categorysecond_id) {
		this.categorysecond_id = categorysecond_id;
	}

	public String getCategorysecond_name() {
		return categorysecond_name;
	}

	public void setCategorysecond_name(String categorysecond_name) {
		this.categorysecond_name = categorysecond_name;
	}

	@Override
	public String toString() {
		return "CategorySecond [categorysecond_id=" + categorysecond_id
				+ ", categorysecond_name=" + categorysecond_name
				+ ", category=" + category.getCategory_id() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categorysecond_id;
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
		CategorySecond other = (CategorySecond) obj;
		if (categorysecond_id != other.categorysecond_id)
			return false;
		return true;
	}

}
