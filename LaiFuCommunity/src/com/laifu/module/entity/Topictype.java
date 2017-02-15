package com.laifu.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 话题类型表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name = "tb_topictype")
public class Topictype implements Serializable {


	private static final long serialVersionUID = 5843799373646232373L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topictype_id",nullable = false)
	private int topictype_id;//话题类型ID(主键)
	private String topictype_name;//话题类型名
	
	
	
	public int getTopictype_id() {
		return topictype_id;
	}
	public void setTopictype_id(int topictype_id) {
		this.topictype_id = topictype_id;
	}
	public String getTopictype_name() {
		return topictype_name;
	}
	public void setTopictype_name(String topictype_name) {
		this.topictype_name = topictype_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + topictype_id;
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
		Topictype other = (Topictype) obj;
		if (topictype_id != other.topictype_id)
			return false;
		return true;
	}
	
	
}
