package com.laifu.module.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.exception.UserException;
import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.dao.ComplainsDao;
import com.laifu.module.dao.UserDao;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.User;
import com.laifu.module.service.CommunityManageService;

@Service("CommunityManageService")
public class CommunityManageServiceImpl extends BaseServiceImpl<User,Integer>implements CommunityManageService{

	//打印日志
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityManageServiceImpl.class);

    
    @Autowired
	@Qualifier("UserDao")
    private UserDao userDao;
    
    @Autowired
	@Qualifier("CommunityDao")
    private CommunityDao communityDao;
    
    
    @Autowired
	@Qualifier("ComplainsDao")
    private ComplainsDao complainsDao;
    
    /**
	 * 设置通用方法
	 */
    @Override
    public void setBaseDaoImpl(IBaseDao<User, Integer> baseDao) {
		// TODO Auto-generated method stub
		this.baseDao = baseDao;
		this.userDao = (UserDao) baseDao;
	}
    
    
    
    public List<Complains> getAllComplains() throws Exception {
    	return complainsDao.listAll();
    }
    
    public Complains getComplains(int complains_id) throws Exception {
    	return complainsDao.get(complains_id);
    }
    
    /**
	 * 回复投诉建议
	 * 
	 * @param complains 需要回复的投诉建议
	 * @throws Exception
	 */
    @Override
    public void replyComplains(Complains complains) throws Exception {
		complainsDao.update("update Complains set complains_replycontent='" + complains.getComplains_replycontent() + "' where complains_id=" + complains.getComplains_id());
	}
    
    public List<Community> getAllCommunity() throws Exception {
    	return communityDao.listAll();
    }
    
    public Community getCommunity(int community_id) throws Exception {
    	return communityDao.get(community_id);
    }
    
    
    /**
	 * 更新小区信息功能
	 * 
	 * @param community 要更新的小区信息
	 * @throws Exception
	 */
    @Override
    public void updateCommunity(Community community) throws Exception {
    	try {
    		String hql = "update Community set community_name='" + community.getCommunity_name() + "', community_location='" + community.getCommunity_location() + "', community_indirect='" + community.getCommunity_indirect() + "' where community_id=" + community.getCommunity_id();
    		communityDao.update(hql);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
    }
    
    public List<User> getUser(int usertype) throws Exception {
    	return userDao.getList("from User where usertype=" + usertype);
    }
    
    /**
	 * 得到用户列表
	 * @return 业主列表
	 */
	public List<User> getUserList(Integer userType){
		 return userDao.getUserList(userType);
	}
	
	public void deleteCommunity(int community_id) {
		communityDao.delete(community_id);
	}
	
	public void addUser(User user) {
		userDao.save(user);
	}
	
	public void deleteUser(int user_id) {
		userDao.delete(user_id);
	}
	
	public void changeUser(User user) throws UserException {
		userDao.update("update User set user_password='" + user.getUser_password() + "', user_nickname='" + user.getUser_nickname() + "', user_email='" + user.getUser_email() + "' where user_id=" + user.getUser_id());
	}
    
    /***************************************************************************************************/
	
	
	
	/**
	 * 删除用户
	 * @param user_id 业主id
	 */
	public void deleteUser(Integer user_id){
		userDao.delete(user_id);
	}
	
	/**
	 * 得到用户具体信息
	 * @param user_id 业主id
	 */
	public User getUser(Integer user_id){
		return userDao.get(user_id);
	}
	
	/**
	 * 修改用户信息
	 * @param user_id 业主id
	 */
	public void updateUser(User user){
		User u = userDao.get(user.getUser_id());
		u.setUser_account(user.getUser_account());
		u.setUser_age(user.getUser_age());
		u.setUser_approver(user.getUser_approver());
		u.setUser_card(user.getUser_card());
		u.setUser_community(user.getUser_community());
		u.setUser_email(user.getUser_email());
		u.setUser_head(user.getUser_head());
		u.setUser_house(user.getUser_house());
		u.setUser_landstate(user.getUser_landstate());
		u.setUser_nickname(user.getUser_nickname());
		u.setUser_password(user.getUser_password());
		u.setUser_realname(user.getUser_realname());
		u.setUser_sex(user.getUser_sex());
		userDao.update(u);
	}
	
	/**
	 * 注册小区
	 * @param community 小区对象
	 */
	public void addCommunity(Community community){
		communityDao.addCommunity(community);
;	}
	
	/********************************************************************************************/
	
	
	@Override
	public Page<Complains> complains_listAll(String hql, int pn, int pageSize) {
		Integer count = complainsDao.countAll("select count(*) " + hql);
		List<Complains> items = complainsDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	
	@Override
	public Page<Community> community_listAll(String hql, int pn, int pageSize) {
		Integer count = communityDao.countAll("select count(*) " + hql);
		List<Community> items = communityDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	
	
}
