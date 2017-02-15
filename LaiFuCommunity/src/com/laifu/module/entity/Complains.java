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
 * 投诉建议表实体
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_complains")
public class Complains implements Serializable{
	
	

	private static final long serialVersionUID = -4135897211273985247L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complains_id", nullable = false)
	private int complains_id;//投诉ID(主键)
	private int complains_userid;//用户ID(外键)
	private String complains_title;//投诉标题
	private String complains_content;//投诉内容
	private Date complains_datetime;//投诉时间
	private String complains_phone;//用户手机号
	private String complains_replycontent;//管理员回复内容
	private Date complains_replytime;//管理员回复时间
	private int complains_usertype;//角色类型(外键)
	private int complains_state;
	
	
	
	public int getComplains_state() {
		return complains_state;
	}
	public void setComplains_state(int complains_state) {
		this.complains_state = complains_state;
	}
	public int getComplains_usertype() {
		return complains_usertype;
	}
	public void setComplains_usertype(int complains_usertype) {
		this.complains_usertype = complains_usertype;
	}
	public int getComplains_id() {
		return complains_id;
	}
	public void setComplains_id(int complains_id) {
		this.complains_id = complains_id;
	}
	public int getComplains_userid() {
		return complains_userid;
	}
	public void setComplains_userid(int complains_userid) {
		this.complains_userid = complains_userid;
	}
	public String getComplains_title() {
		return complains_title;
	}
	public void setComplains_title(String complains_title) {
		this.complains_title = complains_title;
	}
	public String getComplains_content() {
		return complains_content;
	}
	public void setComplains_content(String complains_content) {
		this.complains_content = complains_content;
	}
	public Date getComplains_datetime() {
		return complains_datetime;
	}
	public void setComplains_datetime(Date complains_datetime) {
		this.complains_datetime = complains_datetime;
	}
	public String getComplains_phone() {
		return complains_phone;
	}
	public void setComplains_phone(String complains_phone) {
		this.complains_phone = complains_phone;
	}
	public String getComplains_replycontent() {
		return complains_replycontent;
	}
	public void setComplains_replycontent(String complains_replycontent) {
		this.complains_replycontent = complains_replycontent;
	}
	public Date getComplains_replytime() {
		return complains_replytime;
	}
	public void setComplains_replytime(Date complains_replytime) {
		this.complains_replytime = complains_replytime;
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + complains_id;
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
		Complains other = (Complains) obj;
		if (complains_id != other.complains_id)
			return false;
		return true;
	}
	
	
	
	
	
}
