package com.laifu.module.service;

import java.util.List;

import com.laifu.common.pagination.Page;
import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.User;

/**
 * 系统管理service层
 * @author zepeng
 *
 */

public interface SystemManageService extends IBaseService<Complains, Integer>{
	
	/**
	 * 获取所有的小区信息
	 * 
	 * @return List<Community> 所有小区的信息
	 * @throws Exception
	 */
	public List<Community> getAllCommunity() throws Exception;
	
	/**
	 * 通过Id查找小区详细信息
	 * 
	 * @return Community 小区对象
	 * @throws Exception
	 */
	public Community getCommunity(int id) throws Exception;
	
	/**
	 * 获取未审核的小区列表
	 * 
	 * @return List<Community> 未审核小区列表 
	 * @throws Exception
	 */
	public List<Community> getUncheckCommunity() throws Exception;
	
	/**
	 * 更新小区信息功能
	 * 
	 * @param community 要更新的小区信息
	 * @throws Exception
	 */
	public void updateCommunity(Community community) throws Exception;
	
	/**
	 * 删除小区
	 * @param id 删除的小区id
	 * @throws Exception
	 */
	public void deleteCommunity(int id) throws Exception;
	
	
	/**
	 * 审核小区
	 * 
	 * @param community_id 审核的小区id
	 * @throws Exception
	 */
	public void checkCommunity(int community_id, int community_checkstate) throws Exception;
	
	
	/**
	 * 返回所有未审核的列表页面
	 * 
	 * @throws Exception
	 */
	public List<Community> getFailCommunity() throws Exception;
	
	/**
	 * 获取所有的投诉建议的列表
	 * 
	 * @return List<Complains>投诉信息列表
	 * @throws Exception
	 */
	public List<Complains> getAllComplains() throws Exception;
	
	/**
	 * 通过id获取投诉建议的详细信息
	 * 
	 * @return Complains 返回投诉建议的详细信息
	 * @throws Exception
	 */
	public Complains getComplains(int id) throws Exception;
	
	/**
	 * 回复投诉建议
	 * 
	 * @param complains 需要回复的投诉建议
	 * @throws Exception
	 */
	public void replyComplains(Complains complains) throws Exception;
	
	//public void get(Complains complains) throws Exception;
	
	/****************************************************************************************************/
	
	/**
	 * 添加管理员
	 * @param sysadmin 
	 * @author zepeng
	 */
	public void addAdmin(User admin);
	
	
	
	
	
	
	
	
	/**
	 * 处理建议
	 * @param  complains
	 * @author zepeng
	 */
	public void dealComplains(Complains complain);
	
	
	/**
	 * 得到所有建议信息
	 */
	
	

	
	/**
	 * 判断用户是否存在
	 */
	public boolean userHasExsit(User user);
	
	
	/******************************************************************************************/
	
	
	public Page<Community> community_listAll(String hql, int pn, int pageSize);
	
	public Page<Complains> complains_listAll(String hql, int pn, int pageSize);
	
}
