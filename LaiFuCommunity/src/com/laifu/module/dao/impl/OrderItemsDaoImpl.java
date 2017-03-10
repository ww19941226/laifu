package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.OrderItemsDao;
import com.laifu.module.entity.OrderItems;

@Repository("OrderItemsDao")
public class OrderItemsDaoImpl extends BaseDaoImpl<OrderItems, Integer>
		implements OrderItemsDao {

	@Override
	public List<OrderItems> getAllOrderItems(int id) throws Exception {
		return getSession().createQuery("from OrderItems where order.order_id="+id).list();
	}

}
