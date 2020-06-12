package com.smxknife.cache.custom.jsr107;

import com.smxknife.cache.custom.CsCache;
import com.smxknife.cache.custom.store.impl.BasicDataStore;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author smxknife
 * 2020/6/8
 */
public class Cs107Cache<K, V> implements Cache<K, V> {

	private CsCache<K, V> csCache = new CsCache<>(new BasicDataStore<>());
	private volatile boolean isClosed;

	private String cacheName;
	private CsCache107Manager cacheManager;

	private Configuration<K, V> configuration;

	public Cs107Cache(CsCache107Manager cacheManager, String cacheName, Configuration<K, V> configuration) {
		this.cacheManager = cacheManager;
		this.cacheName = cacheName;
		this.configuration = configuration;
		this.isClosed = false;
	}

	@Override
	public V get(K k) {
		return csCache.get(k);
	}

	@Override
	public Map<K, V> getAll(Set<? extends K> set) {
		return null;
	}

	@Override
	public boolean containsKey(K k) {
		return csCache.get(k) != null;
	}

	@Override
	public void loadAll(Set<? extends K> set, boolean b, CompletionListener completionListener) {

	}

	@Override
	public void put(K k, V v) {

	}

	@Override
	public V getAndPut(K k, V v) {
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {

	}

	@Override
	public boolean putIfAbsent(K k, V v) {
		return false;
	}

	@Override
	public boolean remove(K k) {
		return false;
	}

	@Override
	public boolean remove(K k, V v) {
		return false;
	}

	@Override
	public V getAndRemove(K k) {
		return null;
	}

	@Override
	public boolean replace(K k, V v, V v1) {
		return false;
	}

	@Override
	public boolean replace(K k, V v) {
		return false;
	}

	@Override
	public V getAndReplace(K k, V v) {
		return null;
	}

	@Override
	public void removeAll(Set<? extends K> set) {

	}

	@Override
	public void removeAll() {

	}

	@Override
	public void clear() {
		csCache.clear();
	}

	@Override
	public <C extends Configuration<K, V>> C getConfiguration(Class<C> aClass) {
		return null;
	}

	@Override
	public <T> T invoke(K k, EntryProcessor<K, V, T> entryProcessor, Object... objects) throws EntryProcessorException {
		return null;
	}

	@Override
	public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> set, EntryProcessor<K, V, T> entryProcessor, Object... objects) {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public CacheManager getCacheManager() {
		return this.cacheManager;
	}

	@Override
	public synchronized void close() {
		if (!isClosed) {
			isClosed = true;

			if (cacheManager != null) {
				cacheManager.releaseCache(cacheName);
			}
			csCache.clear();
		}
	}

	@Override
	public boolean isClosed() {
		return this.isClosed;
	}

	@Override
	public <T> T unwrap(Class<T> aClass) {
		return null;
	}

	@Override
	public void registerCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

	}

	@Override
	public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return null;
	}
}
