package org.smxknife.airport.client.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.Handler;
import org.apache.axis.SimpleChain;
import org.apache.axis.SimpleTargetedChain;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.transport.http.HTTPSender;
import org.apache.axis.transport.http.HTTPTransport;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.smxknife.airport.client.handler.AxisClientEnvelopeHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

import static org.apache.axis.Constants.URI_DEFAULT_SCHEMA_XSD;

/**
 * @author smxknife
 * 2019/10/18
 */
@RestController
@RequestMapping("/gauge")
@Slf4j
public class GaugeController {

	@RequestMapping
	public String gauge() {
		String namespace = "http://ws.travelsky.com/";
		String endpoint = "http://localhost:7800/services/IGaugePort";
		String method = "getGaugeData";

		try {

			Service service = new Service();
			EngineConfiguration config = service.getEngine().getConfig();

			SimpleProvider clientConfig = new SimpleProvider(config);
			AxisClientEnvelopeHandler envelopeHandler = new AxisClientEnvelopeHandler(namespace);
			SimpleChain reqHandler = new SimpleChain();
			SimpleChain respHandler = new SimpleChain();
			reqHandler.addHandler(envelopeHandler);
			Handler pivot = new HTTPSender();
			Handler transport = new SimpleTargetedChain(reqHandler, pivot, respHandler);
			clientConfig.deployTransport(HTTPTransport.DEFAULT_TRANSPORT_NAME, transport);

			service.setEngineConfiguration(clientConfig);

			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setEncodingStyle("utf-8");
			call.setOperationName(new QName(endpoint, method));
//			call.addParameter(new QName(endpoint, "arg0"), XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName(endpoint, "arg0"), new QName(URI_DEFAULT_SCHEMA_XSD, "string", "~CDATA"), ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			call.setTimeout(60000);

//			call.getMessageContext().getAxisEngine().init();
//			SOAPMessage message = call.getMessageContext().getMessage();

//			CDATASection cdataSection = new CDATAImpl(buildXml());
//			RPCParam param = new RPCParam("arg0", cdataSection);
//			RPCElement element = new RPCElement(namespace, method, new Object[]{param});
//			TypeMapping typeMapping = call.getTypeMapping();
//			typeMapping.register(CDATAImpl.class, XMLType.SOAP_DOCUMENT,
//					new CDATASerializerFactory(XMLType.SOAP_DOCUMENT, CDATAImpl.class),
//					new CDATADeserializerFactory(XMLType.SOAP_DOCUMENT, CDATAImpl.class));

//			element.getOwnerDocument().createCDATASection(buildXml());
//			element.getDeserializationContext().startCDATA();
//			element.getDeserializationContext().endCDATA();
//			element.addParam(new RPCParam(XMLType.XSD_STRING, buildXml()));

//			CDATASection cdataSection = XMLUtils.newDocument().createCDATASection(buildXml());
//			Object invoke = call.invoke(new Object[] {cdataSection});

			Object invoke = call.invoke(new Object[] {buildXml()});
//			Object invoke = call.invoke(element);

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

	private String buildXml() {
		Element sems_rq = DocumentHelper.createElement("SEMS_RQ");
		sems_rq.add(DocumentHelper.createElement("SEMS_TYPE").addText("DICTIONARY"));
		sems_rq.add(DocumentHelper.createElement("SNDR").addText("POMS"));
		sems_rq.add(DocumentHelper.createElement("SEQN").addText("123"));
		sems_rq.add(DocumentHelper.createElement("SEMS_CONDITION"));
		return sems_rq.asXML();
	}
}
