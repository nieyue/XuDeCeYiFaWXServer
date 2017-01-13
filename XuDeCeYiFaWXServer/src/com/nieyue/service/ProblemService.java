package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Problem;

/**
 * 问题逻辑层接口
 * @author yy
 *
 */
public interface ProblemService {
	/** 新增问题 */	
	public boolean addProblem(Problem problem) ;	
	/** 删除问题  */	
	public boolean delProblem(Integer problemId) ;
	/** 更新问题 */	
	public boolean updateProblem(Problem problem);
	/** 装载 问题 */	
	public Problem loadProblem(Integer problemId);	
	/** 问题 总共数目 */	
	public int countAll();	
	/** 问题 分页信息 */
	public List<Problem> browsePagingProblem(int pageNum,int pageSize,String orderName,String orderWay);		
	/** 问题 全部信息 */
	public List<Problem> browseAllProblem(String orderName,String orderWay) ;		
}
