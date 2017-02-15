package com.laifu.module.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.laifu.module.web.validator.DateFormat;

/**
 * 测试类People
 * 
 * @author Raindrops
 * @version 2016/8/30
 */
@Entity
@Table(name = "people")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
// 开启缓存
public class People implements Serializable {

	private static final long serialVersionUID = 1203716900200822347L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Pattern(regexp = "[A-Za-z0-9]{5,20}", message = "{username.illegal}")
	// java validator验证（用户名字母数字组成，长度为5-10）
	private String username;

	@NotEmpty(message = "{email.illegal}")
	@Email(message = "{email.illegal}")
	// 错误消息会自动到MessageSource中查找
	private String email;

	@Pattern(regexp = "[A-Za-z0-9]{5,20}", message = "{password.illegal}")
	private String password;

	@DateFormat(message = "{register.date.error}")
	// 自定义的验证器
	private Date registerDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", username=" + username + ", email="
				+ email + ", password=" + password + ", registerDate="
				+ registerDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		People other = (People) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
