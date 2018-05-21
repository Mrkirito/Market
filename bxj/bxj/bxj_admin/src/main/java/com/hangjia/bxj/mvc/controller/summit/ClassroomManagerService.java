package com.hangjia.bxj.mvc.controller.summit;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.summit.SummitManagerDao;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.summit.SummitManagerQuery;

@Service
@Transactional
public class ClassroomManagerService {
	@Autowired
	private SummitManagerDao summitManagerDao; 
	
	/**
	 * 显示路径 
	 */
	@Value("${show_path}")
	private String showPath;

	private static final Log log = LogFactory.getLog(ClassroomManagerService.class);
    /**
     * 保存或修改峰会讲师
     * @param classRoom
     * @return
     */
	@MethodLog(remark = "更新峰会讲师")
	public Integer saveOrUpdateTeacher(ClassroomEntity classRoom){
		Integer id=classRoom.getId();
		if(id==null){
			 summitManagerDao.saveSummitClass(classRoom); //保存讲师
			 //保存ppt图片
			 summitManagerDao.saveTeacherImages(classRoom.getId(), classRoom.getImages());
			return classRoom.getId();
		}else{
			Integer count= summitManagerDao.updateSummitClass(classRoom);
			if (log.isDebugEnabled()) {
				log.debug("更新峰会讲师" + count + "条记录");
			}
			if(classRoom.getId()!=null && classRoom.getImages()!=null){
				 summitManagerDao.deleteImgs(classRoom.getId()); //删除ppt
				 //保存ppt图片
				 summitManagerDao.saveTeacherImages(classRoom.getId(), classRoom.getImages());
			 }
			return count;
		}
		
	}
	
	/**
	 * 查询讲师 列表 
	 * @param params
	 * @return
	 */
	@Transactional(readOnly=true)
	public Result<List<ClassroomEntity>> paginationQueryTeachers(SummitManagerQuery params) {
		//返回对象
		Result<List<ClassroomEntity>> result = new Result<List<ClassroomEntity>>();
		//总条数
		int total = summitManagerDao.sumTeachers(params);
		
		if (total > 0) {
			List<ClassroomEntity> list = summitManagerDao.listSummitTeachers(params);
			for (ClassroomEntity classRoomEntity : list) { 
				classRoomEntity.setImgFileUrl(showPath+classRoomEntity.getImageUrl());
			}
			result.setModel(list);
		}
		params.setTotalItem(total);
		result.setQuery(params);
		
		return result;
	}
	
	/**
	 * 查询单个 讲师 
	 * @param params
	 * @return
	 */
	@Transactional(readOnly=true)
	public ClassroomEntity queryOneTeachers(SummitManagerQuery params) {
		  ClassroomEntity  entity = summitManagerDao.selSummitTeachers(params);
			if (entity!=null) { 
				entity.setImgFileUrl(showPath+entity.getImageUrl());
				//获取图片
				List<ClassroomEntity> listImgs=summitManagerDao.selAllImgs(params); // imageUrl
				if(listImgs!=null && listImgs.size()>0){
					String[] imgs=new String[listImgs.size()];
					for (int i = 0; i < listImgs.size(); i++) {
						imgs[i] = showPath+listImgs.get(i).getImageUrl();
					}
					entity.setShowImages(imgs);
				}
			}
		return entity;
	}
}
