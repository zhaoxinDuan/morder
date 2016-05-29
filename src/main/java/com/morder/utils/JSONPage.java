package com.morder.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by amis on 16-5-28.
 */
public class JSONPage {
    private Long total;
    private List rows;
    private Map othermap;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Map getOthermap() {
        return othermap;
    }

    public void setOthermap(Map othermap) {
        this.othermap = othermap;
    }
}
