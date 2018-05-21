package com.hangjia.bxj.upload;

import com.hangjia.bxj.BXJException;

public class UploadException extends BXJException {
	
	public UploadException(Throwable cause) {
		super("上传文件失败", cause);
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 5474043794986075160L;

}
