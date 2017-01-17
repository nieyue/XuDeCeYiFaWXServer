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

import com.nieyue.bean.Answer;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AnswerService;


/**
 * 答案控制类
 * @author yy
 *
 */
@Controller("answerController")
@RequestMapping("/answer")
public class AnswerController {
	@Resource
	private AnswerService answerService;
	
	/**
	 * 答案分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Answer> browsePagingAnswer(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="answer_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Answer> list = new ArrayList<Answer>();
			list= answerService.browsePagingAnswer(pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 答案全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Answer> browseAllAnswer(
			@RequestParam(value="orderName",required=false,defaultValue="answer_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Answer> list = new ArrayList<Answer>();
		list= answerService.browseAllAnswer( orderName, orderWay);
		return list;
	}
	/**
	 * 根据问题 答案全部查询
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/all/problem", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Answer> browseAllAnswerByProblemId(
			@RequestParam(value="problemId") Integer problemId,
			@RequestParam(value="orderName",required=false,defaultValue="answer_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Answer> list = new ArrayList<Answer>();
		list= answerService.browseAllAnswerByProblemId(problemId, orderName, orderWay);
		return list;
	}
	/**
	 * 答案修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAnswer(@ModelAttribute Answer answer,HttpSession session)  {
		boolean um = answerService.updateAnswer(answer);
		return StateResult.getSR(um);
	}
	
	/**
	 * 答案增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAnswer(@ModelAttribute Answer answer, HttpSession session) {
		boolean am = answerService.addAnswer(answer);
		return StateResult.getSR(am);
	}
	/**
	 *答案 删除
	 * @return
	 */
	@RequestMapping(value = "/{answerId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAnswer(@PathVariable("answerId") Integer answerId,HttpSession session)  {
		boolean dm = answerService.delAnswer(answerId);
		return StateResult.getSR(dm);
	}
	/**
	 * 答案浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = answerService.countAll();
		return count;
	}
	/**
	 * 答案单个加载
	 * @return
	 */
	@RequestMapping(value = "/{answerId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Answer loadAnswer(@PathVariable("answerId") Integer answerId,HttpSession session)  {
		Answer answer=new Answer();
		answer = answerService.loadAnswer(answerId);
		return answer;
	}

	
}
