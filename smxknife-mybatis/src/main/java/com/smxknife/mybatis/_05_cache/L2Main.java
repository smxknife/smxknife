package com.smxknife.mybatis._05_cache;

import com.smxknife.mybatis._comm.mapper.BlogMapper;
import com.smxknife.mybatis._comm.po.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author smxknife
 * 2021/6/18
 */
public class L2Main {
	public static void main(String[] args) throws IOException {
		final Properties properties = new Properties();
		properties.put("driver", "com.mysql.jdbc.Driver");
		properties.put("username", "root");
		properties.put("password", "root");
		properties.put("url", "jdbc:mysql://localhost:3306/mybatis-learn");

		final InputStream xmlStream = Resources.getResourceAsStream("_05_l2_cache.xml");
		final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlStream, properties);

		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			final BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
			final Blog blog1 = blogMapper.selectBlog(1);
			System.out.println(blog1);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog2 = blogMapper.selectBlog(1);
			System.out.println(blog2);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog3 = blogMapper.selectBlog(1);
			System.out.println(blog3);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog4 = blogMapper.selectBlog(1);
			System.out.println(blog4);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog5 = blogMapper.selectBlog(1);
			System.out.println(blog5);
			sqlSession.commit();


			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog6 = blogMapper.selectBlog(2);
			System.out.println(blog6);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog7 = blogMapper.selectBlog(3);
			System.out.println(blog7);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog8 = blogMapper.selectBlog(2);
			System.out.println(blog8);
			sqlSession.commit();

			System.out.println("--------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			final Blog blog9 = blogMapper.selectBlog(1);
			System.out.println(blog9);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
