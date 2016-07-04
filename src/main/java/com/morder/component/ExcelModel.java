package com.morder.component;

/**
 * Created by amis on 16-5-29.
 */
public class ExcelModel {
    private Integer rownum;
    private Integer cellnum;
    private String value;
    private boolean iscellstyle = false;
    private boolean anicelllength = false;
    private Integer maxsize;
    private Integer subsize;
    private short frontsize;

    public ExcelModel(Integer rownum, Integer cellnum, String value) {
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.value = value;
    }

    public ExcelModel(Integer rownum, Integer cellnum, String value, boolean iscellstyle) {
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.value = value;
        this.iscellstyle = iscellstyle;
    }
    public ExcelModel(Integer rownum, Integer cellnum, String value, boolean iscellstyle,short frontsize) {
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.value = value;
        this.iscellstyle = iscellstyle;
        this.frontsize = frontsize;
    }

    public ExcelModel(Integer rownum, Integer cellnum, String value, boolean iscellstyle, boolean anicelllength, Integer maxsize,Integer subsize) {
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.value = value;
        this.iscellstyle = iscellstyle;
        this.anicelllength = anicelllength;
        this.maxsize = maxsize;
        this.subsize = subsize;
    }

    public Integer getSubsize() {
        return subsize;
    }

    public void setSubsize(Integer subsize) {
        this.subsize = subsize;
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    public Integer getCellnum() {
        return cellnum;
    }

    public void setCellnum(Integer cellnum) {
        this.cellnum = cellnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean iscellstyle() {
        return iscellstyle;
    }

    public void setIscellstyle(boolean iscellstyle) {
        this.iscellstyle = iscellstyle;
    }

    public boolean isAnicelllength() {
        return anicelllength;
    }

    public void setAnicelllength(boolean anicelllength) {
        this.anicelllength = anicelllength;
    }

    public Integer getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(Integer maxsize) {
        this.maxsize = maxsize;
    }
}
