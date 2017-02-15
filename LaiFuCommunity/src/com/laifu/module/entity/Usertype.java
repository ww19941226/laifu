package com.laifu.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户类型表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name = "tb_usertype")
public class Usertype implements Serializable {


	private static final long serialVersionUID = 515819110319644368L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usertype_id", nullable = false)
	private int usertype_id;//用户类型ID(主键)
	private String usertype_name;//类型名
	
	
	public int getUsertype_id() {
		return usertype_id;
	}
	public void setUsertype_id(int usertype_id) {
		this.usertype_id = usertype_id;
	}
	public String getUsertype_name() {
		return usertype_name;
	}
	public void setUsertype_name(String usertype_name) {
		this.usertype_name = usertype_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + usertype_id;
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
		Usertype other = (Usertype) obj;
		if (usertype_id != other.usertype_id)
			return false;
		return true;
	}
	
	
	
}
