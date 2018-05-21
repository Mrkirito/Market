package com.hangjia.bxj.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface DateFormatProvider {

	/**
	 * 中文日期格式化，只包含年月日（yyyy年MM月dd日）。
	 */
	DateFormat DATE_FORMAT_CN = new SimpleDateFormat("yyyy年M月d日");
	
	DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	DateFormat DATE_FORMAT_HM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	DateFormat DATE_TIME_FORMAT_CN = new SimpleDateFormat("yyyy年M月d日 H点mm分", Locale.CHINESE);
	
	DateFormat DATE_FULL_FORMAT_CN = new SimpleDateFormat("yyyy年M月d日（E）H点mm分", Locale.CHINESE);
	
	DateFormat DATE_FULL_FORMAT_DAYCN = new SimpleDateFormat("yyyy年M月d日（E）H:mm", Locale.CHINESE);
	
}
