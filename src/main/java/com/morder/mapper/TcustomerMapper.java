package com.morder.mapper;

import com.morder.model.Tcustomer;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TcustomerMapper {
    int deleteByPrimaryKey(Integer idcustomer);

    int insert(Tcustomer record);

    int insertSelective(Tcustomer record);

    Tcustomer selectByPrimaryKey(Integer idcustomer);

    int updateByPrimaryKeySelective(Tcustomer record);

    int updateByPrimaryKey(Tcustomer record);

    List findAllCustomersByPage(RowBounds rowBounds);
}