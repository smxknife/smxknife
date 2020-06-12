package com.smxknife.ehcache.cache.spring.service.impl;

import com.smxknife.ehcache.cache.spring.model.Index;
import com.smxknife.ehcache.cache.spring.service.IndexService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author smxknife
 * 2020/6/11
 */
@CacheConfig(cacheNames = "rmi_index_cache")
@Service
public class IndexServiceImpl implements IndexService {

	@Override
	//@Cacheable(value = "my_index_cache", key = "'idx_'+ #id")
	@Cacheable(key = "'idx_' + #id")
	public Index cachable(Long id, String name) {
		System.out.println("cachable...");
		Index index = new Index();
		index.setId(1L);
		index.setCode("hello");
		index.setName(name);
		return index;
	}

	@Override
//	@CachePut(value = "my_index_cache", key = "'idx_' + #id")
	@CachePut(key = "'idx_' + #id")
	public Index cachePut(Long id) {
		System.out.println("cachePut...");
		Index index = new Index();
		index.setId(id);
		index.setCode(LocalTime.now().toString());
		index.setName("test");
		return index;
	}

	@Override
	//@CacheEvict(value = "my_index_cache", key = "'idx_' + #id")
	@CacheEvict(key = "'idx_' + #id")
	public Index cacheEvict(Long id) {
		return null;
	}


}
