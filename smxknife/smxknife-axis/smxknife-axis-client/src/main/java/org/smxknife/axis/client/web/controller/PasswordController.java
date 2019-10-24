package org.smxknife.axis.client.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author smxknife
 * 2019/10/18
 */
@RestController
@RequestMapping("/pwd")
@Slf4j
public class PasswordController {

	@RequestMapping
	public String pwd() {
		try {
			String namespace = "http://aaaa:12345/server/services/UserService";
			String endpoint = "http://localhost:12345/server/services/PasswordService?wsdl";
			String method = "password";

			Call call = (Call) new Service().createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setEncodingStyle("utf-8");
			call.setOperationName(new QName(endpoint, method));
			call.addParameter(new QName(endpoint, method), XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			call.setTimeout(60000);
			Object invoke = call.invoke(new Object[]{"xxxx"});

			log.info("receive message = {}", invoke.toString());
			return invoke.toString();
		} catch (ServiceException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (RemoteException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
