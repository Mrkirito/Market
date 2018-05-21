package com.hangjia.bxj.service.banner.executor;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.BannerManagerDao;
import com.hangjia.bxj.model.banner.BannerManagerEntity;
import com.hangjia.bxj.model.banner.db.BannerTable;
import com.hangjia.bxj.query.banner.BannerManagerQuery;
import com.hangjia.bxj.service.banner.BannerExecutor;
import com.hangjia.bxj.vo.banner.BannerManagerParameter;
import com.hangjia.bxj.vo.banner.BannerManagerUpdateDisplayParameter;

/**
 * 基类
 * @author K9999
 *
 */
public abstract class BasicBannerExecutor implements BannerExecutor {
	
	private String transferPath;
	
	private String resourcePath;
	
	@Autowired
	private BannerManagerDao bannerManagerDao;
	
	protected Log log = LogFactory.getLog(getClass());
	
	protected boolean debugEnabled = log.isDebugEnabled();
	
	public void setTransferPath(String transferPath) {
		File file = new File(transferPath);
		if (!file.exists()) {
			boolean succ = file.mkdirs();
			if (!succ) {
				throw new IllegalStateException("无法创建文件夹 [" + transferPath + "]，请检查文件权限及磁盘。");
			}
		}
		this.transferPath = rulePath(transferPath);
	}
	
	public void setResourcePath(String resourcePath) {
		this.resourcePath = rulePath(resourcePath);
	}
	
	private String rulePath(String path) {
		if (path.endsWith("/")) {
			return path;
		} else {
			return path + "/";
		}
	}
	
	@Override
	public Result<List<BannerManagerEntity>> paginationQuery(BannerManagerQuery params) {
		
		Result<List<BannerManagerEntity>> result = new Result<List<BannerManagerEntity>>();
		
		BannerTable table = getBannerTable();
		
		int total = bannerManagerDao.count(params, table);
		
		if (total > 0) {
			List<BannerManagerEntity> list = bannerManagerDao.list(params, table);
			result.setModel(list);
		}
		
		params.setTotalItem(total);
		result.setQuery(params);
		
		return result;
	}
	
	@Override
	public void saveOrUpdate(BannerManagerParameter params, MultipartFile image) {
		
		if (image != null && !image.isEmpty()) {
			String filename = resolveFilename(image);
			
			if (debugEnabled) {
				log.debug("确认图片文件名：" + filename);
			}
			
			doCopyFile(image, filename);
			
			String imageURL = resolveImageURL(filename);
			
			if (debugEnabled) {
				log.debug("确认图片访问路径：" + imageURL);
			}
			
			params.setImageUrl(imageURL);
		} else {
			params.setImageUrl(null);
		}
		
		BannerTable table = getBannerTable();
		
		if (params.getId() == null) {
			bannerManagerDao.save(params, table);
			if (debugEnabled) {
				log.debug("已保存新记录，id=" + params.getId());
			}
		} else {
			int count = bannerManagerDao.update(params, table);
			if (debugEnabled) {
				log.debug("已更新" + count + "条记录。");
			}
		}
		
	}
	
	@Override
	public void updateDisplayStatus(Integer id, Boolean status) {
		
		BannerTable table = getBannerTable();
		
		Object value;
		if (status) {
			value = table.getEnabledDisplayValue();
		} else {
			value = table.getDisabledDisplayValue();
		}
		
		int count = bannerManagerDao.updateDisplayStatus(new BannerManagerUpdateDisplayParameter(id, value), table);
		if (debugEnabled) {
			log.debug("已" + (status ? "启用" : "禁用") + count + "条记录。");
		}
		
	}
	
	@Override
	public BannerManagerEntity findToUpdate(Integer bannerId) {
		BannerTable table = getBannerTable();
		return bannerManagerDao.findToUpdate(table, bannerId);
	}
	
	private String resolveImageURL(String filename) {
		String url = resourcePath;
		url += filename;
		return url;
	}

	protected void doCopyFile(MultipartFile image, String filename) {
		
		String file = transferPath + filename;
		
		if (debugEnabled) {
			log.debug("确认图片复制路径：" + file);
		}
		
		File dest = new File(file);
		try {
			image.transferTo(dest);
		} catch (IOException e) {
			throw new RuntimeException("图片文件存储异常", e);
		}
		
	}
	
	private String resolveFilename(MultipartFile file) {
		String original = file.getOriginalFilename();
		return resolveFilename(original);
	}
	
	protected String resolveFilename(String originalFilename) {
		
		String filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		
		int lif = originalFilename.lastIndexOf(".");
		if (lif != -1) {
			// 不包含 . 的情况
			filename += originalFilename.substring(lif);
		}
		
		return filename;
	}
	
	protected abstract BannerTable getBannerTable();
	
}
