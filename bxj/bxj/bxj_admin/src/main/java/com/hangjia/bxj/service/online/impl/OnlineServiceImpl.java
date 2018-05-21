package com.hangjia.bxj.service.online.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.SalesOnlineCourseMapper;
import com.hangjia.bxj.model.online.SalesOnlineCourse;
import com.hangjia.bxj.model.online.SalesOnlineCourseQuery;
import com.hangjia.bxj.service.online.IOnlineService;
@Service
@Transactional(rollbackFor = Throwable.class)
public class OnlineServiceImpl implements IOnlineService {
	@Autowired
	private SalesOnlineCourseMapper salesOnlineCourseMapper;

	@Override
	public Result getResultByPage(SalesOnlineCourseQuery query) {
		Result result = new Result();
    	int	count = salesOnlineCourseMapper.selectCount(query);
    	if(count > 0){
    		List<SalesOnlineCourse> userCardList = salesOnlineCourseMapper.selectByPage(query);
    		result.setModel(userCardList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
    	return result;
	}

	@Override
	public Result updateSalesOnlineCourse(SalesOnlineCourse course) {
    	Result result = new Result();
    	int update =salesOnlineCourseMapper.updateByPrimaryKey(course);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("修改失败");
    	}
        return result;
	}

	@Override
	public Result insertSalesOnlineCourse(SalesOnlineCourse course) {
    	Result result = new Result();
    	int update =salesOnlineCourseMapper.insertSelective(course);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("添加失败");
    	}
        return result;
	}

	@Override
	public SalesOnlineCourse getSalesOnlineCourseById(Long id) {
		return salesOnlineCourseMapper.selectByPrimaryKey(id);
	}

	@Override
	public Result queryLectures() {
		Result result = new Result();
		result.setModel(salesOnlineCourseMapper.queryAllSalesOnlineLecturer());
		return result;
	}
}
