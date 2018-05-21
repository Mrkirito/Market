package com.hangjia.bxj.dao.study_core;

import com.hangjia.bxj.model.study_core.ArticleCreateArgs;
import com.hangjia.bxj.model.study_core.ArticleRow;
import com.hangjia.bxj.model.study_core.ArticleType;
import com.hangjia.bxj.model.study_core.ArticleUpdateArgs;
import com.hangjia.bxj.query.study_core.ArticleManagerQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyCoreManagerDao {

	List<ArticleType> listSimpleArticleTypies();

	void saveArticle(ArticleCreateArgs args);

	void saveArticleDetails(ArticleCreateArgs args);

	int saveArticleImages(@Param("articleId") Integer articleId, @Param("images") String[] images);

	int saveTopicBannerImage(@Param("articleId") Integer articleId, @Param("bannerImage") String bannerImage);

	void saveRefTypies(@Param("articleId") Integer articleId, @Param("typies") Integer[] typies);

	int articlesCount(ArticleManagerQuery params);

	List<ArticleRow> listArticles(ArticleManagerQuery params);

	int updateDisplayStatus(@Param("articleId") Integer articleId, @Param("display") Boolean display);

	ArticleUpdateArgs findArticleToUpdate(@Param("articleId") Integer articleId);

	int updateArticle(ArticleCreateArgs args);
	
	int updateArticleDetails(ArticleCreateArgs args);

	int updateArticleScore(ArticleCreateArgs args);

	int deleteArticleImages(@Param("articleId") Integer articleId);

	int deleteTopicImage(@Param("articleId") Integer articleId);
	
	/**
	 * 
	 * 删除指定文章相关分类的关联关系（重置关联分类）。
	 * 
	 * @param articleId
	 * @return
	 */
	int deleteArticleRefTypies(@Param("articleId") Integer articleId);
	
}
