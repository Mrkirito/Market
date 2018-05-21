package com.znb.cms.enums;

import java.util.concurrent.BlockingQueue;

import com.znb.cms.exceptions.BusinessException;
import com.znb.cms.factory.QueueInitFactory;
import com.znb.cms.service.ISendService;
import com.znb.cms.service.impl.MailSendServiceImpl;
import com.znb.cms.util.SpringBeanUtils;

public enum QueueEnum {

	MAIL_QUEUE("queueMail",QueueInitFactory.queueMail,MailSendServiceImpl.class);//
	
	private  final String queueName;
	private  BlockingQueue<Object> queue;
	private final Class<? extends ISendService> sendService;
	
	private QueueEnum(String queueName,BlockingQueue<Object> queue,Class<? extends ISendService> sendService){
		this.queueName = queueName;
		this.queue = queue;
		this.sendService = sendService;
	}
	
	/**
	 * 获取即将发送消息队列
	* @author yuanxin
	* @date 2017年5月25日下午7:09:41
	* @version <b>1.0.0</b>
	* @return
	 */
	public static BlockingQueue<Object> getQueue(String queueName){
		QueueEnum queueEnum = getEnumByQueueName(queueName);
		if(null != queueEnum){
			return queueEnum.getQueue();
		}
		return null;
	}
	
	/**
	 * 获取发送接口实例
	* @author yuanxin
	* @date 2017年5月25日下午7:09:24
	* @version <b>1.0.0</b>
	* @return
	 */
	public static ISendService createInstance(String queueName) throws BusinessException{
		QueueEnum queueEnum =  getEnumByQueueName(queueName);
		if(null != queueEnum){
			try {
				return SpringBeanUtils.getBean(queueEnum.sendService);
			} catch (Exception e) {
				throw new BusinessException("instance class [" + queueEnum.sendService.getName() + "] failed...");
			}
		}
		return null;
	}
	
	
	/**
	 * 根据获取实例
	 * @param 队列名字
	 * @return
	 */
	public static QueueEnum getEnumByQueueName(String queueName){
		QueueEnum[] queueEnums = values();
		for(QueueEnum queueEnum : queueEnums){
			if(queueEnum.getQueueName().equals(queueName)){
				return queueEnum;
			}
		}
		return null;
	}

	public String getQueueName() {
		return queueName;
	}

	public BlockingQueue<Object> getQueue() {
		return queue;
	}
	

}
