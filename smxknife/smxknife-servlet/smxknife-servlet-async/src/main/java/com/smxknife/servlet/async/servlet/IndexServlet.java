package com.smxknife.servlet.async.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/9
 */
@WebServlet(name = "indexServlet", urlPatterns = "/*", asyncSupported = true)
public class IndexServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(config.getServletName() + " init...");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		try {
			PrintWriter writer = resp.getWriter();
			writer.println("servlet started.<br/>");
			writer.flush();

			AsyncContext asyncContext = req.startAsync();
			asyncContext.addListener(getListener());
			asyncContext.start(new IndexAsyncThread(asyncContext));

			writer.println("servlet end.<br/>");
			writer.flush();

		} catch (Exception e) {

		}
	}

	private AsyncListener getListener() {
		return new AsyncListener() {

			@Override
			public void onComplete(AsyncEvent asyncEvent) throws IOException {
				asyncEvent.getSuppliedResponse().getWriter().close();
				System.out.println("listen thread completed.");
			}

			@Override
			public void onTimeout(AsyncEvent asyncEvent) throws IOException {
				System.out.println("listen thread timeout");
			}

			@Override
			public void onError(AsyncEvent asyncEvent) throws IOException {
				System.out.println("listen thread error");
			}

			@Override
			public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
				System.out.println("listen thread start");
			}
		};
	}

	static class IndexAsyncThread extends Thread {
		private AsyncContext asyncContext;

		public IndexAsyncThread(AsyncContext asyncContext) {
			this.asyncContext = asyncContext;
		}

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(3);

				PrintWriter writer = asyncContext.getResponse().getWriter();
				writer.println("async handler...");
				writer.flush();
				asyncContext.complete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
