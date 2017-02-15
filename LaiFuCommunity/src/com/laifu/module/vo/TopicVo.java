package com.laifu.module.vo;

import java.util.List;

import com.laifu.module.entity.Comment;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;

public class TopicVo {
	
	private Topic topic;
	private AuthorVo author;
	private List<AuthorVo> praiseVo;
	private List<List<CommentVo>> commentVo;
	private boolean isPraise;
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public AuthorVo getAuthor() {
		return author;
	}
	public void setAuthor(AuthorVo author) {
		this.author = author;
	}
	public List<AuthorVo> getPraiseVo() {
		return praiseVo;
	}
	public void setPraiseVo(List<AuthorVo> praiseVo) {
		this.praiseVo = praiseVo;
	}
	public List<List<CommentVo>> getCommentVo() {
		return commentVo;
	}
	public void setCommentVo(List<List<CommentVo>> commentVo) {
		this.commentVo = commentVo;
	}
	public boolean isPraise() {
		return isPraise;
	}
	public void setPraise(boolean isPraise) {
		this.isPraise = isPraise;
	}
	
}
