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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.bean.Answer;
import com.nieyue.bean.Problem;
import com.nieyue.bean.Test;
import com.nieyue.comments.MyDateFormatter;
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
	 * 根据类别测试分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/type", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<Test> browsePagingTestByType(
			@RequestParam(value="type")String type,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="test_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Test> list = new ArrayList<Test>();
			list= testService.browsePagingTestByType(type,pageNum, pageSize, orderName, orderWay);
			return list;
	}
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
	 * 后台测试测试修改
	 * @return
	 */
	@RequestMapping(value = "/update/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAllTest(@RequestParam("testDTO") String testDTO,HttpSession session)  {
		boolean am=false;
		JSONObject jsonObject = JSONObject.fromObject(testDTO);
		Integer testId = (Integer) jsonObject.get("test_id");
		Test test=testService.loadTest(testId);
		test.setTitle((String)jsonObject.get("title"));
		test.setType((String)jsonObject.get("type"));
		test.setLevel((Integer)jsonObject.get("level"));
		test.setImg((String)jsonObject.get("img"));
		am=testService.updateTest(test);
		
		Object problemList = jsonObject.get("problemList");
		JSONArray problemListArray = JSONArray.fromObject(problemList);
		Iterator<JSONObject> pi = problemListArray.iterator();
		 while (pi.hasNext()) {
			 JSONObject problemDTO = pi.next();
			 Problem problem=new Problem();
			 if(problemDTO.get("problem_id")!=null&&!problemDTO.get("problem_id").equals("")){
			 Integer problemId = problemDTO.getInt("problem_id");
			 problem=problemService.loadProblem(problemId);
			 }
             problem.setName(problemDTO.getString("name"));
             problem.setType(problemDTO.getString("type"));
             problem.setImg(problemDTO.getString("img"));
             problem.setOrderNumber((Integer)problemDTO.get("order_number"));
             problem.setTestId(test.getTestId());
             if(problemDTO.get("problem_id")!=null&&!problemDTO.get("problem_id").equals("")){
            	 am=problemService.updateProblem(problem);
             }else{
            	 am=problemService.addProblem(problem);
             }
             
             Object answerList = problemDTO.get("answerList");
     		JSONArray answerListArray = JSONArray.fromObject(answerList);
     		Iterator<JSONObject> ai = answerListArray.iterator();
     		 while (ai.hasNext()) {
     			 Answer answer=new Answer();
     			JSONObject answerDTO = ai.next();
     			if(answerDTO.get("answer_id")!=null&&!answerDTO.get("answer_id").equals("")){
     				 Integer answerId = answerDTO.getInt("answer_id");
     				 answer=answerService.loadAnswer(answerId);
     				 }
     			 answer.setName(answerDTO.getString("name"));
     			 answer.setType(answerDTO.getString("type"));
     			 answer.setImg(answerDTO.getString("img"));
     			 answer.setResult(answerDTO.getString("result"));
     			 answer.setProblemId(problem.getProblemId());
     			if(answerDTO.get("answer_id")!=null&&!answerDTO.get("answer_id").equals("")){
     			 am=answerService.updateAnswer(answer);
     			}else{
     			am=answerService.addAnswer(answer);
     			}
     		 }
         }
		return StateResult.getSR(am);
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
	public @ResponseBody StateResult addAllTest(@RequestParam("testDTO") String testDTO, HttpSession session) {
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
		List<Problem> pl = problemService.browseAllProblemByTestId(testId, "order_number", "asc");
		for (int i = 0; i < pl.size(); i++) {
			Problem problem = pl.get(i);
			List<Answer> al = answerService.browseAllAnswerByProblemId(problem.getProblemId(), "answer_id", "asc");
			for (int j = 0; j < al.size(); j++) {
				answerService.delAnswer(al.get(j).getAnswerId());
			}
			problemService.delProblem(pl.get(i).getProblemId());
		}
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
	 * 根据类别测试浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count/type", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAllByType(@RequestParam("type")String type,HttpSession session)  {
		int count = testService.countAllByType(type);
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
	 * 测试单个加载一套
	 * @return
	 */
	@RequestMapping(value = "/{testId}/all", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String loadAllTest(@PathVariable("testId") Integer testId,HttpSession session)  {
		Test test=new Test();
		test = testService.loadTest(testId);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("test_id", testId);
		if(test.getTitle()==null){
			test.setTitle("");
		}
		jsonObj.put("title", test.getTitle());
		if(test.getType()==null){
			test.setType("");
		}
		jsonObj.put("type", test.getType());
		if(test.getLevel()==null){
			test.setLevel(10);
		}
		jsonObj.put("level", test.getLevel());
		if(test.getImg()==null){
			test.setImg("");
		}
		jsonObj.put("img", test.getImg());
		jsonObj.put("update_date",DateUtil.getFormatDate(test.getUpdateDate()));
		
		JSONArray problemListArray = new JSONArray();
		List<Problem> pl = problemService.browseAllProblemByTestId(test.getTestId(), "order_number", "asc");
		for (int i = 0; i < pl.size(); i++) {
			Problem problem = pl.get(i);
			JSONObject problemjsonobj = new JSONObject();
			problemjsonobj.put("problem_id", problem.getProblemId());
			
			if(problem.getName()==null){
				problem.setName("");
			}
			problemjsonobj.put("name", problem.getName());
			
			if(problem.getType()==null){
				problem.setType("");
			}
			problemjsonobj.put("type", problem.getType());
			
			if(problem.getOrderNumber()==null){
				problem.setOrderNumber(1);
			}
			problemjsonobj.put("order_number", problem.getOrderNumber());
			
			if(problem.getImg()==null){
				problem.setImg("");
			}
			problemjsonobj.put("img", problem.getImg());
			problemjsonobj.put("update_date", DateUtil.getFormatDate(problem.getUpdateDate()));
			
			JSONArray answerListArray = new JSONArray();
			List<Answer> al = answerService.browseAllAnswerByProblemId(problem.getProblemId(), "answer_id", "asc");
			for (int j = 0; j < al.size(); j++) {
				Answer answer = al.get(j);
				JSONObject answerjsonobj = new JSONObject();
				answerjsonobj.put("answer_id", answer.getAnswerId());
				
				if(answer.getName()==null){
					answer.setName("");
				}
				answerjsonobj.put("name", answer.getName());
				
				if(answer.getType()==null){
					answer.setType("");
				}
				answerjsonobj.put("type", answer.getType());
				
				if(answer.getResult()==null){
					answer.setResult("");
				}
				answerjsonobj.put("result", answer.getResult());
				
				if(answer.getImg()==null){
					answer.setImg("");
				}
				answerjsonobj.put("img", answer.getImg());
				answerjsonobj.put("update_date", DateUtil.getFormatDate(answer.getUpdateDate()));
				answerListArray.add(answerjsonobj);
				
			}
			problemjsonobj.put("answerList",answerListArray);
			
			problemListArray.add(problemjsonobj);
			
		}
		jsonObj.put("problemList",problemListArray);
		return jsonObj.toString();
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
