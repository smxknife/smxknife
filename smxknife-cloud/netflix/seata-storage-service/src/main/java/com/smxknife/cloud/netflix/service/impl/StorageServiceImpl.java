package com.smxknife.cloud.netflix.service.impl;

import com.smxknife.cloud.netflix.entity.Storage;
import com.smxknife.cloud.netflix.repository.StorageRepository;
import com.smxknife.cloud.netflix.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/31
 */
@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private StorageRepository repository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deduct(String commodityCode, int count) {
		Storage storage = repository.findByCommodityCode(commodityCode);
		if (Objects.isNull(storage)) {
			throw new IllegalArgumentException("Storage with commodityCode = " + commodityCode + " is not exist!");
		}
		final int storageCount = storage.getCount();
		if (storageCount < count) {
			throw new IllegalArgumentException("Storage with commodityCode = " + commodityCode + " count is not enough!");
		}

		storage.setCount(storageCount - count);
		repository.save(storage);
	}
}
