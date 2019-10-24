package org.smxknife.axis.client.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.SimpleChain;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.smxknife.axis.client.handler.AxisClientEnvelopeHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/user")
@Slf4j
public class UserController {

	@RequestMapping("info")
	public String info(@RequestParam("name") String name) throws ServiceException, RemoteException {

		String namespace = "http://aaaa:12345/server/services/UserService";
		String endpoint = "http://localhost:12345/server/services/UserService?wsdl";
		String method = "userInfo";

		Service service = new Service();
		EngineConfiguration config = service.getEngine().getConfig();

//		SimpleProvider clientConfig = new SimpleProvider(config);
		AxisClientEnvelopeHandler envelopeHandler = new AxisClientEnvelopeHandler(namespace);
		SimpleChain reqHandler = new SimpleChain();
		SimpleChain respHandler = new SimpleChain();
		reqHandler.addHandler(envelopeHandler);
//		Handler pivot = new HTTPSender();
//		Handler transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
//		clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);

//		service.setEngineConfiguration(clientConfig);

		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(endpoint);
//		call.setEncodingStyle("utf-8");

		call.setClientHandlers(reqHandler, respHandler);

		call.setOperationName(new QName(endpoint, method));
//		call.addParameter(new QName(endpoint, method), new QName(URI_DEFAULT_SCHEMA_XSD, "string", "~CDATA"), ParameterMode.IN);
		call.addParameter(new QName(endpoint, method), XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnType(XMLType.XSD_STRING);
//		call.setOption("CDATA", true);
		call.setTimeout(60000);

//		RPCParam param = new RPCParam(namespace, method, buildXml(name));
//		param.setAttribute("CDATA", "true");
//
//		RPCElement element = new RPCElement(namespace, method, new Object[] {param});
		Object invoke = call.invoke(new Object[]{buildXml(name)});

		log.info("receive message = {}", invoke.toString());

		return invoke.toString();
	}

	private String buildXml(String name) {
		Element sems_rq = DocumentHelper.createElement("SEMS_RQ");
		sems_rq.add(DocumentHelper.createElement("SEMS_TYPE").addText("DICTIONARY"));
		sems_rq.add(DocumentHelper.createElement("SNDR").addText("POMS"));
		sems_rq.add(DocumentHelper.createElement("SEQN").addText("123"));
		return sems_rq.asXML();
	}
}
