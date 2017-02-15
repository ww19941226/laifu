package com.laifu.module.service;

import java.util.List;

import com.laifu.common.exception.UserException;
import com.laifu.common.pagination.Page;
import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.User;

public interface CommunityManageService extends IBaseService<User, Integer> {
	
	
	public List<Complains> getAllComplains() throws Exception;
	
	public Complains getComplains(int complains_id) throws Exception;
	
	public void replyComplains(Complains complains) throws Exception;
	
	public List<Community> getAllCommunity() throws Exception;
	
	public Community getCommunity(int community_id) throws Exception;
	
	public void updateCommunity(Community community) throws Exception;
	
	public void deleteCommunity(int community_id);
	
	public void addUser(User user);
	
	public void deleteUser(int user_id);
	
	public void changeUser(User user) throws UserException;
	
	/******************************************************************************************/

	/**
	 * 得到业主列表
	 * @return 业主列表
	 */
	public List<User> getUserList(Integer userType);
	
	/**
	 * 删除业主
	 * @param user_id 业主id
	 */
	public void deleteUser(Integer user_id);
	
	/**
	 * 得到业主具体信息
	 * @param user_id 业主id
	 */
	public User getUser(Integer user_id);
	
	/**
	 * 修改业主信息
	 * @param user_id 业主id
	 */
	public void updateUser(User user);
	
	/**
	 * 注册小区
	 * @param community 小区对象
	 */
	public void addCommunity(Community community);
	
	/*************************************************************************************************/
	
	public Page<Complains> complains_listAll(String hql, int pn, int pageSize);
	
	public Page<Community> community_listAll(String hql, int pn, int pageSize);
	
}
