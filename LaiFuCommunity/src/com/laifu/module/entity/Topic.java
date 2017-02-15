package com.laifu.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * 话题表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name = "tb_topic")
public class Topic implements Serializable {
	
	

	private static final long serialVersionUID = -4977424095822533495L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id", nullable = false)
	private int topic_id;//话题ID (主键)
	
	
	
	private String topic_title;//标题
	private String topic_comment;//内容
	private int topic_type;//话题类型
	private int topic_userid;//用户ID(外键)
	private Date topic_datetime;//发布时间
	private String topicpicture_path;//图片路径（自定义界定符）
	
	
	
	
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_title() {
		return topic_title;
	}
	public void setTopic_title(String topic_title) {
		this.topic_title = topic_title;
	}
	public String getTopic_comment() {
		return topic_comment;
	}
	public void setTopic_comment(String topic_comment) {
		this.topic_comment = topic_comment;
	}
	public int getTopic_type() {
		return topic_type;
	}
	public void setTopic_type(int topic_type) {
		this.topic_type = topic_type;
	}
	public int getTopic_userid() {
		return topic_userid;
	}
	public void setTopic_userid(int topic_userid) {
		this.topic_userid = topic_userid;
	}
	public Date getTopic_datetime() {
		return topic_datetime;
	}
	public void setTopic_datetime(Date topic_datetime) {
		this.topic_datetime = topic_datetime;
	}
	public String getTopicpicture_path() {
		return topicpicture_path;
	}
	public void setTopicpicture_path(String topicpicture_path) {
		this.topicpicture_path = topicpicture_path;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + topic_id;
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
		Topic other = (Topic) obj;
		if (topic_id != other.topic_id)
			return false;
		return true;
	}
	
	
	
	
}
