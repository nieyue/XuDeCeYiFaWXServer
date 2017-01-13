package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Test;
import com.nieyue.exception.StateResult;
import com.nieyue.service.TestService;


/**
 * 测试控制类
 * @author yy
 *
 */
@Controller("testController")
@RequestMapping("/test")
public class TestController {
	@Resource
	private TestService testService;
	
	/**
	 * 测试分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Test> browsePagingTest(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="test_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Test> list = new ArrayList<Test>();
			list= testService.browsePagingTest(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 测试全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Test> browseAllTest(
			@RequestParam(value="orderName",required=false,defaultValue="test_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Test> list = new ArrayList<Test>();
		list= testService.browseAllTest( orderName, orderWay);
		return list;
	}
	/**
	 * 测试修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateTest(@ModelAttribute Test test,HttpSession session)  {
		boolean um = testService.updateTest(test);
		return StateResult.getSR(um);
	}
	
	/**
	 * 测试增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addTest(@ModelAttribute Test test, HttpSession session) {
		boolean am = testService.addTest(test);
		return StateResult.getSR(am);
	}
	/**
	 *测试 删除
	 * @return
	 */
	@RequestMapping(value = "/{testId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delTest(@PathVariable("testId") Integer testId,HttpSession session)  {
		boolean dm = testService.delTest(testId);
		return StateResult.getSR(dm);
	}
	/**
	 * 测试浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = testService.countAll();
		return count;
	}
	/**
	 * 测试单个加载
	 * @return
	 */
	@RequestMapping(value = "/{testId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Test loadTest(@PathVariable("testId") Integer testId,HttpSession session)  {
		Test test=new Test();
		test = testService.loadTest(testId);
		return test;
	}

	
}
