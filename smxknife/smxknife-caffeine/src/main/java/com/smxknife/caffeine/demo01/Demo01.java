package com.smxknife.caffeine.demo01;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

/**
 * @author smxknife
 * 2018-12-06
 */
public class Demo01 {
	public static void main(String[] args) {
		LoadingCache<String, DataObject> cache = Caffeine.newBuilder().maximumWeight(5)
				.weigher((k, v) -> {
					System.out.println("k = " + k);
					System.out.println("v = " + v);
					return 3;
				})
				.build(k -> {
					System.out.println("k = " + k);
					DataObject object = new DataObject();
					object.name = "aaa";
					return object;
				});

		for (int i = 0; i < 10; i++) {
			DataObject dataObject = new DataObject();
			dataObject.name = "name" + i;
			cache.put(dataObject.name, dataObject);
		}
		System.out.println(cache.estimatedSize());
	}
}

class DataObject {
	String name;
	int age;
}
