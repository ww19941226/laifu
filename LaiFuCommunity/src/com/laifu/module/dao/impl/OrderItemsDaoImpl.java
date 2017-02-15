package com.laifu.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.OrderItemsDao;
import com.laifu.module.entity.OrderItems;

@Repository("OrderItemsDao")
public class OrderItemsDaoImpl extends BaseDaoImpl<OrderItems, Integer>
		implements OrderItemsDao {

}
