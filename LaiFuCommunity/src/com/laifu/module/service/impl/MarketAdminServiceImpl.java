package com.laifu.module.service.impl;

import org.springframework.stereotype.Service;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.entity.Product;
import com.laifu.module.service.MarketAdminService;

@Service("MarketAdminService")
public class MarketAdminServiceImpl extends BaseServiceImpl<Product, Integer>
		implements MarketAdminService {

	@Override
	public void setBaseDaoImpl(IBaseDao<Product, Integer> baseDao) {
		// TODO Auto-generated method stub

	}

}
