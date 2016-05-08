package com.morder.mapper;

import com.morder.model.Bmdelivery;

public interface BmdeliveryMapper {
    int deleteByPrimaryKey(Integer idbmdelivery);

    int insert(Bmdelivery record);

    int insertSelective(Bmdelivery record);

    Bmdelivery selectByPrimaryKey(Integer idbmdelivery);

    int updateByPrimaryKeySelective(Bmdelivery record);

    int updateByPrimaryKey(Bmdelivery record);
}