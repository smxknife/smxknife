package com.smxknife.servlet.annotation.response.setcookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author smxknife
 * 2020/1/10
 */
@WebServlet(name = "setCookie", urlPatterns = "/cookie/*")
public class SetCookieServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) {
		resp.setHeader("Set-Cookie", "name=sy; pat=122");  // 注意这种方式是错误的，只有name=sy会被写入，后面会忽略
		resp.setHeader("Set-Cookie", "other=111 tow=222"); // 这样可以将other写入，但是对应的值是111 tow=222
		System.out.println();                                     // Padding: 所以，如果要写入多个cookie，需要resp.setHeader("Set-Cookie", "")多次
		// 上面这种方式虽然可行，但是Servlet不推荐，推荐使用以下方式
		Cookie cookie1 = new Cookie("hello", "world");
		resp.addCookie(cookie1);
		Cookie cookie2 = new Cookie("nihao", "test");
		resp.addCookie(cookie2);
	}
}
