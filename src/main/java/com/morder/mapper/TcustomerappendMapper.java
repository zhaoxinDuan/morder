package com.morder.mapper;

import com.morder.model.Tcustomerappend;

public interface TcustomerappendMapper {
    int deleteByPrimaryKey(Integer idcusapp);

    int insert(Tcustomerappend record);

    int insertSelective(Tcustomerappend record);

    Tcustomerappend selectByPrimaryKey(Integer idcusapp);

    int updateByPrimaryKeySelective(Tcustomerappend record);

    int updateByPrimaryKey(Tcustomerappend record);
}