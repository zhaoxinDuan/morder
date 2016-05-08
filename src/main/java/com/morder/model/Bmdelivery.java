package com.morder.model;

import java.util.Date;

public class Bmdelivery {
    private Integer idbmdelivery;

    private String bmdreccompany;

    private String bmdeliverynum;

    private Date bmdeliverydate;

    private String bmdeliveryuser;

    private Integer bmdeliveryuserid;

    public Integer getIdbmdelivery() {
        return idbmdelivery;
    }

    public void setIdbmdelivery(Integer idbmdelivery) {
        this.idbmdelivery = idbmdelivery;
    }

    public String getBmdreccompany() {
        return bmdreccompany;
    }

    public void setBmdreccompany(String bmdreccompany) {
        this.bmdreccompany = bmdreccompany == null ? null : bmdreccompany.trim();
    }

    public String getBmdeliverynum() {
        return bmdeliverynum;
    }

    public void setBmdeliverynum(String bmdeliverynum) {
        this.bmdeliverynum = bmdeliverynum == null ? null : bmdeliverynum.trim();
    }

    public Date getBmdeliverydate() {
        return bmdeliverydate;
    }

    public void setBmdeliverydate(Date bmdeliverydate) {
        this.bmdeliverydate = bmdeliverydate;
    }

    public String getBmdeliveryuser() {
        return bmdeliveryuser;
    }

    public void setBmdeliveryuser(String bmdeliveryuser) {
        this.bmdeliveryuser = bmdeliveryuser == null ? null : bmdeliveryuser.trim();
    }

    public Integer getBmdeliveryuserid() {
        return bmdeliveryuserid;
    }

    public void setBmdeliveryuserid(Integer bmdeliveryuserid) {
        this.bmdeliveryuserid = bmdeliveryuserid;
    }
}