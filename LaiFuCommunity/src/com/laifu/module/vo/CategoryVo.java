package com.laifu.module.vo;

import com.laifu.module.entity.Category;
import com.laifu.module.entity.CategorySecond;

public class CategoryVo {
	private Category category;
	private CategorySecond categorySecond;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CategorySecond getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
}
