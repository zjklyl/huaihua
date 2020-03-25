package com.briup.demo.service;

import java.util.List;


import com.briup.demo.bean.Article;
import com.briup.demo.utils.CustomerException;

/**
 * 文章相关内容的service接口
 * */
public interface IArticleService {
	/**
	 * 新增或修改文章
	 * */
	public void savaOrUpdateArticle(Article article) throws CustomerException;
	/**
	 * 删除文章
	 * */
	public void deleteArticleById(Short id) throws CustomerException;
	/**
	 * 
	 * @param keyStr
	 * @param conditing
	 * @return
	 * @throws CustomerException
	 */
	//根据关键字或者栏目信息进行查询
	public List<Article> findArticleConditiong(String keyStr,String conditing)
			throws CustomerException;
	/**
	 * 根据id查询文章
	 * */
	public Article findArticleById(Short id) throws CustomerException;
	/**
	 * 点击数增加
	 */
	public void clink(Article article) throws CustomerException;
}
