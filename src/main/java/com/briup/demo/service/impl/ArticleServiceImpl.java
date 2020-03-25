package com.briup.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class ArticleServiceImpl implements IArticleService{

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void savaOrUpdateArticle(Article article) throws CustomerException {
		if(article == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
		}
		if(article.getId() == null) {
			//需要额外添加数据（点赞个数，发表日期）
			article.setPublisurdate(new Date());
			article.setClicktimes((short)0);
			articleMapper.insert(article);
		}
		else {
			article.setPublisurdate(new Date());
			articleMapper.updateByPrimaryKey(article);
		}
	}

	@Override
	public void deleteArticleById(Short id) throws CustomerException {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Article> findArticleConditiong(String keyStr, String conditing) throws CustomerException {
		/**
		 * 分三种情况：
		 * 1.无栏目 无关键字 查询所有
		 * 2.无栏目 有关键字 根据关键字查询
		 * 3.有栏目 无关键字 根据栏目查询
		 * 4.有栏目 有关键字 根据栏目关键字查询
		 * */
		keyStr = keyStr == null ? "" : keyStr.trim();
		conditing = conditing == null ? "" : conditing.trim();
		
		ArticleExample example = new ArticleExample();
		if("".equals(keyStr) && "".equals(conditing)) {
			return articleMapper.selectByExample(example);
		}
		else if(!"".equals(keyStr) && "".equals(conditing)) {
			example.createCriteria().andTitleLike("%"+keyStr+"%");
			return articleMapper.selectByExample(example);
		}
		else if(!"".equals(conditing) && "".equals(keyStr)) {
			CategoryExample example2 = new CategoryExample();
			example2.createCriteria().andNameEqualTo(conditing);
			List<Category> category = categoryMapper.selectByExample(example2);
			if(category.size()>0) {
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());				
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有指定的搜索栏目");
			}
			return articleMapper.selectByExample(example);
		}
		else {
			CategoryExample example3 = new CategoryExample();
			example3.createCriteria().andNameEqualTo(conditing);
			List<Category> list = categoryMapper.selectByExample(example3);
			
			example.createCriteria().andCategoryIdEqualTo(list.get(0).getId())
			.andTitleLike("%"+keyStr+"%");
			return articleMapper.selectByExample(example);
		}
	}

	@Override
	public Article findArticleById(Short id) throws CustomerException {
		
		return articleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 点击数增加
	 */
	public void clink(Article article)throws CustomerException{
		articleMapper.updateByPrimaryKey(article);
	}
}
