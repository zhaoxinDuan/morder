package com.morder.model;

import java.math.BigDecimal;

public class Bmorderitem {
    private Integer idbmitem;

    private String bmiproname;

    private Integer bmiprotype;

    private BigDecimal bmiprice;

    private Integer bminum;

    private BigDecimal bmiamount;

    private Integer bmiistax;

    private String bmioutternum;

    private String bmorderitemcol;

    private Integer bmorderIdbmorder;

    public Integer getIdbmitem() {
        return idbmitem;
    }

    public void setIdbmitem(Integer idbmitem) {
        this.idbmitem = idbmitem;
    }

    public String getBmiproname() {
        return bmiproname;
    }

    public void setBmiproname(String bmiproname) {
        this.bmiproname = bmiproname == null ? null : bmiproname.trim();
    }

    public Integer getBmiprotype() {
        return bmiprotype;
    }

    public void setBmiprotype(Integer bmiprotype) {
        this.bmiprotype = bmiprotype;
    }

    public BigDecimal getBmiprice() {
        return bmiprice;
    }

    public void setBmiprice(BigDecimal bmiprice) {
        this.bmiprice = bmiprice;
    }

    public Integer getBminum() {
        return bminum;
    }

    public void setBminum(Integer bminum) {
        this.bminum = bminum;
    }

    public BigDecimal getBmiamount() {
        return bmiamount;
    }

    public void setBmiamount(BigDecimal bmiamount) {
        this.bmiamount = bmiamount;
    }

    public Integer getBmiistax() {
        return bmiistax;
    }

    public void setBmiistax(Integer bmiistax) {
        this.bmiistax = bmiistax;
    }

    public String getBmioutternum() {
        return bmioutternum;
    }

    public void setBmioutternum(String bmioutternum) {
        this.bmioutternum = bmioutternum == null ? null : bmioutternum.trim();
    }

    public String getBmorderitemcol() {
        return bmorderitemcol;
    }

    public void setBmorderitemcol(String bmorderitemcol) {
        this.bmorderitemcol = bmorderitemcol == null ? null : bmorderitemcol.trim();
    }

    public Integer getBmorderIdbmorder() {
        return bmorderIdbmorder;
    }

    public void setBmorderIdbmorder(Integer bmorderIdbmorder) {
        this.bmorderIdbmorder = bmorderIdbmorder;
    }
}