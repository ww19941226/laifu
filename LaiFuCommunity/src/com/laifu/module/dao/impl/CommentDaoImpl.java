package com.laifu.module.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.CommentDao;
import com.laifu.module.entity.Comment;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.PraiseVo;
import com.laifu.module.vo.TopicVo;

/**
 * 评论表的Dao实现类
 * 
 * @author Raindrops
 * @version 2016/9/6
 */
@Repository("CommentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment, Integer> implements
		CommentDao {
	/**
	 * 根据话题id删除评论
	 */
	@Override
	public void deleteCommentByTopicid(Integer id) {
		// TODO Auto-generated method stub
		String hql = "delete from Comment c where comment_topic=" + id;
		getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 根据话题id获取所有评论
	 */
	@Override
	public List<Comment> getCommentByTopicId(Integer id) {
		// TODO Auto-generated method stub
		String hqlString = "from Comment  comment where comment_topic=" + id;
		Query query = getSession().createQuery(hqlString);
		return query.list();
	}
	
	/*********************************************************************************************/
	
	
	public List<TopicVo> getTopicVo(List<TopicVo> list, int user_id) throws Exception {
		String str = "";
		for(int i=0; i<list.size(); i++) {
			if(i == 0) str += " ( ";
			if(i != 0) str += " , ";
			str += list.get(i).getTopic().getTopic_id();
			if(i == list.size()-1) str += " ) ";
		}
		
		String hql = "from Comment as c, User as u where c.comment_topic in" + str + " and c.comment_user = u.user_id order by c.comment_topic desc, c.comment_id asc";
		List<Object[]> objList = getSession().createQuery(hql).list();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, AuthorVo> nickNameMap = new HashMap<Integer, AuthorVo>();
		
		for(int j=0, i=0; j<objList.size() && i<list.size(); j++) {
			while( i < list.size() && list.get(i).getTopic().getTopic_id() != ((Comment) objList.get(j)[0]).getComment_topic() ) {
				i ++;
			}
			if(i < list.size()) {
				CommentVo cvo = (CommentVo) copyObject(objList.get(j)[0], new CommentVo());
				cvo.setUser((AuthorVo) copyObject(objList.get(j)[1], new AuthorVo()));
				//System.out.println(cvo.getComment().getComment_id() + " " + cvo.getUser().getUser_nickname());
				//list.get(i).getCommentVo().add(cvo);
				//直接评论
				Comment c = ((Comment) objList.get(j)[0]);
				if(c.getComment_replycommentid() == 0) {
					map.put(c.getComment_id(), list.get(i).getCommentVo().size());
					list.get(i).getCommentVo().add(new ArrayList<CommentVo>());
					list.get(i).getCommentVo().get(list.get(i).getCommentVo().size()-1).add(cvo);
					nickNameMap.put(cvo.getUser().getUser_id(), cvo.getUser());
				}
				else {
					nickNameMap.put(cvo.getUser().getUser_id(), cvo.getUser());
					cvo.setReplyUser(nickNameMap.get(c.getComment_replyuserid()));
					list.get(i).getCommentVo().get(map.get(c.getComment_replycommentid())).add(cvo);
				}
			}
		}
	
		return list;
	}
	
	
	@Override
	public List<List<CommentVo>> getComment(int id) throws Exception {
		
		String hql = "from Comment as c, User as u where c.comment_topic=" + id + " and c.comment_user = u.user_id order by c.comment_topic desc, c.comment_id asc";
		List<Object[]> objList = getSession().createQuery(hql).list();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, AuthorVo> nickNameMap = new HashMap<Integer, AuthorVo>();
		int top = 0;
		List<List<CommentVo>> list = new ArrayList<List<CommentVo>>();
		
		for(int j=0; j<objList.size(); j++) {
			CommentVo cvo = (CommentVo) copyObject(objList.get(j)[0], new CommentVo());
			cvo.setUser((AuthorVo) copyObject(objList.get(j)[1], new AuthorVo()));
			//直接评论
			Comment c = ((Comment) objList.get(j)[0]);
			if(c.getComment_replycommentid() == 0) {
				list.add(new ArrayList<CommentVo>());
				list.get(top).add(cvo);
				map.put(c.getComment_id(), top++);
				nickNameMap.put(cvo.getUser().getUser_id(), cvo.getUser());
			}
			else {
				nickNameMap.put(cvo.getUser().getUser_id(), cvo.getUser());
				cvo.setReplyUser(nickNameMap.get(c.getComment_replyuserid()));
				list.get(map.get(c.getComment_replycommentid())).add(cvo);
			}
		}
	
		return list;
	}

}
