/**
 * ResponseMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class ResponseMsg  implements java.io.Serializable {
    private String MESSAGE;

    private Integer STATUS_CODE;

    public ResponseMsg() {
    }

    public ResponseMsg(
           String MESSAGE,
           Integer STATUS_CODE) {
           this.MESSAGE = MESSAGE;
           this.STATUS_CODE = STATUS_CODE;
    }


    /**
     * Gets the MESSAGE value for this ResponseMsg.
     * 
     * @return MESSAGE
     */
    public String getMESSAGE() {
        return MESSAGE;
    }


    /**
     * Sets the MESSAGE value for this ResponseMsg.
     * 
     * @param MESSAGE
     */
    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }


    /**
     * Gets the STATUS_CODE value for this ResponseMsg.
     * 
     * @return STATUS_CODE
     */
    public Integer getSTATUS_CODE() {
        return STATUS_CODE;
    }


    /**
     * Sets the STATUS_CODE value for this ResponseMsg.
     * 
     * @param STATUS_CODE
     */
    public void setSTATUS_CODE(Integer STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof ResponseMsg)) return false;
        ResponseMsg other = (ResponseMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MESSAGE==null && other.getMESSAGE()==null) || 
             (this.MESSAGE!=null &&
              this.MESSAGE.equals(other.getMESSAGE()))) &&
            ((this.STATUS_CODE==null && other.getSTATUS_CODE()==null) || 
             (this.STATUS_CODE!=null &&
              this.STATUS_CODE.equals(other.getSTATUS_CODE())));
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
        if (getMESSAGE() != null) {
            _hashCode += getMESSAGE().hashCode();
        }
        if (getSTATUS_CODE() != null) {
            _hashCode += getSTATUS_CODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponseMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "responseMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STATUS_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STATUS_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
