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
@Table(name = "tb_house")
public class House implements Serializable{


	private static final long serialVersionUID = -110801630889588693L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "house_id", nullable = false)
	private int house_id;//房屋ID(主键)
	private int community_id;//小区ID(外键)
	private Integer user_id;//业主ID(外键)
	private String house_picture;//房屋平面图
	private double house_area;//房屋面积
	private double house_prePrice;//每平方米价格
	private double house_price;//售价
	private String house_floornumber;  //楼层
	private String house_roomnumber;  //房号
	
	
	
	
	
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
	}
	public int getHouse_id() {
		return house_id;
	}
	public void setHouse_id(int house_id) {
		this.house_id = house_id;
	}
	public int getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getHouse_picture() {
		return house_picture;
	}
	public void setHouse_picture(String house_picture) {
		this.house_picture = house_picture;
	}
	public double getHouse_area() {
		return house_area;
	}
	public void setHouse_area(double house_area) {
		this.house_area = house_area;
	}
	public double getHouse_prePrice() {
		return house_prePrice;
	}
	public void setHouse_prePrice(double house_prePrice) {
		this.house_prePrice = house_prePrice;
	}
	public double getHouse_price() {
		return house_price;
	}
	public void setHouse_price(double house_price) {
		this.house_price = house_price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + house_id;
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
		House other = (House) obj;
		if (house_id != other.house_id)
			return false;
		return true;
	}

	
}
