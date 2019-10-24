/**
 * SemsCondition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class SemsCondition  implements java.io.Serializable {
    private String AREA_NUM;

    private String COLLECT_END;

    private String COLLECT_STRAT;

    private String INTERVAL;

    public SemsCondition() {
    }

    public SemsCondition(
           String AREA_NUM,
           String COLLECT_END,
           String COLLECT_STRAT,
           String INTERVAL) {
           this.AREA_NUM = AREA_NUM;
           this.COLLECT_END = COLLECT_END;
           this.COLLECT_STRAT = COLLECT_STRAT;
           this.INTERVAL = INTERVAL;
    }


    /**
     * Gets the AREA_NUM value for this SemsCondition.
     * 
     * @return AREA_NUM
     */
    public String getAREA_NUM() {
        return AREA_NUM;
    }


    /**
     * Sets the AREA_NUM value for this SemsCondition.
     * 
     * @param AREA_NUM
     */
    public void setAREA_NUM(String AREA_NUM) {
        this.AREA_NUM = AREA_NUM;
    }


    /**
     * Gets the COLLECT_END value for this SemsCondition.
     * 
     * @return COLLECT_END
     */
    public String getCOLLECT_END() {
        return COLLECT_END;
    }


    /**
     * Sets the COLLECT_END value for this SemsCondition.
     * 
     * @param COLLECT_END
     */
    public void setCOLLECT_END(String COLLECT_END) {
        this.COLLECT_END = COLLECT_END;
    }


    /**
     * Gets the COLLECT_STRAT value for this SemsCondition.
     * 
     * @return COLLECT_STRAT
     */
    public String getCOLLECT_STRAT() {
        return COLLECT_STRAT;
    }


    /**
     * Sets the COLLECT_STRAT value for this SemsCondition.
     * 
     * @param COLLECT_STRAT
     */
    public void setCOLLECT_STRAT(String COLLECT_STRAT) {
        this.COLLECT_STRAT = COLLECT_STRAT;
    }


    /**
     * Gets the INTERVAL value for this SemsCondition.
     * 
     * @return INTERVAL
     */
    public String getINTERVAL() {
        return INTERVAL;
    }


    /**
     * Sets the INTERVAL value for this SemsCondition.
     * 
     * @param INTERVAL
     */
    public void setINTERVAL(String INTERVAL) {
        this.INTERVAL = INTERVAL;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SemsCondition)) return false;
        SemsCondition other = (SemsCondition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AREA_NUM==null && other.getAREA_NUM()==null) || 
             (this.AREA_NUM!=null &&
              this.AREA_NUM.equals(other.getAREA_NUM()))) &&
            ((this.COLLECT_END==null && other.getCOLLECT_END()==null) || 
             (this.COLLECT_END!=null &&
              this.COLLECT_END.equals(other.getCOLLECT_END()))) &&
            ((this.COLLECT_STRAT==null && other.getCOLLECT_STRAT()==null) || 
             (this.COLLECT_STRAT!=null &&
              this.COLLECT_STRAT.equals(other.getCOLLECT_STRAT()))) &&
            ((this.INTERVAL==null && other.getINTERVAL()==null) || 
             (this.INTERVAL!=null &&
              this.INTERVAL.equals(other.getINTERVAL())));
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
        if (getAREA_NUM() != null) {
            _hashCode += getAREA_NUM().hashCode();
        }
        if (getCOLLECT_END() != null) {
            _hashCode += getCOLLECT_END().hashCode();
        }
        if (getCOLLECT_STRAT() != null) {
            _hashCode += getCOLLECT_STRAT().hashCode();
        }
        if (getINTERVAL() != null) {
            _hashCode += getINTERVAL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SemsCondition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "semsCondition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AREA_NUM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AREA_NUM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COLLECT_END");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COLLECT_END"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COLLECT_STRAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COLLECT_STRAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INTERVAL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INTERVAL"));
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
