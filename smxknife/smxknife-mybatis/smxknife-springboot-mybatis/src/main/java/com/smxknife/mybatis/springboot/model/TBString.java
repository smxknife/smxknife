package com.smxknife.mybatis.springboot.model;

public class TBString {
    private Long id;

    private String cChar;

    private String cVarchar;

    public TBString(Long id, String cChar, String cVarchar) {
        this.id = id;
        this.cChar = cChar;
        this.cVarchar = cVarchar;
    }

    public TBString() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcChar() {
        return cChar;
    }

    public void setcChar(String cChar) {
        this.cChar = cChar;
    }

    public String getcVarchar() {
        return cVarchar;
    }

    public void setcVarchar(String cVarchar) {
        this.cVarchar = cVarchar;
    }
}