package com.morder.model;

import java.math.BigDecimal;

public class Bmaddcosts {
    private Integer idbmaddcosts;

    private BigDecimal bmcosts;

    private String bmcostsdesc;

    private Integer bmorderIdbmorder;

    public Integer getIdbmaddcosts() {
        return idbmaddcosts;
    }

    public void setIdbmaddcosts(Integer idbmaddcosts) {
        this.idbmaddcosts = idbmaddcosts;
    }

    public BigDecimal getBmcosts() {
        return bmcosts;
    }

    public void setBmcosts(BigDecimal bmcosts) {
        this.bmcosts = bmcosts;
    }

    public String getBmcostsdesc() {
        return bmcostsdesc;
    }

    public void setBmcostsdesc(String bmcostsdesc) {
        this.bmcostsdesc = bmcostsdesc == null ? null : bmcostsdesc.trim();
    }

    public Integer getBmorderIdbmorder() {
        return bmorderIdbmorder;
    }

    public void setBmorderIdbmorder(Integer bmorderIdbmorder) {
        this.bmorderIdbmorder = bmorderIdbmorder;
    }
}