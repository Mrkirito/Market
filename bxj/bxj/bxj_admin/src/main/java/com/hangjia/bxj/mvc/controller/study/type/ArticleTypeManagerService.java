package com.hangjia.bxj.mvc.controller.study.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.study.ArticleTypeManagerDao;
import com.hangjia.bxj.model.study.type.ArticleTypeEntity;
import com.hangjia.bxj.model.study.type.ArticleTypeRow;

@Service
@Transactional
public class ArticleTypeManagerService {
	
	@Autowired
	private ArticleTypeManagerDao articleTypeManagerDao;

	@Transactional(readOnly=true)
	public Result<List<ArticleTypeRow>> listAllType() {
		
		List<ArticleTypeRow> model = articleTypeManagerDao.listAllType();
		
		Result<List<ArticleTypeRow>> result = new Result<List<ArticleTypeRow>>();
		
		BaseCommonQuery query = new BaseCommonQuery();
		
		query.setTotalItem(model.size());
		query.setPageSize(100);
		
		result.setQuery(query);
		result.setModel(model);
		return result;
	}

	public void deleteType(Integer typeId) {
		articleTypeManagerDao.deleteType(typeId);
	}

	public void updateDisplayStatus(Integer typeId, Boolean display) {
		articleTypeManagerDao.updateDisplayStatus(typeId, display);
	}

	@Transactional(readOnly=true)
	public ArticleTypeEntity findArticleTypeToUpdate(Integer typeId) {
		return articleTypeManagerDao.findArticleTypeToUpdate(typeId);
	}

	public void saveOrUpdate(ArticleTypeEntity entity) {
		if (entity.getId() == null) {
			articleTypeManagerDao.saveType(entity);
		} else {
			articleTypeManagerDao.updateType(entity);
		}
	}
	
}
