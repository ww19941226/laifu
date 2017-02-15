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
 * 日常通知表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_notify")
public class Notify implements Serializable {
	
	private static final long serialVersionUID = -762374680121046696L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notify_id", nullable = false)
	private int notify_id;//通知表ID(主键)
	private int notify_userid;//用户ID(外键)
	private String notify_titile;//标题
	private String notify_content;//正文
	private Date notify_datetime;//发布时间
	//private String house_floornumber;//楼号
	//private String house_roomnumber;//房号
	
	
	
	/*
	public String getHouse_floornumber() {
		return house_floornumber;
	}
	public void setHouse_floornumber(String house_floornumber) {
		this.house_floornumber = house_floornumber;
	}
	public String getHouse_roomnumber() {
		return house_roomnumber;
	}
	public void setHouse_roomnumber(String house_roomnumber) {
		this.house_roomnumber = house_roomnumber;
	}*/
	public int getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(int notify_id) {
		this.notify_id = notify_id;
	}
	public int getNotify_userid() {
		return notify_userid;
	}
	public void setNotify_userid(int notify_userid) {
		this.notify_userid = notify_userid;
	}
	public String getNotify_titile() {
		return notify_titile;
	}
	public void setNotify_titile(String notify_titile) {
		this.notify_titile = notify_titile;
	}
	public String getNotify_content() {
		return notify_content;
	}
	public void setNotify_content(String notify_content) {
		this.notify_content = notify_content;
	}
	public Date getNotify_datetime() {
		return notify_datetime;
	}
	public void setNotify_datetime(Date notify_datetime) {
		this.notify_datetime = notify_datetime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + notify_id;
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
		Notify other = (Notify) obj;
		if (notify_id != other.notify_id)
			return false;
		return true;
	}
	
	
	
}
