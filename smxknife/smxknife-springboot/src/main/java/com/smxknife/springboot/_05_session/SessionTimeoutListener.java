package com.smxknife.springboot._05_session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

/**
 * @author smxknife
 * 2021/3/9
 */
@WebListener
public class SessionTimeoutListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("session create");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		System.out.println("session destroy " + session.getMaxInactiveInterval());
		Object req = session.getAttribute("req");
		Object resp = session.getAttribute("resp");
		if (Objects.nonNull(req) && Objects.nonNull(resp)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) req;
			HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
			try {
				httpServletResponse.sendRedirect("/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
