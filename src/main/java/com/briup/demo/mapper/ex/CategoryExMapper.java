package com.briup.demo.mapper.ex;

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

/**
 * 处理查询栏目及其包含的文章信息
 * @author zjk
 *
 */
public interface CategoryExMapper {
	/**
	 * 实现查询所有栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryEx();
	
	/**
	 * 根据栏目id查找栏目及栏目下的文章信息
	 */
	CategoryEx showByIdCategoryEx(Short id);
	

	
}
