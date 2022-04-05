package com.smxknife.mybatis.demo.ch01_fromxml;

import com.smxknife.mybatis.demo.ch01_fromxml.mapper.BlogMapper;
import com.smxknife.mybatis.demo.ch01_fromxml.model.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author smxknife
 * 2020/11/6
 */
public class _Main {
	public static void main(String[] args) throws IOException {
		String resource = "ch01/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			Blog blog1 = (Blog)sqlSession.selectOne("com.smxknife.mybatis.demo.ch01_fromxml.mapper.BlogMapper.selectBlog", 1);
			System.out.println(blog1);
			BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
			System.out.println(blogMapper.getClass().getName());
			Blog blog2 = blogMapper.selectBlog(2);
			System.out.println(blog2);

			Blog blog22 = blogMapper.selectBlogDetail(2);
			System.out.println(blog22);
			System.out.println(blog22.getAuthor());
			System.out.println(blog22.getPosts());
		}
	}
}
