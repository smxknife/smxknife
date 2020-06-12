package com.smxknife.servlet.springboot.demo05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author smxknife
 * 2020/2/14
 */
@RestController
public class RegEquipmentRemoteController {

	@Autowired
	private RegEquipmentService regEquipmentService;

	@Autowired
	private PermissionProperties properties;

	@RequestMapping("/internel/sync/equip")
	public String syncEquip(Long id, short isCheck, String key) {

		if (null == key || !properties.getKey().equals(key)) {
			return "param error";
		}

		Optional<RegEquipment> optional = regEquipmentService.get(id);
		if (!optional.isPresent()) {
			return "kernel regEquipment is not exist";
		}
		RegEquipment equipment = optional.get();

		if (isCheck != 1) {
			equipment.setIsCheck((short) 0);
		} else {
			equipment.setIsCheck((short) 1);
		}

		regEquipmentService.saveOrUpdate(equipment);
		return "kernel sync equip success";
	}
}
