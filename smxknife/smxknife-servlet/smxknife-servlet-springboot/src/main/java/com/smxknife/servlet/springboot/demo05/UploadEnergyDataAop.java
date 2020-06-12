package com.smxknife.servlet.springboot.demo05;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author smxknife
 * 2020/2/15
 */
@Aspect
public class UploadEnergyDataAop {

	@Autowired
	RegEquipmentService regEquipmentService;

	@Around("execution(public * com.smxknife.servlet.springboot.demo05.UploadEnergyDataController.uploadEnergyData(..))")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();

		if (null == args || args.length == 0) {
			return;
		}

		if (!(args[0] instanceof EnergyData)) {
			return;
		}

		EnergyData energyData = (EnergyData) args[0];
		String deviceId = energyData.getDeviceId();
		RegEquipment equipment = regEquipmentService.getRegEquipmentByGuid(deviceId);
		if (null == equipment || equipment.getIsCheck() == null || equipment.getIsCheck() != (short) 1) {
			return;
		}
		joinPoint.proceed();
	}
}
