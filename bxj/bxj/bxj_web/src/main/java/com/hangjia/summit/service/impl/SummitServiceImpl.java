package com.hangjia.summit.service.impl;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.summit.SummitShowDao;
import com.hangjia.bxj.model.summit.ChampionSummitListEntry;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.query.summit.SummitManagerQuery;
import com.hangjia.summit.service.ISummitService;

@Service
@Transactional
public class SummitServiceImpl implements ISummitService{
	
	@Autowired
	private SummitShowDao summitDao; 
	
	private static final Log log = LogFactory.getLog(SummitServiceImpl.class);
	/**
	 * 显示路径 
	 */
	@Value("${static_path}")
	private String staticPath;
	
	@Override
	@Transactional(readOnly=true)
	public Result<List<ChampionSummitListEntry>> querySummitList(SummitManagerQuery params) {
 		Result<List<ChampionSummitListEntry>> result=new Result<List<ChampionSummitListEntry>>();
 	    	//开始查询位置
			Integer firstIndex = (params.getCurrentPage() - 1) * params.getPageSize();
			params.setFirstIndex(firstIndex);
   	        //查询的时候多查1个
 		    Integer pageSize=params.getPageSize();
 			params.setPageSize(pageSize+1);
 			//查询峰会
			List<ChampionSummitListEntry> list = summitDao.listSummits(params);
			for (ChampionSummitListEntry championSummitListEntry : list) {
				championSummitListEntry.setImgFileUrl(staticPath+championSummitListEntry.getImageUrl());
			}
			//结果 比pagesize 多一个 
			int resultSize = list.size();
			// 如果查询出的记录数比 pageSize 多（因为多查了1个），说明有下一页。这样即可少查1次数据库。
			boolean hasNext = resultSize > pageSize;
			
			// 多查的那条记录去掉。
			if (hasNext) {
				list.remove(resultSize - 1);
			}
			result.setModel(list);
			result.setHasNext(hasNext);//hasNext
			
		return result;
	}
	
	@Override
	public Integer updateTeacher(ClassroomEntity classRoom){
		Integer id=classRoom.getId();
		if(id!=null){
			classRoom.setClickReal(classRoom.getClickReal()+1);
//			classRoom.setClickSham(classRoom.getClickSham()+1);
			Integer count= summitDao.updateSummitClass(classRoom);
			if (log.isDebugEnabled()) {
				log.debug("更新峰会讲师" + count + "条记录");
			}
			return count;
		}
		return id;
	}
	
	 
	@Override
	@Transactional(readOnly=true)
	public List<ClassroomEntity> paginationQueryTeachers(SummitManagerQuery params) {
			List<ClassroomEntity> list = summitDao.listSummitTeachers(params);
			for (ClassroomEntity classRoomEntity : list) { 
				classRoomEntity.setImgFileUrl(staticPath+classRoomEntity.getImageUrl());
			}
		
		return list;
	}
	
	 
	@Override
	@Transactional(readOnly=true)
	public ClassroomEntity queryOneTeachers(SummitManagerQuery params) {
		  ClassroomEntity  entity = summitDao.selSummitTeachers(params);
			if (entity!=null) { 
				entity.setImgFileUrl(staticPath+entity.getImageUrl());
			}
		return entity;
	}
	
	@Override
	public ClassroomEntity getImgArray(SummitManagerQuery params){
		ClassroomEntity entity=new ClassroomEntity(); 
		//获取图片
		List<ClassroomEntity> listImgs=summitDao.selAllImgs(params); // imageUrl
		if(listImgs!=null && listImgs.size()>0){
			String[] imgs=new String[listImgs.size()];
			for (int i = 0; i < listImgs.size(); i++) {
				imgs[i] = staticPath+listImgs.get(i).getImageUrl();
			}
			entity.setShowImages(imgs);
		}
		return entity;
	}
}
