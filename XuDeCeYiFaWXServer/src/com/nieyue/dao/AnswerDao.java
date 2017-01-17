package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Answer;

/**
 * 答案数据库接口
 * @author yy
 *
 */
public interface AnswerDao {
	/** 新增答案 */	
	public boolean addAnswer(Answer answer) ;	
	/** 删除答案  */	
	public boolean delAnswer(Integer answerId) ;
	/** 更新答案 */	
	public boolean updateAnswer(Answer answer);
	/** 装载答案  */	
	public Answer loadAnswer(Integer answerId);
	/** 答案 总共数目 */	
	public int countAll();
	/** 答案 分页信息 */
	public List<Answer> browsePagingAnswer(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 答案 全部信息 */
	public List<Answer> browseAllAnswer(@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 根据问题答案 全部信息 */
	public List<Answer> browseAllAnswerByProblemId(@Param("problemId")Integer problemId,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
