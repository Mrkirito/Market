package com.hangjia.bxj.upload;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.util.ImageUtils;

@Component
public class ImageUploadManagerImpl implements ImageUploadManager {
	
	@Autowired
	private UploadManager uploadManager;
	
	@Override
	public void upload(String namespace, String filename, MultipartFile file) throws UploadException {
		if (!isImage(file)) {
			throw new UploadException("上传文件不是图片");
		}
		uploadManager.upload(namespace, filename, file);
	}
	
	@Override
	public boolean remove(String namespace, String filename) {
		return uploadManager.remove(namespace, filename);
	}

	@Override
	public boolean isImage(MultipartFile file) throws UploadException {
		try {
			return ImageUtils.isImage(file.getInputStream());
		} catch (IOException e) {
			throw new UploadException("无法识别上传的文件");
		}
	}

}
