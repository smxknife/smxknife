/**
 * Iinout.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class Iinout  implements java.io.Serializable {
    private String AREA_NAME;

    private String AREA_NUM;

    private String END_TIME;

    private String IENTERS_NUM;

    private String IEXITS_NUM;

    private String START_TIME;

    public Iinout() {
    }

    public Iinout(
           String AREA_NAME,
           String AREA_NUM,
           String END_TIME,
           String IENTERS_NUM,
           String IEXITS_NUM,
           String START_TIME) {
           this.AREA_NAME = AREA_NAME;
           this.AREA_NUM = AREA_NUM;
           this.END_TIME = END_TIME;
           this.IENTERS_NUM = IENTERS_NUM;
           this.IEXITS_NUM = IEXITS_NUM;
           this.START_TIME = START_TIME;
    }


    /**
     * Gets the AREA_NAME value for this Iinout.
     * 
     * @return AREA_NAME
     */
    public String getAREA_NAME() {
        return AREA_NAME;
    }


    /**
     * Sets the AREA_NAME value for this Iinout.
     * 
     * @param AREA_NAME
     */
    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME;
    }


    /**
     * Gets the AREA_NUM value for this Iinout.
     * 
     * @return AREA_NUM
     */
    public String getAREA_NUM() {
        return AREA_NUM;
    }


    /**
     * Sets the AREA_NUM value for this Iinout.
     * 
     * @param AREA_NUM
     */
    public void setAREA_NUM(String AREA_NUM) {
        this.AREA_NUM = AREA_NUM;
    }


    /**
     * Gets the END_TIME value for this Iinout.
     * 
     * @return END_TIME
     */
    public String getEND_TIME() {
        return END_TIME;
    }


    /**
     * Sets the END_TIME value for this Iinout.
     * 
     * @param END_TIME
     */
    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }


    /**
     * Gets the IENTERS_NUM value for this Iinout.
     * 
     * @return IENTERS_NUM
     */
    public String getIENTERS_NUM() {
        return IENTERS_NUM;
    }


    /**
     * Sets the IENTERS_NUM value for this Iinout.
     * 
     * @param IENTERS_NUM
     */
    public void setIENTERS_NUM(String IENTERS_NUM) {
        this.IENTERS_NUM = IENTERS_NUM;
    }


    /**
     * Gets the IEXITS_NUM value for this Iinout.
     * 
     * @return IEXITS_NUM
     */
    public String getIEXITS_NUM() {
        return IEXITS_NUM;
    }


    /**
     * Sets the IEXITS_NUM value for this Iinout.
     * 
     * @param IEXITS_NUM
     */
    public void setIEXITS_NUM(String IEXITS_NUM) {
        this.IEXITS_NUM = IEXITS_NUM;
    }


    /**
     * Gets the START_TIME value for this Iinout.
     * 
     * @return START_TIME
     */
    public String getSTART_TIME() {
        return START_TIME;
    }


    /**
     * Sets the START_TIME value for this Iinout.
     * 
     * @param START_TIME
     */
    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Iinout)) return false;
        Iinout other = (Iinout) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AREA_NAME==null && other.getAREA_NAME()==null) || 
             (this.AREA_NAME!=null &&
              this.AREA_NAME.equals(other.getAREA_NAME()))) &&
            ((this.AREA_NUM==null && other.getAREA_NUM()==null) || 
             (this.AREA_NUM!=null &&
              this.AREA_NUM.equals(other.getAREA_NUM()))) &&
            ((this.END_TIME==null && other.getEND_TIME()==null) || 
             (this.END_TIME!=null &&
              this.END_TIME.equals(other.getEND_TIME()))) &&
            ((this.IENTERS_NUM==null && other.getIENTERS_NUM()==null) || 
             (this.IENTERS_NUM!=null &&
              this.IENTERS_NUM.equals(other.getIENTERS_NUM()))) &&
            ((this.IEXITS_NUM==null && other.getIEXITS_NUM()==null) || 
             (this.IEXITS_NUM!=null &&
              this.IEXITS_NUM.equals(other.getIEXITS_NUM()))) &&
            ((this.START_TIME==null && other.getSTART_TIME()==null) || 
             (this.START_TIME!=null &&
              this.START_TIME.equals(other.getSTART_TIME())));
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
        if (getAREA_NAME() != null) {
            _hashCode += getAREA_NAME().hashCode();
        }
        if (getAREA_NUM() != null) {
            _hashCode += getAREA_NUM().hashCode();
        }
        if (getEND_TIME() != null) {
            _hashCode += getEND_TIME().hashCode();
        }
        if (getIENTERS_NUM() != null) {
            _hashCode += getIENTERS_NUM().hashCode();
        }
        if (getIEXITS_NUM() != null) {
            _hashCode += getIEXITS_NUM().hashCode();
        }
        if (getSTART_TIME() != null) {
            _hashCode += getSTART_TIME().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Iinout.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iinout"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AREA_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AREA_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AREA_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AREA_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("END_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "END_TIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IENTERS_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IENTERS_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IEXITS_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IEXITS_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("START_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "START_TIME"));
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
