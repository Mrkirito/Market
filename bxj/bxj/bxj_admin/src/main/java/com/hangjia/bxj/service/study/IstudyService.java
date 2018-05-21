package com.hangjia.bxj.service.study;

import java.util.List;

import com.hangjia.bxj.model.Knowledge;
import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.query.app.StudyDetailQuery;

public interface IstudyService {

	/**
	 * 查询学习详情
	 * @param id
	 * @return
	 */
  StudyDetail selectByPK(Long id);

  /**
   * 新增学习详情
   * @param detail
   * @param knowID 知识id
   * @return
   */
  int insertStudyDetail(StudyDetail detail,Integer knowID);
  
  /**
   * 修改学习详情
   * @param detail
   * @param knowID 知识id
   * @return
   */
  int updateByPKeyDetail(StudyDetail detail,Integer knowID);
  
  /**
   * 置顶
   * @param record
   * @param index
   * @param pageSize
   * @return
   */
  List<StudyDetail> queryUpList(StudyDetailQuery query,int index,int pageSize);
  
  /**
   * 普通
   * @param record
   * @param index
   * @param pageSize
   * @return
   */
  List<StudyDetail> queryList(StudyDetailQuery query,int index,int pageSize);
  
  /**
   * 查询 后台详情展示列表
   * @param query
   * @return
   */
  List<StudyDetail> queryPageData(StudyDetailQuery query);
  /**
	 * 返回知识的总数
	 * @return
	 */
	int queryKnowtotal();
	
	/**
	 * 查询知识列表
	 * @param query
	 * @param index 页码
	 * @param pageSize 个数 
	 * @return
	 */
	List<Knowledge> queryKnowList(StudyDetailQuery query,int index, int pageSize);
   
	/**
	 * 查询单个知识对象
	 * @param id
	 * @return
	 */
	Knowledge selknowByPK(Integer id);
   /**
    * 更新
    * @param knowledge
    * @return
    */
   int updateKnowledge(Knowledge know);
   
   /**
    * 插入知识信息
    * @param know
    */
   int insertKnowledge(Knowledge know);
   
   /**
    * 查询学习详情总数
    * @param query
    * @return
    */
   int queryCount(StudyDetailQuery query);
}
