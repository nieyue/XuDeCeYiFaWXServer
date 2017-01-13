package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试类
 * @author yy
 *
 */
public class Test implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 测试id
	 */
	private Integer testId;
	
	/**
	 * 名称
	 */
	private String  title;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 图片url
	 */
	private String img;
	/**
	 * 更新日期
	 */
	private Date updateDate;
	
	public Test() {
		super();
	}

	public Test(Integer testId, String title, String type, Integer level,
			String img, Date updateDate) {
		super();
		this.testId = testId;
		this.title = title;
		this.type = type;
		this.level = level;
		this.img = img;
		this.updateDate = updateDate;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	
}
