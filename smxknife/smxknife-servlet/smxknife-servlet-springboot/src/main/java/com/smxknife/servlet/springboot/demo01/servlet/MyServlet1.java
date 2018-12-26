package com.smxknife.servlet.springboot.demo01.servlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author smxknife
 * 2018-12-26
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/ser1")
public class MyServlet1 extends AbsMyServlet {

}
