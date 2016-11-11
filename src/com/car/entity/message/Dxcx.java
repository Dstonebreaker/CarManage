package com.car.entity.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Mrkin on 2016/10/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "dxcx", propOrder = { "LSH", "YHM", "DXLX" })
public class Dxcx {
    private String LSH;
    private String YHM;
    private String DXLX;

    public String getLSH() {
        return LSH;
    }

    public void setLSH(String LSH) {
        this.LSH = LSH;
    }

    public String getYHM() {
        return YHM;
    }

    public void setYHM(String YHM) {
        this.YHM = YHM;
    }

    public String getDXLX() {
        return DXLX;
    }

    public void setDXLX(String DXLX) {
        this.DXLX = DXLX;
    }
}
