package com.laifu.module.entity;

public class SendCode {
	
	private int code;
	private String message;
	public SendCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SendCode(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
