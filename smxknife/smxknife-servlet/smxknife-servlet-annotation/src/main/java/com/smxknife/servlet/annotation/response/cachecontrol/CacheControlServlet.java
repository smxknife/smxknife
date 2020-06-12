package com.smxknife.servlet.annotation.response.cachecontrol;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author smxknife
 * 2019/12/31
 */
@WebServlet(name = "cacheControlServlet", urlPatterns = "/cacheControl/*")
public class CacheControlServlet extends HttpServlet {

	private static int flag = 0;
	private static ResourceBundle lStrings = ResourceBundle.getBundle("javax.servlet.http.LocalStrings");

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.service(req, resp);
		String method = req.getMethod();
		long lastModified;
		if (method.equals("GET")) {
			lastModified = this.getLastModified(req);
			if (lastModified == -1L) {
				this.doGet(req, resp);
			} else {
				long ifModifiedSince = req.getDateHeader("If-Modified-Since");
				if (ifModifiedSince < lastModified) {
					this.maybeSetLastModified(resp, lastModified);
					this.doGet(req, resp);
				} else {
					resp.setStatus(304);
				}
			}
		} else if (method.equals("HEAD")) {
			lastModified = this.getLastModified(req);
			this.maybeSetLastModified(resp, lastModified);
			this.doHead(req, resp);
		} else if (method.equals("POST")) {
			this.doPost(req, resp);
		} else if (method.equals("PUT")) {
			this.doPut(req, resp);
		} else if (method.equals("DELETE")) {
			this.doDelete(req, resp);
		} else if (method.equals("OPTIONS")) {
			this.doOptions(req, resp);
		} else if (method.equals("TRACE")) {
			this.doTrace(req, resp);
		} else {
			String errMsg = lStrings.getString("http.method_not_implemented");
			Object[] errArgs = new Object[]{method};
			errMsg = MessageFormat.format(errMsg, errArgs);
			resp.sendError(501, errMsg);
		}
	}

	private void maybeSetLastModified(HttpServletResponse resp, long lastModified) {
		if (!resp.containsHeader("Last-Modified")) {
			if (lastModified >= 0L) {
				resp.setDateHeader("Last-Modified", lastModified);
			}

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---------------");
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setHeader("Cache-Control", "public, max-age=" + 3600);
//		resp.setDateHeader("Expires", System.currentTimeMillis() + 10000 * 3600);
		String requestURI = req.getRequestURI();
		if (requestURI.contains("flag")) {
			dispatchFlag(req, resp);
		} else {
			resp.getWriter().write("Flag = ");
		}
	}

	private void dispatchFlag(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().write("Flag = " + flag++);
	}
}
