package com.morder.model;

public class Tcustomerappend {
    private Integer idcusapp;

    private String cacontacts;

    private Integer capositions;

    private String catelphone;

    private String camphone;

    private String cafax;

    private String carecaddress;

    private String cacomments;


    public Integer getIdcusapp() {
        return idcusapp;
    }

    public void setIdcusapp(Integer idcusapp) {
        this.idcusapp = idcusapp;
    }

    public String getCacontacts() {
        return cacontacts;
    }

    public void setCacontacts(String cacontacts) {
        this.cacontacts = cacontacts == null ? null : cacontacts.trim();
    }

    public Integer getCapositions() {
        return capositions;
    }

    public void setCapositions(Integer capositions) {
        this.capositions = capositions;
    }

    public String getCatelphone() {
        return catelphone;
    }

    public void setCatelphone(String catelphone) {
        this.catelphone = catelphone == null ? null : catelphone.trim();
    }

    public String getCamphone() {
        return camphone;
    }

    public void setCamphone(String camphone) {
        this.camphone = camphone == null ? null : camphone.trim();
    }

    public String getCafax() {
        return cafax;
    }

    public void setCafax(String cafax) {
        this.cafax = cafax == null ? null : cafax.trim();
    }

    public String getCarecaddress() {
        return carecaddress;
    }

    public void setCarecaddress(String carecaddress) {
        this.carecaddress = carecaddress == null ? null : carecaddress.trim();
    }

    public String getCacomments() {
        return cacomments;
    }

    public void setCacomments(String cacomments) {
        this.cacomments = cacomments == null ? null : cacomments.trim();
    }

}