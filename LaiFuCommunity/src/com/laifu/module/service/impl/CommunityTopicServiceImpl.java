package com.laifu.module.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CommentDao;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.dao.PraiseDao;
import com.laifu.module.dao.TopicDao;
import com.laifu.module.dao.UserDao;
import com.laifu.module.entity.Comment;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.service.CommunityTopicService;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.TopicVo;

@Service("CommunityTopicService")
public class CommunityTopicServiceImpl extends BaseServiceImpl<Topic, Integer>
		implements CommunityTopicService {
	private CommentDao commentDao;
	private PraiseDao praiseDao;
	private TopicDao topicDao;
	private UserDao userDao;
	private CommunityDao communityDao;

	public CommunityDao getCommunityDao() {
		return communityDao;
	}

	@Resource(name = "CommunityDao")
	public void setCommunityDao(CommunityDao communityDao) {
		this.communityDao = communityDao;
	}

	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource(name = "UserDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	@Resource(name = "CommentDao")
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public PraiseDao getPraiseDao() {
		return praiseDao;
	}

	@Resource(name = "PraiseDao")
	public void setPraiseDao(PraiseDao praiseDao) {
		this.praiseDao = praiseDao;
	}

	@Resource(name = "TopicDao")
	@Override
	public void setBaseDaoImpl(IBaseDao<Topic, Integer> baseDao) {
		// TODO Auto-generated method stub
		this.baseDao = baseDao;
		this.topicDao = (TopicDao) baseDao;

	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.save(comment);
	}
	
	
	@Override
	public void deleteTopic(int topic_id) {
		// TODO Auto-generated method stub
		praiseDao.execute("delete from Praise where praise_topicid="+topic_id);
		commentDao.execute("delete from Comment where comment_topic="+topic_id);
		topicDao.execute("delete from Topic where topic_id="+topic_id);
	}

	@Override
	public void deleteComment(int comment_id) {
		// TODO Auto-generated method stub
		commentDao.execute("delete from Comment where comment_id="+comment_id);
		commentDao.execute("delete from Comment where comment_replycommentid="+comment_id);
	}

	@Override
	public void addPraise(Praise praise) {
		// TODO Auto-generated method stub
		praiseDao.save(praise);
	}

	@Override
	public void deletePraise(Integer user_id, Integer topic_id) {
		// TODO Auto-generated method stub
		praiseDao.deletePraise(user_id, topic_id);
	}

	@Override
	public List<Topic> getTopicTypeAll(int topicType) {
		// TODO Auto-generated method stub
		return topicDao.getTopicTypeAll(topicType);

	}

	@Override
	public List<Topic> getTopicSelfAll(Integer id) {
		// TODO Auto-generated method stub
		return topicDao.getTopicSelfAll(id);
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	@Override
	public Community getCommunity(Integer id) {
		// TODO Auto-generated method stub

		return communityDao.getCommunity(id);
	}

	@Override
	public void deleteCommentByTopicid(Integer id) {
		commentDao.deleteCommentByTopicid(id);

	}

	@Override
	public List<Comment> getCommentByTopicId(Integer id) {
		// TODO Auto-generated method stub
		return commentDao.getCommentByTopicId(id);
	}

	@Override
	public List<Praise> getPraiseUserIdByTopicId(Integer id) {
		// TODO Auto-generated method stub
		return praiseDao.getPraiseUserIdByTopicId(id);
	}
	
	/**************************************************************************************************/
	
	@Override
	public List<TopicVo> getTopicVo(int user_id, int pn, int size, String communityTopic) throws Exception {
		List<TopicVo> list= topicDao.getTopicVo(pn, size, communityTopic);
		if(list.size() > 0) {
			list = praiseDao.getTopicVo(list, user_id, user_id);
			list = commentDao.getTopicVo(list, user_id);
		}
		return list;
	}
	
	@Override
	public List<TopicVo> getTopicVoByUser(int user_id, int pn, int size, int self_id) throws Exception {
		List<TopicVo> list= topicDao.getTopicVoByUser(user_id, pn, size);
		if(list.size() > 0) {
			list = praiseDao.getTopicVo(list, user_id, self_id);
			list = commentDao.getTopicVo(list, user_id);
		}
		return list;
	}

	@Override
	public List<AuthorVo> getPraise(int topic_id) throws Exception {
		List<AuthorVo> list = praiseDao.getAuthorVoByTopicId(topic_id);
		return list;
	}
	
	
	@Override
	public List<List<CommentVo>> getComment(int id) throws Exception {
		List<List<CommentVo>> list = commentDao.getComment(id);
		return list;
	}

}
