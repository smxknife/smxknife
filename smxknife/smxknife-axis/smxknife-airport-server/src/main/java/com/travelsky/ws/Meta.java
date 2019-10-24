/**
 * Meta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class Meta  implements java.io.Serializable {
    private String DTTM;

    private String SEQN;

    private String SNDR;

    private String TYPE;

    public Meta() {
    }

    public Meta(
           String DTTM,
           String SEQN,
           String SNDR,
           String TYPE) {
           this.DTTM = DTTM;
           this.SEQN = SEQN;
           this.SNDR = SNDR;
           this.TYPE = TYPE;
    }


    /**
     * Gets the DTTM value for this Meta.
     * 
     * @return DTTM
     */
    public String getDTTM() {
        return DTTM;
    }


    /**
     * Sets the DTTM value for this Meta.
     * 
     * @param DTTM
     */
    public void setDTTM(String DTTM) {
        this.DTTM = DTTM;
    }


    /**
     * Gets the SEQN value for this Meta.
     * 
     * @return SEQN
     */
    public String getSEQN() {
        return SEQN;
    }


    /**
     * Sets the SEQN value for this Meta.
     * 
     * @param SEQN
     */
    public void setSEQN(String SEQN) {
        this.SEQN = SEQN;
    }


    /**
     * Gets the SNDR value for this Meta.
     * 
     * @return SNDR
     */
    public String getSNDR() {
        return SNDR;
    }


    /**
     * Sets the SNDR value for this Meta.
     * 
     * @param SNDR
     */
    public void setSNDR(String SNDR) {
        this.SNDR = SNDR;
    }


    /**
     * Gets the TYPE value for this Meta.
     * 
     * @return TYPE
     */
    public String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this Meta.
     * 
     * @param TYPE
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Meta)) return false;
        Meta other = (Meta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DTTM==null && other.getDTTM()==null) || 
             (this.DTTM!=null &&
              this.DTTM.equals(other.getDTTM()))) &&
            ((this.SEQN==null && other.getSEQN()==null) || 
             (this.SEQN!=null &&
              this.SEQN.equals(other.getSEQN()))) &&
            ((this.SNDR==null && other.getSNDR()==null) || 
             (this.SNDR!=null &&
              this.SNDR.equals(other.getSNDR()))) &&
            ((this.TYPE==null && other.getTYPE()==null) || 
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE())));
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
        if (getDTTM() != null) {
            _hashCode += getDTTM().hashCode();
        }
        if (getSEQN() != null) {
            _hashCode += getSEQN().hashCode();
        }
        if (getSNDR() != null) {
            _hashCode += getSNDR().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Meta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "meta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DTTM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DTTM"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TYPE"));
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
