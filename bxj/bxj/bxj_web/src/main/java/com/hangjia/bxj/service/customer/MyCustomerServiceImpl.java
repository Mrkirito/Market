package com.hangjia.bxj.service.customer;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.dao.FreeReceiveLogDao;
import com.hangjia.bxj.dao.MyCustomerDao;
import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.CustomersPlanBook;
import com.hangjia.bxj.model.FreeReceiveLog;
import com.hangjia.bxj.service.free.FreeInsuranceService;
import com.hangjia.bxj.upload.ImageUploadManager;

@Service
public class MyCustomerServiceImpl implements MyCustomerService {
	
	@Autowired
	private MyCustomerDao myCustomerDao;
	
	/**
	 * 查询我的客户的赠险领取记录。
	 */
	@Autowired
	private FreeReceiveLogDao freeReceiveLogDao;
	
	@Autowired
	private ImageUploadManager imageUploadManager;
	
	/**
	 * 
	 */
	@Autowired
	private FreeInsuranceService freeInsuranceService;
	
	private static final String imageFileNamespace = "c";
	
	private static final Log log = LogFactory.getLog(MyCustomerServiceImpl.class);
	
//	public void setImageFileNamespace(String imageFileNamespace) {
//		this.imageFileNamespace = imageFileNamespace;
//	}

	@Transactional(readOnly=true)
	@Override
	public List<Customer> listMyCustomers(Integer userId) {
		return myCustomerDao.listMyCustomers(userId);
	}

	@Transactional(readOnly=true)
	@Override
	public Customer getMyCustomerDetails(Integer userId, Long customerId) {
		return myCustomerDao.getMyCustomerDetails(userId, customerId);
	}

	@Transactional(rollbackFor=Throwable.class)
	@Override
	public int deleteMyCustomer(Integer userId, Long customerId) {
		int count = myCustomerDao.deleteMyCustomer(userId, customerId);
		
		if (log.isDebugEnabled()) {
			log.debug("已删除 [" + count + "] 条客户记录。");
		}
		
		// 删除客户后（其实是逻辑删除改个状态而已），头像不用删除。
//		if (count != 0) {
//			String filename = resolveImageName(customerId);
//			boolean b = imageUploadManager.remove(imageFileNamespace, filename);
//			if (log.isDebugEnabled()) {
//				log.debug("删除头像文件" + (b ? "成功：" : "失败：") + filename);
//			}
//		}
		return count;
	}
	
	@Override
	public CustomerDetails getMyCustomerDetailsPageData(Integer userId, Long customerId) {
		
		// 查询客户，如果没有客户，直接返回空。
		Customer customer = getMyCustomerDetails(userId, customerId);
		if (customer == null) {
			return null;
		}
		
		// 查询该客户的计划书。
		List<CustomersPlanBook> planBooks = listMyCustomerBooks(userId, customerId);
		
		// 查询该客户的赠险领取记录。
		List<FreeReceiveLog> freeReceiveds = freeReceiveLogDao.listCustomersReceiveLog(userId, customerId);
		
		return new CustomerDetails(customer, planBooks, freeReceiveds);
	}

	@Transactional(rollbackFor=Throwable.class)
	@Override
	public int updateMyCustomer(Customer customer, MultipartFile file, boolean deleteImage) {
		
		/*
		 * 关于上传文件为空的情况：
		 * 1、用户不想更换头像，只希望更新数据，此时上传文件为空，并且不应该变更客户头像。
		 * 2、用户点击头像、并点击取消，此时上传文件为空，这样认为用户主动取消头像，视为删除头像。
		 * 
		 * 因此增加一个 deleteImage 参数加已确认。
		 * 界面上的逻辑：默认为 false，如果用户变更过头像，且文件为空时，将这个参数改为 true。
		 */
		
		int count;
		
		// 用户提交头像，此时应当保存。
		if (file != null && !file.isEmpty()) {
			
			String filename = System.currentTimeMillis() + ".jpg";
			customer.setImageUrl(filename);
			
			// 原头像文件名，应当在更新成功后，删除原文件。先查，更新后就查不到了。
			// 不删除头像
//			String oldImageName = myCustomerDao.getCustomerImage(customer.getId());
			
			count = updateCustomer(customer);
			
			if (count != 0) {
				imageUploadManager.upload(imageFileNamespace + "/" + customer.getId() + "/images", filename, file);
				if (log.isDebugEnabled()) {
					log.debug("文件上传成功：" + filename);
				}
				
//				if (oldImageName != null) {
//					imageUploadManager.remove(imageFileNamespace, oldImageName);
//					if (log.isDebugEnabled()) {
//						log.debug("删除原头像：" + oldImageName);
//					}
//				}
			}
			
		} else {
			
			// 上传文件为空的第二种情况
			if (deleteImage) {
				// 直接删除文件，并还原默认头像（imageURL=null）
				
				customer.setImageUrl(null);
				count = updateCustomer(customer);
				
				// 不删除头像
//				if (count != 0) {
//					String filename = myCustomerDao.getCustomerImage(customer.getId());
//					boolean b = imageUploadManager.remove(imageFileNamespace, filename);
//					
//					if (log.isDebugEnabled()) {
//						log.debug("删除头像文件" + (b ? "成功：" : "失败：") + filename);
//					}
//				}
			} 
			
			// 第一种情况，不应当修改客户头像字段。
			else {
				count = myCustomerDao.updateMyCustomerWithoutImage(customer);
				if (log.isDebugEnabled()) {
					log.debug("未上传头像，已更新 [" + count + "] 条客户记录。");
				}
			}
			
		}
		
		return count;
	}
	
	private int updateCustomer(Customer customer) {
		int count = myCustomerDao.updateMyCustomer(customer);
		if (log.isDebugEnabled()) {
			log.debug("已更新 [" + count + "] 条客户记录。");
		}
		return count;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<CustomersPlanBook> listMyCustomerBooks(Integer userId, Long customerId) {
		return myCustomerDao.listMyCustomerPlanBooks(userId, customerId);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	@Override
	public int deleteMyCustomerBook(Integer userId, Integer bookId) {
		return myCustomerDao.deleteMyCustomerBook(userId, bookId);
	}
	
}
