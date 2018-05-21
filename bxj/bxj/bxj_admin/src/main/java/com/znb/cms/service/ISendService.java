package com.znb.cms.service;

import java.util.concurrent.BlockingQueue;


public interface ISendService {
	/**
	 * 消息发送方法
	* @author yuanxin
	* @date 2017年5月25日下午7:05:49
	* @version <b>1.0.0</b>
	* @return
	 */
	void send(BlockingQueue<Object> queue);
	
}
