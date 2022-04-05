package com.smxknife.msb.sysio.sean.io.test.util;

import com.smxknife.msb.sysio.sean.io.test.rpc.protocol.MyContent;
import com.smxknife.msb.sysio.sean.io.test.rpc.protocol.Myheader;

/**
 * @author: 马士兵教育
 * @create: 2020-07-19 20:47
 */
public class Packmsg {

    Myheader header;
    MyContent content;

    public Myheader getHeader() {
        return header;
    }

    public void setHeader(Myheader header) {
        this.header = header;
    }

    public MyContent getContent() {
        return content;
    }

    public void setContent(MyContent content) {
        this.content = content;
    }

    public Packmsg(Myheader header, MyContent content) {
        this.header = header;
        this.content = content;
    }
}
