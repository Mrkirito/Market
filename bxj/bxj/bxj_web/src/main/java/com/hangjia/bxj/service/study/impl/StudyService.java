package com.hangjia.bxj.service.study.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hangjia.bxj.dao.StudyCollectionMapper;
import com.hangjia.bxj.dao.StudyCommentMapper;
import com.hangjia.bxj.dao.StudyCommentRelMapper;
import com.hangjia.bxj.dao.StudyDetailMapper;
import com.hangjia.bxj.dao.StudyShareMapper;
import com.hangjia.bxj.model.StudyCollection;
import com.hangjia.bxj.model.StudyComment;
import com.hangjia.bxj.model.StudyCommentRel;
import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.model.StudyShare;
import com.hangjia.bxj.service.study.IstudyService;
import com.hangjia.bxj.util.DateUtil;
import com.hangjia.bxj.vo.Pagination;

@Service
public class StudyService implements IstudyService {

	@Autowired
	StudyDetailMapper detailMapper;

	@Autowired
	StudyShareMapper shareMapper;

	@Autowired
	private StudyCollectionMapper studyCollectionMapper;
	
	@Autowired
	private StudyCommentMapper studyCommentMapper;
	@Autowired
	private StudyCommentRelMapper studyCommentRelMapper;

	@Value("${static_path}")
	private String staticPath;

	@Override
	public StudyDetail selectByPK(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		return detailMapper.selectByPrimaryKey(id);
	}

	@Override
	public StudyShare selectShareByPK(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		return shareMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertShare(StudyShare entity) {
		// TODO Auto-generated method stub
		return shareMapper.insertSelective(entity);
	}

	@Override
	public StudyShare selShareByPK(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		return shareMapper.selectByPrimaryKey(id);
	}

	@Override
	public StudyShare selectBystudyID(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return shareMapper.selectBystudyID(map);
	}

	/**
	 * 查询置顶List(分页)
	 */
	@Override
	public List<StudyDetail> queryUpList(StudyDetail record, int index, int pageSize) {
		List<StudyDetail> list = new ArrayList<StudyDetail>();
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		map.put("studyType", 2);
		list = detailMapper.queryList(map);
		if (null != list && list.size() > 0) {
			for (StudyDetail StudyDetail : list) {
				if (StringUtils.isNotBlank(StudyDetail.getImgUrl())) {
					StudyDetail.setFileUrl(staticPath + StudyDetail.getImgUrl());
				}
				String timeContext = DateUtil.getTimeContext(StudyDetail.getOnlineTime(), new Date());
				StudyDetail.setTimeContext(timeContext);
			}
		}
		return list;
	}

	/**
	 * 查询普通List(分页)
	 */
	@Override
	public List<StudyDetail> queryList(StudyDetail record, int index, int pageSize) {
		List<StudyDetail> list = new ArrayList<StudyDetail>();
		record.setStudyType(1);
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		map.put("studyType", 1);
		list = detailMapper.queryList(map);
		if (null != list && list.size() > 0) {
			for (StudyDetail study : list) {
				if (StringUtils.isNotBlank(study.getImgUrl())) {
					study.setFileUrl(staticPath + study.getImgUrl());
				}
				String timeContext = DateUtil.getTimeContext(study.getOnlineTime(), new Date());
				study.setTimeContext(timeContext);
			}
		}
		return list;
	}

	@Override
	public int collectOrcancelStudy(StudyCollection studyCollection) {
		// TODO Auto-generated method stub
		int numResult = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studyId", studyCollection.getStudyId());
		map.put("userId", studyCollection.getUserId());
		// 是否存在收藏记录
		List<StudyCollection> list = studyCollectionMapper.selectCollectList(map);
		if (!list.isEmpty()) {
			StudyCollection studyCollect = list.get(0);
			boolean isok = studyCollect.getIsCollection();
			//取消时间 
			if(isok)studyCollection.setCancelAt(new Date());
			studyCollect.setIsCollection(!isok);
			// 更新
			numResult = studyCollectionMapper.updateByPrimaryKeySelective(studyCollect);
		} else {
			// 新增
			studyCollection.setCreateAt(new Date());  
			numResult = studyCollectionMapper.insertSelective(studyCollection);
		}
		return numResult;
	}

	@Override
	public Pagination<StudyCollection> selCollectList(StudyCollection studyCollection, int index, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		int total=studyCollectionMapper.selectTotal(map);
		// 收藏列表
		List<StudyCollection> list = studyCollectionMapper.selectCollectList(map);
		return new Pagination<StudyCollection>(total,list);
	}

	@Override
	public int addStudyComment(StudyComment studyComment) {
		// TODO Auto-generated method stub
		studyComment.setSupportNum(0L);
		int num=studyCommentMapper.insertSelective(studyComment);
		return num;
	}

	@Override
	public int updateStudyComment(StudyComment studyComment) {
		int num=0;
		// 关系表
		StudyCommentRel stuRel=new StudyCommentRel();
		stuRel.setUserId(studyComment.getUserId());
		stuRel.setCommentId(studyComment.getId());
		// 是否已经点赞过
	    List<StudyCommentRel> list=studyCommentRelMapper.selectCommentRelList(stuRel);
	    if(!list.isEmpty()){
	    	return 2; //已经点赞过
	    }
	    num=studyCommentMapper.updateSupportNum(studyComment.getId());
		return	num;
	}

	@Override
	public Pagination<StudyComment> selListComment(StudyComment studyComment, int index, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		// 查询评论
		List<StudyComment> list=studyCommentMapper.selectList(map);
		int total=studyCommentMapper.selectCount(); //总条数
		return new Pagination<StudyComment>(total, list);
	}
	
	
}
