package com.smxknife.cache.custom.jsr107;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/6/8
 */
public class CsCache107Manager implements CacheManager {
	public void releaseCache(String cacheName) {

	}

	@Override
	public CachingProvider getCachingProvider() {
		return null;
	}

	@Override
	public URI getURI() {
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

	@Override
	public Properties getProperties() {
		return null;
	}

	@Override
	public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String s, C c) throws IllegalArgumentException {
		return null;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String s, Class<K> aClass, Class<V> aClass1) {
		return null;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String s) {
		return null;
	}

	@Override
	public Iterable<String> getCacheNames() {
		return null;
	}

	@Override
	public void destroyCache(String s) {

	}

	@Override
	public void enableManagement(String s, boolean b) {

	}

	@Override
	public void enableStatistics(String s, boolean b) {

	}

	@Override
	public void close() {

	}

	@Override
	public boolean isClosed() {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> aClass) {
		return null;
	}
}
