package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Test;

/**
 * 测试数据库接口
 * @author yy
 *
 */
public interface TestDao {
	/** 新增测试 */	
	public boolean addTest(Test test) ;	
	/** 删除测试  */	
	public boolean delTest(Integer testId) ;
	/** 更新测试 */	
	public boolean updateTest(Test test);
	/** 装载测试  */	
	public Test loadTest(Integer testId);
	/** 测试 总共数目 */	
	public int countAll();
	/** 根据类型测试 总共数目 */	
	public int countAllByType(String type);
	/** 根据类型测试 分页信息 */
	public List<Test> browsePagingTestByType(@Param("type")String type,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 测试 分页信息 */
	public List<Test> browsePagingTest(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 测试 全部信息 */
	public List<Test> browseAllTest(@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
