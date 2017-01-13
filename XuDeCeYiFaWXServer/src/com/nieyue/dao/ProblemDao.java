package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Problem;

/**
 * 问题数据库接口
 * @author yy
 *
 */
public interface ProblemDao {
	/** 新增问题 */	
	public boolean addProblem(Problem problem) ;	
	/** 删除问题  */	
	public boolean delProblem(Integer problemId) ;
	/** 更新问题 */	
	public boolean updateProblem(Problem problem);
	/** 装载问题  */	
	public Problem loadProblem(Integer problemId);
	/** 问题 总共数目 */	
	public int countAll();
	/** 问题 分页信息 */
	public List<Problem> browsePagingProblem(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 问题 全部信息 */
	public List<Problem> browseAllProblem(@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
