package com.morder.model;

import java.util.Date;

public class Tlogs {
    private Integer tlogsid;

    private Date tltime;

    private Integer tltype;

    private Integer iduser;

    private Integer idbmorder;

    public Integer getTlogsid() {
        return tlogsid;
    }

    public void setTlogsid(Integer tlogsid) {
        this.tlogsid = tlogsid;
    }

    public Date getTltime() {
        return tltime;
    }

    public void setTltime(Date tltime) {
        this.tltime = tltime;
    }

    public Integer getTltype() {
        return tltype;
    }

    public void setTltype(Integer tltype) {
        this.tltype = tltype;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIdbmorder() {
        return idbmorder;
    }

    public void setIdbmorder(Integer idbmorder) {
        this.idbmorder = idbmorder;
    }
}