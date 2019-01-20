package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author smxknife
 * 2019-01-16
 */
@WebServlet(name = "single", loadOnStartup = 0, urlPatterns = "/single", initParams = {
		@WebInitParam(name = "msg", value = " 0 servlet")
})
public class MySingleThreadModelServlet extends AbsMyServlet implements SingleThreadModel {
}
