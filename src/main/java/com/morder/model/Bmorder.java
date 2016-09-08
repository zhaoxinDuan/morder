package com.morder.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Bmorder {
    private Integer idbmorder;

    private String bmcusname;

    private String bmordernum;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bmbillingdate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bmdeliverydate;

    private String bmpaymethod;

    private Integer bmcurrencytype;


    private BigDecimal bmaddcosts;
//    @NumberFormat(style= NumberFormat.Style.NUMBER, pattern="##.##")
    private BigDecimal bmorderamount;

    private String bmcomments;

    private String bmpacreq;
    private String bmdenum;

    private Integer bmstatus;

    private Integer bmcreateuserid;

    private Integer bmdeliveryIdbmdelivery;

    private Integer tuserIduser;

    private Integer tcustomerIdcustomer;

    public Integer getIdbmorder() {
        return idbmorder;
    }

    public void setIdbmorder(Integer idbmorder) {
        this.idbmorder = idbmorder;
    }

    public String getBmcusname() {
        return bmcusname;
    }

    public void setBmcusname(String bmcusname) {
        this.bmcusname = bmcusname == null ? null : bmcusname.trim();
    }

    public String getBmordernum() {
        return bmordernum;
    }

    public void setBmordernum(String bmordernum) {
        this.bmordernum = bmordernum == null ? null : bmordernum.trim();
    }

    public Date getBmbillingdate() {
        return bmbillingdate;
    }

    public void setBmbillingdate(Date bmbillingdate) {
        this.bmbillingdate = bmbillingdate;
    }

    public Date getBmdeliverydate() {
        return bmdeliverydate;
    }

    public void setBmdeliverydate(Date bmdeliverydate) {
        this.bmdeliverydate = bmdeliverydate;
    }

    public String getBmpaymethod() {
        return bmpaymethod;
    }

    public void setBmpaymethod(String bmpaymethod) {
        this.bmpaymethod = bmpaymethod == null ? null : bmpaymethod.trim();
    }

    public Integer getBmcurrencytype() {
        return bmcurrencytype;
    }

    public void setBmcurrencytype(Integer bmcurrencytype) {
        this.bmcurrencytype = bmcurrencytype;
    }

    public BigDecimal getBmaddcosts() {
        return bmaddcosts;
    }

    public void setBmaddcosts(BigDecimal bmaddcosts) {
        this.bmaddcosts = bmaddcosts;
    }

    public BigDecimal getBmorderamount() {
        return bmorderamount;
    }

    public void setBmorderamount(BigDecimal bmorderamount) {
        this.bmorderamount = bmorderamount;
    }

    public String getBmcomments() {
        return bmcomments;
    }

    public void setBmcomments(String bmcomments) {
        this.bmcomments = bmcomments == null ? null : bmcomments.trim();
    }

    public String getBmpacreq() {
        return bmpacreq;
    }

    public void setBmpacreq(String bmpacreq) {
        this.bmpacreq = bmpacreq == null ? null : bmpacreq.trim();
    }


    public String getBmdenum() {
        return bmdenum;
    }

    public void setBmdenum(String bmdenum) {
        this.bmdenum = bmdenum== null ? null : bmdenum.trim();
    }

    public Integer getBmstatus() {
        return bmstatus;
    }

    public void setBmstatus(Integer bmstatus) {
        this.bmstatus = bmstatus;
    }

    public Integer getBmcreateuserid() {
        return bmcreateuserid;
    }

    public void setBmcreateuserid(Integer bmcreateuserid) {
        this.bmcreateuserid = bmcreateuserid;
    }

    public Integer getBmdeliveryIdbmdelivery() {
        return bmdeliveryIdbmdelivery;
    }

    public void setBmdeliveryIdbmdelivery(Integer bmdeliveryIdbmdelivery) {
        this.bmdeliveryIdbmdelivery = bmdeliveryIdbmdelivery;
    }

    public Integer getTuserIduser() {
        return tuserIduser;
    }

    public void setTuserIduser(Integer tuserIduser) {
        this.tuserIduser = tuserIduser;
    }

    public Integer getTcustomerIdcustomer() {
        return tcustomerIdcustomer;
    }

    public void setTcustomerIdcustomer(Integer tcustomerIdcustomer) {
        this.tcustomerIdcustomer = tcustomerIdcustomer;
    }
}