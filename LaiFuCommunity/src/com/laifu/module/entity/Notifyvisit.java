package com.laifu.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房屋表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name = "tb_notifyvisit")
public class Notifyvisit implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5051984034579923845L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notifyvisit_id", nullable = false)
	private int notifyvisit_id;//房屋ID(主键)
	private int notifyvisit_userid;//小区ID(外键)
	private int notifyvisit_notifyid;//业主ID(外键)
	
	
	
	public int getNotifyvisit_id() {
		return notifyvisit_id;
	}
	public void setNotifyvisit_id(int notifyvisit_id) {
		this.notifyvisit_id = notifyvisit_id;
	}
	public int getNotifyvisit_userid() {
		return notifyvisit_userid;
	}
	public void setNotifyvisit_userid(int notifyvisit_userid) {
		this.notifyvisit_userid = notifyvisit_userid;
	}
	public int getNotifyvisit_notifyid() {
		return notifyvisit_notifyid;
	}
	public void setNotifyvisit_notifyid(int notifyvisit_notifyid) {
		this.notifyvisit_notifyid = notifyvisit_notifyid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + notifyvisit_id;
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
		Notifyvisit other = (Notifyvisit) obj;
		if (notifyvisit_id != other.notifyvisit_id)
			return false;
		return true;
	}

	
}
