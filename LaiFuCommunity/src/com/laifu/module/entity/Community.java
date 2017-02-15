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
 * 小区表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name = "tb_community")
public class Community implements Serializable {

	private static final long serialVersionUID = 1809155424832550529L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "community_id", nullable = false)
	private int community_id;//小区表ID(主键)
	private String community_location;//小区的详细地址
	private String community_name;//小区名称
	private Date community_regitime;//注册时间
	private String community_approver;//审批人
	private Date community_approvaltime;//审批时间
	private String community_indirect;//小区简介
	private int community_checkstate;  //审核状态
	
	
	
	
	
	public int getCommunity_checkstate() {
		return community_checkstate;
	}
	public void setCommunity_checkstate(int community_checkstate) {
		this.community_checkstate = community_checkstate;
	}
	public String getCommunity_approver() {
		return community_approver;
	}
	public void setCommunity_approver(String community_approver) {
		this.community_approver = community_approver;
	}
	public Date getCommunity_approvaltime() {
		return community_approvaltime;
	}
	public void setCommunity_approvaltime(Date community_approvaltime) {
		this.community_approvaltime = community_approvaltime;
	}
	public String getCommunity_indirect() {
		return community_indirect;
	}
	public void setCommunity_indirect(String community_indirect) {
		this.community_indirect = community_indirect;
	}
	public int getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}
	public String getCommunity_location() {
		return community_location;
	}
	public void setCommunity_location(String community_location) {
		this.community_location = community_location;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public Date getCommunity_regitime() {
		return community_regitime;
	}
	public void setCommunity_regitime(Date community_regitime) {
		this.community_regitime = community_regitime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + community_id;
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
		Community other = (Community) obj;
		if (community_id != other.community_id)
			return false;
		return true;
	}
	
	
	
}
