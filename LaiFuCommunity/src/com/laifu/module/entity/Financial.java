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
 * 物业财务统计表
 * @author Raindrops
 * @version 2016/9/1
 */
@Entity
@Table(name="tb_financial")
public class Financial implements Serializable {

	private static final long serialVersionUID = -731490733090452340L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "financial_id", nullable = false)
	private int financial_id;//统计表ID(主键)
	private int financial_userid;//用户ID(外键)
	private String financial_funds;//支出款项
	private double financial_money;//支出金额
	private Date financial_datetime;//支出时间
	
	
	public int getFinancial_id() {
		return financial_id;
	}
	public void setFinancial_id(int financial_id) {
		this.financial_id = financial_id;
	}
	public int getFinancial_userid() {
		return financial_userid;
	}
	public void setFinancial_userid(int financial_userid) {
		this.financial_userid = financial_userid;
	}
	public String getFinancial_funds() {
		return financial_funds;
	}
	public void setFinancial_funds(String financial_funds) {
		this.financial_funds = financial_funds;
	}
	public double getFinancial_money() {
		return financial_money;
	}
	public void setFinancial_money(double financial_money) {
		this.financial_money = financial_money;
	}
	public Date getFinancial_datetime() {
		return financial_datetime;
	}
	public void setFinancial_datetime(Date financial_datetime) {
		this.financial_datetime = financial_datetime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + financial_id;
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
		Financial other = (Financial) obj;
		if (financial_id != other.financial_id)
			return false;
		return true;
	}
	
	
}
