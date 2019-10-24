package org.smxknife.airport.client.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * @author smxknife
 * 2019/10/21
 */
@Slf4j
@AllArgsConstructor
public class AxisClientEnvelopeHandler extends BasicHandler {

	private String namespace;

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		try {
//			messageContext.getMessage().getSOAPPart().createCDATASection()
			// get the soap header
			SOAPMessageContext smc = (SOAPMessageContext) messageContext;
			SOAPMessage message = smc.getMessage();
			SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();

			// fiddle with the namespaces
			envelope.addNamespaceDeclaration("ws", this.namespace);
		} catch (SOAPException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

	}
}
