/**
 * Iwat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class Iwat  implements java.io.Serializable {
    private String AREA_NAME;

    private String AREA_NUM;

    private String END_TIME;

    private String IWAIT_TIME;

    private String START_TIME;

    public Iwat() {
    }

    public Iwat(
           String AREA_NAME,
           String AREA_NUM,
           String END_TIME,
           String IWAIT_TIME,
           String START_TIME) {
           this.AREA_NAME = AREA_NAME;
           this.AREA_NUM = AREA_NUM;
           this.END_TIME = END_TIME;
           this.IWAIT_TIME = IWAIT_TIME;
           this.START_TIME = START_TIME;
    }


    /**
     * Gets the AREA_NAME value for this Iwat.
     * 
     * @return AREA_NAME
     */
    public String getAREA_NAME() {
        return AREA_NAME;
    }


    /**
     * Sets the AREA_NAME value for this Iwat.
     * 
     * @param AREA_NAME
     */
    public void setAREA_NAME(String AREA_NAME) {
        this.AREA_NAME = AREA_NAME;
    }


    /**
     * Gets the AREA_NUM value for this Iwat.
     * 
     * @return AREA_NUM
     */
    public String getAREA_NUM() {
        return AREA_NUM;
    }


    /**
     * Sets the AREA_NUM value for this Iwat.
     * 
     * @param AREA_NUM
     */
    public void setAREA_NUM(String AREA_NUM) {
        this.AREA_NUM = AREA_NUM;
    }


    /**
     * Gets the END_TIME value for this Iwat.
     * 
     * @return END_TIME
     */
    public String getEND_TIME() {
        return END_TIME;
    }


    /**
     * Sets the END_TIME value for this Iwat.
     * 
     * @param END_TIME
     */
    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }


    /**
     * Gets the IWAIT_TIME value for this Iwat.
     * 
     * @return IWAIT_TIME
     */
    public String getIWAIT_TIME() {
        return IWAIT_TIME;
    }


    /**
     * Sets the IWAIT_TIME value for this Iwat.
     * 
     * @param IWAIT_TIME
     */
    public void setIWAIT_TIME(String IWAIT_TIME) {
        this.IWAIT_TIME = IWAIT_TIME;
    }


    /**
     * Gets the START_TIME value for this Iwat.
     * 
     * @return START_TIME
     */
    public String getSTART_TIME() {
        return START_TIME;
    }


    /**
     * Sets the START_TIME value for this Iwat.
     * 
     * @param START_TIME
     */
    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Iwat)) return false;
        Iwat other = (Iwat) obj;
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
            ((this.IWAIT_TIME==null && other.getIWAIT_TIME()==null) || 
             (this.IWAIT_TIME!=null &&
              this.IWAIT_TIME.equals(other.getIWAIT_TIME()))) &&
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
        if (getIWAIT_TIME() != null) {
            _hashCode += getIWAIT_TIME().hashCode();
        }
        if (getSTART_TIME() != null) {
            _hashCode += getSTART_TIME().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Iwat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iwat"));
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
        elemField.setFieldName("IWAIT_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IWAIT_TIME"));
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
