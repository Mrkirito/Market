package com.hangjia.bxj.mvc.util;

public class FilenameUtils {

	public static String resolveFileName(String src) {
		long nano = System.nanoTime();
		int idf = src.lastIndexOf(".");
		if (idf == -1) {
			return nano + ".jpg";
		}
		return nano + src.substring(idf);
	}
	
}
