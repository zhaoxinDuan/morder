package com.morder.mapper;

import com.morder.model.Bmorder;

public interface BmorderMapper {
    int deleteByPrimaryKey(Integer idbmorder);

    int insert(Bmorder record);

    int insertSelective(Bmorder record);

    Bmorder selectByPrimaryKey(Integer idbmorder);

    int updateByPrimaryKeySelective(Bmorder record);

    int updateByPrimaryKey(Bmorder record);
}