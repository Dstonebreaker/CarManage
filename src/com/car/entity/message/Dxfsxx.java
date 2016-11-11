package com.car.entity.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by Mrkin on 2016/10/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType(name = "dxfsxx", propOrder = { "LSH", "TJSJ","DHHM","DXNR", "DXLX","FSSJ" })
public class Dxfsxx {
   /* <dxfsxx>
    <LSH>da42347527ae4db0afdaf7f8febbbb9b</LSH>  --流水号
            <TJSJ>2016-10-09 14:30:12</TJSJ>  ------提交时间
            <DHHM>13625645687</DHHM>  ---电话号码
    <DXNR>需发送短信内容</DXNR>  ---短信内容
            <FSSJ>2016-10-09 14:30:12</FSSJ>  -----发送时间
            <DXLX>11</DXLX>  --短信类型代码
            </dxfsxx>*/
    private String LSH;
    private String TJSJ;
    private String DHHM;
    private String DXNR;
    private String FSSJ;
    private String DXLX;

    public String getTJSJ() {
        return TJSJ;
    }

    public void setTJSJ(String TJSJ) {
        this.TJSJ = TJSJ;
    }

    public String getDHHM() {
        return DHHM;
    }

    public void setDHHM(String DHHM) {
        this.DHHM = DHHM;
    }

    public String getDXNR() {
        return DXNR;
    }

    public void setDXNR(String DXNR) {
        this.DXNR = DXNR;
    }

    public String getFSSJ() {
        return FSSJ;
    }

    public void setFSSJ(String FSSJ) {
        this.FSSJ = FSSJ;
    }

    public String getDXLX() {
        return DXLX;
    }

    public void setDXLX(String DXLX) {
        this.DXLX = DXLX;
    }

    public String getLSH() {
        return LSH;
    }

    public void setLSH(String LSH) {
        this.LSH = LSH;
    }
}
