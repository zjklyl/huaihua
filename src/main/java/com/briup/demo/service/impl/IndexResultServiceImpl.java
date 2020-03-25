package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;

@Service
public class IndexResultServiceImpl implements IIndexResultService{

	@Autowired
	private ILinkService linkService;
	
	@Autowired
	private ICategoryService categoryService;
	
	
	@Override
	public IndexResult findIndexAllResult() throws CustomerException {
		IndexResult indexResult = new IndexResult();
		//设置所有的超链接信息
		indexResult.setLinks(linkService.findAllLinks());
		//设置所有栏目及其包含的所有文章信息
		indexResult.setCategoryExs(categoryService.findAllCategryExs());
		return indexResult;
	}


}
