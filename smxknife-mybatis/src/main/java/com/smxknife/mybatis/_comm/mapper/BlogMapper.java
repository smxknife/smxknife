package com.smxknife.mybatis._comm.mapper;

import com.smxknife.mybatis._comm.po.Blog;

/**
 * @author smxknife
 * 2021/6/18
 */
public interface BlogMapper {
	Blog selectBlog(int id);
}
