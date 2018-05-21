package com.znb.cms.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.core.cms.model.mapper.PolicyMail;
import com.ibaoxianjia.message.dto.MailAttachmentDto;
import com.ibaoxianjia.message.dto.MailMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;
import com.znb.cms.service.ISendService;

@Service
public class MailSendServiceImpl implements ISendService {
	
	@Autowired
	private MessageOpenAPI messageOpenAPI;
	
	@Value("${core_policy_mail}")
	private String cmsPolicyMail;
	@Value("${core_sysid}")
	private String coreSysid;
	
	@Override
	public void send(BlockingQueue<Object> queue) {
		for(Object ob : queue){
			send(ob,queue);
		}
	}
	
	private void send(Object ob,BlockingQueue<Object> queue){
		PolicyMail policyMail = (PolicyMail) ob;
		//发送保单邮件通知
		MailMessageDto messageDto = new MailMessageDto();
		messageDto.setMailCode(cmsPolicyMail);
		messageDto.setFrom("新概念保险");
		messageDto.setSysid(coreSysid);
		messageDto.addTo(policyMail.getMailPolicy());
		messageDto.addAttachment(new MailAttachmentDto("电子保单.pdf", policyMail.getBytePolicy()) );
		Map<String,String> mailInfoMap = new HashMap<String,String>();
		mailInfoMap.put("name", policyMail.getUserName());
		mailInfoMap.put("productName",policyMail.getProductName());
		messageDto.setMailInfoMap(mailInfoMap);
		boolean result = messageOpenAPI.sendMail(messageDto);
		if (result) {
			queue.remove(ob);
		}
		System.out.println("Sending mail, result:" + result);
	};

}
