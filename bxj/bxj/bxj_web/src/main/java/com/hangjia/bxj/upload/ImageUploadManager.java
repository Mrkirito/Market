package com.hangjia.bxj.upload;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadManager {
	
	void upload(String namespace, String filename, MultipartFile file) throws UploadException;

	boolean isImage(MultipartFile file) throws UploadException;

	boolean remove(String namespace, String filename);
	
}
