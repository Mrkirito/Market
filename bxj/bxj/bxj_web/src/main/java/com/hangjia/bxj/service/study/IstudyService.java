package com.hangjia.bxj.service.study;

import java.util.List;

import com.hangjia.bxj.model.StudyCollection;
import com.hangjia.bxj.model.StudyComment;
import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.model.StudyShare;
import com.hangjia.bxj.vo.Pagination;

public interface IstudyService {

	/**
	 * 查询学习详情
	 * @param id
	 * @return
	 */
  StudyDetail selectByPK(Long id);

	/**
	 * 查询学习分享
	 * @param id
	 * @return
	 */
  StudyShare selectShareByPK(Long id);

  /**
   * 插入分享 
   * @param entity
   * @return
   */
  int insertShare(StudyShare entity);
  
  /**
   * 获取分享数据
   * @param id
   * @return
   */
  StudyShare selShareByPK(Long id);
  
  /**
   * 置顶
   * @param record
   * @param index
   * @param pageSize
   * @return
   */
  List<StudyDetail> queryUpList(StudyDetail record,int index,int pageSize);
  
  /**
   * 普通
   * @param record
   * @param index
   * @param pageSize
   * @return
   */
  List<StudyDetail> queryList(StudyDetail record,int index,int pageSize);
  
  StudyShare selectBystudyID(Long id);
  
  /**
   * 收藏或取消学习
   * @param studyCollection
   * @return
   */
  int collectOrcancelStudy(StudyCollection studyCollection);
  
  /**
   * 查询所有收藏学习列表
   * @param studyCollection
   * @param index
   * @param pageSize
   * @return
   */
  Pagination<StudyCollection> selCollectList(StudyCollection studyCollection, int index,int pageSize);
  
  /**
   *添加学习评论
   */
  int addStudyComment(StudyComment studyComment);
  
  /**
   * 更新点赞数
   * @param id
   * @return
   */
  int updateStudyComment(StudyComment studyComment);
  
  /**
   * 查询所有的学习评论
   * @param studyComment
   * @param index
   * @param pageSize
   * @return
   */
  Pagination<StudyComment> selListComment(StudyComment studyComment,int index,int pageSize);
}
