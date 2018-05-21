package com.hangjia.bxj.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadManager {

	void upload(String namespace, String filename, MultipartFile file) throws UploadException;

	boolean remove(String namespace, String filename);
	
	boolean removeVoice(String namespace, String filename);
	
	String uploadVoice(String namespace, String filename, MultipartFile file) throws UploadException;
	
}
