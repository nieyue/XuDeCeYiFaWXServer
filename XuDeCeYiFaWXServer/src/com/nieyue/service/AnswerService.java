package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Answer;

/**
 * 答案逻辑层接口
 * @author yy
 *
 */
public interface AnswerService {
	/** 新增答案 */	
	public boolean addAnswer(Answer answer) ;	
	/** 删除答案  */	
	public boolean delAnswer(Integer answerId) ;
	/** 更新答案 */	
	public boolean updateAnswer(Answer answer);
	/** 装载 答案 */	
	public Answer loadAnswer(Integer answerId);	
	/** 答案 总共数目 */	
	public int countAll();	
	/** 答案 分页信息 */
	public List<Answer> browsePagingAnswer(int pageNum,int pageSize,String orderName,String orderWay);		
	/** 答案 全部信息 */
	public List<Answer> browseAllAnswer(String orderName,String orderWay) ;	
	/** 根据问题答案 全部信息 */
	public List<Answer> browseAllAnswerByProblemId(Integer problemId,String orderName,String orderWay) ;		
}
