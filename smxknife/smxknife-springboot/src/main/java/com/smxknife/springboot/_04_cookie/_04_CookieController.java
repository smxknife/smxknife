package com.smxknife.springboot._04_cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2021/2/22
 */
@RestController
@RequestMapping("/cookie")
public class _04_CookieController {

	@Value("${in:/**}")
	private String[] path;

	@Value("${exclude:}")
	private String[] exclude;

	@RequestMapping("/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", "aaaaa");
		cookie.setPath("/");
		response.addCookie(cookie);
		HttpSession session = request.getSession(true);
	}

	@RequestMapping("/get")
	public List<Cookie> get(HttpServletRequest request, HttpServletResponse response) {
		return Arrays.asList(request.getCookies());
	}

}
