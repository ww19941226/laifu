package com.laifu.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.NotifyvisitDao;
import com.laifu.module.entity.Notify;
import com.laifu.module.entity.Notifyvisit;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("NotifyvisitDao")
public class NotifyvisitDaoImpl extends BaseDaoImpl<Notifyvisit, Integer> implements NotifyvisitDao{
}
