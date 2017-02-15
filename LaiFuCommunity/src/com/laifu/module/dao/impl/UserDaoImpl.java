package com.laifu.module.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.common.exception.UserException;
import com.laifu.module.dao.UserDao;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.House;
import com.laifu.module.entity.User;
import com.laifu.module.entity.Usertype;
import com.laifu.module.vo.UserVo;

/**
 * UserDaoImpl
 * 
 * @author Raindrops
 * @version 2016/9/6
 */
@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {
	
	/**
	 * 通过手机号判断用户是否存在
	 * 
	 * @param user_account 用户的手机号
	 * @return 用户是否存在
	 * @throws Exception
	 */
	@Override
	public boolean exit(String user_account) throws Exception {
		String hqlString = "select count(*) from User where user_account=" + user_account;
		return ((Long)getSession().createQuery(hqlString).uniqueResult()).equals(Long.parseLong("1"));
	}
	
	/**
	 * 通过账号获取用户的所有信息
	 * 
	 * @param user_account 用户账号
	 * @return	用户的所有信息
	 * @throws Exception
	 */
	@Override
	public UserVo getUserVoByAccount(String user_account) throws Exception {

		String hql = "from User as p, Usertype as q, Community as c, House as h where p.user_account = :user_account and p.user_type = q.usertype_id and c.community_id  = p.user_community and p.user_house = h.house_id";
		Object[] obj = (Object[]) getSession().createQuery(hql).setString("user_account", user_account).uniqueResult();
		if (obj == null) return null;

		UserVo vo = new UserVo();
		vo.setUser((User) obj[0]);
		vo.setUsertype((Usertype) obj[1]);
		vo.setCommunity((Community) obj[2]);
		vo.setHouse((House) obj[3]);

		return vo;
	}
	
	/**
	 * 通过id获取用户的所有信息
	 * 
	 * @param user_account 用户账号
	 * @return	用户的所有信息
	 * @throws Exception
	 */
	@Override
	public UserVo getUserVoById(int user_id) throws Exception {

		String hql = "from User as p, Usertype as q, Community as c, House as h where p.user_id = :user_id and p.user_type = q.usertype_id and c.community_id  = p.user_community and p.user_house = h.house_id";
		Object[] obj = (Object[]) getSession().createQuery(hql).setInteger("user_id", user_id).uniqueResult();
		if (obj == null) return null;

		UserVo vo = new UserVo();
		vo.setUser((User) obj[0]);
		vo.setUsertype((Usertype) obj[1]);
		vo.setCommunity((Community) obj[2]);
		vo.setHouse((House) obj[3]);

		return vo;
	}

	/**
	 * 通过用户手机来获取用户的基本信息
	 * 
	 * @param user_account 用户手机号
	 * @return 用户的基本信息
	 * @throws Exception
	 */
	@Override
	public User getByAccount(String user_account) throws Exception {
		String hql = "from User where user_account=:user_account";
		return (User) getSession().createQuery(hql).setString("user_account", user_account).uniqueResult();
	}
	
	/**********************************************************************************************/

	private static final String HQL = "from User";
	/**
	 * 通过hql语句进行修改User
	 * 
	 * @param hql
	 *            要执行修改的hql
	 * @throws UserException
	 *             抛出用户异常
	 */
	@Override
	public void update(String hql) throws UserException {
		try {
			getSession().createQuery(hql).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	public List<User> getList(String hql) throws UserException {
		try {
			return getSession().createQuery(hql).list();

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException();
		}
	}

	/*******************************************************************************************************/

	/**
	 * 获取所有用户的list
	 */
	@Override
	public List<User> getList() {

		return getSession().createQuery(HQL).list();
	}
	
	/**
	 * 根据传过来的用户类型得到用户列表
	 * 
	 * @return 用户列表
	 */
	public List<User> getUserList(Integer userType) {
		String hql = "from User where user_type = " + userType;
		Query query = getSession().createQuery(hql);
		return (List<User>) query.list();
	}

	@Override
	public Long getAllByAccount(String user_account) {
		// TODO Auto-generated method stub
		String hqlString = "select count(*) from User where user_account="
				+ user_account;
		Query query = getSession().createQuery(hqlString);
		long total = (Long) query.uniqueResult();
		return total;
	}

	@Override
	public void updateHouseUserIdByHouseId(Integer houserid, Integer houseUserId) {
		// TODO Auto-generated method stub
		String hqlString = "update House set user_id=" + houseUserId
				+ "where house_id=" + houserid;
		getSession().createQuery(hqlString).executeUpdate();
		System.out.println(123);

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String hqlString = "update User " +
				"set user_account='" + user.getUser_account() 
				+ "',user_card='" + user.getUser_card() 
				+ "',user_sex='" + user.getUser_sex() 
				+ "',user_age='" + user.getUser_age() 
				+ "',user_realname='" + user.getUser_realname() 
				+ "',user_nickname='" + user.getUser_nickname() 
				+ "',user_email='" + user.getUser_email() 
				+ "',user_house='" + user.getUser_house() 
				+ "',user_community='" + user.getUser_community()
				+ "' where user_id=" + user.getUser_id();
		getSession().createQuery(hqlString).executeUpdate();
		System.out.println(12354);

	}
	
	
}
