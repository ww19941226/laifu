package com.laifu.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.TopictypeDao;
import com.laifu.module.entity.Topictype;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("TopictypeDao")
public class TopictypeDaoImpl extends BaseDaoImpl<Topictype, Integer> implements TopictypeDao{

}
