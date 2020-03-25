package com.briup.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;

/**
 * 用户相关的service接口
 * @author zjk
 *
 */
public interface ICustomerService extends JpaRepository<Customer, Long>{
	List<Customer> findByUsername(String username);
	List<Customer> findByPassword(String password);
}
