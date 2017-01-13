package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员类
 * @author yy
 *
 */
public class Admin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 管理员id
	 */
	private Integer adminId;
	
	/**
	 * 管理者姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String cellPhone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 账号创建时间
	 */
	private Date createDate;
	/**
	 * 最新登录时间
	 */
	private Date lastLoginDate;
	
	public Admin() {
		super();
	}


	public Admin(Integer adminId, String name, String cellPhone, String email,
			String password, Date createDate, Date lastLoginDate) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.cellPhone = cellPhone;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
	}


	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}



}
