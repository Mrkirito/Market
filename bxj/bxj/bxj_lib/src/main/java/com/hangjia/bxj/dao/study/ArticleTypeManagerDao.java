package com.hangjia.bxj.dao.study;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hangjia.bxj.model.study.type.ArticleTypeEntity;
import com.hangjia.bxj.model.study.type.ArticleTypeRow;

public interface ArticleTypeManagerDao {

	/**
	 * 查询所有分类。
	 * @return
	 */
	List<ArticleTypeRow> listAllType();

	int deleteType(Integer typeId);

	int updateDisplayStatus(@Param("typeId") Integer typeId, @Param("display") Boolean display);

	ArticleTypeEntity findArticleTypeToUpdate(@Param("typeId") Integer typeId);

	void saveType(ArticleTypeEntity entity);

	void updateType(ArticleTypeEntity entity);
	
}
