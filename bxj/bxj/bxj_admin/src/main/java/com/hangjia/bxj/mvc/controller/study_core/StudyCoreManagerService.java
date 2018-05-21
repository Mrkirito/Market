package com.hangjia.bxj.mvc.controller.study_core;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.study_core.StudyCoreManagerDao;
import com.hangjia.bxj.model.study_core.ArticleCreateArgs;
import com.hangjia.bxj.model.study_core.ArticleRow;
import com.hangjia.bxj.model.study_core.ArticleType;
import com.hangjia.bxj.model.study_core.ArticleUpdateArgs;
import com.hangjia.bxj.mvc.util.FilenameUtils;
import com.hangjia.bxj.mvc.util.ImageUtils;
import com.hangjia.bxj.query.study_core.ArticleManagerQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudyCoreManagerService {
	/**
	 * 保存路径
	 */
    @Value("${cdn_study_path}")
	private String showCdnPath;
    
	@Autowired
	private StudyCoreManagerDao studyCoreManagerDao;
	
	@Transactional(readOnly=true)
	public List<ArticleType> listSimpleArticleTypies() {
		return studyCoreManagerDao.listSimpleArticleTypies();
	}
	
	public void update(ArticleCreateArgs args) {
		boolean debugEnabled = log.isDebugEnabled();
		
		Integer articleId = args.getId();
		
		if (debugEnabled) {
			log.debug("更新文章：id=" + articleId);
		}
		
		int count = studyCoreManagerDao.updateArticle(args);
		
		if (count == 0) {
			log.error("更新文章数据不存在，id=" + articleId);
			return;
		}
		
		studyCoreManagerDao.updateArticleDetails(args);
		
		String[] images = args.getImages();
		
		if (images != null && images.length > 0) {
			
			forImages(args);
			
			int imageDeleted = studyCoreManagerDao.deleteArticleImages(articleId);
			if (debugEnabled) {
				log.debug("删除图片，共" + imageDeleted + "张。");
			}
			
			// 保存封面图和文章的关联关系。
			int imageSaved = studyCoreManagerDao.saveArticleImages(articleId, args.getImages());
			
			if (debugEnabled) {
				log.debug("重新保存图片，共" + imageSaved  + "张。");
			}
			
		}

		if (StringUtils.isNotBlank(args.getBannerImage())){
			int imageDeleted = studyCoreManagerDao.deleteTopicImage(articleId);
			if (debugEnabled) {
				log.debug("删除专题图片，共" + imageDeleted + "张。");
			}
			studyCoreManagerDao.saveTopicBannerImage(articleId, args.getBannerImage());
		}
		
		// 先删除所有关联关系，再重新保存关联分类
		studyCoreManagerDao.deleteArticleRefTypies(articleId);
		Integer[] typies = args.getRefTypies();
		if (typies != null && typies.length > 0) {
			studyCoreManagerDao.saveRefTypies(articleId, typies);
		}
		
	}


	public void updateScore(ArticleCreateArgs args) {
		boolean debugEnabled = log.isDebugEnabled();

		studyCoreManagerDao.updateArticleScore(args);
	}
	
	private void forImages(ArticleCreateArgs args) {
		
		boolean debugEnabled = log.isDebugEnabled();
		String[] images = args.getImages();
		
		if ("NORMAL".equalsIgnoreCase(args.getImageType())) {
			/*
			 * 手动档，
			 * 选择封面图片的第1张作为分享图（如果有）
			 * 任何图片不做干预
			 */
			args.setShareImage(images[0]);
			
			if (debugEnabled) {
				log.debug("分享图片地址：" + args.getShareImage());
			}
		} else {
			/*
			 * 自动档
			 * 封面图片的第1张作为分享图（如果有）
			 * 其他封面图需要经过缩放。
			 */
			
			// 迭代全部图片，需要进行压缩
			for (int i = 0; i < images.length; i++) {
				
				String url = images[i];
				
				String filename = FilenameUtils.resolveFileName(url);
				
				String dest = articleImageStoreBasePath + filename;
				
				if (debugEnabled) {
					log.debug("压缩封面图片到：" + dest + "，访问地址：" + url);
				}
				images[i]=showCdnPath+"/upload/study_core/small/"+filename; //目标路径  cdn_study_path +"upload/study/small/"
				saveImage(url, dest);
				
				if (i == 0) {
					args.setShareImage(url);
					
					if (debugEnabled) {
						log.debug("分享图片地址：" + url);
					}
				}
			}
		}		
		
	}


    /**
	 * 1.一级分类
	 * 2.二级分类
	 * 3.三级分类
	 * @param classifyId
	 * @return
     */
	public Object classify(Integer classifyId) {
		return null;
	}
	
	public void create(ArticleCreateArgs args) {
		studyCoreManagerDao.saveArticle(args);
		
		Integer articleId = args.getId();
		
		if (log.isDebugEnabled()) {
			log.debug("保存文章完成，ID=" + articleId);
		}
		
		String[] images = args.getImages();
		
		if (images != null && images.length > 0) {
			
			forImages(args);
			
			// 保存封面图和文章的关联关系。
			studyCoreManagerDao.saveArticleImages(articleId, args.getImages());
			
		}

		if (StringUtils.isNotBlank(args.getBannerImage())){
			studyCoreManagerDao.saveTopicBannerImage(articleId, args.getBannerImage());
		}

		studyCoreManagerDao.saveArticleDetails(args);
		
		// 保存关联分类
//		Integer[] typies = args.getRefTypies();
//		if (typies != null && typies.length > 0) {
//			studyCoreManagerDao.saveRefTypies(articleId, typies);
//		}
		
	}
	
	public void updateDisplayStatus(Integer articleId, Boolean display) {
		studyCoreManagerDao.updateDisplayStatus(articleId, display);
	}



	@Transactional(readOnly=true)
	public ArticleUpdateArgs findArticleToUpdate(Integer articleId) {
		return studyCoreManagerDao.findArticleToUpdate(articleId);
	}
	
	@Transactional(readOnly=true)
	public Result<List<ArticleRow>> paginationQuery(ArticleManagerQuery params) {
		
		Result<List<ArticleRow>> result = new Result<List<ArticleRow>>();
		
		int total = studyCoreManagerDao.articlesCount(params);
		
		if (total > 0) {
			List<ArticleRow> list = studyCoreManagerDao.listArticles(params);
			result.setModel(list);
		}
		
		params.setTotalItem(total);
		result.setQuery(params);
		
		return result;
	}
	
	private static final void saveImage(String url, String dest) {
		InputStream in = null;
		try {
			in = new URL(url).openStream();
			ImageUtils.zoomAutoFix(in, new File(dest) , imageWidth, imageHeight);
		} catch (Exception e) {
			throw new RuntimeException("保存图片异常，源地址：" + url + "，目标：" + dest, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		
	}
	
	private static final int imageWidth = 220;
	
	private static final int imageHeight = 150;
	
	private String articleImageRequestBasePath;
	
	private String articleImageStoreBasePath;
	
	@Value("${study_core.articleImg.requestBasePath}")
	public void setArticleImageRequestBasePath(String articleImageRequestBasePath) {
		this.articleImageRequestBasePath = articleImageRequestBasePath + "/small/";
	}
	
	@Value("${study_core.articleImg.storeBasePath}")
	public void setArticleImageStoreBasePath(String articleImageStoreBasePath) {
		this.articleImageStoreBasePath = articleImageStoreBasePath + "/small/";
	}
	
	private static final Log log = LogFactory.getLog(StudyCoreManagerService.class);

}
