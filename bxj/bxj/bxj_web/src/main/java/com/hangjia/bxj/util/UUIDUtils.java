package com.hangjia.bxj.util;

import java.util.UUID;

public class UUIDUtils {
	
	public static String random() {
		UUID uid = UUID.randomUUID();
		return uid.toString().replaceAll("-", "");
	}
	
}
