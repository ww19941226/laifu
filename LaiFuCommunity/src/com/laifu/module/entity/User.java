package com.laifu.module.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.laifu.module.support.editor.DateEditor;
/**
 * 用户表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)//开启缓存 记得在ehcache_hibernate.xml配置
public class User implements Serializable{

	private static final long serialVersionUID = 2717504400081800499L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
	private int user_id; //主键 
	private String user_account; //账号 手机号码
	private String user_password; //密码
	private String user_card; //身份证号码
	private String user_sex; //性别
	private String user_realname;//真实姓名
	private String user_nickname;//昵称
	private String user_email;//邮箱
	private String user_head;//头像
	private int user_type;//角色类型
	private int user_landstate;//登录状态
	private int user_community;//小区
	private int user_house;//房屋
	private int user_age;//年龄
	private Date user_registertime;//注册时间
	private String user_approver;//审批人
	private Date user_approvaltime;//审批时间
	private int user_checkstate;  //审核状态
	
	
	
	public User(int user_id, String user_account, String user_password,
			String user_card, String user_sex, String user_realname,
			String user_nickname, String user_email, String user_head,
			int user_type, int user_landstate, int user_community,
			int user_house, int user_age, Date user_registertime,
			String user_approver, Date user_approvaltime, int user_checkstate) {
		super();
		this.user_id = user_id;
		this.user_account = user_account;
		this.user_password = user_password;
		this.user_card = user_card;
		this.user_sex = user_sex;
		this.user_realname = user_realname;
		this.user_nickname = user_nickname;
		this.user_email = user_email;
		this.user_head = user_head;
		this.user_type = user_type;
		this.user_landstate = user_landstate;
		this.user_community = user_community;
		this.user_house = user_house;
		this.user_age = user_age;
		this.user_registertime = user_registertime;
		this.user_approver = user_approver;
		this.user_approvaltime = user_approvaltime;
		this.user_checkstate = user_checkstate;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getUser_checkstate() {
		return user_checkstate;
	}
	public void setUser_checkstate(int user_checkstate) {
		this.user_checkstate = user_checkstate;
	}
	public String getUser_approver() {
		return user_approver;
	}
	public void setUser_approver(String user_approver) {
		this.user_approver = user_approver;
	}
	public Date getUser_approvaltime() {
		return user_approvaltime;
	}/*
	public String getFormat_user_approvaltime() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formater.format(user_approvaltime);
	}*/
	public void setUser_approvaltime(Date user_approvaltime) {
		this.user_approvaltime = user_approvaltime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_card() {
		return user_card;
	}
	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_realname() {
		return user_realname;
	}
	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_head() {
		return user_head;
	}
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public int getUser_landstate() {
		return user_landstate;
	}
	public void setUser_landstate(int user_landstate) {
		this.user_landstate = user_landstate;
	}
	public int getUser_community() {
		return user_community;
	}
	public void setUser_community(int user_community) {
		this.user_community = user_community;
	}
	public int getUser_house() {
		return user_house;
	}
	public void setUser_house(int user_house) {
		this.user_house = user_house;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public Date getUser_registertime() {
		return user_registertime;
	}
	/*
	public String getFormat_user_registertimes() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formater.format(user_registertime);
	}
	*/
	public void setUser_registertime(Date user_registertime) {
		this.user_registertime = user_registertime;
	}
	
	
	@Override
	public int hashCode() {
		 final int prime = 31;
	        int result = 1;
	        result = prime * result + user_id;
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
		User other = (User) obj;
		
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
}
