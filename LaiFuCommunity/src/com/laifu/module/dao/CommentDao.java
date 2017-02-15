package com.laifu.module.dao;

import java.util.List;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.Comment;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.TopicVo;
/**
 * 
 * @author Raindrops
 *
 */
public interface CommentDao extends IBaseDao<Comment, Integer>{
	
	  public void deleteCommentByTopicid(Integer id);

	  public List<Comment> getCommentByTopicId(Integer id);
	  
	  /*********************************************************************************************/
	  
	  public List<TopicVo> getTopicVo(List<TopicVo> list, int user_id) throws Exception;
	  
	  public List<List<CommentVo>> getComment(int id) throws Exception;
}
