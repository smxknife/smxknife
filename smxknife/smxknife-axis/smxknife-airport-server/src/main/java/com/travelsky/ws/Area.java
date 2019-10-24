/**
 * Area.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class Area  implements java.io.Serializable {
    private String AREA_DESC;

    private String AREA_NUM;

    private String AREA_TYPE;

    private com.travelsky.ws.SubArea[] SUB_AREA;

    public Area() {
    }

    public Area(
           String AREA_DESC,
           String AREA_NUM,
           String AREA_TYPE,
           com.travelsky.ws.SubArea[] SUB_AREA) {
           this.AREA_DESC = AREA_DESC;
           this.AREA_NUM = AREA_NUM;
           this.AREA_TYPE = AREA_TYPE;
           this.SUB_AREA = SUB_AREA;
    }


    /**
     * Gets the AREA_DESC value for this Area.
     * 
     * @return AREA_DESC
     */
    public String getAREA_DESC() {
        return AREA_DESC;
    }


    /**
     * Sets the AREA_DESC value for this Area.
     * 
     * @param AREA_DESC
     */
    public void setAREA_DESC(String AREA_DESC) {
        this.AREA_DESC = AREA_DESC;
    }


    /**
     * Gets the AREA_NUM value for this Area.
     * 
     * @return AREA_NUM
     */
    public String getAREA_NUM() {
        return AREA_NUM;
    }


    /**
     * Sets the AREA_NUM value for this Area.
     * 
     * @param AREA_NUM
     */
    public void setAREA_NUM(String AREA_NUM) {
        this.AREA_NUM = AREA_NUM;
    }


    /**
     * Gets the AREA_TYPE value for this Area.
     * 
     * @return AREA_TYPE
     */
    public String getAREA_TYPE() {
        return AREA_TYPE;
    }


    /**
     * Sets the AREA_TYPE value for this Area.
     * 
     * @param AREA_TYPE
     */
    public void setAREA_TYPE(String AREA_TYPE) {
        this.AREA_TYPE = AREA_TYPE;
    }


    /**
     * Gets the SUB_AREA value for this Area.
     * 
     * @return SUB_AREA
     */
    public com.travelsky.ws.SubArea[] getSUB_AREA() {
        return SUB_AREA;
    }


    /**
     * Sets the SUB_AREA value for this Area.
     * 
     * @param SUB_AREA
     */
    public void setSUB_AREA(com.travelsky.ws.SubArea[] SUB_AREA) {
        this.SUB_AREA = SUB_AREA;
    }

    public com.travelsky.ws.SubArea getSUB_AREA(int i) {
        return this.SUB_AREA[i];
    }

    public void setSUB_AREA(int i, com.travelsky.ws.SubArea _value) {
        this.SUB_AREA[i] = _value;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Area)) return false;
        Area other = (Area) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AREA_DESC==null && other.getAREA_DESC()==null) || 
             (this.AREA_DESC!=null &&
              this.AREA_DESC.equals(other.getAREA_DESC()))) &&
            ((this.AREA_NUM==null && other.getAREA_NUM()==null) || 
             (this.AREA_NUM!=null &&
              this.AREA_NUM.equals(other.getAREA_NUM()))) &&
            ((this.AREA_TYPE==null && other.getAREA_TYPE()==null) || 
             (this.AREA_TYPE!=null &&
              this.AREA_TYPE.equals(other.getAREA_TYPE()))) &&
            ((this.SUB_AREA==null && other.getSUB_AREA()==null) || 
             (this.SUB_AREA!=null &&
              java.util.Arrays.equals(this.SUB_AREA, other.getSUB_AREA())));
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
        if (getAREA_DESC() != null) {
            _hashCode += getAREA_DESC().hashCode();
        }
        if (getAREA_NUM() != null) {
            _hashCode += getAREA_NUM().hashCode();
        }
        if (getAREA_TYPE() != null) {
            _hashCode += getAREA_TYPE().hashCode();
        }
        if (getSUB_AREA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSUB_AREA());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSUB_AREA(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Area.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "area"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AREA_DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AREA_DESC"));
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
        elemField.setFieldName("AREA_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AREA_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUB_AREA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SUB_AREA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "subArea"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
