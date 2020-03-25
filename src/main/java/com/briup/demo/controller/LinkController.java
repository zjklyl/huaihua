package com.briup.demo.controller;
/**
 * 与链接相关的 和前端交互的web层
 * @author zjk
 * 
 * */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Link;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController 
@Api(description = "链接相关接口")
public class LinkController {

	//将实现类的对象添加到了容器中，能自动把容器内的实现对象关联过来
	@Autowired
	private ILinkService linkService;
	
	@PostMapping("/addLink")
	@ApiOperation("新增链接")
	public Message<String> addLink(Link link){   
		
		try {
			if(linkService.findLinksById(link.getId()) == null) {
				linkService.savaOrUpdateLink(link);
				return MessageUtil.success();
			}
			else
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "添加失败，id已存在");
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	
	@PostMapping("/updataLink")
	@ApiOperation("更新链接")
	public Message<String> updateLink(Link link){
		linkService.savaOrUpdateLink(link);
		return MessageUtil.success();
	}
	
	@GetMapping("/findLinks")
	@ApiOperation("查询所有链接")
	public Message<List<Link>> selectLinks(){
		List<Link> list = linkService.findAllLinks();
		return MessageUtil.success(list);
	}
	
	@GetMapping("/deleteLinkById")
	@ApiOperation("根据id删除链接")
	public Message<List<Link>> deleteById(Short id){
		linkService.deleteLinkById(id);
		return MessageUtil.success();
	}
	@GetMapping("/selectLinkByNameLike")
	@ApiOperation("根据链接名查询")
	public Message<List<Link>> selectByName(String name){
		List<Link> list = linkService.findLinksByName(name);
		return MessageUtil.success(list);
	}
}