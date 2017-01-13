package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Problem;
import com.nieyue.dao.ProblemDao;
import com.nieyue.service.ProblemService;
@Service("problemService")
public class ProblemServiceImpl implements ProblemService{
	@Resource
	ProblemDao problemDao;

	@Override
	public boolean addProblem(Problem problem) {
		problem.setUpdateDate(new Date());
		boolean b = problemDao.addProblem(problem);
		return b;
	}

	@Override
	public boolean delProblem(Integer problemId) {
		boolean b = problemDao.delProblem(problemId);
		return b;
	}

	@Override
	public boolean updateProblem(Problem problem) {
		problem.setUpdateDate(new Date());
		boolean b = problemDao.updateProblem(problem);
		return b;
	}

	@Override
	public Problem loadProblem(Integer problemId) {
		Problem r = problemDao.loadProblem(problemId);
		return r;
	}

	@Override
	public int countAll() {
		int c = problemDao.countAll();
		return c;
	}

	@Override
	public List<Problem> browsePagingProblem(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Problem> l = problemDao.browsePagingProblem(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}


	@Override
	public List<Problem> browseAllProblem(String orderName, String orderWay) {
			List<Problem> l = problemDao.browseAllProblem(orderName, orderWay);
			return l;
	}

}
