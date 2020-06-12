package com.smxknife.servlet.springboot.demo05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2020/2/14
 */
public class RegisterEdtPermissionService implements EdtPermissionService {

	private Logger logger = LoggerFactory.getLogger(RegisterEdtPermissionService.class);

	@Autowired
	private PermissionProperties properties;

	@Override
	public void handle(Object returnVal, Object... params) {
		String kernelUrl = properties.getKernelUrl();
		if (StringUtils.isEmpty(kernelUrl)) {
			logger.warn("permission.kernelUrl properties is blank");
			return;
		}

		Object id = null;
		Object isCheck = null;
		Object key = null;
		if (null == params || params.length < 3 || null == params[0] || null == params[1]) {
			logger.error("id or isCheck or key is null");
			return;
		}

		id = params[0];
		isCheck = params[1];
		key = params[2];

		Long dbId = Long.valueOf(id.toString());

		RestTemplate rest = new RestTemplate();

		MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		map.add("id", id);
		map.add("isCheck", isCheck);
		map.add("key", key);

		String postForObject = rest.postForObject(kernelUrl, map, String.class);
		if (logger.isInfoEnabled()) {
			logger.info("kernel return {}", postForObject);
		}

	}
}
