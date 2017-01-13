package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 答案类
 * @author yy
 *
 */
public class Answer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 答案id
	 */
	private Integer answerId;
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
	 * 返回结果
	 */
	private String result;
	/**
	 * 更新日期
	 */
	private Date updateDate;
	/**
	 * 问题id外键
	 */
	private Integer problemId;
	
	public Answer() {
		super();
	}

	public Answer(Integer answerId, String name, String type, String img,
			String result, Date updateDate, Integer problemId) {
		super();
		this.answerId = answerId;
		this.name = name;
		this.type = type;
		this.img = img;
		this.result = result;
		this.updateDate = updateDate;
		this.problemId = problemId;
	}

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}


}
