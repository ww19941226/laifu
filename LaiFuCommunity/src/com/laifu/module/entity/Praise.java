package com.laifu.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 点赞表实体
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_praise")
public class Praise implements Serializable {
	

	private static final long serialVersionUID = -1755225765746907619L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "praise_id", nullable = false)
	private int praise_id;//点赞ID (主键)
	private int praise_userid;//用户ID(外键)
	private int praise_topicid;//话题ID（外键）
	public int getPraise_id() {
		return praise_id;
	}
	public void setPraise_id(int praise_id) {
		this.praise_id = praise_id;
	}
	public int getPraise_userid() {
		return praise_userid;
	}
	public void setPraise_userid(int praise_userid) {
		this.praise_userid = praise_userid;
	}
	public int getPraise_topicid() {
		return praise_topicid;
	}
	public void setPraise_topicid(int praise_topicid) {
		this.praise_topicid = praise_topicid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + praise_id;
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
		Praise other = (Praise) obj;
		if (praise_id != other.praise_id)
			return false;
		return true;
	}
	
	
	
}
