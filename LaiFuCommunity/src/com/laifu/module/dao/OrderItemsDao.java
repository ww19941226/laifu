package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.OrderItems;

public interface OrderItemsDao extends IBaseDao<OrderItems, Integer> {
	public List<OrderItems> getAllOrderItems(int id) throws Exception;
}
