/**
 * Sems.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.travelsky.ws;

public class Sems  implements java.io.Serializable {
    private Area[] AREA;

    private Iads[] IADS;

    private com.travelsky.ws.Iinout[] IINOUT;

    private com.travelsky.ws.Iservice[] ISERVICE;

    private com.travelsky.ws.Istay ISTAY;

    private Iwat IWAT;

    public Sems() {
    }

    public Sems(
           Area[] AREA,
           Iads[] IADS,
           com.travelsky.ws.Iinout[] IINOUT,
           com.travelsky.ws.Iservice[] ISERVICE,
           com.travelsky.ws.Istay ISTAY,
           Iwat IWAT) {
           this.AREA = AREA;
           this.IADS = IADS;
           this.IINOUT = IINOUT;
           this.ISERVICE = ISERVICE;
           this.ISTAY = ISTAY;
           this.IWAT = IWAT;
    }


    /**
     * Gets the AREA value for this Sems.
     * 
     * @return AREA
     */
    public Area[] getAREA() {
        return AREA;
    }


    /**
     * Sets the AREA value for this Sems.
     * 
     * @param AREA
     */
    public void setAREA(Area[] AREA) {
        this.AREA = AREA;
    }

    public Area getAREA(int i) {
        return this.AREA[i];
    }

    public void setAREA(int i, Area _value) {
        this.AREA[i] = _value;
    }


    /**
     * Gets the IADS value for this Sems.
     * 
     * @return IADS
     */
    public Iads[] getIADS() {
        return IADS;
    }


    /**
     * Sets the IADS value for this Sems.
     * 
     * @param IADS
     */
    public void setIADS(Iads[] IADS) {
        this.IADS = IADS;
    }

    public Iads getIADS(int i) {
        return this.IADS[i];
    }

    public void setIADS(int i, Iads _value) {
        this.IADS[i] = _value;
    }


    /**
     * Gets the IINOUT value for this Sems.
     * 
     * @return IINOUT
     */
    public com.travelsky.ws.Iinout[] getIINOUT() {
        return IINOUT;
    }


    /**
     * Sets the IINOUT value for this Sems.
     * 
     * @param IINOUT
     */
    public void setIINOUT(com.travelsky.ws.Iinout[] IINOUT) {
        this.IINOUT = IINOUT;
    }

    public com.travelsky.ws.Iinout getIINOUT(int i) {
        return this.IINOUT[i];
    }

    public void setIINOUT(int i, com.travelsky.ws.Iinout _value) {
        this.IINOUT[i] = _value;
    }


    /**
     * Gets the ISERVICE value for this Sems.
     * 
     * @return ISERVICE
     */
    public com.travelsky.ws.Iservice[] getISERVICE() {
        return ISERVICE;
    }


    /**
     * Sets the ISERVICE value for this Sems.
     * 
     * @param ISERVICE
     */
    public void setISERVICE(com.travelsky.ws.Iservice[] ISERVICE) {
        this.ISERVICE = ISERVICE;
    }

    public com.travelsky.ws.Iservice getISERVICE(int i) {
        return this.ISERVICE[i];
    }

    public void setISERVICE(int i, com.travelsky.ws.Iservice _value) {
        this.ISERVICE[i] = _value;
    }


    /**
     * Gets the ISTAY value for this Sems.
     * 
     * @return ISTAY
     */
    public com.travelsky.ws.Istay getISTAY() {
        return ISTAY;
    }


    /**
     * Sets the ISTAY value for this Sems.
     * 
     * @param ISTAY
     */
    public void setISTAY(com.travelsky.ws.Istay ISTAY) {
        this.ISTAY = ISTAY;
    }


    /**
     * Gets the IWAT value for this Sems.
     * 
     * @return IWAT
     */
    public Iwat getIWAT() {
        return IWAT;
    }


    /**
     * Sets the IWAT value for this Sems.
     * 
     * @param IWAT
     */
    public void setIWAT(Iwat IWAT) {
        this.IWAT = IWAT;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Sems)) return false;
        Sems other = (Sems) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AREA==null && other.getAREA()==null) || 
             (this.AREA!=null &&
              java.util.Arrays.equals(this.AREA, other.getAREA()))) &&
            ((this.IADS==null && other.getIADS()==null) || 
             (this.IADS!=null &&
              java.util.Arrays.equals(this.IADS, other.getIADS()))) &&
            ((this.IINOUT==null && other.getIINOUT()==null) || 
             (this.IINOUT!=null &&
              java.util.Arrays.equals(this.IINOUT, other.getIINOUT()))) &&
            ((this.ISERVICE==null && other.getISERVICE()==null) || 
             (this.ISERVICE!=null &&
              java.util.Arrays.equals(this.ISERVICE, other.getISERVICE()))) &&
            ((this.ISTAY==null && other.getISTAY()==null) || 
             (this.ISTAY!=null &&
              this.ISTAY.equals(other.getISTAY()))) &&
            ((this.IWAT==null && other.getIWAT()==null) || 
             (this.IWAT!=null &&
              this.IWAT.equals(other.getIWAT())));
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
        if (getAREA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAREA());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getAREA(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIADS() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIADS());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIADS(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIINOUT() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIINOUT());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIINOUT(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getISERVICE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getISERVICE());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getISERVICE(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getISTAY() != null) {
            _hashCode += getISTAY().hashCode();
        }
        if (getIWAT() != null) {
            _hashCode += getIWAT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Sems.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "sems"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AREA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AREA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "area"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IADS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IADS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iads"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IINOUT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IINOUT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iinout"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ISERVICE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ISERVICE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iservice"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ISTAY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ISTAY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "istay"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IWAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IWAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.travelsky.com/", "iwat"));
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
