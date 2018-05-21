package com.hangjia.bxj.util;

public class StringUtils {

	public static String clean(String str) {
		String result = null;
		if (str != null) {
			result = str.trim();
			if (result.isEmpty()) {
				result = null;
			}
		}
		return result;
	}
	
}
