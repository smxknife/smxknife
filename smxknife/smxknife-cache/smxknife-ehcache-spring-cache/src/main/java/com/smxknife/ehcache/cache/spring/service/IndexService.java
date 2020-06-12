package com.smxknife.ehcache.cache.spring.service;

import com.smxknife.ehcache.cache.spring.model.Index;

/**
 * @author smxknife
 * 2020/6/11
 */
public interface IndexService {
	Index cachable(Long id, String name);

	Index cachePut(Long id);

	Index cacheEvict(Long id);
}
