/**
 * IGaugeServiceSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class IGaugeServiceSoapBindingSkeleton implements IGauge, org.apache.axis.wsdl.Skeleton {
    private IGauge impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("heartBeat", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://ws.travelsky.com/", "HeartBeat"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("heartBeat") == null) {
            _myOperations.put("heartBeat", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("heartBeat")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.travelsky.com/", "semsRQ"), SemsRQ.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("getGaugeData", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "semsRS"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://ws.travelsky.com/", "getGaugeData"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getGaugeData") == null) {
            _myOperations.put("getGaugeData", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getGaugeData")).add(_oper);
    }

    public IGaugeServiceSoapBindingSkeleton() {
        this.impl = new IGaugeServiceSoapBindingImpl();
    }

    public IGaugeServiceSoapBindingSkeleton(IGauge impl) {
        this.impl = impl;
    }
    public String heartBeat(String arg0) throws java.rmi.RemoteException
    {
        String ret = impl.heartBeat(arg0);
        return ret;
    }

    public SemsRS getGaugeData(SemsRQ arg0) throws java.rmi.RemoteException
    {
        SemsRS ret = impl.getGaugeData(arg0);
        return ret;
    }

}
