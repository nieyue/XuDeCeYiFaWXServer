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

import com.nieyue.bean.Problem;
import com.nieyue.exception.StateResult;
import com.nieyue.service.ProblemService;


/**
 * 问题控制类
 * @author yy
 *
 */
@Controller("problemController")
@RequestMapping("/problem")
public class ProblemController {
	@Resource
	private ProblemService problemService;
	
	/**
	 * 问题分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Problem> browsePagingProblem(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="problem_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Problem> list = new ArrayList<Problem>();
			list= problemService.browsePagingProblem(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 问题全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Problem> browseAllProblem(
			@RequestParam(value="orderName",required=false,defaultValue="problem_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Problem> list = new ArrayList<Problem>();
		list= problemService.browseAllProblem( orderName, orderWay);
		return list;
	}
	/**
	 * 问题修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateProblem(@ModelAttribute Problem problem,HttpSession session)  {
		boolean um = problemService.updateProblem(problem);
		return StateResult.getSR(um);
	}
	
	/**
	 * 问题增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addProblem(@ModelAttribute Problem problem, HttpSession session) {
		boolean am = problemService.addProblem(problem);
		return StateResult.getSR(am);
	}
	/**
	 *问题 删除
	 * @return
	 */
	@RequestMapping(value = "/{problemId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delProblem(@PathVariable("problemId") Integer problemId,HttpSession session)  {
		boolean dm = problemService.delProblem(problemId);
		return StateResult.getSR(dm);
	}
	/**
	 * 问题浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = problemService.countAll();
		return count;
	}
	/**
	 * 问题单个加载
	 * @return
	 */
	@RequestMapping(value = "/{problemId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Problem loadProblem(@PathVariable("problemId") Integer problemId,HttpSession session)  {
		Problem problem=new Problem();
		problem = problemService.loadProblem(problemId);
		return problem;
	}

	
}
