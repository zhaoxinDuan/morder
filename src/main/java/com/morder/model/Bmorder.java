package com.morder.model;

import java.util.Date;

public class Bmorder {
    private Integer idbmorder;

    private String bmcusname;

    private String bmordernum;

    private Date bmbillingdate;

    private Date bmdeliverydate;

    private String bmpaymethod;

    private Integer bmcurrencytyoe;

    private Long bmaddcosts;

    private Long bmorderamount;

    private String bmcomments;

    private Integer bmdeliveryIdbmdelivery;

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

    public Integer getBmcurrencytyoe() {
        return bmcurrencytyoe;
    }

    public void setBmcurrencytyoe(Integer bmcurrencytyoe) {
        this.bmcurrencytyoe = bmcurrencytyoe;
    }

    public Long getBmaddcosts() {
        return bmaddcosts;
    }

    public void setBmaddcosts(Long bmaddcosts) {
        this.bmaddcosts = bmaddcosts;
    }

    public Long getBmorderamount() {
        return bmorderamount;
    }

    public void setBmorderamount(Long bmorderamount) {
        this.bmorderamount = bmorderamount;
    }

    public String getBmcomments() {
        return bmcomments;
    }

    public void setBmcomments(String bmcomments) {
        this.bmcomments = bmcomments == null ? null : bmcomments.trim();
    }

    public Integer getBmdeliveryIdbmdelivery() {
        return bmdeliveryIdbmdelivery;
    }

    public void setBmdeliveryIdbmdelivery(Integer bmdeliveryIdbmdelivery) {
        this.bmdeliveryIdbmdelivery = bmdeliveryIdbmdelivery;
    }
}