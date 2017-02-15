package com.laifu.module.vo;

import com.laifu.module.entity.House;
import com.laifu.module.entity.Repair;
import com.laifu.module.entity.User;


public class RepairVo {
	
	private Repair repair;	
	private User user;
	private House house;
	
	public RepairVo(Repair repair, User user, House house) {
		super();
		this.repair = repair;
		this.user = user;
		this.house = house;
	}
	
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public RepairVo() {
		super();
	}
	
	public Repair getRepair() {
		return repair;
	}
	public void setRepair(Repair repair) {
		this.repair = repair;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
