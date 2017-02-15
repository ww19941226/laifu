package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.exception.UserException;
import com.laifu.module.entity.User;
import com.laifu.module.vo.UserVo;

/**
 * 
 * @author Raindrops
 * 
 */
public interface UserDao extends IBaseDao<User, Integer> {
	
	
	/**
	 * 通过手机号判断用户是否存在
	 * 
	 * @param user_account 用户的手机号
	 * @return 用户是否存在
	 * @throws Exception
	 */
	public boolean exit(String user_account) throws Exception;
	
	/**
	 * 通过账号获取用户的所有信息
	 * 
	 * @param user_account 用户账号
	 * @return	用户的所有信息
	 * @throws Exception
	 */
	public UserVo getUserVoByAccount(String user_account) throws Exception;
	
	/**
	 * 通过id获取用户的所有信息
	 * 
	 * @param user_account 用户账号
	 * @return	用户的所有信息
	 * @throws Exception
	 */
	public UserVo getUserVoById(int user_id) throws Exception;
	
	/**
	 * 通过用户手机来获取用户的基本信息
	 * 
	 * @param user_account 用户手机号
	 * @return 用户的基本信息
	 * @throws Exception
	 */
	public User getByAccount(String user_account) throws Exception;
	
	/*****************************************************************************************************************/
	
	/**
	 * 通过hql语句进行修改User
	 * 
	 * @param hql
	 *            要执行修改的hql
	 * @throws UserException
	 *             抛出用户异常
	 */
	public void update(String hql) throws UserException;

	/**
	 * 通过hql来获取用户列表
	 * 
	 * @param hql
	 * @return
	 * @throws UserException
	 */
	public List<User> getList(String hql) throws UserException;

	/***************************************************************************************************/

	public List<User> getList();

	/**
	 * 根据手机号码查询总条数
	 */
	public Long getAllByAccount(String user_account);

	/**
	 * 根据传过来的用户类型得到用户列表
	 * 
	 * @return 用户列表
	 */
	public List<User> getUserList(Integer userType);

	/**
	 * 根据房子id修改用户id
	 */

	public void updateHouseUserIdByHouseId(Integer houserid, Integer houseUserId);

	public void updateUser(User user);
}
