package com.hangjia.bxj.service.online;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.online.SalesOnlineCourse;
import com.hangjia.bxj.model.online.SalesOnlineCourseQuery;

public interface IOnlineService {
	Result getResultByPage(SalesOnlineCourseQuery query);

	SalesOnlineCourse getSalesOnlineCourseById(Long id);
	
	Result updateSalesOnlineCourse(SalesOnlineCourse course);
	
	Result insertSalesOnlineCourse(SalesOnlineCourse course);
	
	Result queryLectures();
}
