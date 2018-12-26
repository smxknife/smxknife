package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author smxknife
 * 2018-12-26
 */
@WebServlet(name = "def", value = "/def/*", initParams = {
		@WebInitParam(name = "msg", value = "default servlet")
})
public class MyServletDef extends AbsMyServlet {

}
