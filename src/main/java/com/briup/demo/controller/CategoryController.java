package com.briup.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "栏目相关接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	

	@GetMapping("/findAllCategory")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> findAllCategory(){
		List<Category> list = categoryService.findAllCategorys();
		return MessageUtil.success(list);
	}
	
	@PostMapping("/addCategory")
	@ApiOperation("保存栏目")
	public Message<String> addCategory(Category category){
		try {
//			if(null == categoryService.findCategoryById(category.getId()))
//			{
				categoryService.savaOrUpdateCategory(category);
				return MessageUtil.success();
//			}
//			else 
//				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "添加失败，栏目id已存在");
			
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
		
	}
	
	@PostMapping("/updataCategory")
	@ApiOperation("更新栏目信息")
	public Message<String> updataCategory(Category category){
			categoryService.savaOrUpdateCategory(category);
			return MessageUtil.success();
		
	}
	
	@GetMapping("/deleteCategory")
	@ApiOperation("根据id删除栏目")
	public Message<List<Category>> deleteByIdCategory(Short id){
		categoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	@PostMapping("/findByIdCategory")
	@ApiOperation("根据id查找栏目")
	public Message<List<Category>> findByIdCategory(Short id){
		Category c = categoryService.findCategoryById(id);
		List<Category> l = new ArrayList<Category>();
		l.add(c);
		return MessageUtil.success(l);
		
	}
	
	@PostMapping("/showCategory")
	@ApiOperation("根据id查找栏目及文章")
	public Message<CategoryEx> showCategory(Short id){
		
		
		return MessageUtil.success(categoryService.showByIdCategoryEx(id));
		
	}
}
