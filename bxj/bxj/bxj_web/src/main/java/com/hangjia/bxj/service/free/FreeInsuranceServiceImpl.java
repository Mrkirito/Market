package com.hangjia.bxj.service.free;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.dao.FreeReceiveLogDao;
import com.hangjia.bxj.dao.MyCustomerDao;
import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.FreeInsurance;
import com.hangjia.bxj.model.FreeReceiveLog;

@Transactional
@Service
public class FreeInsuranceServiceImpl implements FreeInsuranceService {
	
	/**
	 * 赠险服务客户端，调取服务端领取赠险。
	 */
	@Autowired
	private FreeInsuranceClient freeInsuranceClient;
	
	@Autowired
	private MyCustomerDao myCustomerDao;
	
	@Autowired
	private FreeReceiveLogDao freeReceiveLogDao;
	
	private static final Log log = LogFactory.getLog(FreeInsuranceServiceImpl.class);
	
	public void setFreeInsuranceClient(FreeInsuranceClient freeInsuranceClient) {
		this.freeInsuranceClient = freeInsuranceClient;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<FreeInsurance> listAllFreeInsurances() {
		return freeReceiveLogDao.listFreeInsurances();
	}
	
	@Override
	public FreeInsurance getFreeInsuranceDetails(String id) throws BXJException {
		FreeInsurance free = getFreeInsurance(id);
		if (free == null) {
			throw new BXJException("产品没有找到");
		}
		if (!free.isHeal()) {
			throw new BXJException("该产品已下架");
		}
		return free;
	}
	
	@Override
	public FreeInsurance getFreeInsurance(String id) {
		return freeReceiveLogDao.getFreeInsurance(id);
	}
	
	@Override
	public long receive(Integer shareId, ReceiverArgs args) {
		freeInsuranceClient.receive(args);
		return saveReceiveLog(shareId, args);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public long saveReceiveLog(Integer shareId, ReceiverArgs args) {
		// 被分享人领取到该用户分享的免费险后，为该用户保存客户（领取者成为该用户的客户）。
		Customer c = new Customer();
		c.setName(args.getRealName());
		c.setMobile(args.getMobile());
		c.setSex(parseSex(args.getIdCardCode()));
		c.setUserId(shareId);
		c.setIdCardCode(args.getIdCardCode());
		
		Long customerId;
		try {
			myCustomerDao.insertCustomer(c);
			customerId = c.getId();
			if (log.isInfoEnabled()) {
				log.info("新保存的客户ID：" + customerId);
			}
		} catch (DuplicateKeyException e) {
			// 约束被违反，忽略这个异常，说明用户已经有这个客户了
			if (log.isInfoEnabled()) {
				log.info("相同的客户已存在。用户ID：" + shareId + "，客户身份证号：" + args.getIdCardCode(), e);
			}
			// 用身份证号和用户ID查询这个已存在的客户ID
			customerId = myCustomerDao.selectCustomerIdByIdCard(shareId, args.getIdCardCode());
			if (log.isInfoEnabled()) {
				log.info("查询已有客户ID：" + customerId);
			}
		}
		
		// 保存领取记录。
		FreeReceiveLog log = new FreeReceiveLog();
		log.setCustomerId(customerId);
		log.setFreeId(args.getPid());
		log.setReceived(true);
		freeReceiveLogDao.save(log);
		
		return log.getId();
	}
	
	private static Integer parseSex(String idCardCode) {
		int length = idCardCode.length();
		String l = idCardCode.substring(length - 2, length - 1);
		
		if (log.isInfoEnabled()) {
			log.info("身份证号：" + idCardCode + "，最后第二位：" + l);
		}
		
		int i = 0;
		try {
			i = Integer.parseInt(l);
		} catch (Exception e) {
		}
		
		// 单数
		if ((i & 1) == 1) {
			return 0;
		} 
		// 偶数
		else {
			return 1;
		}
	}
	
}
