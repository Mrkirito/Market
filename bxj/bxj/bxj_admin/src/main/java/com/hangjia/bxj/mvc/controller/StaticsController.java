package com.hangjia.bxj.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.MemberIdeaDao;
import com.hangjia.bxj.dao.PlanGroupProInfosMapper;
import com.hangjia.bxj.model.MemberIdea;
import com.hangjia.bxj.mvc.util.DateUtils;
import com.hangjia.bxj.query.ProductQuery;

@Controller
@RequestMapping(value = "/statistics")
public class StaticsController {	
	@Autowired
	private MemberIdeaDao dao;
	@Autowired
	private PlanGroupProInfosMapper planGroupProInfosMapper;
    @RequestMapping("idea_list.jhtml")
    public ModelAndView statisticsBook(String key) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("begin",DateUtils.getPrefixDate(10));
    	map.put("end", DateUtils.getCurrentDate());
    	map.put("key",key);
    	ModelAndView view=new ModelAndView("statistics/idea_list",map);
        return view;
    }
	@RequestMapping("idea.json")
	@ResponseBody
	public Result statistics(ProductQuery query,HttpServletRequest request) {
		Result result = new Result();
		String begin = query.getBegin();
		String end = query.getEnd();
		if (StringUtils.isEmpty(begin) || StringUtils.isEmpty(end)) {
			query.setBegin(DateUtils.getPrefixDate(10));
			query.setEnd(DateUtils.getCurrentDate());
		}
		int count =0; 
		String key=request.getParameter("key");
		if(key.equals("idea")){
			count=dao.getIdeaCount(query);			
			if(count>0)result.setModel(dao.getIdeaData(query));
		}else if(key.equals("groups")){
			count=planGroupProInfosMapper.getQueryCount(query);
			if(count>0)	result.setModel(planGroupProInfosMapper.getQueryData(query));
		}
		query.setTotalItem(count);
		result.setQuery(query);
		return result;
	}
	
	@RequestMapping("updateIdea.json")
	@ResponseBody
	public Result updateIdea(MemberIdea idea) {
		Result result = new Result();
		int i=dao.updateMember(idea);
		result.setModel(idea);
		return result;
	}
}
