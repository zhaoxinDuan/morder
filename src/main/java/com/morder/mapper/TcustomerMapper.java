package com.morder.mapper;

import com.morder.model.Tcustomer;

public interface TcustomerMapper {
    int deleteByPrimaryKey(Integer idcustomer);

    int insert(Tcustomer record);

    int insertSelective(Tcustomer record);

    Tcustomer selectByPrimaryKey(Integer idcustomer);

    int updateByPrimaryKeySelective(Tcustomer record);

    int updateByPrimaryKey(Tcustomer record);
}