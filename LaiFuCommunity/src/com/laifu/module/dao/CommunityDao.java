package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Community;

/**
 * 
 * @author Raindrops
 * 
 */
public interface CommunityDao extends IBaseDao<Community, Integer> {

	/**
	 * 根据hql更新小区
	 * 
	 * @param hql
	 *            hql语句
	 * @throws Exception
	 */
	public void update(String hql) throws Exception;

	/**
	 * 通过hql获取小区
	 * 
	 * @param hql
	 *            hql语句
	 * @return List<Community> 小区列表
	 * @throws Exception
	 */
	public List<Community> get(String hql) throws Exception;

	/************************************************************************************************/

	/**
	 * 注册小区
	 * 
	 * @param community
	 *            小区对象
	 */
	public void addCommunity(Community community);

	/**
	 * 根据id查询小区信息
	 */
	public Community getCommunity(Integer id);

	/**
	 * 根据小区名字查询小区是否存在
	 */
	public boolean getCommunity(String communityName);
}

