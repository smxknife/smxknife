/**
 * Iservice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class Iservice  implements java.io.Serializable {
    private String AREA_NAME;

    private String AREA_NUM;

    private String END_TIME;

    private String LINE_LENGTH;

    private String SECONDS_SERVICE;

    private String START_TIME;

    private String TERMINAL_SUM;

    public Iservice() {
    }

    public Iservice(
           String AREA_NAME,
           String AREA_NUM,
           String END_TIME,
           String LINE_LENGTH,
           String SECONDS_SERVICE,
           String START_TIME,
           String TERMINAL_SUM) {
           this.AREA_NAME = AREA_NAME;
           this.AREA_NUM = AREA_NUM;
           this.END_TIME = END_TIME;
           this.LINE_LENGTH = LINE_LENGTH;
           this.SECONDS_SERVICE = SECONDS_SERVICE;
           this.START_TIME = START_TIME;
           this.TERMINAL_SUM = TERMINAL_SUM;
    }


    /**
     * Gets the AREA_NAME value for this Iservice.
     * 
     * @return AREA_NAME
     */
    public String getAREA_NAME() {
        return AREA_NAME;
    }


    /**
     * Sets the AREA_NAME value for this Iservice.
     * 
     * @param AREA_NAME
     */
    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME;
    }


    /**
     * Gets the AREA_NUM value for this Iservice.
     * 
     * @return AREA_NUM
     */
    public String getAREA_NUM() {
        return AREA_NUM;
    }


    /**
     * Sets the AREA_NUM value for this Iservice.
     * 
     * @param AREA_NUM
     */
    public void setAREA_NUM(String AREA_NUM) {
        this.AREA_NUM = AREA_NUM;
    }


    /**
     * Gets the END_TIME value for this Iservice.
     * 
     * @return END_TIME
     */
    public String getEND_TIME() {
        return END_TIME;
    }


    /**
     * Sets the END_TIME value for this Iservice.
     * 
     * @param END_TIME
     */
    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }


    /**
     * Gets the LINE_LENGTH value for this Iservice.
     * 
     * @return LINE_LENGTH
     */
    public String getLINE_LENGTH() {
        return LINE_LENGTH;
    }


    /**
     * Sets the LINE_LENGTH value for this Iservice.
     * 
     * @param LINE_LENGTH
     */
    public void setLINE_LENGTH(String LINE_LENGTH) {
        this.LINE_LENGTH = LINE_LENGTH;
    }


    /**
     * Gets the SECONDS_SERVICE value for this Iservice.
     * 
     * @return SECONDS_SERVICE
     */
    public String getSECONDS_SERVICE() {
        return SECONDS_SERVICE;
    }


    /**
     * Sets the SECONDS_SERVICE value for this Iservice.
     * 
     * @param SECONDS_SERVICE
     */
    public void setSECONDS_SERVICE(String SECONDS_SERVICE) {
        this.SECONDS_SERVICE = SECONDS_SERVICE;
    }


    /**
     * Gets the START_TIME value for this Iservice.
     * 
     * @return START_TIME
     */
    public String getSTART_TIME() {
        return START_TIME;
    }


    /**
     * Sets the START_TIME value for this Iservice.
     * 
     * @param START_TIME
     */
    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME;
    }


    /**
     * Gets the TERMINAL_SUM value for this Iservice.
     * 
     * @return TERMINAL_SUM
     */
    public String getTERMINAL_SUM() {
        return TERMINAL_SUM;
    }


    /**
     * Sets the TERMINAL_SUM value for this Iservice.
     * 
     * @param TERMINAL_SUM
     */
    public void setTERMINAL_SUM(String TERMINAL_SUM) {
        this.TERMINAL_SUM = TERMINAL_SUM;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Iservice)) return false;
        Iservice other = (Iservice) obj;
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
            ((this.LINE_LENGTH==null && other.getLINE_LENGTH()==null) || 
             (this.LINE_LENGTH!=null &&
              this.LINE_LENGTH.equals(other.getLINE_LENGTH()))) &&
            ((this.SECONDS_SERVICE==null && other.getSECONDS_SERVICE()==null) || 
             (this.SECONDS_SERVICE!=null &&
              this.SECONDS_SERVICE.equals(other.getSECONDS_SERVICE()))) &&
            ((this.START_TIME==null && other.getSTART_TIME()==null) || 
             (this.START_TIME!=null &&
              this.START_TIME.equals(other.getSTART_TIME()))) &&
            ((this.TERMINAL_SUM==null && other.getTERMINAL_SUM()==null) || 
             (this.TERMINAL_SUM!=null &&
              this.TERMINAL_SUM.equals(other.getTERMINAL_SUM())));
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
        if (getLINE_LENGTH() != null) {
            _hashCode += getLINE_LENGTH().hashCode();
        }
        if (getSECONDS_SERVICE() != null) {
            _hashCode += getSECONDS_SERVICE().hashCode();
        }
        if (getSTART_TIME() != null) {
            _hashCode += getSTART_TIME().hashCode();
        }
        if (getTERMINAL_SUM() != null) {
            _hashCode += getTERMINAL_SUM().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Iservice.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iservice"));
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
        elemField.setFieldName("LINE_LENGTH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LINE_LENGTH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SECONDS_SERVICE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SECONDS_SERVICE"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TERMINAL_SUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TERMINAL_SUM"));
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
