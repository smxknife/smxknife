package com.smxknife.servlet.springboot.demo05;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author smxknife
 * 2020/2/13
 */
public interface RegEquipmentService {
	boolean saveOrUpdate(Model model, HttpServletRequest request, int id, short isCheck);

	void saveOrUpdate(RegEquipment entity);

	Optional<RegEquipment> get(Long id);

	RegEquipment getRegEquipmentByGuid(String guid);
}
