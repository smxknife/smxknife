package com.smxknife.ehcache.cache.demo01;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * @author smxknife
 * 2020/6/10
 */
public class Demo01CacheEventListener implements CacheEventListener {
	@Override
	public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
		System.out.println(ehcache.getName() + " remove " + element.getObjectKey() + " : " + element.getObjectValue());
	}

	@Override
	public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
		System.out.println(ehcache.getName() + " put " + element.getObjectKey() + " : " + element.getObjectValue());
	}

	@Override
	public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
		System.out.println(ehcache.getName() + " updated " + element.getObjectKey() + " : " + element.getObjectValue());
	}

	@Override
	public void notifyElementExpired(Ehcache ehcache, Element element) {
		System.out.println(ehcache.getName() + " expired " + element.getObjectKey() + " : " + element.getObjectValue());
	}

	@Override
	public void notifyElementEvicted(Ehcache ehcache, Element element) {
		System.out.println(ehcache.getName() + " evicted " + element.getObjectKey() + " : " + element.getObjectValue());
	}

	@Override
	public void notifyRemoveAll(Ehcache ehcache) {
		System.out.println(ehcache.getName() + " removeAll");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return this;
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
	}
}
