package com.znb.cms.mvc.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibaoxianjia.message.dto.MailMessageDto;
import com.ibaoxianjia.message.dto.SmsMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;
import com.znb.cms.dao.mapper.ContactsInfoMapper;
import com.znb.cms.dao.mapper.OrderPaidMapper;
import com.znb.cms.exceptions.SystemException;
import com.znb.cms.model.mapper.ContactsInfo;
import com.znb.cms.model.mapper.OrderPaid;

@Component
public class NotificationScheduler implements Scheduler {

	private static final int SMS_TYPE = 1;

	private static final int MAIL_TYPE = 2;

	@Autowired
	public OrderPaidMapper orderPaidMapper;

	@Autowired
	public ContactsInfoMapper contactsInfoMapper;

	@Autowired
	private MessageOpenAPI messageOpenAPI;

	@Scheduled(cron="0 0/15 * * * ?")
//	@Scheduled(fixedDelay = 1000)
	@Override
	public void checkForPaidOrder() {
		System.out.println("正在检查！");
		List<OrderPaid> orders = selectOrderData();
		if (null == orders || orders.size() == 0) {
			return;
		} else {
			List<ContactsInfo> contactsInfos = contactsInfoMapper.selectAll();
			List<String> phones = new ArrayList<String>();
			List<String> mails = new ArrayList<String>();
			for (ContactsInfo info : contactsInfos) {
				if (info.getContactType() == SMS_TYPE) {
					phones.add(info.getContactInfo());
				} else if (info.getContactType() == MAIL_TYPE) {
					mails.add(info.getContactInfo());
				}
			}
			try {
				sendSMS(phones, orders);
				sendMail(mails, orders);
				orderPaidMapper.deleteAll();
			} catch (SystemException ex) {
				ex.printStackTrace();
			}
			
		}
	}

	private void sendSMS(List<String> phones, List<OrderPaid> orders) throws SystemException {
		try {
			SmsMessageDto sms = new SmsMessageDto();
			for (String phone : phones) {
				sms.addPhone(phone);
			}
			sms.setSysid("core_1001");
			sms.setSmsCode("BXJ_BATCH_SEND");
			sms.setIp("192.168.10.240");
		    Map<String, String> smsInfoMap = new HashMap<String, String>();
		    smsInfoMap.put("content", "【新概念保险】有新的已支付企业险订单，数量为：" + orders.size());

		    sms.setSmsInfoMap(smsInfoMap);
		    boolean result = messageOpenAPI.sendSms(sms);
			messageOpenAPI.sendSms(sms);
			System.out.println("Sending msg, result:" + result);
        } catch (Exception ex) {
        	System.out.println("短信通知发送异常，异常原因：" + ex.toString());
        	throw new SystemException(ex.getMessage());
        }
	}

	private void sendMail(List<String> mails, List<OrderPaid> orders) throws SystemException {
		try {
			// 提现申请邮件通知
			MailMessageDto messageDto = new MailMessageDto();
			messageDto.setMailCode("NEW_ORDER");
			messageDto.setSysid("core_1001");
			for (String mail : mails) {
				messageDto.addTo(mail);
			}
			messageDto.setMailInfoMap(new HashMap<String, String>());
			boolean result = messageOpenAPI.sendMail(messageDto);
			System.out.println("Sending mail, result:" + result);
        } catch (Exception ex) {
        	System.out.println("邮件通知发送异常，异常原因：" + ex.toString());
        	throw new SystemException(ex.getMessage());
        }
	}

	private List<OrderPaid> selectOrderData() {
		synchronized(this) {
			List<OrderPaid> infos = orderPaidMapper.selectAll();
			orderPaidMapper.deleteAll();
			return infos;
		}
	}
}
