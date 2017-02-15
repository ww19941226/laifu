package com.laifu.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 评论表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_comment")
public class Comment implements Serializable{
	

	
	private static final long serialVersionUID = 4453958691451821312L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
	private int comment_id;//评论ID (主键)
	private int comment_user;//用户ID(外键)
	private int comment_topic;//话题ID(外键)
	private Date comment_time;//评论时间
	private String comment_content;//评论内容
	private int comment_replyuserid;
	private int comment_replycommentid;
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getComment_user() {
		return comment_user;
	}
	public void setComment_user(int comment_user) {
		this.comment_user = comment_user;
	}
	public int getComment_topic() {
		return comment_topic;
	}
	public void setComment_topic(int comment_topic) {
		this.comment_topic = comment_topic;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_replyuserid() {
		return comment_replyuserid;
	}
	public void setComment_replyuserid(int comment_replyuserid) {
		this.comment_replyuserid = comment_replyuserid;
	}
	public int getComment_replycommentid() {
		return comment_replycommentid;
	}
	public void setComment_replycommentid(int comment_replycommentid) {
		this.comment_replycommentid = comment_replycommentid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + comment_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (comment_id != other.comment_id)
			return false;
		return true;
	}
	
	
	
	
	
}
