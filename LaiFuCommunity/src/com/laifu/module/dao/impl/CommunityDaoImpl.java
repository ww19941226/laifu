package com.laifu.module.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.entity.Community;

/**
 * 
 * @author Raindrops
 * 
 */
@Repository("CommunityDao")
public class CommunityDaoImpl extends BaseDaoImpl<Community, Integer> implements
		CommunityDao {

	/**
	 * 根据hql更新小区
	 * 
	 * @param hql
	 *            hql语句
	 * @throws Exception
	 */
	public void update(String hql) throws Exception {
		getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 通过hql获取小区
	 * 
	 * @param hql
	 *            hql语句
	 * @return List<Community> 小区列表
	 * @throws Exception
	 */
	public List<Community> get(String hql) throws Exception {
		return getSession().createQuery(hql).list();
	}

	/************************************************************************************************************/

	/**
	 * 注册小区
	 * 
	 * @param community
	 *            小区对象
	 */
	public void addCommunity(Community community) {
		Date registerTime = new Date();
		community.setCommunity_regitime(registerTime);
		save(community);
	}

	@Override
	public Community getCommunity(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Community where community_id=" + id;
		Query query = getSession().createQuery(hql);
		return (Community) query.uniqueResult();
	}

	@Override
	public boolean getCommunity(String communityName) {
		// TODO Auto-generated method stub
		String hqlString = "from Community where community_name="
				+ communityName;
		int i = getSession().createQuery(hqlString).list().size();
		if (i != 0) {
			return true;
		} else
			return false;

	}
}
