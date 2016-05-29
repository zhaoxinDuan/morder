package com.morder.component;

/**
 * Created by amis on 16-5-29.
 */
public class ExcelModel {
    private Integer rownum;
    private Integer cellnum;
    private String value;

    public ExcelModel(Integer rownum, Integer cellnum, String value) {
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.value = value;
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
}
