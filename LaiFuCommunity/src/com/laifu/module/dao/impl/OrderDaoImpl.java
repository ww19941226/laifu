package com.laifu.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.OrderDao;
import com.laifu.module.entity.Order;

@Repository("OrderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements
		OrderDao {

}
