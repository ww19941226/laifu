package com.laifu.module.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.dao.ComplainsDao;
import com.laifu.module.dao.UserDao;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.User;
import com.laifu.module.service.SystemManageService;

@Service("SystemManageService")
public class SystemManageServiceImpl extends BaseServiceImpl<Complains,Integer>implements SystemManageService{

	//打印日志
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemManageServiceImpl.class);

    
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
	 * 获取所有的小区信息
	 * 
	 * @return List<Community> 所有小区的信息
	 * @throws Exception
	 */
    @Override
    public List<Community> getAllCommunity() throws Exception {
    	try {
    		return communityDao.listAll();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
    }
    
    /**
	 * 通过Id查找小区详细信息
	 * 
	 * @return Community 小区对象
	 * @throws Exception
	 */
    @Override
    public Community getCommunity(int id) throws Exception {
    	try {
    		return communityDao.get(id);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
    }
    
    /**
	 * 获取未审核的小区列表
	 * 
	 * @return List<Community> 未审核小区列表 
	 * @throws Exception
	 */
    @Override
    public List<Community> getUncheckCommunity() throws Exception {
    	try {
    		return communityDao.get("from Community where community_checkstate=0");
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
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
    
    /**
	 * 更新小区信息功能
	 * 
	 * @param id 要更新的小区信息
	 * @throws Exception
	 */
    public void deleteCommunity(int id) throws Exception {
    	try {
    		communityDao.delete(id);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
	}
    
    /**
	 * 审核小区
	 * 
	 * @param community_id 审核的小区id
	 * @throws Exception
	 */
	public void checkCommunity(int community_id, int community_checkstate) throws Exception {
		try {
			communityDao.update("update Community set community_checkstate=" + community_checkstate + " where community_id=" + community_id);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
	}
	
	
	
	/**
	 * 返回所有未审核的列表页面
	 * 
	 * @throws Exception
	 */
	@Override
	public List<Community> getFailCommunity() throws Exception {
		try {
			return communityDao.get("from Community where community_checkstate=2");
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw new Exception();
    	}
	}
	
	/**
	 * 获取所有的投诉建议的列表
	 * 
	 * @return List<Complains>投诉信息列表
	 * @throws Exception
	 */
	@Override
	public List<Complains> getAllComplains() throws Exception {
		return complainsDao.getList("from Complains where complains_usertype=3");
	}
	
	/**
	 * 通过id获取投诉建议的详细信息
	 * 
	 * @return Complains 返回投诉建议的详细信息
	 * @throws Exception
	 */
	@Override
	public Complains getComplains(int id) throws Exception {
		return complainsDao.get(id);
	}
	
	/**
	 * 回复投诉建议
	 * 
	 * @param complains 需要回复的投诉建议
	 * @throws Exception
	 */
    @Override
    public void replyComplains(Complains complains) throws Exception {
		complainsDao.update("update Complains set complains_replycontent='" + complains.getComplains_replycontent() + "', complains_state=1 where complains_id=" + complains.getComplains_id());
	}
	
    
    /*******************************************************************************************************/
    

    /**
     * 添加管理员
     */
	public void addAdmin(User admin) {
		Date time =new Date();
		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String registerTimeStr = timeFormat.format(time);
		try {
			Date   registerTime = timeFormat.parse(registerTimeStr);
			admin.setUser_registertime(registerTime);
			userDao.save(admin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	
	

	/**
	 * 建议处理
	 */
	public void dealComplains(Complains complain) {	
		Complains c = complainsDao.get(complain.getComplains_id());
		c.setComplains_replycontent(complain.getComplains_replycontent());
		Date time =new Date();
		SimpleDateFormat timeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String replyTimeStr = timeFormat.format(time);
		try {
			Date   replyTime = timeFormat.parse(replyTimeStr);
			c.setComplains_replytime(replyTime);
			complainsDao.save(c);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 判断用户是否存在
	 */
	public boolean userHasExsit(User user){
		
		return true;
	}
	
	
	/**
	 * 设置通用信息
	 */
	@Override
	public void setBaseDaoImpl(IBaseDao<Complains, Integer> baseDao) {
		
	}

	/************************************************************************************************/
	
	
	public Page<Community> community_listAll(String hql, int pn, int pageSize) {
		Integer count = communityDao.countAll("select count(*) " + hql);
		List<Community> items = communityDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	
	public Page<Complains> complains_listAll(String hql, int pn, int pageSize) {
		Integer count = complainsDao.countAll("select count(*) " + hql);
		List<Complains> items = complainsDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	
}
