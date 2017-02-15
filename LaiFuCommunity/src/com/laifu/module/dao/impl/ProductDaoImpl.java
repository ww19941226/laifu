package com.laifu.module.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.ProductDao;
import com.laifu.module.entity.Product;

@Repository("ProductDao")
public class ProductDaoImpl extends BaseDaoImpl<Product, Integer> implements
		ProductDao {

	@Override
	public List<Product> getHotJinkou() throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Product where  is_imported=1 order by product_deal Desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(20);

		return query.list();
	}

	@Override
	public List<Product> getNewJinkou() throws Exception {
		String hql = "from Product where  is_imported=1 order by product_creattime desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(20);

		return query.list();
	}

	@Override
	public List<Product> getcuxiaoProducts() throws Exception {
		// TODO Auto-generated method stub
		String hqlString = "from Product where is_discount=1 order by product_discount ,product_creattime desc ";
		Query query = getSession().createQuery(hqlString);
		query.setFirstResult(0);
		query.setMaxResults(8);
		return query.list();
	}

	@Override
	public List<Product> getremaiProducts() throws Exception {
		// TODO Auto-generated method stub
		String hqlString = "from Product order by product_deal desc,product_creattime desc ";
		Query query = getSession().createQuery(hqlString);
		query.setFirstResult(0);
		query.setMaxResults(8);
		return query.list();
	}

	@Override
	public List<Product> getxinpinProducts() throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Product order by product_creattime desc ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(8);
		return query.list();
	}

	@Override
	public List<Product> getjinkouProducts() throws Exception {
		String hql = "from Product where  is_imported=1 order by product_creattime ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(8);
		return query.list();
	}
}
