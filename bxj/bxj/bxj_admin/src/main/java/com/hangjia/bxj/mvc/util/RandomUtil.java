package com.hangjia.bxj.mvc.util;

/**
 * <strong>生成随机数</strong> 工具类。
 * <p>
 * 生成随机数
 * 
 * </p>
 * @author yaoy
 * @since
 */
public class RandomUtil {
	
	/**
	 * 生成3位随机数 1~50
	 */
	public synchronized static int genThreeRandomNum() {
		int random = (int) ((Math.random() * 50) + 1);
		return random;
	}
	
	/**
	 * 测试用
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			int random = genThreeRandomNum();
			System.out.println(random);
		}
	}
}
