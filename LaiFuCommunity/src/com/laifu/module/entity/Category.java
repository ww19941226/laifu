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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 
 * @ClassName: 分类表
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author jians
 * @date 2016-12-27 下午8:56:02
 * 
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name="
				+ category_name + "]";
	}

	private static final long serialVersionUID = 4453958691451821312L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id", nullable = false)
	private int category_id;
	private String category_name;
	// 一级分类中存放二级分类的集合:
	@OrderBy(value = "categorysecond_id")
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "categorysecond_categoryId")
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();

	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}

	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + category_id;
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
		Category other = (Category) obj;
		if (category_id != other.category_id)
			return false;
		return true;
	}

}
