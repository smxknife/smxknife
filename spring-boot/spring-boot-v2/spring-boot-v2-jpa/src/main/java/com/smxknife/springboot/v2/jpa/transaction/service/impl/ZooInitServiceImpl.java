package com.smxknife.springboot.v2.jpa.transaction.service.impl;

import com.smxknife.springboot.v2.jpa.transaction.domain.Lion;
import com.smxknife.springboot.v2.jpa.transaction.domain.Zoo;
import com.smxknife.springboot.v2.jpa.transaction.repository.LionRepository;
import com.smxknife.springboot.v2.jpa.transaction.repository.ZooRepository;
import com.smxknife.springboot.v2.jpa.transaction.service.ZooInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author smxknife
 * 2019-08-09
 */
@Service
public class ZooInitServiceImpl implements ZooInitService {

	@Autowired
	ZooRepository zooRepository;

	@Autowired
	LionRepository lionRepository;

	@Override
	@Transactional
	public void normal() {
		Zoo zoo = new Zoo();
		zoo.setName("Normal: 中心动物园");
		zooRepository.save(zoo);

		Lion lion = new Lion();
		lion.setName("Normal: 雄狮");
		lionRepository.save(lion);
	}

	@Override
	public void nonTranException() throws Exception {
		Zoo zoo = new Zoo();
		zoo.setName("NonTranException: 中心动物园");
		zooRepository.save(zoo);

		Lion lion = new Lion();
//		if (Objects.isNull(lion.getName())) {
//			throw new Exception("nonTranException");
//		}
//		Objects.requireNonNull(lion.getName());
//		lion.setName("NonTranException: 雄狮");
		lionRepository.save(lion);
	}

	@Override
	@Transactional
	public void tranException() throws Exception {
		Zoo zoo = new Zoo();
		zoo.setName("TranException: 中心动物园");
		zooRepository.save(zoo);

		Lion lion = new Lion();
//		if (Objects.isNull(lion.getName())) {
//			throw new Exception("tranException");
//		}
//		Objects.requireNonNull(lion.getName());
//		lion.setName("TranException: 雄狮");
		lionRepository.save(lion);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void annWithTranException() throws Exception {
		Zoo zoo = new Zoo();
		zoo.setName("annWithTranException: 中心动物园");
		zooRepository.save(zoo);

		Lion lion = new Lion();
//		if (Objects.isNull(lion.getName())) {
//			throw new Exception("annWithTranException");
//		}
//		Objects.requireNonNull(lion.getName());
//		lion.setName("annWithTranException: 雄狮");
		lionRepository.save(lion);
	}
}
