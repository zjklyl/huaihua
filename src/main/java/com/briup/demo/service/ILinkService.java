package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;
import com.briup.demo.utils.CustomerException;

/**
 * 关于链接的service层
 * 保存链接信息
 * @author zjk
 * */
public interface ILinkService {
	/**
	 * 保存修改链接。
	 * 
	 * */
	void savaOrUpdateLink(Link link) throws CustomerException;
	/**
	 * 查询所有链接信息 
	 * */
	List<Link> findAllLinks() throws CustomerException;
	/**
	 * 根据id删除链接
	 * */
	void deleteLinkById(Short id) throws CustomerException;
	/**
	 * 根据 链接名 查询链接
	 * */
	List<Link> findLinksByName(String name) throws CustomerException;
	/**
	 * 根据id 查询链接
	 * */
	Link findLinksById(Short id) throws CustomerException;
}
