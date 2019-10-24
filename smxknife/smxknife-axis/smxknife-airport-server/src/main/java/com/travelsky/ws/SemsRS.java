/**
 * SemsRS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class SemsRS  implements java.io.Serializable {
    private Meta META;

    private com.travelsky.ws.ResponseMsg RESPONSE_MSG;

    private Sems SEMS;

    public SemsRS() {
    }

    public SemsRS(
           Meta META,
           com.travelsky.ws.ResponseMsg RESPONSE_MSG,
           Sems SEMS) {
           this.META = META;
           this.RESPONSE_MSG = RESPONSE_MSG;
           this.SEMS = SEMS;
    }


    /**
     * Gets the META value for this SemsRS.
     * 
     * @return META
     */
    public Meta getMETA() {
        return META;
    }


    /**
     * Sets the META value for this SemsRS.
     * 
     * @param META
     */
    public void setMETA(Meta META) {
        this.META = META;
    }


    /**
     * Gets the RESPONSE_MSG value for this SemsRS.
     * 
     * @return RESPONSE_MSG
     */
    public com.travelsky.ws.ResponseMsg getRESPONSE_MSG() {
        return RESPONSE_MSG;
    }


    /**
     * Sets the RESPONSE_MSG value for this SemsRS.
     * 
     * @param RESPONSE_MSG
     */
    public void setRESPONSE_MSG(com.travelsky.ws.ResponseMsg RESPONSE_MSG) {
        this.RESPONSE_MSG = RESPONSE_MSG;
    }


    /**
     * Gets the SEMS value for this SemsRS.
     * 
     * @return SEMS
     */
    public Sems getSEMS() {
        return SEMS;
    }


    /**
     * Sets the SEMS value for this SemsRS.
     * 
     * @param SEMS
     */
    public void setSEMS(Sems SEMS) {
        this.SEMS = SEMS;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SemsRS)) return false;
        SemsRS other = (SemsRS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.META==null && other.getMETA()==null) || 
             (this.META!=null &&
              this.META.equals(other.getMETA()))) &&
            ((this.RESPONSE_MSG==null && other.getRESPONSE_MSG()==null) || 
             (this.RESPONSE_MSG!=null &&
              this.RESPONSE_MSG.equals(other.getRESPONSE_MSG()))) &&
            ((this.SEMS==null && other.getSEMS()==null) || 
             (this.SEMS!=null &&
              this.SEMS.equals(other.getSEMS())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getMETA() != null) {
            _hashCode += getMETA().hashCode();
        }
        if (getRESPONSE_MSG() != null) {
            _hashCode += getRESPONSE_MSG().hashCode();
        }
        if (getSEMS() != null) {
            _hashCode += getSEMS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SemsRS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "semsRS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("META");
        elemField.setXmlName(new javax.xml.namespace.QName("", "META"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "meta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPONSE_MSG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RESPONSE_MSG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "responseMsg"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "sems"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
