package com.smxknife.servlet.springboot.demo05;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author smxknife
 * 2020/2/14
 */
@Service
public class RegEquipmentServiceImpl implements RegEquipmentService {
	@Override
	public boolean saveOrUpdate(Model model, HttpServletRequest request, int id, short isCheck) {
		return false;
	}

	@Override
	public void saveOrUpdate(RegEquipment entity) {

	}

	@Override
	public Optional<RegEquipment> get(Long id) {
		return Optional.of(new RegEquipment());
	}

	@Override
	public RegEquipment getRegEquipmentByGuid(String guid) {
		RegEquipment regEquipment = new RegEquipment();
		regEquipment.setIsCheck((short)1);
		return regEquipment;
	}
}
