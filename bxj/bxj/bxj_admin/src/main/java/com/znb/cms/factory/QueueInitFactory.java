package com.znb.cms.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * 初始消息队列
* @author yuanxin
* @date 2017年5月25日下午7:10:47
* @version <b>1.0.0</b>
 */
public class QueueInitFactory {
	public static BlockingQueue<Object> queueMail = new LinkedBlockingDeque<Object>();
	public static Map<String,String> queueMap = new HashMap<String,String>();
	static{
		QueueInitFactory.queueMap.put("queueMail", "queueMail");
	}
}
