package com.laifu.module.vo;

import java.util.Date;

import com.laifu.module.entity.*;

public class UserVo {
	
	private User user;
	private Usertype usertype;
	private House house;
	private Community community;
	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserVo(User user, Usertype usertype, House house, Community community) {
		super();
		this.user = user;
		this.usertype = usertype;
		this.house = house;
		this.community = community;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Usertype getUsertype() {
		return usertype;
	}
	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
}
