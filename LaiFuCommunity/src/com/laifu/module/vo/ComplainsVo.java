package com.laifu.module.vo;

import com.laifu.module.entity.Complains;
import com.laifu.module.entity.Usertype;

public class ComplainsVo {
	private Complains complains;
	private Usertype usertype;
	public Complains getComplains() {
		return complains;
	}
	public void setComplains(Complains complains) {
		this.complains = complains;
	}
	public Usertype getUsertype() {
		return usertype;
	}
	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}
	public ComplainsVo(Complains complains, Usertype usertype) {
		super();
		this.complains = complains;
		this.usertype = usertype;
	}
	public ComplainsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
