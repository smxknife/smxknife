package org.smxknife.axis.server.endpoint;

import org.apache.axis.transport.http.AxisServlet;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;

/**
 * @author smxknife
 * 2019/10/18
 */
@Component
@WebServlet(urlPatterns = "/services/*", loadOnStartup = 1, name = "AxisServlet")
public class WsServlet extends AxisServlet {

}
