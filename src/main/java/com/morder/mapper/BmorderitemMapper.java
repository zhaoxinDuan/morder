package com.morder.mapper;

import com.morder.model.Bmorderitem;

public interface BmorderitemMapper {
    int deleteByPrimaryKey(Integer idbmitem);

    int insert(Bmorderitem record);

    int insertSelective(Bmorderitem record);

    Bmorderitem selectByPrimaryKey(Integer idbmitem);

    int updateByPrimaryKeySelective(Bmorderitem record);

    int updateByPrimaryKey(Bmorderitem record);
}