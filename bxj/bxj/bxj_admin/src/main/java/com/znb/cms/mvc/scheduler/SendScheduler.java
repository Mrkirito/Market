package com.znb.cms.mvc.scheduler;

import java.util.Iterator;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.znb.cms.enums.QueueEnum;
import com.znb.cms.exceptions.BusinessException;
import com.znb.cms.factory.QueueInitFactory;
@Component
/**
 * 发送消息定时器
* @author yuanxin
* @date 2017年6月7日上午10:11:45
* @version <b>1.0.0</b>
 */
public class SendScheduler implements Scheduler{
	
	@Scheduled(cron="* * * * * *")
	@Override
	public void checkForPaidOrder() {
		//System.out.println("检查是否有邮件需要发送");
		Iterator<Map.Entry<String, String>> it = QueueInitFactory.queueMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			send(entry.getValue());
		}
		
	}
	
	private synchronized static void send(String queueName){
		QueueEnum queueEnum = QueueEnum.getEnumByQueueName(queueName);
		if(queueEnum.getQueue().size() <= 0 ){
			return;
		}
		try {
			QueueEnum.createInstance(queueName).send(queueEnum.getQueue());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	

}
