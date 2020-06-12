package com.smxknife.ehcache.cache.demo01;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/6/10
 */
public class Main {
	public static void main(String[] args) {
		InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("ehcache.xml");
		CacheManager cacheManager = CacheManager.create(resourceAsStream);
		Cache sampleCache = cacheManager.getCache("sample_cache");
		sampleCache.getCacheEventNotificationService().registerListener(new Demo01CacheEventListener());

		if (Objects.nonNull(sampleCache)) {
			readCache(sampleCache, "hello");
			putCache(sampleCache, "hello", "world");
			readCache(sampleCache, "hello");
			updatedCache(sampleCache, "hello", "my world");
			deleteCache(sampleCache, "hello");
			cacheManager.shutdown();
		}
	}

	private static void deleteCache(Cache cache, Object key) {
		cache.remove(key);
	}

	private static void updatedCache(Cache cache, Object key, Object value) {
		cache.replace(new Element(key, value));
	}

	public static void putCache(Cache cache, Object key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	public static void readCache(Cache cache, Object key) {
		Element element = cache.get(key);
		if (Objects.nonNull(element)) {
			Object objectValue = element.getObjectValue();
			System.out.println(objectValue);
		} else {
			System.out.println("key is not in cache");
		}
	}
}
