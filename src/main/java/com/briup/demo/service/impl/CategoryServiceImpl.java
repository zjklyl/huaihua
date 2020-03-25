package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class CategoryServiceImpl implements ICategoryService{
	//栏目dao
	@Autowired
	private CategoryMapper categorymap;
	//文章dao
	@Autowired
	private ArticleMapper articlemap;
	
	@Autowired
	private CategoryExMapper categoryExmap;
	
	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		
		CategoryExample example = new CategoryExample();
		List<Category> list = categorymap.selectByExample(example);
		return list;
	}

	@Override
	public void savaOrUpdateCategory(Category category) throws CustomerException {
		if(category == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"参数为空");
		}
		if(category.getId() == null) {
			categorymap.insert(category);
		}
		else{
			categorymap.updateByPrimaryKey(category);
		}
	}

	@Override
	public void deleteCategoryById(Short id) throws CustomerException{
		//删除栏目的同时需要把栏目下的文章删除
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articlemap.deleteByExample(example);
		
		categorymap.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(Short id) throws CustomerException {
		Category c = categorymap.selectByPrimaryKey(id);
		return c;
	}

	@Override
	public List<CategoryEx> findAllCategryExs() throws CustomerException {
		return categoryExmap.findAllCategoryEx();
	}

	@Override
	public CategoryEx showByIdCategoryEx(Short id) throws CustomerException {
		return categoryExmap.showByIdCategoryEx(id);
		
	}



}
