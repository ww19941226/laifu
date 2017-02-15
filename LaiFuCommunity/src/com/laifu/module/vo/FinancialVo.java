package com.laifu.module.vo;

public class FinancialVo {

	private String name;
	private double value;
	private String color;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public FinancialVo(String name, double value, String color) {
		super();
		this.name = name;
		this.value = value;
		this.color = color;
	}
	public FinancialVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
