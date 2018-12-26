package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author smxknife
 * 2018-12-26
 */
@WebServlet(name = "ser0", loadOnStartup = 0, urlPatterns = "/ser0", initParams = {
		@WebInitParam(name = "msg", value = " 0 servlet")
})
public class MyServlet0 extends AbsMyServlet {

}
