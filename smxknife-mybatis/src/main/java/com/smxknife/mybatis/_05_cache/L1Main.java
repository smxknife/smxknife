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
public class L1Main {
	public static void main(String[] args) throws IOException {
		final Properties properties = new Properties();
		properties.put("driver", "com.mysql.jdbc.Driver");
		properties.put("username", "root");
		properties.put("password", "root");
		properties.put("url", "jdbc:mysql://localhost:3306/mybatis-learn");

		final InputStream xmlStream = Resources.getResourceAsStream("_05_l1_cache.xml");
		final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlStream, properties);

		try {
			System.out.println("第一次查询 --------------------------------");
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
			Blog blog1 = blogMapper.selectBlog(1);
			//System.out.println(blog1);

			System.out.println("第二次查询 --------------------------------");
			final Blog blog2 = blogMapper.selectBlog(1);
			//System.out.println(blog2);
			sqlSession.commit();

			System.out.println("第三次查询 --------------------------------");
			sqlSession = sqlSessionFactory.openSession(true);
			blogMapper = sqlSession.getMapper(BlogMapper.class);
			final Blog blog3 = blogMapper.selectBlog(1);
			//System.out.println(blog3);

			System.out.println("第四次查询 --------------------------------");
			sqlSession = sqlSessionFactory.openSession();
			blogMapper = sqlSession.getMapper(BlogMapper.class);
			final Blog blog4 = blogMapper.selectBlog(1);
			//System.out.println(blog4);
			sqlSession.commit();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
