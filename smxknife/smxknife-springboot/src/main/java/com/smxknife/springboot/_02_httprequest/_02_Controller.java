package com.smxknife.springboot._02_httprequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2021/2/19
 */
@RestController
@RequestMapping("httprequest")
public class _02_Controller {

	@RequestMapping
	public Map<String, String> request(HttpServletRequest request) {
		Map<String, String> maps = new LinkedHashMap<>();
		maps.put("RequestURI", request.getRequestURI());
		maps.put("ContextPath", request.getContextPath());
		maps.put("PathInfo", request.getPathInfo());
		maps.put("ServletPath", request.getServletPath());
		maps.put("RemoteAddr", request.getRemoteAddr());
		maps.put("ServerPort", String.valueOf(request.getServerPort()));
		maps.put("RemotePort", String.valueOf(request.getRemotePort()));
		maps.put("RemoteHost", request.getRemoteHost());
		maps.put("LocalAddr", request.getLocalAddr());
		return maps;
	}

}
