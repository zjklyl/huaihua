
package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 栏目相关的Service层
 * @author zjk
 * 
 * */
public interface ICategoryService {

	/**
	 * 查询所有的栏目
	 * */
	public List<Category> findAllCategorys() throws CustomerException;
	/**
	 * 添加或修改栏目信息
	 * */
	public void savaOrUpdateCategory(Category category) throws CustomerException;
	/**
	 * 根据id删除栏目信息
	 * */
	public void deleteCategoryById(Short i);
	/**
	 * 根据id查找指定的栏目信息
	 * */
	public Category findCategoryById(Short id) throws CustomerException;
	/**
	 * 查询栏目信息并且级联查询包含的文章信息
	 */
	public List<CategoryEx> findAllCategryExs() throws CustomerException;
	
	/**
	 * 查询指定id栏目下的文章信息
	 */
	public CategoryEx showByIdCategoryEx(Short id) throws CustomerException;
}
