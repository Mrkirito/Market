package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.CustomersPlanBook;
import com.hangjia.bxj.model.PlanBook;

public interface MyCustomerDao {

	/**
	 * 查询【我】的全部客户。
	 * @param userId 【我】的用户ID。
	 * @return 【我】的所有【客户】列表，无结果时返回空集（size=0）。
	 */
	List<Customer> listMyCustomers(Integer userId);

	/**
	 * 查询【我】的客户详情。
	 * @param userId 【我】的用户ID。
	 * @param customerId 需要查询详情的客户ID，此客户必须是【我】的客户，否则视为无结果。
	 * @return 客户详情，或 {@code null}（无结果）。
	 */
	Customer getMyCustomerDetails(Integer userId, Long customerId);

	/**
	 * 
	 * @param userId
	 * @param customerId
	 * @return
	 */
	String getCustomerImage(Long customerId);

	/**
	 * 更新指定客户的头像数据记录。
	 * 仅更新数据记录，不影响任何文件。
	 * @param customerId 客户ID，指定更新哪个客户。
	 * @param filename 客户头像文件名称。
	 * @return 被更新的记录数，返回结果只可能是 0 或 1。（0：没有更新到记录，1：更新1条记录，因为是主键唯一）。
	 */
	int updateCustomerImage(Long customerId, String filename);
	
	int deleteMyCustomer(Integer userId, Long customerId);
	
	int updateMyCustomer(Customer customer);

	/**
	 * 更新我的客户记录，但不更新头像字段。
	 * @param customer
	 * @return
	 */
	int updateMyCustomerWithoutImage(Customer customer);
	
	/**
	 * 查询计划书。
	 * 查询指定用户的指定客户的所有计划书。
	 * @param userId 用户ID。
	 * @param customerId 客户ID，这个客户必须和 userId 对应，否则视为无结果。
	 * @return 计划书列表，或空列表（无结果时）。
	 */
	List<CustomersPlanBook> listMyCustomerPlanBooks(Integer userId, Long customerId);

	/**
	 * 删除【我】为客户制定的【计划书】。
	 * @param userId 我的用户ID。
	 * @param bookId 计划书ID，此计划书必须是【我】制定的，否则不予删除。
	 * @return 如果已删除，返回1，否则返回0（没有删除任何记录）。
	 */
	int deleteMyCustomerBook(Integer userId, Integer bookId);
	/**
	 * 生成计划书时  新增 客户
	 * @param customer
	 * @return
	 */
	int insertCustomer(Customer customer);
	/**
	 * 为客户绑定计划书
	 * @param customersPlanBook
	 * @return
	 */
	int insertCustomerBookRel(CustomersPlanBook customersPlanBook);
	/**
	 * 根据姓名获得重名的客户
	 * @param userId
	 * @return
	 */
	List<Customer> listMyCustomersByName(PlanBook planBook);
	/**
	 * 更新CustomersPlanBook
	 * @param customersPlanBook
	 * @return
	 */
	int updateCustomersPlanBook(CustomersPlanBook customersPlanBook);

	/**
	 * 根据身份证号和用户ID，查询客户ID。
	 * @param userId
	 * @param idCardCode
	 * @return
	 */
	Long selectCustomerIdByIdCard(Integer userId, String idCardCode);
	/**
	 * 
	 * @param customerId
	 * @param planId
	 * @return
	 */
	CustomersPlanBook getCustomersPlanBook(Long customerId,Long planId);
}
