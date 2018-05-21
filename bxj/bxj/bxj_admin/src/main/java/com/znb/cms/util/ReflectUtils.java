package com.znb.cms.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ReflectUtils {
	public static Object getValue(Object o, String filedName) {
		try {
			return new PropertyDescriptor(filedName, o.getClass()).getReadMethod().invoke(o);
		} catch (Exception e) {
			return null;
		}
	}

	public static void setValue(Object o, String filedName, String v) {
		try {
			Method method=new PropertyDescriptor(filedName, o.getClass()).getWriteMethod();
			Type type=method.getGenericParameterTypes()[0];
			if(type==Integer.class){
				method.invoke(o, Integer.parseInt(v));
			}else if(type==Double.class){
				method.invoke(o, Double.parseDouble(v));
			}else{				
				method.invoke(o, v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
