package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author smxknife
 * 2018-12-26
 */
@WebServlet(loadOnStartup = 2, urlPatterns = "/ser2")
public class MyServlet2 extends AbsMyServlet {

}
