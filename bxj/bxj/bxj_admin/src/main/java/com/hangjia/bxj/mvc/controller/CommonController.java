package com.hangjia.bxj.mvc.controller;
import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hangjia.bxj.service.BookService;
@Controller
@RequestMapping(value="/common")
public class CommonController {
	@Autowired
	private BookService service;
	
	@RequestMapping(value="{key}.jhtml")
	public ModelAndView common(@PathVariable String key) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			Class clazz=service.getClass();
			Method[] methods=clazz.getDeclaredMethods();
			String methodName = "get" + StringUtils.capitalise(key);
			for (Method method : methods) {
//				Parameter[] parameters=method.getParameters();
//				if(method.getName().equals(methodName)&&parameters.length==0){
//					map.put(key, method.invoke(service));
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("planbook/"+key,map);
	}
}
