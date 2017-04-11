package com.laifu.module.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.exception.UserException;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.dao.HouseDao;
import com.laifu.module.dao.UserDao;
import com.laifu.module.entity.House;
import com.laifu.module.entity.User;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.UserVo;

@Service("UserService")
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements
		UserService {

	@Autowired
	@Qualifier("CommunityDao")
	private CommunityDao communityDao;
	@Autowired
	@Qualifier("HouseDao")
	private HouseDao houseDao;

	private UserDao userDao;

	@Autowired
	@Qualifier("UserDao")
	@Override
	public void setBaseDaoImpl(IBaseDao<User, Integer> baseDao) {
		// TODO Auto-generated method stub
		this.baseDao = baseDao;
		this.userDao = (UserDao) baseDao;
	}

	/*********************************************************************************************/

	/**
	 * 通过手机判断用户是否存在
	 * 
	 * @param user_account
	 *            手机号
	 * @return 用户是否存在
	 * @throws Exception
	 */
	@Override
	public boolean exit(String user_account) throws Exception {
		return userDao.exit(user_account);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param user_account
	 *            用户手机号
	 * @return 短信验证码
	 * @throws Exception
	 */
	@Override
	public String SendCode(String user_account) throws Exception {
		String code = "";
		for (int i = 0; i < 6; i++) {
			code = code + (int) (Math.random() * 10);
		}
		System.out.println(user_account + " " + code); // 便捷入口
		// BSONSms.requestSms(user_account, code); //发送短信
		return code;
	}

	/**
	 * 用户注册服务
	 * 
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void register(User user) throws Exception {

		user.setUser_registertime(new Date());
		user.setUser_nickname(user.getUser_account());
		user.setUser_head("/upload/default.jpg");
		user.setUser_type(1);
		user.setUser_landstate(0);
		user.setUser_community(1);
		user.setUser_checkstate(0);
		userDao.save(user);
	}

	/**
	 * 用户登录服务
	 * 
	 * @param user
	 * @param type
	 * @return 用户基本信息
	 * @throws Exception
	 */
	@Override
	public User login(User preUser, int type) throws Exception {
		User user = userDao.getByAccount(preUser.getUser_account());
		if (user != null
				&& preUser.getUser_password().equals(user.getUser_password())) {
			if (type == 1 && user.getUser_type() == 1)
				return user;
			if (type == 0 && user.getUser_type() != 1)
				return user;
		}
		return null;
	}

	/**
	 * 通过用户名获取用户信息
	 * 
	 * @param user_account
	 * @return 用户信息
	 * @throws Exception
	 */
	@Override
	public User getByAccount(String user_account) throws Exception {
		return userDao.getByAccount(user_account);
	}

	/**
	 * 通过用户手机号获取用户的全部信息
	 * 
	 * @param user_account
	 *            用户手机号
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserVo getUserVoByAccount(String user_account) throws Exception {
		return userDao.getUserVoByAccount(user_account);
	}

	public UserVo getUserVoById(int user_id) throws Exception {
		return userDao.getUserVoById(user_id);
	}

	/**
	 * 完善个人信息
	 * 
	 * @param preUser
	 * @param newUser
	 * @return
	 * @throws Exception
	 */
	@Override
	public User complete(User preUser, User newUser, int user_house)
			throws Exception {
		if (newUser.getUser_card() != null) {
			preUser.setUser_card(newUser.getUser_card());
			preUser.setUser_sex(this.getSex(newUser.getUser_card()));
			preUser.setUser_age(this.getAge(newUser.getUser_card()));
		}
		if (newUser.getUser_realname() != null)
			preUser.setUser_realname(newUser.getUser_realname());
		if (newUser.getUser_nickname() != null)
			preUser.setUser_nickname(newUser.getUser_nickname());
		if (newUser.getUser_email() != null)
			preUser.setUser_email(newUser.getUser_email());
		if (newUser.getUser_head() != null)
			preUser.setUser_head(newUser.getUser_head());
		preUser.setUser_house(user_house);
		System.out.println(preUser.toString());
		userDao.update(preUser);
		return preUser;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void updatePassword(User user) throws Exception {
		String hql = "update User set user_password='"
				+ user.getUser_password() + "' where user_id="
				+ user.getUser_id();
		userDao.update(hql);
	}

	/**
	 * 通过账号修改用户密码
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updatePasswordByAccount(User user) throws Exception {
		String hql = "update User set user_password='"
				+ user.getUser_password() + "' where user_account='"
				+ user.getUser_account() + "'";
		userDao.update(hql);
	}

	/**
	 * 更改昵称
	 * 
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void updateNickName(User user) throws Exception {
		String hql = "update User set user_nickname='"
				+ user.getUser_nickname() + "' where user_id="
				+ user.getUser_id();
		userDao.update(hql);
	}

	/**
	 * 通过hql语句修改数据表
	 * 
	 * @param hql
	 * @throws Exception
	 */
	@Override
	public void update(String hql) throws Exception {
		userDao.update(hql);
	}

	/*********************************************************************************************/

	/**
	 * android登录接口
	 * 
	 * @param user
	 *            要登录的android用户
	 * @return UserVo 查询到的用户信息
	 * @throws Exception
	 */
	@Override
	public UserVo android_login(User user) throws Exception {

		UserVo vo = userDao.getUserVoByAccount(user.getUser_account());
		if (vo != null
				&& user.getUser_password().equals(
						vo.getUser().getUser_password())
				&& vo.getUsertype().getUsertype_id() == 1) {
			return vo;
		}
		return null;
	}

	/*********************************************************************************************/

	/**
	 * 注销登录服务
	 * 
	 * @param request
	 *            请求对象
	 * @throws UserException
	 *             抛出用户异常
	 */
	@Override
	public void logout(HttpServletRequest request) throws UserException {
		try {

			HttpSession session = request.getSession();
			if (session.getAttribute("admin") != null)
				session.removeAttribute("admin");

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

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
	@Override
	public void updateInfor(User user, HttpServletRequest request)
			throws UserException {
		try {
			UserVo vo = (UserVo) request.getSession().getAttribute("admin");
			vo.getUser().setUser_nickname(user.getUser_nickname());
			vo.getUser().setUser_email(user.getUser_email());
			userDao.update(vo.getUser());
			request.getSession().setAttribute("admin", vo);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	/**
	 * 获取所有的系统管理员
	 * 
	 * @return List<User> 所有的系统管理员
	 * @throws UserException
	 */
	public List<User> getSysadmin() throws UserException {
		return userDao.getList("from User where user_type=4");
	}

	/**
	 * 获取所有的小区管理员
	 * 
	 * @return List<User> 所有的小区管理员
	 * @throws UserException
	 */
	public List<User> getComadmin() throws UserException {
		return userDao.getList("from User where user_type=3");
	}

	public void ChangeUserInfor(User user) throws UserException {
		userDao.update("update User set user_password='"
				+ user.getUser_password() + "',user_nickname='"
				+ user.getUser_nickname() + "',user_email='"
				+ user.getUser_email() + "' where user_id=" + user.getUser_id());
	}

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
	@Override
	public void android_updatePassword(User user) throws UserException {
		try {
			userDao.update("update User set user_password='"
					+ user.getUser_password() + "'where user_id="
					+ user.getUser_id());

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	/**
	 * 通过id获取用户
	 * 
	 * @param user_id
	 *            用户id
	 * @return 返回查询的用户
	 * @throws UserException
	 */
	@Override
	public User getUser(int user_id) throws UserException {
		try {
			return userDao.get(user_id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            要添加的User
	 */
	@Override
	public void add(User user) throws UserException {
		try {
			user.setUser_sex(getSex(user.getUser_card()));
			user.setUser_age(getAge(user.getUser_card()));
			userDao.save(user);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	@Override
	public void delete(User user) throws UserException {
		try {
			userDao.deleteObject(user);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	/*******************************************************************************************************/

	@Override
	public List<User> getList() {
		return userDao.getList();
	}

	@Override
	public int getAge(String user_card) {
		// 440921 1994 01 27 00 1 4
		Calendar cal = Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH) + 1;
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		int year = Integer.parseInt(user_card.substring(6, 10));
		int month = Integer.parseInt(user_card.substring(10, 12));
		int day = Integer.parseInt(user_card.substring(12, 14));

		int age = nowYear - year;
		if (nowMonth < month || (nowMonth == month && nowDay < day))
			age--;

		return age;
	}

	@Override
	public String getSex(String user_card) {
		if ((user_card.charAt(16)) % 2 == 1)
			return "男";
		else
			return "女";
	}

	@Override
	public Long getAllByAccount(String user_account) {
		// TODO Auto-generated method stub
		return userDao.getAllByAccount(user_account);

	}

	@Override
	public boolean getCommunity(String communityName) {
		// TODO Auto-generated method stub
		return communityDao.getCommunity(communityName);
	}

	@Override
	public int getHouseid(String houseFloorNum, String houseRoomNum,
			Integer users_id) {
		// TODO Auto-generated method stub
		return houseDao.getHouseid(houseFloorNum, houseRoomNum, users_id);
	}

	@Override
	public void updateHouseUserIdByHouseId(Integer houserid, Integer houseUserId) {
		// TODO Auto-generated method stub
		userDao.updateHouseUserIdByHouseId(houserid, houseUserId);

	}

	@Override
	public House getHouse(Integer id) {
		// TODO Auto-generated method stub

		return houseDao.get(id);
	}

	@Override
	public void addHouse(House house) {
		// TODO Auto-generated method stub
		houseDao.save(house);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);

	}

}
