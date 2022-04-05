package com.smxknife.mybatis.demo.ch01_fromxml.mapper;

import com.smxknife.mybatis.demo.ch01_fromxml.model.Blog;

/**
 * @author smxknife
 * 2020/11/6
 */
public interface BlogMapper {
	Blog selectBlog(int id);

	Blog selectBlogDetail(int id);
}
