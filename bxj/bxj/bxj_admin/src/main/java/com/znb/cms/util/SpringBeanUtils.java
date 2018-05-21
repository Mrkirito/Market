package com.znb.cms.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
* @author yuanxin
* @date 2017年5月25日下午7:01:06
* @version <b>1.0.0</b>
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
//	public static <T> T getBean(Class<T> clazz,Object args) {
//		return applicationContext.getBean(clazz, args);
//	}
	
	public static <T> Map<String, T> getBeans(Class<T> clazz) {
		return applicationContext.getBeansOfType(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T)applicationContext.getBean(beanName);
	}
	
	//获取上下文
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}