package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Test;
import com.nieyue.dao.TestDao;
import com.nieyue.service.TestService;
@Service("testService")
public class TestServiceImpl implements TestService{
	@Resource
	TestDao testDao;

	@Override
	public boolean addTest(Test test) {
		test.setUpdateDate(new Date());
		boolean b = testDao.addTest(test);
		return b;
	}

	@Override
	public boolean delTest(Integer testId) {
		boolean b = testDao.delTest(testId);
		return b;
	}

	@Override
	public boolean updateTest(Test test) {
		test.setUpdateDate(new Date());
		boolean b = testDao.updateTest(test);
		return b;
	}

	@Override
	public Test loadTest(Integer testId) {
		Test r = testDao.loadTest(testId);
		return r;
	}

	@Override
	public int countAll() {
		int c = testDao.countAll();
		return c;
	}

	@Override
	public List<Test> browsePagingTest(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Test> l = testDao.browsePagingTest(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}


	@Override
	public List<Test> browseAllTest(String orderName, String orderWay) {
			List<Test> l = testDao.browseAllTest(orderName, orderWay);
			return l;
	}

	@Override
	public int countAllByType(String type) {
		int c = testDao.countAllByType(type);
		return c;
	}

	@Override
	public List<Test> browsePagingTestByType(String type, int pageNum,
			int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Test> l = testDao.browsePagingTestByType(type,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

}
