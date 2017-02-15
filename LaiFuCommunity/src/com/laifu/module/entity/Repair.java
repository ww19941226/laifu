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
 * 自助报修表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_repair")
public class Repair implements Serializable{
	
	private static final long serialVersionUID = -6989709521048260427L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "repair_id", nullable = false)
	private int repair_id;//自助报修ID(主键)
	private int repair_userid;//用户ID(外键)
	private String repair_project;//报修内容
	private Date repair_decldatatime;//报修时间
	private Date repair_starttime;//预期上门维修起始时间
	private Date repair_endtime;//预期上门维修结束时间
	private Date repair_completetime;//维修完成时间
	private int repair_state;
	private String repair_reply;
	private String repair_picturepath; //维修图片的路径
	
	
	public int getRepair_state() {
		return repair_state;
	}
	public void setRepair_state(int repair_state) {
		this.repair_state = repair_state;
	}
	public String getRepair_reply() {
		return repair_reply;
	}
	public void setRepair_reply(String repair_reply) {
		this.repair_reply = repair_reply;
	}
	public int getRepair_id() {
		return repair_id;
	}
	public void setRepair_id(int repair_id) {
		this.repair_id = repair_id;
	}
	public int getRepair_userid() {
		return repair_userid;
	}
	public void setRepair_userid(int repair_userid) {
		this.repair_userid = repair_userid;
	}
	public String getRepair_project() {
		return repair_project;
	}
	public void setRepair_project(String repair_project) {
		this.repair_project = repair_project;
	}
	public Date getRepair_decldatatime() {
		return repair_decldatatime;
	}
	public void setRepair_decldatatime(Date repair_decldatatime) {
		this.repair_decldatatime = repair_decldatatime;
	}
	public Date getRepair_starttime() {
		return repair_starttime;
	}
	public void setRepair_starttime(Date repair_starttime) {
		this.repair_starttime = repair_starttime;
	}
	public Date getRepair_endtime() {
		return repair_endtime;
	}
	public void setRepair_endtime(Date repair_endtime) {
		this.repair_endtime = repair_endtime;
	}
	public Date getRepair_completetime() {
		return repair_completetime;
	}
	public void setRepair_completetime(Date repair_completetime) {
		this.repair_completetime = repair_completetime;
	}
	
	public String getRepair_picturepath() {
		return repair_picturepath;
	}
	public void setRepair_picturepath(String repair_picturepath) {
		this.repair_picturepath = repair_picturepath;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + repair_id;
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
		Repair other = (Repair) obj;
		if (repair_id != other.repair_id)
			return false;
		return true;
	}
	
	
	
}
