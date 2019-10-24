/**
 * IGauge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public interface IGauge extends java.rmi.Remote {
    public String heartBeat(String arg0) throws java.rmi.RemoteException;
    public com.travelsky.ws.SemsRS getGaugeData(com.travelsky.ws.SemsRQ arg0) throws java.rmi.RemoteException;
}
