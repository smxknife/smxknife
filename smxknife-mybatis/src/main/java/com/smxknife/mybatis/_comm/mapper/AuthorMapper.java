package com.smxknife.mybatis._comm.mapper;

import com.smxknife.mybatis._comm.po.Author;

/**
 * @author smxknife
 * 2021/6/18
 */
public interface AuthorMapper {
	Author selectAuthor(int id);
}
