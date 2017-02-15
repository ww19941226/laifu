package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Praise;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.TopicVo;

/**
 * 
 * @author Raindrops
 * 
 */
public interface PraiseDao extends IBaseDao<Praise, Integer> {
	/**
	 * 根据话题ID查询到已点过赞的用户id
	 */
	public List<Praise> getPraiseUserIdByTopicId(Integer id);
	
	public List<AuthorVo> getAuthorVoByTopicId(int topic_id) throws Exception;

	/**
	 * 根据话题id和用户id取消点赞
	 */
	public void deletePraise(int praise_userid, int praise_topicid);
	
	public List<TopicVo> getTopicVo(List<TopicVo> list, int user_id, int self_id) throws Exception;
}
