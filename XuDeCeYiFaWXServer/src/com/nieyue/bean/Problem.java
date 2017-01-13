package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题类
 * @author yy
 *
 */
public class Problem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 问题id
	 */
	private Integer problemId;
	/**
	 * 名称
	 */
	private String  name;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 图片url
	 */
	private String img;
	/**
	 * 更新日期
	 */
	private Date updateDate;
	/**
	 * 测试id外键
	 */
	private Integer testId;
	
	public Problem() {
		super();
	}

	public Problem(Integer problemId, String name, String type, String img,
			Date updateDate, Integer testId) {
		super();
		this.problemId = problemId;
		this.name = name;
		this.type = type;
		this.img = img;
		this.updateDate = updateDate;
		this.testId = testId;
	}

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

}
