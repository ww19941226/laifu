package com.laifu.module.vo;

import com.laifu.module.entity.House;
import com.laifu.module.entity.User;

public class HouseVo {
	
	private House house;
	private User user;
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public HouseVo(House house, User user) {
		super();
		this.house = house;
		this.user = user;
	}
	public HouseVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
