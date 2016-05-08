package com.morder.model;

public class Tcustomer {
    private Integer idcustomer;

    private String cname;

    private String caddress;

    private Integer csettype;

    private String cofficername;

    private Integer cofficeruid;

    private String comments;

    public Integer getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Integer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress == null ? null : caddress.trim();
    }

    public Integer getCsettype() {
        return csettype;
    }

    public void setCsettype(Integer csettype) {
        this.csettype = csettype;
    }

    public String getCofficername() {
        return cofficername;
    }

    public void setCofficername(String cofficername) {
        this.cofficername = cofficername == null ? null : cofficername.trim();
    }

    public Integer getCofficeruid() {
        return cofficeruid;
    }

    public void setCofficeruid(Integer cofficeruid) {
        this.cofficeruid = cofficeruid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}