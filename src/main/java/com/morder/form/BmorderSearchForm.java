package com.morder.form;

import org.springframework.util.StringUtils;


/**
 * Created by amis on 16-5-29.
 */
public class BmorderSearchForm {
    private Integer searchBmstatus;
    private Integer searchTcustomerIdcustomer;
    private String searchBmbillingdateFrom;
    private String searchBmbillingdateTo;
    private String searchBmdeliverydateFrom;
    private String searchBmdeliverydateTo;
    private Integer searchTuserIduser;

    public Integer getSearchBmstatus() {
        return searchBmstatus;
    }

    public void setSearchBmstatus(Integer searchBmstatus) {
        this.searchBmstatus = searchBmstatus;
    }

    public Integer getSearchTcustomerIdcustomer() {
        return searchTcustomerIdcustomer;
    }

    public void setSearchTcustomerIdcustomer(Integer searchTcustomerIdcustomer) {
        this.searchTcustomerIdcustomer = searchTcustomerIdcustomer;
    }

    public String getSearchBmbillingdateFrom() {
        return searchBmbillingdateFrom;
    }

    public void setSearchBmbillingdateFrom(String searchBmbillingdateFrom) {
        this.searchBmbillingdateFrom = searchBmbillingdateFrom;
    }

    public String getSearchBmbillingdateTo() {
        return searchBmbillingdateTo;
    }

    public void setSearchBmbillingdateTo(String searchBmbillingdateTo) {
        this.searchBmbillingdateTo = searchBmbillingdateTo;
    }

    public String getSearchBmdeliverydateFrom() {
        return searchBmdeliverydateFrom;
    }

    public void setSearchBmdeliverydateFrom(String searchBmdeliverydateFrom) {
        this.searchBmdeliverydateFrom = searchBmdeliverydateFrom;
    }

    public String getSearchBmdeliverydateTo() {
        return searchBmdeliverydateTo;
    }

    public void setSearchBmdeliverydateTo(String searchBmdeliverydateTo) {
        this.searchBmdeliverydateTo = searchBmdeliverydateTo;
    }

    public Integer getSearchTuserIduser() {
        return searchTuserIduser;
    }

    public void setSearchTuserIduser(Integer searchTuserIduser) {
        this.searchTuserIduser = searchTuserIduser;
    }

    public String getBuilderSql(){
        StringBuilder sqlBuilder = new StringBuilder("");
        if(this.searchBmstatus!=null&&this.searchBmstatus!=-1){
            sqlBuilder.append(" and bmstatus="+this.searchBmstatus);
        }
        if(this.searchTcustomerIdcustomer!=null){
            sqlBuilder.append(" and tcustomer_idcustomer="+this.searchTcustomerIdcustomer);
        }
        if(this.searchTuserIduser!=null){
            sqlBuilder.append(" and tuser_iduser="+this.searchTuserIduser);
        }
        if(!StringUtils.isEmpty(this.searchBmbillingdateFrom)){
            sqlBuilder.append(" and bmbillingdate>='"+this.searchBmbillingdateFrom+"'");
        }
        if(!StringUtils.isEmpty(this.searchBmbillingdateTo)){
            sqlBuilder.append(" and bmbillingdate<'"+this.searchBmbillingdateTo+"'");
        }
        if(!StringUtils.isEmpty(this.searchBmdeliverydateFrom)){
            sqlBuilder.append(" and bmdeliverydate>='"+this.searchBmdeliverydateFrom+"'");
        }
        if(!StringUtils.isEmpty(this.searchBmdeliverydateTo)){
            sqlBuilder.append(" and bmdeliverydate<'"+this.searchBmdeliverydateTo+"'");
        }


        return sqlBuilder.toString();
    }
}
