package com.laifu.module.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.laifu.common.exception.UserException;
import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.House;
import com.laifu.module.entity.User;
import com.laifu.module.vo.UserVo;

public interface UserService extends IBaseService<User, Integer> {
	
	/**
	 * 通过手机判断用户是否存在
	 * 
	 * @param user_account 手机号
	 * @return 用户是否存在
	 * @throws Exception
	 */
	public boolean exit(String user_account) throws Exception;
	
	/**
	 * 发送短信验证码
	 * 
	 * @param user_account 用户手机号
	 * @return 短信验证码
	 * @throws Exception
	 */
	public String SendCode(String user_account) throws Exception;
	
	/**
	 * 用户注册服务
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void register(User user) throws Exception;
	
	/**
	 * 用户登录服务
	 * 
	 * @param user
	 * @param type
	 * @return 用户基本信息
	 * @throws Exception
	 */
	public User login(User user, int type) throws Exception;
	
	/**
	 * 通过用户名获取用户信息
	 * 
	 * @param user_account
	 * @return 用户信息
	 * @throws Exception
	 */
	public User getByAccount(String user_account) throws Exception;
	
	/**
	 * 通过用户手机号获取用户的全部信息
	 * 
	 * @param user_account 用户手机号
	 * @return
	 * @throws Exception
	 */
	public UserVo getUserVoByAccount(String user_account) throws Exception;
	
	
	/**
	 * 通过用户id获取用户的全部信息
	 * 
	 * @param user_account 用户手机号
	 * @return
	 * @throws Exception
	 */
	public UserVo getUserVoById(int user_id) throws Exception;
	
	/**
	 * 完善个人信息
	 * 
	 * @param preUser
	 * @param newUser
	 * @return
	 * @throws Exception
	 */
	public User complete(User preUser, User newUser, int user_house) throws Exception;
	
	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updatePassword(User user) throws Exception;
	
	/**
	 * 更改昵称
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updateNickName(User user) throws Exception;
	
	/**
	 * 通过账号修改用户密码
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updatePasswordByAccount(User user) throws Exception;
	
	
	/**
	 * 通过hql语句修改数据表
	 * 
	 * @param hql
	 * @throws Exception
	 */
	public void update(String hql) throws Exception;
	
	/******************************************************************************************************/
	
	/**
	 * android登录接口
	 * 
	 * @param user 要登录的android用户
	 * @return UserVo 查询到的用户信息
	 * @throws Exception
	 */
	public UserVo android_login(User user) throws Exception;
	
	
	/********************************************************************************************************/
	

	

	/**
	 * 注销登录服务
	 * 
	 * @param request
	 *            请求对象
	 * @throws UserException
	 *             抛出用户异常
	 */
	public void logout(HttpServletRequest request) throws UserException;

	

	/**
	 * 更新用户个人信息
	 * 
	 * @param user
	 *            用户信息
	 * @param request
	 *            请求对象
	 * @throws UserException
	 *             抛出用户异常
	 */
	public void updateInfor(User user, HttpServletRequest request)
			throws UserException;

	/**
	 * 获取所有的系统管理员
	 * 
	 * @return List<User> 所有的系统管理员
	 * @throws UserException
	 */
	public List<User> getSysadmin() throws UserException;

	/**
	 * 获取所有的小区管理员
	 * 
	 * @return List<User> 所有的小区管理员
	 * @throws UserException
	 */
	public List<User> getComadmin() throws UserException;

	/**
	 * 通过id获取用户
	 * 
	 * @param user_id
	 *            用户id
	 * @return User 返回查询的用户
	 * @throws UserException
	 */
	public User getUser(int user_id) throws UserException;

	public void ChangeUserInfor(User user) throws UserException;

	/************************************************************************************************************/

	

	/**
	 * android更改密码的服务
	 * 
	 * @param user
	 *            要修改的对象
	 * @return
	 * @throws UserException
	 *             抛出用户异常
	 */
	public void android_updatePassword(User user) throws UserException;

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            要添加的User
	 */
	public void add(User user) throws UserException;

	

	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	public void delete(User user) throws UserException;

	/************************************************************************************************************/

	public List<User> getList();

	

	public int getAge(String user_card);

	public String getSex(String user_sex);

	public Long getAllByAccount(String user_account);

	public boolean getCommunity(String communityName);

	/**
	 * 根据楼号，房号查询房屋id
	 */
	public int getHouseid(String houseFloorNum, String houseRoomNum,
			Integer user_id);

	/**
	 * 
	 * @Title: updateHouseUserIdByHouseId
	 * @Description: 根据房子id修改用户id
	 * @param @param houserid
	 * @param @param houseUserId 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void updateHouseUserIdByHouseId(Integer houserid, Integer houseUserId);

	/**
	 * 根据用户的房屋id查询房屋信息
	 */
	public House getHouse(Integer id);

	/**
	 * 增加房屋信息
	 */
	public void addHouse(House house);

	public void updateUser(User user);

}
