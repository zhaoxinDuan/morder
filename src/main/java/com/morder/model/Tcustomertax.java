package com.morder.model;

public class Tcustomertax {
    private Integer idtcustax;

    private String tctcompname;

    private String tcbillingaddr;

    private String tcbillingnum;

    private String tcbankname;

    private String tcbankaccount;

    private Integer tcustomerIdcustomer;

    public Integer getIdtcustax() {
        return idtcustax;
    }

    public void setIdtcustax(Integer idtcustax) {
        this.idtcustax = idtcustax;
    }

    public String getTctcompname() {
        return tctcompname;
    }

    public void setTctcompname(String tctcompname) {
        this.tctcompname = tctcompname == null ? null : tctcompname.trim();
    }

    public String getTcbillingaddr() {
        return tcbillingaddr;
    }

    public void setTcbillingaddr(String tcbillingaddr) {
        this.tcbillingaddr = tcbillingaddr == null ? null : tcbillingaddr.trim();
    }

    public String getTcbillingnum() {
        return tcbillingnum;
    }

    public void setTcbillingnum(String tcbillingnum) {
        this.tcbillingnum = tcbillingnum == null ? null : tcbillingnum.trim();
    }

    public String getTcbankname() {
        return tcbankname;
    }

    public void setTcbankname(String tcbankname) {
        this.tcbankname = tcbankname == null ? null : tcbankname.trim();
    }

    public String getTcbankaccount() {
        return tcbankaccount;
    }

    public void setTcbankaccount(String tcbankaccount) {
        this.tcbankaccount = tcbankaccount == null ? null : tcbankaccount.trim();
    }

    public Integer getTcustomerIdcustomer() {
        return tcustomerIdcustomer;
    }

    public void setTcustomerIdcustomer(Integer tcustomerIdcustomer) {
        this.tcustomerIdcustomer = tcustomerIdcustomer;
    }
}