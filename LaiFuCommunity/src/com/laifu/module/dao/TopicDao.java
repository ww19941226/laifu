package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.People;
import com.laifu.module.entity.Topic;
import com.laifu.module.vo.TopicVo;

/**
 * 
 * @author Raindrops
 *
 */
public interface TopicDao extends IBaseDao<Topic, Integer>{
	  List<Topic> query(int pn, int pageSize,Topic topic);
	  List<Topic> topicTypeQuery(int pn, int pageSize, Topic topic);
	    int countQuery(Topic topic);
	    /**
	     * 
	    * @Title: getTopicTypeAll
	    * @Description: TODO根据类型获取所有id
	    * @param @param topicType
	    * @param @return    设定文件
	    * @return List<Topic>    返回类型
	    * @throws
	     */
	    public List<Topic> getTopicTypeAll(int topicType);
	    /**
	     * 
	    * @Title: getTopicSelfAll
	    * @Description: TODO根据用户id获取所有话题
	    * @param @param id
	    * @param @return    设定文件
	    * @return List<Topic>    返回类型
	    * @throws
	     */
	    public List<Topic> getTopicSelfAll(Integer id);
	    /**
	     *用户删除本人的话题
	     */
	    public  void deleteTopicByUserid(Integer id);
	    
	    /**************************************************************************************************/
	    
	    public List<TopicVo> getTopicVo(int pn, int size, String communityTopic) throws Exception;
	    
	    public List<TopicVo> getTopicVoByUser(int user_id, int pn, int size) throws Exception;
}
