package com.morder.model;

public class Tunits {
    private Integer idunit;

    private String unitname;

    private String pidunit;

    public Integer getIdunit() {
        return idunit;
    }

    public void setIdunit(Integer idunit) {
        this.idunit = idunit;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }

    public String getPidunit() {
        return pidunit;
    }

    public void setPidunit(String pidunit) {
        this.pidunit = pidunit == null ? null : pidunit.trim();
    }
}