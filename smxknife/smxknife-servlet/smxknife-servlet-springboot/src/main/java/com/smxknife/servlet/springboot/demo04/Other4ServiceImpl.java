package com.smxknife.servlet.springboot.demo04;

import org.springframework.stereotype.Service;

/**
 * @author smxknife
 * 2020/2/10
 */
@Service
public class Other4ServiceImpl implements Other4Service {

	@Override
	public String other() {
		return "other aaa";
	}
}
