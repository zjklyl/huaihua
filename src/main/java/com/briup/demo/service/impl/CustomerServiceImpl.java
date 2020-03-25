package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
import com.briup.demo.mapper.CustomerMapper;
import com.briup.demo.service.ICustomerService;

@Service
public class CustomerServiceImpl{

	private CustomerMapper customerMapper;
	public void registerCustomer(Customer customer) {
		CustomerExample example = new CustomerExample();
		example.createCriteria().andUsernameEqualTo(customer.getUsername());
		if(customerMapper.selectByExample(example) ==null) {
		}
		
		
	}

	public void loginCustomer() {
		// TODO Auto-generated method stub
		
	}

}
