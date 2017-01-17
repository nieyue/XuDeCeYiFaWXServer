package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Test;

/**
 * 测试逻辑层接口
 * @author yy
 *
 */
public interface TestService {
	/** 新增测试 */	
	public boolean addTest(Test test) ;	
	/** 删除测试  */	
	public boolean delTest(Integer testId) ;
	/** 更新测试 */	
	public boolean updateTest(Test test);
	/** 装载 测试 */	
	public Test loadTest(Integer testId);	
	/** 测试 总共数目 */	
	public int countAll();	
	/** 根据类型测试 总共数目 */	
	public int countAllByType(String type);
	/** 根据类型测试 分页信息 */
	public List<Test> browsePagingTestByType(String type,int pageNum,int pageSize,String orderName,String orderWay) ;		
	/** 测试 分页信息 */
	public List<Test> browsePagingTest(int pageNum,int pageSize,String orderName,String orderWay);		
	/** 测试 全部信息 */
	public List<Test> browseAllTest(String orderName,String orderWay) ;		
}
