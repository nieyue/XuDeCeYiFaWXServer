package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Answer;
import com.nieyue.dao.AnswerDao;
import com.nieyue.service.AnswerService;
@Service("answerService")
public class AnswerServiceImpl implements AnswerService{
	@Resource
	AnswerDao answerDao;

	@Override
	public boolean addAnswer(Answer answer) {
		answer.setUpdateDate(new Date());
		boolean b = answerDao.addAnswer(answer);
		return b;
	}

	@Override
	public boolean delAnswer(Integer answerId) {
		boolean b = answerDao.delAnswer(answerId);
		return b;
	}

	@Override
	public boolean updateAnswer(Answer answer) {
		answer.setUpdateDate(new Date());
		boolean b = answerDao.updateAnswer(answer);
		return b;
	}

	@Override
	public Answer loadAnswer(Integer answerId) {
		Answer r = answerDao.loadAnswer(answerId);
		return r;
	}

	@Override
	public int countAll() {
		int c = answerDao.countAll();
		return c;
	}

	@Override
	public List<Answer> browsePagingAnswer(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Answer> l = answerDao.browsePagingAnswer(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}


	@Override
	public List<Answer> browseAllAnswer(String orderName, String orderWay) {
			List<Answer> l = answerDao.browseAllAnswer(orderName, orderWay);
			return l;
	}

	@Override
	public List<Answer> browseAllAnswerByProblemId(Integer problemId,
			String orderName, String orderWay) {
		List<Answer> l = answerDao.browseAllAnswerByProblemId(problemId,orderName, orderWay);
		return l;
	}

}
