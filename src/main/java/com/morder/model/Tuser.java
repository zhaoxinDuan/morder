package com.morder.model;

import java.util.Date;

public class Tuser {
    private Integer iduser;

    private String uname;

    private String uemail;

    private String upwd;

    private Date createTime;

    private String umphone;

    private String urealname;

    private Integer uisdel;

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUmphone() {
        return umphone;
    }

    public void setUmphone(String umphone) {
        this.umphone = umphone == null ? null : umphone.trim();
    }

    public String getUrealname() {
        return urealname;
    }

    public void setUrealname(String urealname) {
        this.urealname = urealname == null ? null : urealname.trim();
    }

    public Integer getUisdel() {
        return uisdel;
    }

    public void setUisdel(Integer uisdel) {
        this.uisdel = uisdel;
    }
}