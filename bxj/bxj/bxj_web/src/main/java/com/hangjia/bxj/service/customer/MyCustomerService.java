package com.hangjia.bxj.service.customer;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.CustomersPlanBook;

/**
 * 【我的客户】，
 * @author K9999
 *
 */
public interface MyCustomerService {
	
	/**
	 * 返回指定用户的全部客户列表。
	 * @param userId 指定用户ID。
	 * @return
	 */
	List<Customer> listMyCustomers(Integer userId);

	/**
	 * 返回指定用户的指定客户。
	 * @param userId 指定用户ID。
	 * @param customerId 指定需获取详情的客户ID。
	 * @return
	 */
	Customer getMyCustomerDetails(Integer userId, Long customerId);
	
	/**
	 * 返回我的客户 详情页数据（客户资料、赠险、计划书等）。
	 * @param userId
	 * @param customerId
	 * @return 如果没找到客户记录，返回 null。否则返回 CustomerDetails 对象，其中的字段都不会为空。
	 */
	CustomerDetails getMyCustomerDetailsPageData(Integer userId, Long customerId);

	/**
	 * 删除指定用户的指定客户。
	 * @param userId 指定用户ID。
	 * @param customerId
	 * @return
	 */
	int deleteMyCustomer(Integer userId, Long customerId);

	/**
	 * 更新指定用户的指定客户信息。
	 * 调用 {@link Customer#getUserId()} 获取当前用户ID，
	 * 调用 {@link Customer#getId()} 获取需要被更新信息的客户ID。
	 * 调用此方法前必须注入这些参数。
	 * @param customer 被更新的客户信息。
	 * @param file 头像文件。如果不为空，则更新头像信息。
	 * @param deleteImage 删除头像，如果为 {@code true}，且 file 为空，删除客户的头像文件。
	 */
	int updateMyCustomer(Customer customer, MultipartFile file, boolean deleteImage);
	
	List<CustomersPlanBook> listMyCustomerBooks(Integer userId, Long customerId);

	/**
	 * 删除我（为客户制定）的计划书。
	 * @param userId 用户ID。 
	 * @param customerId 客户ID。
	 * @param bookId 计划书标识。 
	 * @return
	 */
	int deleteMyCustomerBook(Integer userId, Integer bookId);
	
}
