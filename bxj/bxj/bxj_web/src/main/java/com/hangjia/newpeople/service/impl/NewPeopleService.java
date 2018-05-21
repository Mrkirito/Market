package com.hangjia.newpeople.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.NewPeopleCourseMapper;
import com.hangjia.bxj.dao.NewPeopleVideoMapper;
import com.hangjia.bxj.model.NewPeopleCourse;
import com.hangjia.bxj.model.NewPeopleVideo;
import com.hangjia.bxj.vo.NewPeoplePassVo;
import com.hangjia.newpeople.service.InewPeopleService;

/**
 * 新人通 实现类
 * @ClassName: NewPeopleService
 * @Description: 
 * @author: he-Yi
 * @date: 2016年5月9日 下午2:39:20
 */
@Service
public class NewPeopleService implements InewPeopleService{
 
	/**
	 * 视频dao
	 */
	@Autowired
	NewPeopleVideoMapper newVDdao;
	
	/**	
	 * 课件dao
	 */
	@Autowired
	NewPeopleCourseMapper newCourseDao;

	@Value("${static_path}")
	private String staticPath;
	
	@Override
	public NewPeoplePassVo selByPKgetCourse(NewPeoplePassVo newPeoplePassVo) {
		NewPeopleCourse newCourse=new NewPeopleCourse();
		BeanUtils.copyProperties(newPeoplePassVo, newCourse);
		//获取课件信息
		NewPeopleCourse newCourseget= newCourseDao.selectByPrimaryKey(newCourse.getId());
		//返回课件vo
		NewPeoplePassVo newPeoplePassVoReturn=new NewPeoplePassVo();
		BeanUtils.copyProperties(newCourseget, newPeoplePassVoReturn);
		return newPeoplePassVoReturn;
	}
	
	
	@Override
	public NewPeoplePassVo selOneNewpeopleVD(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return newVDdao.selOneNewpeople(map);
	}

	@Override
	@Transactional
	public void updateNewPeople(NewPeoplePassVo newPeoplePassVo) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		//获取真实次数数据
		NewPeopleCourse getcourseInfo=newCourseDao.selTrueCourseInfo(newPeoplePassVo.getId());
		map.put("id", getcourseInfo.getId());
		map.put("playCount", getcourseInfo.getPlayCount()+1); //更新播放次数 
		map.put("falseCount", getcourseInfo.getFalseCount()+1); //更新次数 
		newCourseDao.updateCountByPK(map);
	}

	
	@Override
	public List<NewPeoplePassVo> selList(NewPeoplePassVo newPeoplePassVo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if(newPeoplePassVo.getDay() !=null && newPeoplePassVo.getNewText()!=null){
			map.put("day", newPeoplePassVo.getDay());
		}
		List<NewPeoplePassVo> listNewpeopleVD =newVDdao.selList(map);
		List<NewPeoplePassVo> listNewpeos=new ArrayList<NewPeoplePassVo>();
		for (NewPeoplePassVo newPeoplePassVo2 : listNewpeopleVD) {
			//初始列表信息  
			listNewpeos.add(newPeoplePassVo2);
		}
		return listNewpeopleVD.isEmpty()?null:listNewpeos;
	}


	@Override
	public List<NewPeoplePassVo> selListNewPeoCourse(NewPeoplePassVo newPeoplePassVo,int index,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("userId", newPeoplePassVo.getUserId());
		map.put("day", newPeoplePassVo.getDay());
		map.put("start", start);
		map.put("end", pageSize);	
		//查询 课件 
		List<NewPeoplePassVo> listCourse=newCourseDao.selListNewCourse(map);
		List<NewPeoplePassVo> listgetCourses=new ArrayList<NewPeoplePassVo>();
		for (NewPeoplePassVo newPeoplePassVo2 : listCourse) {
			//newPeoplePassVo2.setCourseImageUrl(staticPath+"/"+newPeoplePassVo2.getCourseImageUrl()); //课件图片地址 
			listgetCourses.add(newPeoplePassVo2);
		}
		return listCourse.isEmpty()?null:listgetCourses;
	}

	@Override
	public List<NewPeoplePassVo> selListNewPeopleVD(NewPeoplePassVo newPeoplePassVo, int index, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("userId", newPeoplePassVo.getUserId());
		map.put("day", newPeoplePassVo.getDay());
		map.put("start", start);
		map.put("end", pageSize);	
		//查询 视频 
		List<NewPeoplePassVo> listNewpeopleVD =newVDdao.selListNewpeopleVD(map);
		List<NewPeoplePassVo> listNewpeos=new ArrayList<NewPeoplePassVo>();
		for (NewPeoplePassVo newPeoplePassVo2 : listNewpeopleVD) {
			//newPeoplePassVo2.setCoverImageUrl(staticPath+"/"+newPeoplePassVo2.getCoverImageUrl()); //视频图片地址 
			//视频 
			listNewpeos.add(newPeoplePassVo2);
		}
		return listNewpeopleVD.isEmpty()?null:listNewpeos;
	}

	@Override
	public void updateNum(Long id) {
			NewPeopleVideo newpeo=new NewPeopleVideo();
			if(id!=null){
				newpeo.setId(id); 
				newVDdao.updateNumAdd(newpeo);
			}
	}

	
	
	
}
