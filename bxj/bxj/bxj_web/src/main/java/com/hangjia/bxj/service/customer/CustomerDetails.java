package com.hangjia.bxj.service.customer;

import java.io.Serializable;
import java.util.List;

import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.CustomersPlanBook;
import com.hangjia.bxj.model.FreeReceiveLog;

/**
 * 客户详情页，数据封装类。
 * @author K9999
 *
 */
public class CustomerDetails implements Serializable {

	private final Customer customer;
	
	private final List<CustomersPlanBook> planBooks;
	
	private final List<FreeReceiveLog> freeReceiveds;
	
	public CustomerDetails(Customer customer, List<CustomersPlanBook> planBooks, List<FreeReceiveLog> freeReceiveds) {
		this.customer = customer;
		this.planBooks = planBooks;
		this.freeReceiveds = freeReceiveds;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<CustomersPlanBook> getPlanBooks() {
		return planBooks;
	}

	public List<FreeReceiveLog> getFreeReceiveds() {
		return freeReceiveds;
	}

	private static final long serialVersionUID = -5472648487647228956L;

}
