package com.hangjia.bxj.mvc.util;

import java.text.DecimalFormat;
/**
 * 数字格式化
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年5月31日 下午5:35:47
 */
import java.text.NumberFormat;
public class DecimalFormatUtil {
	
    /**整数用,逗号每三位隔开**/
	private static final DecimalFormat decimalFmt = new DecimalFormat("#,###");
	
	/**浮点数用,逗号每三位隔开**/
	private static final NumberFormat nf = NumberFormat.getInstance();
	
	public static String formatNum(Integer dbnum){
		return decimalFmt.format(dbnum);
	}
	
	public static String formatDouble(double dbnum){
		return nf.format(dbnum); 
	}
}
