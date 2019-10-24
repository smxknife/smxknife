/**
 * SubArea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class SubArea  implements java.io.Serializable {
    private String SUB_DESC;

    private String SUB_NUM;

    private String SUB_TYPE;

    public SubArea() {
    }

    public SubArea(
           String SUB_DESC,
           String SUB_NUM,
           String SUB_TYPE) {
           this.SUB_DESC = SUB_DESC;
           this.SUB_NUM = SUB_NUM;
           this.SUB_TYPE = SUB_TYPE;
    }


    /**
     * Gets the SUB_DESC value for this SubArea.
     * 
     * @return SUB_DESC
     */
    public String getSUB_DESC() {
        return SUB_DESC;
    }


    /**
     * Sets the SUB_DESC value for this SubArea.
     * 
     * @param SUB_DESC
     */
    public void setSUB_DESC(String SUB_DESC) {
        this.SUB_DESC = SUB_DESC;
    }


    /**
     * Gets the SUB_NUM value for this SubArea.
     * 
     * @return SUB_NUM
     */
    public String getSUB_NUM() {
        return SUB_NUM;
    }


    /**
     * Sets the SUB_NUM value for this SubArea.
     * 
     * @param SUB_NUM
     */
    public void setSUB_NUM(String SUB_NUM) {
        this.SUB_NUM = SUB_NUM;
    }


    /**
     * Gets the SUB_TYPE value for this SubArea.
     * 
     * @return SUB_TYPE
     */
    public String getSUB_TYPE() {
        return SUB_TYPE;
    }


    /**
     * Sets the SUB_TYPE value for this SubArea.
     * 
     * @param SUB_TYPE
     */
    public void setSUB_TYPE(String SUB_TYPE) {
        this.SUB_TYPE = SUB_TYPE;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SubArea)) return false;
        SubArea other = (SubArea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SUB_DESC==null && other.getSUB_DESC()==null) || 
             (this.SUB_DESC!=null &&
              this.SUB_DESC.equals(other.getSUB_DESC()))) &&
            ((this.SUB_NUM==null && other.getSUB_NUM()==null) || 
             (this.SUB_NUM!=null &&
              this.SUB_NUM.equals(other.getSUB_NUM()))) &&
            ((this.SUB_TYPE==null && other.getSUB_TYPE()==null) || 
             (this.SUB_TYPE!=null &&
              this.SUB_TYPE.equals(other.getSUB_TYPE())));
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
        if (getSUB_DESC() != null) {
            _hashCode += getSUB_DESC().hashCode();
        }
        if (getSUB_NUM() != null) {
            _hashCode += getSUB_NUM().hashCode();
        }
        if (getSUB_TYPE() != null) {
            _hashCode += getSUB_TYPE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubArea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "subArea"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUB_DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUB_DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUB_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUB_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUB_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUB_TYPE"));
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
