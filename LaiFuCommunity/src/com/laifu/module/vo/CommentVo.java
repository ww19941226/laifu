package com.laifu.module.vo;

import java.util.Date;

import com.laifu.module.entity.Comment;
import com.laifu.module.entity.User;

public class CommentVo {
	
	private String comment_content;
	private Date comment_time;
	private int comment_id;
	private AuthorVo user;
	private AuthorVo replyUser;
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public AuthorVo getUser() {
		return user;
	}
	public void setUser(AuthorVo user) {
		this.user = user;
	}
	public AuthorVo getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(AuthorVo replyUser) {
		this.replyUser = replyUser;
	}
	
}
