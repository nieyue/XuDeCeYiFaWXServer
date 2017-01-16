package com.nieyue.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.bean.Answer;
import com.nieyue.bean.Problem;
import com.nieyue.bean.Test;
import com.nieyue.exception.StateResult;
import com.nieyue.service.AnswerService;
import com.nieyue.service.ProblemService;
import com.nieyue.service.TestService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.FileUploadUtil;
import com.nieyue.util.UploaderPath;


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
	@Resource
	private ProblemService problemService;
	@Resource
	private AnswerService answerService;
	
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
	 * 后台测试增加
	 * @return 
	 */
	@RequestMapping(value = "/add/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addALlTest(@RequestParam("testDTO") String testDTO, HttpSession session) {
		boolean am=false;
		Test test=new Test();
		JSONObject jsonObject = JSONObject.fromObject(testDTO);
		test.setTitle((String)jsonObject.get("title"));
		test.setType((String)jsonObject.get("type"));
		test.setLevel((Integer)jsonObject.get("level"));
		test.setImg((String)jsonObject.get("img"));
		am=testService.addTest(test);
		
		Object problemList = jsonObject.get("problemList");
		JSONArray problemListArray = JSONArray.fromObject(problemList);
		Iterator<JSONObject> pi = problemListArray.iterator();
		 while (pi.hasNext()) {
			 Problem problem=new Problem();
			 JSONObject problemDTO = pi.next();
             problem.setName(problemDTO.getString("name"));
             problem.setType(problemDTO.getString("type"));
             problem.setImg(problemDTO.getString("img"));
             problem.setOrderNumber(problemDTO.getInt("order_number"));
             problem.setTestId(test.getTestId());
             am=problemService.addProblem(problem);
             
             Object answerList = problemDTO.get("answerList");
     		JSONArray answerListArray = JSONArray.fromObject(answerList);
     		Iterator<JSONObject> ai = answerListArray.iterator();
     		 while (ai.hasNext()) {
     			 Answer answer=new Answer();
     			JSONObject answerDTO = ai.next();
     			 answer.setName(answerDTO.getString("name"));
     			 answer.setType(answerDTO.getString("type"));
     			 answer.setImg(answerDTO.getString("img"));
     			 answer.setResult(answerDTO.getString("result"));
     			 answer.setProblemId(problem.getProblemId());
     			 am=answerService.addAnswer(answer);
     		 }
         }
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
	/**
	 * 图片增加
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String addAdvertiseImg(
			@RequestParam("testFileUpload") MultipartFile file,
			HttpSession session ) throws IOException  {
		String imgUrl = null;
		String imgdir=DateUtil.getImgDir();
		try{
			imgUrl = FileUploadUtil.FormDataMerImgFileUpload(file, session,UploaderPath.GetValueByKey(UploaderPath.ROOTPATH),UploaderPath.GetValueByKey(UploaderPath.IMG),imgdir);
		}catch (IOException e) {
			throw new IOException();
		}
		return imgUrl;
	}
	
}
