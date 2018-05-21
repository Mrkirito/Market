package cn.usst.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CompetitionMapper;
import cn.usst.market.mapper.CustomerSupportMapper;
import cn.usst.market.po.CustomerSupport;
import cn.usst.market.service.CustomerSupportService;

@Service("customerSupportService")
public class CustomerSupportServiceImpl implements CustomerSupportService {
	@Autowired
	private CustomerSupportMapper customerSupportMapper;

	@Override
	public int insertCustomerSupport(CustomerSupport customerSupport) {
		// TODO Auto-generated method stub
		return customerSupportMapper.insertCustomerSupport(customerSupport);
	}
	
}
