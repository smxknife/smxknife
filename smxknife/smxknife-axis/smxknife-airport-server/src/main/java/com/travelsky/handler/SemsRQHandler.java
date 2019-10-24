package com.travelsky.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

import javax.xml.soap.SOAPException;

/**
 * @author smxknife
 * 2019/10/18
 */
@Slf4j
public class SemsRQHandler extends BasicHandler {
	@Override
	public void invoke(MessageContext mc) throws AxisFault {
		log.info("password is {}", mc.getPassword());
		log.info("message content description is {}", mc.getMessage().getContentDescription());
		log.info("message toString is {}", mc.getMessage().toString());
		log.info("message content description is {}", mc.getMessage().getContentDescription());
		try {
			log.info("request m {}", mc.getRequestMessage().getSOAPPartAsString());
			log.info("message soabody is {}", mc.getMessage().getSOAPBody().toString());
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		log.info("operation is {}", mc.getOperation());

		mc.getAllPropertyNames().forEachRemaining(prop -> {
			log.info("propperty name = {}", prop.toString());
		});

//		throw new AxisFault("line number1");

	}
}
