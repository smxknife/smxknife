/**
 * SemsRQ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class SemsRQ  implements java.io.Serializable {
    private com.travelsky.ws.SemsCondition SEMS_CONDITION;

    private String SEMS_TYPE;

    private String SEQN;

    private String SNDR;

    public SemsRQ() {
    }

    public SemsRQ(
           com.travelsky.ws.SemsCondition SEMS_CONDITION,
           String SEMS_TYPE,
           String SEQN,
           String SNDR) {
           this.SEMS_CONDITION = SEMS_CONDITION;
           this.SEMS_TYPE = SEMS_TYPE;
           this.SEQN = SEQN;
           this.SNDR = SNDR;
    }


    /**
     * Gets the SEMS_CONDITION value for this SemsRQ.
     * 
     * @return SEMS_CONDITION
     */
    public com.travelsky.ws.SemsCondition getSEMS_CONDITION() {
        return SEMS_CONDITION;
    }


    /**
     * Sets the SEMS_CONDITION value for this SemsRQ.
     * 
     * @param SEMS_CONDITION
     */
    public void setSEMS_CONDITION(com.travelsky.ws.SemsCondition SEMS_CONDITION) {
        this.SEMS_CONDITION = SEMS_CONDITION;
    }


    /**
     * Gets the SEMS_TYPE value for this SemsRQ.
     * 
     * @return SEMS_TYPE
     */
    public String getSEMS_TYPE() {
        return SEMS_TYPE;
    }


    /**
     * Sets the SEMS_TYPE value for this SemsRQ.
     * 
     * @param SEMS_TYPE
     */
    public void setSEMS_TYPE(String SEMS_TYPE) {
        this.SEMS_TYPE = SEMS_TYPE;
    }


    /**
     * Gets the SEQN value for this SemsRQ.
     * 
     * @return SEQN
     */
    public String getSEQN() {
        return SEQN;
    }


    /**
     * Sets the SEQN value for this SemsRQ.
     * 
     * @param SEQN
     */
    public void setSEQN(String SEQN) {
        this.SEQN = SEQN;
    }


    /**
     * Gets the SNDR value for this SemsRQ.
     * 
     * @return SNDR
     */
    public String getSNDR() {
        return SNDR;
    }


    /**
     * Sets the SNDR value for this SemsRQ.
     * 
     * @param SNDR
     */
    public void setSNDR(String SNDR) {
        this.SNDR = SNDR;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SemsRQ)) return false;
        SemsRQ other = (SemsRQ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SEMS_CONDITION==null && other.getSEMS_CONDITION()==null) || 
             (this.SEMS_CONDITION!=null &&
              this.SEMS_CONDITION.equals(other.getSEMS_CONDITION()))) &&
            ((this.SEMS_TYPE==null && other.getSEMS_TYPE()==null) || 
             (this.SEMS_TYPE!=null &&
              this.SEMS_TYPE.equals(other.getSEMS_TYPE()))) &&
            ((this.SEQN==null && other.getSEQN()==null) || 
             (this.SEQN!=null &&
              this.SEQN.equals(other.getSEQN()))) &&
            ((this.SNDR==null && other.getSNDR()==null) || 
             (this.SNDR!=null &&
              this.SNDR.equals(other.getSNDR())));
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
        if (getSEMS_CONDITION() != null) {
            _hashCode += getSEMS_CONDITION().hashCode();
        }
        if (getSEMS_TYPE() != null) {
            _hashCode += getSEMS_TYPE().hashCode();
        }
        if (getSEQN() != null) {
            _hashCode += getSEQN().hashCode();
        }
        if (getSNDR() != null) {
            _hashCode += getSNDR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SemsRQ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "semsRQ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEMS_CONDITION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEMS_CONDITION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "semsCondition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEMS_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEMS_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEQN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SEQN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SNDR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SNDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
