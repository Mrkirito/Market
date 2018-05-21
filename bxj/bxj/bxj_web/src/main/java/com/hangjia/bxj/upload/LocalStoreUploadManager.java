package com.hangjia.bxj.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import com.hangjia.bxj.util.ImageUtils;

public class LocalStoreUploadManager implements UploadManager {
	
	/**
	 * 存储根目录。
	 */
	private String rootPath;
	
	/**
	 * 注入本地存储根目录，必须已 / 结尾。
	 * @param rootPath 本地存储根目录。
	 */
	public void setRootPath(String rootPath) {
		if (!rootPath.endsWith("/") && !rootPath.endsWith("\\")) {
			rootPath += "/";
		}
		this.rootPath = rootPath;
	}
	
	@Override
	public void upload(String namespace, String filename, MultipartFile file) throws UploadException {
		String dir = mkdirIfNotExists(rootPath + namespace + "/");
		File target = new File(dir + filename);
//		try {
//			file.transferTo(target);
//		} catch (Exception e) {
//			throw new UploadException(e);
//		}
		try {
			BufferedImage image = ImageUtils.readImage(file.getInputStream());
			ImageUtils.writeImage(image, 64, 64, target);
		} catch (Exception e) {
			throw new UploadException(e);
		}
	}
	
	@Override
	public boolean remove(String namespace, String filename) {
		String target = rootPath + namespace + "/" + filename;
		File file = new File(target);
		
		// 文件不存在，返回true。没什么好删的
		if (!file.exists()) {
			if (log.isDebugEnabled()) {
				log.debug("删除头像，但文件不存在：" + target);
			}
			return true;
		}
		
		return file.delete();
	}
	
	@Override
	public boolean removeVoice(String namespace, String filename) {
		String target = rootPath + namespace + "/" + filename+".mp3";
		File file = new File(target);
		
		// 文件不存在，返回true。没什么好删的
		if (!file.exists()) {
			if (log.isDebugEnabled()) {
				log.debug("删除录音，但文件不存在：" + target);
			}
			return true;
		}
		
		return file.delete();
	}
	
	private String mkdirIfNotExists(String path) {
		File f = new File(path);
		if (!f.exists()) {
			boolean success = f.mkdirs();
			if (!success) {
				throw new IllegalStateException("无法创建目录：" + path);
			}
		}
		return path;
	}
	
	private static final Log log = LogFactory.getLog(LocalStoreUploadManager.class);

	@Override
	public String uploadVoice(String namespace, String filename,
			MultipartFile file) throws UploadException {
		String dir = mkdirIfNotExists(rootPath + namespace + "/");
		long timestamp =  System.currentTimeMillis();
		File target = new File(dir + timestamp);
		try {
			InputStream stream = null;
			OutputStream os = null;
			try {
				byte[] bts = new byte[1024];
				int length = 0;
				 stream =  file.getInputStream();
				 os = new FileOutputStream(target+".mp3");
				while ((length = stream.read(bts)) > 0) {
			        os.write(bts, 0, length);
			    }
			} finally {
				os.flush();
			    os.close();
			    stream.close();
			}
			return timestamp+".mp3";
		} catch (Exception e) {
			throw new UploadException(e);
		}
	}
	
}
