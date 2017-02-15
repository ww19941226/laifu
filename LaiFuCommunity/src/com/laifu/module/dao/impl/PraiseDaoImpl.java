package com.laifu.module.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.PraiseDao;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.PraiseVo;
import com.laifu.module.vo.TopicVo;

/**
 * 
 * @author Raindrops
 * 
 */
@Repository("PraiseDao")
public class PraiseDaoImpl extends BaseDaoImpl<Praise, Integer> implements
		PraiseDao {

	@Override
	public List<Praise> getPraiseUserIdByTopicId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "From Praise p where praise_topicid=" + id;
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	

	@Override
	public void deletePraise(int praise_userid, int praise_topicid) {
		// TODO Auto-generated method stub
		String hql = "delete from Praise where praise_topicid="+praise_topicid+" and praise_userid="+praise_userid;
		getSession().createQuery(hql).executeUpdate();
		
	}
	
	/*******************************************************************************************/
	
	@Override
	public List<AuthorVo> getAuthorVoByTopicId(int topic_id) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Praise p, User u where p.praise_userid = u.user_id and p.praise_topicid=" + topic_id + " order by p.praise_id desc";
		List<Object[]> list = getSession().createQuery(hql).list();
		List<AuthorVo> resultList = new ArrayList<AuthorVo>();
		for(int i=0; i<list.size(); i++) {
			resultList.add((AuthorVo) copyObject(list.get(i)[1], new AuthorVo()));
		}
		return resultList;
	}
	
	@Override 
	public List<TopicVo> getTopicVo(List<TopicVo> list, int user_id, int self_id) throws Exception {
		String str = "";
		for(int i=0; i<list.size(); i++) {
			if(i == 0) str += " ( ";
			if(i != 0) str += " , ";
			str += list.get(i).getTopic().getTopic_id();
			if(i == list.size()-1) str += " ) ";
		}
		String hql = "from Praise as p where p.praise_userid=" + self_id + " and p.praise_topicid in" + str + " order by p.praise_topicid desc, p.praise_id desc";

		List<Praise> pList = getSession().createQuery(hql).list();
		for(int i=0, j=0; j<pList.size() && i<list.size(); i++) {
			if(pList.get(j).getPraise_topicid() == list.get(i).getTopic().getTopic_id()) {
				list.get(i).setPraise(true);
				j++;
			}
			else list.get(i).setPraise(false);
		}
		
		/*********************************************************************/
		
		hql = "from Praise as p, User as u where p.praise_topicid in" + str + " and p.praise_userid = u.user_id order by p.praise_topicid desc, p.praise_id desc";
		List<Object[]> objList = getSession().createQuery(hql).list();
		System.out.println(objList.size());
		for(int j=0, i=0; j<objList.size() && i<list.size(); j++) {
			while( i < list.size() && list.get(i).getTopic().getTopic_id() != ((Praise) objList.get(j)[0]).getPraise_topicid() ) {
				i ++;
			}
			if(i < list.size()) {
				list.get(i).getPraiseVo().add((AuthorVo) copyObject(objList.get(j)[1], new AuthorVo()));
			}
		}
		
		
		return list;
	}
	

}
