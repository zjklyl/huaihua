package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "登录相关接口")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/loginCustomer")
	@ApiOperation("登录接口")
	public Message<String> loginCustomer(Customer customer){
		System.out.println(customer);
		List<Customer> list = customerService.findByUsername(customer.getUsername());		
		//如果用户名存在,即用户名正确
		//注意，list根据条件没查询到数据，但是list 已经存在，所以不是null，而为空，需要用list.size进行判断
		if(list.size() != 0) {
		Customer customer2 = list.get(0);
			//如果密码相同
		//密码为String类型，要用 .equals 进行比较
			if(customer2.getPassword().equals(customer.getPassword()))
				return MessageUtil.success("登录成功");
			else 
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "密码错误");
		}
		else
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "用户名错误或用户不存在");
		
		
	}
	//其实也不用做密码判空处理，因为设置了密码必须填写，懒得改了
	
	@PostMapping("/registerCustomer")
	@ApiOperation("注册接口")
	public Message<String> registerCustomer(Customer customer){
		List<Customer> list = customerService.findByUsername(customer.getUsername());
		//如果用户名不存在且密码不为空，则可以保存
		if(list.size() == 0 && customer.getPassword() != null) {
			
			customerService.save(customer);
			return MessageUtil.success("保存成功");
		}
		else
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "注册失败，用户名重复");
		
		
	}
}
