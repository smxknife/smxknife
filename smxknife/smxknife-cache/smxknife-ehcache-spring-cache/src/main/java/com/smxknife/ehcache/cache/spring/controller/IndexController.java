package com.smxknife.ehcache.cache.spring.controller;

import com.smxknife.ehcache.cache.spring.model.Index;
import com.smxknife.ehcache.cache.spring.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author smxknife
 * 2020/6/11
 */
@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	private IndexService indexService;

	@GetMapping
	public Index cachable(Long id, HttpServletRequest request) {
		int localPort = request.getLocalPort();
		return indexService.cachable(id, localPort + "");
	}

	@GetMapping("put")
	public Index put(Long id) {
		return indexService.cachePut(id);
	}

	@GetMapping("evict")
	public void evict(Long id) {
		indexService.cacheEvict(id);
		return;
	}
}
