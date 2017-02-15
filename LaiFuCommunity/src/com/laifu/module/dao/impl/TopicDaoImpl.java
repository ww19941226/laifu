package com.laifu.module.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.antlr.grammar.v3.ANTLRv3Parser.finallyClause_return;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.TopicDao;
import com.laifu.module.entity.Comment;
import com.laifu.module.entity.People;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.PraiseVo;
import com.laifu.module.vo.TopicVo;
/**
 * 
 * @author Raindrops
 *
 */
@Repository("TopicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic, Integer> implements TopicDao{
	
	 private SessionFactory sessionFactory;
		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
	    @Resource
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

	/**
	 * 表示来自Topic
	 */
    private static final String HQL_LIST="from TopicModel";
    /**
     * 查询Topic表的数据量
     */
    private static final  String HQL_COUNT="select count(*) from TopicModel";
    /**
     * 添加约束条件
     */
    private static final String HQL_LIST_QUERY_CONDITION="where topic_title like ? ";
 
    /**
     * 约束表和约束条件并且按降序
     */
    private static final String HQL_LIST_QUERY_ALL=HQL_LIST+HQL_LIST_QUERY_CONDITION+"oreder by topic_id desc";
    /**
     * 约束条件后查询的数据量
     */
    private static final String HQL_COUNT_QUERY_ALL=HQL_COUNT+ HQL_LIST_QUERY_CONDITION;
    
  
	 
	@Override
	public List<Topic> query(int pn, int pageSize, Topic topic) {
		// TODO Auto-generated method stub
		return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(topic));
	}


	@Override
	public int countQuery(Topic topic) {
		// TODO Auto-generated method stub
		return  this.<Number>aggregate(HQL_COUNT_QUERY_ALL, getQueryParam(topic)).intValue();
		
	}
	private Object[] getQueryParam(Topic topic){
		String topicTitleLike="%"+topic.getTopic_title()+"%";
		return new Object[]{
				topicTitleLike
		};
	}

	@Override
	public List<Topic> getTopicTypeAll(int topicType) {
		// TODO Auto-generated method stub
		String hqlString="from Topic t where t.topic_type=? order by topic_datetime DESC";
		Query query=sessionFactory.getCurrentSession().createQuery(hqlString);
	  query.setInteger(0,topicType);
		return query.list();
	}
	@Override
	public List<Topic> topicTypeQuery(int pn, int pageSize, Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * 
    * @Title: getTopicSelfAll
    * @Description: TODO根据用户id获取所有话题
    * @param @param id
    * @param @return    设定文件
    * @return List<Topic>    返回类型
    * @throws
     */
	@Override
	public List<Topic> getTopicSelfAll(Integer id) {
		// TODO Auto-generated method stub
		String hql=" from Topic t where topic_userid="+id+"order by topic_datetime DESC";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	@Override
	public void deleteTopicByUserid(Integer id) {
		// TODO Auto-generated method stub
		String hql="delete Topic t where ";
	}
	
	
	/**************************************************************************************************/
 
	@Override
	public List<TopicVo> getTopicVo(int pn, int size, String communityTopic) throws Exception {
		
		List<TopicVo> list = new ArrayList<TopicVo>();
		String hql = "from Topic as t, User as u where t.topic_userid = u.user_id and t.topic_type=" + communityTopic + " order by t.topic_id desc";
		List<Object[]> result = getSession().createQuery(hql).setFirstResult(pn).setMaxResults(size).list();
		for(Object[] obj : result) {
			TopicVo vo = new TopicVo();
			vo.setCommentVo(new ArrayList<List<CommentVo>>());
			vo.setPraiseVo(new ArrayList<AuthorVo>());
			vo.setTopic((Topic) obj[0]);
			vo.setAuthor((AuthorVo)copyObject(obj[1], new AuthorVo()));
			//System.out.println(vo.getAuthor().getUser_nickname() + " " + vo.getTopic().getTopic_comment());
			list.add(vo);
		}
		
		return list;
	}
	
	@Override
	public List<TopicVo> getTopicVoByUser(int user_id, int pn, int size) throws Exception {
		
		List<TopicVo> list = new ArrayList<TopicVo>();
		String hql = "from Topic as t, User as u where t.topic_userid = u.user_id and t.topic_userid=" + user_id + " order by t.topic_id desc";
		List<Object[]> result = getSession().createQuery(hql).setFirstResult(pn).setMaxResults(size).list();
		for(Object[] obj : result) {
			TopicVo vo = new TopicVo();
			vo.setCommentVo(new ArrayList<List<CommentVo>>());
			vo.setPraiseVo(new ArrayList<AuthorVo>());
			vo.setTopic((Topic) obj[0]);
			vo.setAuthor((AuthorVo)copyObject(obj[1], new AuthorVo()));
			//System.out.println(vo.getAuthor().getUser_nickname() + " " + vo.getTopic().getTopic_comment());
			list.add(vo);
		}
		
		return list;
	}
}
