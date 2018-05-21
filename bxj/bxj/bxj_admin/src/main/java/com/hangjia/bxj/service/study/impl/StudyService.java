package com.hangjia.bxj.service.study.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.KnowledgeDao;
import com.hangjia.bxj.dao.StudyDetailMapper;
import com.hangjia.bxj.model.Knowledge;
import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.query.app.StudyDetailQuery;
import com.hangjia.bxj.service.study.IstudyService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class StudyService implements IstudyService {

	@Autowired
	StudyDetailMapper detailMapper;

	@Autowired
	private KnowledgeDao knowledgeDao;

	@Value("${show_path}")
	private String showPath;

	@Value("${bxjweb_path}")
	private String bxjWebPath;

	@Override
	public StudyDetail selectByPK(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			return null;
		StudyDetail study = detailMapper.selectByPrimaryKey(id);
		study.setFileUrl(showPath + study.getImgUrl());
		return study;
	}

	@Transactional
	@Override
	@MethodLog(remark = "新增学习详情")
	public int insertStudyDetail(StudyDetail detail, Integer knowID) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			if (detail.getAuditStatus() == null)
				detail.setAuditStatus(1); // 不传值则默认审核不通过
			num = detailMapper.insertSelective(detail); // 获取详情主键
			// 根据知识id 获取知识know对象
			if (knowID != null) {
				Knowledge know = selknowByPK(knowID); // 查询知识信息
				String location = bxjWebPath + "/study/detail.page?id=" + detail.getId() + "&showName=console";
				know.setLocation(location);
				updateKnowledge(know);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return num;
	}

	@Override
	@MethodLog(remark = "更新学习详情")
	public int updateByPKeyDetail(StudyDetail detail, Integer knowID) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			num = detailMapper.updateByPrimaryKeySelective(detail);
			// 根据知识id 获取知识know对象
			if (knowID != null) {
				Knowledge know = selknowByPK(knowID); // 查询知识信息
				String location = bxjWebPath + "/study/detail.page?id=" + detail.getId() + "&showName=console";
				know.setLocation(location);
				updateKnowledge(know);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return num;
	}

	@Override
	@MethodLog(remark = "新增学习")
	public int insertKnowledge(Knowledge know) {
		// TODO Auto-generated method stub
		return knowledgeDao.insert(know);
	}

	@Override
	public Knowledge selknowByPK(Integer id) {
		// TODO Auto-generated method stub
		Knowledge know = knowledgeDao.selknowByPK(id);
		know.setFileUrl(showPath + "/" + know.getImageURL());
		return know;
	}

	@Override
	public List<Knowledge> queryKnowList(StudyDetailQuery query, int index, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("pageSize", pageSize);
		map.put("text", query.getText());
		int total = knowledgeDao.total(map); //查询总条数 
		List<Knowledge> rows;
		if (total > 0) {
			rows = knowledgeDao.konwlist(map);
		} else {
			rows = new ArrayList<Knowledge>();
		}

		if (null != rows && rows.size() > 0) {
			for (Knowledge know : rows) {
				if (StringUtils.isNotBlank(know.getImageURL())) {
					know.setImageURL(showPath + "/" + know.getImageURL());
				}
			}
		}
		return rows;
	}

	/**
	 * 返回know 总记录数
	 * 
	 * @return
	 */
	@Override
	public int queryKnowtotal() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		return knowledgeDao.total(map);
	}

	@Override
	@MethodLog(remark = "更新学习")
	public int updateKnowledge(Knowledge know) {
		/*
		 * Knowledge know=new Knowledge(); know.setId(query.getId().intValue());
		 * know.setTitle(query.getTitle());
		 */
		// TODO Auto-generated method stub
		return knowledgeDao.updateByPKSelective(know);
	}

	@Override
	public int queryCount(StudyDetailQuery query) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		return detailMapper.queryCount(map);
	}

	@Override
	public List<StudyDetail> queryUpList(StudyDetailQuery query, int index, int pageSize) {
		// TODO Auto-generated method stub
		List<StudyDetail> list = new ArrayList<StudyDetail>();
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		map.put("studyType", 2); // 置顶
		list = detailMapper.queryList(map);
		if (null != list && list.size() > 0) {
			for (StudyDetail StudyDetail : list) {
				if (StringUtils.isNotBlank(StudyDetail.getImgUrl())) {
					StudyDetail.setFileUrl(showPath + StudyDetail.getImgUrl());
				}
			}
		}
		return list;
	}

	@Override
	public List<StudyDetail> queryList(StudyDetailQuery query, int index, int pageSize) {
		// TODO Auto-generated method stub
		List<StudyDetail> list = new ArrayList<StudyDetail>();
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("start", start);
		map.put("end", pageSize);
		map.put("studyType", 1); // 普通
		list = detailMapper.queryList(map);
		if (null != list && list.size() > 0) {
			for (StudyDetail StudyDetail : list) {
				if (StringUtils.isNotBlank(StudyDetail.getImgUrl())) {
					StudyDetail.setFileUrl(showPath + StudyDetail.getImgUrl());
				}
			}
		}
		return list;
	}

	/**
	 * 查询学习列表分页
	 * 
	 * @param query
	 * @return
	 */
	@Override
	public List<StudyDetail> queryPageData(StudyDetailQuery query) {
		List<StudyDetail> studys = new ArrayList<StudyDetail>();
		studys = detailMapper.queryPageData(query);
		if (null != studys && studys.size() > 0) {
			for (StudyDetail headlineNews : studys) {
				if (StringUtils.isNotBlank(headlineNews.getImgUrl())) {
					headlineNews.setFileUrl(showPath + headlineNews.getImgUrl());
				}
			}
		}
		return studys;
	}

}
