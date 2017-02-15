package com.laifu.module.service;

import java.util.List;

import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.Comment;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.TopicVo;

public interface CommunityTopicService extends IBaseService<Topic, Integer> {

	public List<Topic> getTopicTypeAll(int topicType);

	public void addComment(Comment comment);

	public void addPraise(Praise praise);

	public void deletePraise(Integer user_id, Integer topic_id);

	public List<Topic> getTopicSelfAll(Integer id);

	public User getUser(Integer id);

	public Community getCommunity(Integer id);

	public void deleteCommentByTopicid(Integer id);
	
	public void deleteComment(int comment_id);

	public List<Comment> getCommentByTopicId(Integer id);

	public List<Praise> getPraiseUserIdByTopicId(Integer id);
	
	public void deleteTopic(int topic_id);
	
	/****************************************************************************************/
	
	public List<TopicVo> getTopicVo(int user_id, int pn, int size, String communityTopic) throws Exception;
	
	public List<TopicVo> getTopicVoByUser(int user_id, int pn, int size, int self_id) throws Exception;
	
	public List<AuthorVo> getPraise(int topic_id) throws Exception;
	  
	public List<List<CommentVo>> getComment(int id) throws Exception;
}
