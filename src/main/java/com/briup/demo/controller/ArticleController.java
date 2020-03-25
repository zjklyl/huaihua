package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * 文章相关信息的controller
 * 
 */
@RestController
@Api(description = "文章相关接口")
public class ArticleController {

	
	private static Short cli;
	
	@Autowired
	private IArticleService articleService;

	@PostMapping("/addArticle")
	@ApiOperation("添加文章信息")
	public Message<String> savaArticle(Article article) {
		try {
			articleService.savaOrUpdateArticle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误" + e.getMessage());
		}
	}

	@PostMapping("/deleteArticleById")
	@ApiOperation("根据id删除文章")
	public Message<String> deleteArticle(Short id) {
			articleService.deleteArticleById(id);
			return MessageUtil.success();
		
	}

	@GetMapping("/findAriticleByCondition")
	@ApiOperation("根据条件查询文章信息")
	public Message<List<Article>> getArticleByClicktimes(@ApiParam("搜索的关键字")String keyStr,@ApiParam("搜索的栏目") String conditing) {
		try {
			List<Article> list = articleService.findArticleConditiong(keyStr, conditing);
			return MessageUtil.success(list);
			
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}

	@GetMapping("/findAriticleById")
	@ApiOperation("根据Id查询文章信息")
	public Message<Article> getArticlesById(Short id) {
			
		return MessageUtil.success(articleService.findArticleById(id));
	}
	
	@GetMapping("/showByIdAriticle")
	@ApiOperation("根据Id查询文章信息，点击数加1")
	public Message<Article> showByIdAriticle(Short id) {
		//尽量让service层来修改数据库的数据，所以调用service层中的方法来对点击数进行自加1；
		//且点击数应该是web层中的功能
		Article article = articleService.findArticleById(id);
		//在查询文章时读取对象的点击数，保证是每个对象的点击数
		cli = article.getClicktimes();
		
		if(cli  == null)
			cli = 0;
		else
			cli++;
		
		article.setClicktimes(cli);
		articleService.clink(article);
		return MessageUtil.success(article);
	}

}
